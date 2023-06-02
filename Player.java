package mycode;

import java.util.ArrayList;
/**
 * The class is for player
 */
public class Player 
{  
    private String name; //Name of the player
    private String title; //Current title of  the player
    private double objectcoin; //currency of the player
    private double exp; //Exp level of the player
    /**
    * Constructor for the seed class
    */
    Player()
    {
        this.name = "Tohru Adachi";
        this.title = "New Farmer";
        this.objectcoin = 100;
        this.exp = 0;
    }

    /**
     * Get the player's name
     * @return name of the player
     */
    public String getName() 
    {
        return name;
    }
    
    /**
     * Getter for the player's title
     * @return title of the player
     */
    public String getTitle() 
    {
        return title;
    }
    /**
     * Getter for the player's coins
     * @return coins of the player
     */
    public double getCoin()
    {
        return objectcoin;
    }
    /**
     * Getter for the player's exp
     * @return exp of the player
     */
    public double getExp() 
    {
        return exp;
    }

    /**
     * Just display the stats within console
     */
    public void DisplayStat()
    {
        System.out.println("Name: " + name);
        System.out.println("Objectcoin/s: " + objectcoin);
        System.out.println("EXP: " + exp);
        System.out.println("Level: " + (int)exp/100);
        System.out.println("Title: " + title);
    }
    /**
     * Check if the player can buy it and return a true or false statement
     * @param price amount being reduce or added
     * @return true if the player has enough money
     */
    public boolean changeCoin(Double price)
    {
       if ((objectcoin + price) >= 0)
       {
            objectcoin += price;
            return true;
       }
       else
       {
            System.out.println("Cant afford");
            return false;
       }
    }

    /**
     * Increase the exp of the player
     * @param exp the amount being increase
     */
    public void increaseexp(Double  exp)
    {
        this.exp += exp;
    }

    /**
     * Changes in the player's title and change the amount
     * @param plantlist the list being edited due to the stats changes of plant
     * @return if the player status has change
     */
    public boolean registerPlayer(ArrayList<Plant> plantlist)
    {
        if (exp >= 500 && objectcoin >= 200 && title.equals("New Farmer"))
        {
            title = "Registered Farmer";
            objectcoin = objectcoin - 200;
            for (int i = 0; i < plantlist.size(); i++)
            {
                plantlist.get(i).addBonusEarning(1);
                plantlist.get(i).addReduceCost(1);
            }
            return true;
        }
        else if (exp >= 1000 && objectcoin >= 300 && title.equals("Registered Farmer"))
        {
            title = "Distinguished Farmer";
            objectcoin = objectcoin - 300;
            for (int i = 0; i < plantlist.size(); i++)
            {
                plantlist.get(i).addBonusEarning(1);
                plantlist.get(i).addReduceCost(1);
                plantlist.get(i).addBonusWater(1);
            }
            return true;
        }
        else if (exp >= 1500 && objectcoin >= 400 && title.equals("Distinguished Farmer"))
        {
            title = "Legendary Farmer";
            objectcoin = objectcoin - 400;
            for (int i = 0; i < plantlist.size(); i++)
            {
                plantlist.get(i).addBonusEarning(2);
                plantlist.get(i).addReduceCost(1);
                plantlist.get(i).addBonusWater(1);
                plantlist.get(i).addBonusFertil(1);
            }
            return true;
        }
        else if (title.equals("Legendary Farmer"))
        {
            return false;
        }
        else
        {
            System.out.println("Cant afford or not enough exp");
            return false;
        }
    }

}
