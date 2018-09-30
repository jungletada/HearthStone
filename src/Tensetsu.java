import java.util.*;
import java.util.Scanner;

class character{
    int HP;
    int attack;
    public character(int HP,int attack){
        this.HP=HP;
        this.attack=attack;
    }
}

class crystal{
    int num;
    public crystal(){
        num=1;
    }
    public void addCrystal(){
        num++;
    }
    public void useSkill(){
        num-=2;
    }
}

public class Tensetsu {
    public static void main(String[]args)
    {
        Scanner in=new Scanner(System.in);
        int T=in.nextInt();
        List<character> player_1=new ArrayList<character>();
        List<character> player_2=new ArrayList<character>();
        /*-----------------------------------------------*/
        character hero_1=new character(30,0);
        character hero_2=new character(30,0);
        /*-----------------------------------------------*/
        crystal Crystal_1=new crystal();
        crystal Crystal_2=new crystal();
        /*-----------------------------------------------*/
        player_1.add(hero_1);  player_2.add(hero_2);
        /*-----------------------------------------------*/
        int turns=1;
        String option;
        for(int i=0;i<T;i++)
        {
            int position,attack,health,attacker,defender;
            option=in.next();
            if(option.equals("summon"))
            {
                position=in.nextInt();
                attack=in.nextInt();
                health=in.nextInt();
                character summon=new character(health,attack);

                    if(turns%2!=0)
                        player_1.add(position,summon);
                    else
                        player_2.add(position,summon);
            }
            else if(option.equals("attack"))
            {
                attacker=in.nextInt();
                defender=in.nextInt();
                if(turns%2!=0){
                    character affect_1=player_1.get(attacker);
                    character affect_2=player_2.get(defender);
                    affect_1.HP=affect_1.HP-affect_2.attack;
                    affect_2.HP=affect_2.HP-affect_1.attack;
                    if(affect_1.HP<=0){
                        player_1.remove(attacker);
                    }
                    else
                        player_1.set(attacker,affect_1);

                    if(affect_2.HP<=0){
                        if(defender==0){
                            player_2.set(defender,affect_2);
                        }

                        else
                            player_2.remove(defender);
                    }
                    else
                        player_2.set(defender,affect_2);
                }
                //------------------------------------------------
                else if(turns%2==0){
                    character affect_1=player_1.get(defender);
                    character affect_2=player_2.get(attacker);
                    affect_1.HP=affect_1.HP-affect_2.attack;
                    affect_2.HP=affect_2.HP-affect_1.attack;
                    if(affect_1.HP<=0){
                        if(defender==0){
                            player_1.set(defender,affect_1);
                        }
                        else
                        player_1.remove(defender);
                    }
                    else
                        player_1.set(defender,affect_1);

                    if(affect_2.HP<=0){
                        player_2.remove(attacker);
                    }
                    else
                        player_2.set(attacker,affect_2);
                }
            }
            //----------------------------------------------------
            else if(option.equals("Arcane"))
            {
                int maxHP=1;
                if(turns%2!=0){
                    for(int j=0;j<player_2.size();j++)
                    {
                        character Incase=player_2.get(j);
                        if(Incase.HP>maxHP)
                        {
                            maxHP=Incase.HP;
                        }
                    }
                    for(int k=0;k<player_2.size();k++)
                    {
                        character maxchara=player_2.get(k);
                        if(maxchara.HP==maxHP)
                        {
                            maxchara.HP=maxchara.HP/2;
                            player_2.set(k,maxchara);
                            character Inhero=player_1.get(0);
                            Inhero.HP=Inhero.HP*3/4;
                            player_1.set(0, Inhero);
                            break;
                        }
                    }
                    Crystal_1.useSkill();
                }

                else{
                    for(int j=0;j<player_1.size();j++)
                    {
                        character Incase=player_1.get(j);
                        if(Incase.HP>maxHP)
                        {
                            maxHP=Incase.HP;
                        }
                    }
                    for(int k=0;k<player_1.size();k++)
                    {
                        character maxchara=player_1.get(k);
                        if(maxchara.HP==maxHP)
                        {
                            maxchara.HP=maxchara.HP/2;
                            player_1.set(k,maxchara);
                            character Inhero=player_2.get(0);
                            Inhero.HP=Inhero.HP*3/4;
                            player_2.set(0, Inhero);
                            break;
                        }
                    }
                    Crystal_2.useSkill();
                }
            }
            else if(option.equals("end"))
            {
                if(turns%2!=0)
                    Crystal_1.addCrystal();
                else
                    Crystal_2.addCrystal();
                turns++;
            }

        }

        character affect_1=player_1.get(0);

        character affect_2=player_2.get(0);

        if(affect_1.HP<=0)
            System.out.println("player_2 WINS");
        else if(affect_2.HP<=0)
            System.out.println("player_1 WINS");
        else
            System.out.println("No winner");

        System.out.println(affect_1.HP);
        System.out.print(player_1.size()-1+" ");
        for(int i=1;i<player_1.size();i++){
            character Summon=player_1.get(i);
            System.out.print(Summon.HP+" ");
        }
        System.out.println();
        //----------------------------------------
        System.out.println(affect_2.HP);
        System.out.print(player_2.size()-1+" ");
        for(int i=1;i<player_2.size();i++){
            character Summon=player_2.get(i);
            System.out.print(Summon.HP+" ");
        }
    }
}
