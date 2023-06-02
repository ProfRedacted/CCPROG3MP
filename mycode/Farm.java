package mycode;

import java.util.*;
/**
 * The class is for farm
 */
public class Farm
{
    private Farmtile[][] farmtiles; //the tile of the farm
    private int day; //days progressed
    private Player player; //player class
    private  ArrayList<Plant> plantlist; //the plant list
      /**
     * Constructor for the farm
     */
    Farm()
    {
        farmtiles = new Farmtile[5][10];
        day = 0;
        this.player = new Player();
        this.plantlist = new ArrayList<Plant>();
        plantlist.add(new Plant("Turnip","Root", 2, 1, 2, 0, 1, 1,2, 5, 6, 5));
        plantlist.add(new Plant("Carrot", "Root", 3, 1, 2, 0, 1, 1, 2, 10, 9, 7.5));
        plantlist.add(new Plant("Potato", "Root", 5, 3, 4, 1, 2, 1, 10, 20, 3, 12.5));
        plantlist.add(new Plant("Rose", "Flower", 1, 1, 2, 0, 1, 1, 1, 5, 5, 2.5));
        plantlist.add(new Plant("Tulips", "Flower", 2, 2, 3, 0, 1, 1, 1, 10, 10,5));
        plantlist.add(new Plant("Sunflower", "Flower", 3, 2, 3, 1, 2, 1, 1, 20, 20, 7.5));
        plantlist.add(new Plant("Mango", "Fruit Tree", 10, 7, 7, 4, 4, 5, 15, 100, 100, 25));
        plantlist.add(new Plant("Apple", "Fruit Tree", 10, 7, 7, 5, 5, 10, 15, 200, 200, 25));
        SetFarmtile();
    }

    /**
     * Get how many days pass
     * @return days pass
     */
    public int getDays() 
    {
        return day;
    }
    /**
     * Get the player class
     * @return the player
     */
    public Player getplayer()
    {
        return player;
    }
    /**
     * Get the plantlist being used for the game
     * @return the array of the plant used in the game
     */
    public  ArrayList<Plant> getPlantlist()
    {
        return plantlist;
    }
    /**
     * Get the whole farm
     * @return whole farm
     */
    public Farmtile[][] getFarm()
    {
        return farmtiles;
    }
    /**
     * Intialized the array of Farmtile
     */
    public void SetFarmtile()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                farmtiles[i][j] = new Farmtile(player, plantlist);
            }
        }
    }

    /**
     * Check if the farm still have plant
     * @return true is there is a plant
     */
    public boolean isPlant()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (farmtiles[i][j].getisPlant())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Reset the farm when a day pass
     */
    public void farmReset()
    {
        for (int  i = 0; i < 5; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    if (farmtiles[i][j].getisPlant())
                    {
                        farmtiles[i][j].getPlanted().addGrowth();
                    }
                    farmtiles[i][j].landReset();   
                    farmtiles[i][j].checkWither();
                }
            }
    }
    /**
     * Check at the end of the day whether the plant has been water or not
     */
    public void increaseDay()
    {
        for (int i = 0; i < farmtiles.length; i++)
        {
            for (int j = 0; j < farmtiles[i].length; j++)
            {
                if (farmtiles[i][j].getisWater())
                {
                    farmtiles[i][j].getPlanted().addwater();
                }
                if (farmtiles[i][j].getisFertilized())
                {
                    farmtiles[i][j].getPlanted().addfertil();
                }
            }
        }
        day++;
    }

    /**
     * Check the area if the tree is able to be planted
     * @param row the row pf the farmtile
     * @param col  the column of the farmtile
     * @return true if it can plant a tree
     */
    public boolean canPlantTree(int row, int col)
    {
        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                if (row == -1 || row == 6 || col == -1 || col == 11 || farmtiles[row + i][col + j].getisPlant())
                {
                    return false;
                }
            }
        }
        return true;
    }
     /**
     * Display and ask for the Input for the main menu in the command line
     * @return the action 
     */
    public int displayMenu()
    {
        Scanner input = new Scanner(System.in);
        int act = 0;
        System.out.println("[1]Visit Farm [2]Stats [3]Register [4]Next Day");
        act = input.nextInt();
        input.close();
        return act;
    }

    /**
     * Display and ask for Input for the row 
     * @return row number
     */
    public int displayRowtile()
    {
        Scanner input = new Scanner(System.in);
        int row;
        System.out.println("Which row");
        row = input.nextInt();
        input.close();
        return row;
    }

    /**
     * Input for the column
     * @return column number
     */
    public int displayColumntile()
    {
        Scanner input = new Scanner(System.in);
        int column;
        System.out.println("Which column");
        column = input.nextInt();
        input.close();
        return column;
    }
    /**
     * Display the action to do in the farm in the command line
     * @return the action
     */
    public int displayFarm()
    {
        Scanner input = new Scanner(System.in);
        int act;
        System.out.println("[1]Plant [2]Water [3]Fertilized [4]Plow [5]Harvest");
        act = input.nextInt();
        input.close();
        return act;
    }
    /**
     * Display the farm in the command
     * @param farm check to see if the farmtiles have a plant
     */
    public void showFarmTile(Farm farm)
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                System.out.print("|");
                if (farm.getFarm()[i][j].getisPlant())
                {
                    System.out.print("P");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
}
