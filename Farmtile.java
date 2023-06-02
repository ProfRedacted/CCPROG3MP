package mycode;

import java.util.*;
/**
 * The class is for farmtile
 */
public class Farmtile
{
    private boolean watered; //State of the farmtile being water
    private boolean fertilized; //State of the farmtile being fertilized
    private boolean isPlant; //State of the farmtile being planted
    private boolean isPlowed; //State of  the farmtile being plowed
    private boolean isWithered; //State whether the farmtile has a  withered plant
    private boolean isRock; //State whether the farmtile has a rock
    private Plant farmPlant; //The plant of the farmtile
    private ArrayList<Plant> plantlist; //The list of plant available
    private Player player; //The player

    /**
     * Constructor of the Farmtile class
     * @param player the player neededd to increase/decrease exp or coinss
     * @param plantlist the plant list for the farm tile
     */
    Farmtile(Player player, ArrayList<Plant> plantlist)
    {
        this.watered = false;
        this.fertilized = false;
        this.isPlowed = false;
        this.isPlant = false;
        this.isWithered = false;
        this.isRock = false;
        this.farmPlant = new Plant("None", "N/A", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.player = player;
        this.plantlist = plantlist;
    }

    /**
     * Getter for the status of water for the farm tile
     * @return true if the tile has been watered
     */
    public boolean getisWater()
    {
        return watered;
    }

    /**
     * Getter for the status of fertilizer for the farm tile
     * @return true if the tile has been fertilized
     */
    public boolean getisFertilized()
    {
        return fertilized;
    }
    /**
     * Getter for the the plant in the farm tile
     * @return the plant within the farm
     */
    public Plant getPlanted()
    {
        return farmPlant;
    }
    /**
     * Getter for the status of a plant in the farm tile
     * @return true if the tile has a plant
     */
    public boolean getisPlant()
    {
        return isPlant;
    }
    
    /**
     * Getter for the status of a plowed in the farm tile
     * @return true if the tile has been plowed
     */
    public boolean getisPlowed()
    {
        return isPlowed;
    }
    /**
     * Getter for the status of a rock in the farm tile
     * @return true if the tile has a rock
     */
    public boolean getisRock()
    {
        return isRock;
    }
    /**
     * Getter for the status of a withered in the farm tile
     * @return true if the tile has a withered plant
     */
    public boolean getisWithered()
    {
        return isWithered;
    }
    /**
     * The planting mechanism of the program
     * @param plantnum which plant would he like to plant
     */
    public void doPlant(int plantnum)
    {
        //Check is there a  plant and it is plowed
        if (isPlowed && !isPlant)
        {
            //display
            for (int i = 0; i < plantlist.size(); i++)
            {
                System.out.println(" [" + i + "]  " + plantlist.get(i).getname() +": " + plantlist.get(i).getBuyprice());
            }
            //Copy the plant to the farm plant
            farmPlant.copyPlant(plantlist.get(plantnum)); 
            //Check if it isn't withered nor planted and check the coin if its possible
            if  (!(isPlant || isWithered) && player.changeCoin(-(double)((farmPlant.getBuyprice()))))
            {
                //Set the tile to true  plant and output which plant
                isPlant = true;
                System.out.println("You planted the " + plantlist.get(plantnum).getname() + " Seed");
                
            }
            //If there is a plant
            else if (isPlant)
            {
                System.out.println("There is already a plant");
            }
            //If withered
            else if (isWithered)
            {
                System.out.println("Cant Plant, plant is withered");
            }
        }
        else 
        {
            System.out.println("Can't  Plant");
        }
    }
    
    /**
     * Waterering the tile if the conditions are met
     */
    public void doWater()
    {
        //Check if it has been water or has it been plowed
        if (watered || !isPlowed)
        {
            System.out.println("Cant water");
        }
        else
        {
            watered = true;
            
            player.increaseexp(0.5);
            System.out.println("You watered the tile");
        }
    }

    /**
     * Fertilizing the tile if the conditions are met
     */
    public void doFertilize()
    {
        if (fertilized || !isPlowed)
        {
            System.out.println("Cant fertilized");
        }
        else if (player.changeCoin(-7.0))
        {
            fertilized = true;
            player.increaseexp(4.0);
            System.out.println("You fertilized the tile");
        }
    }
    
    /**
     * Plowing the tile if the condition are met
     */
    public void doPlow()
    {
        if (isPlowed && !isRock)
        {
            System.out.println("Plowed already");
        }
        else
        {
            isPlowed = true;
            player.increaseexp(0.5);
            System.out.println("You plowed the tile");
        }
    }
    /**
     * Do a RNG between the min and max amount of plant
     * @return the RNG value of the plant
     */
    public int doRNGProduce()
    {
        Random random = new Random();
        //Randomization of the plant
        int products = random.nextInt(farmPlant.getMinproduce(), farmPlant.getMaxproduce() + 1);
        //Return the RNG value
        return products;
    }

    /**
     * Calculate and harvest the plant
     * @param products how many products are produce
     * @return the final  total of the produce
     */
    public double doHarvest(int products)
    {
        
        double waterBonus;
        double fertilBonus;
        //Check if the plant is growth and not withered
        if (farmPlant.getHdays() == farmPlant.getGrowth() && !isWithered)
        {
            //Set the lower and upper bound for the plant
            
            System.out.println("You harvested " + products + " " + farmPlant.getname());
            //Calculate the harvest total
            double harvestTotal = products * (farmPlant.getSellprice());
            //Compare the current water if its greater than the max
            if (farmPlant.getwater() > farmPlant.getMaxwater())
            {
                waterBonus = harvestTotal * 0.2 * (farmPlant.getMaxwater() - 1);
            }
            else
            {
                waterBonus = harvestTotal * 0.2 * (farmPlant.getwater() - 1);
            }
            //Compare the current fertilizer if its greater than the max
            if (farmPlant.getfertil() > farmPlant.getMaxfertil())
            {
                fertilBonus = harvestTotal * 0.5 * farmPlant.getMaxfertil();
            }
            else
            {
                fertilBonus = harvestTotal * 0.5 * farmPlant.getfertil();
            }
            //Sum all the bonus
            double finalTotal = harvestTotal + waterBonus + fertilBonus;
            //Bonus for flowerss
            if (farmPlant.getType().equals("Flower"))
            {
                finalTotal *= 1.1;
            }
            //Increase the amount exp
            player.increaseexp(products * farmPlant.getExp());
            //Reset the land and make usable for  another plant
            isPlant = false;
            return finalTotal;
        }
        else
        {
            System.out.println("Plant is not ready");
            return 0;
        }
    }

    /**
     * Mining the Rock if the condition are met
     */
    public void doPickaxe()
    {
        if (isRock)
        {
            if(player.changeCoin(-50.0))
            {
                isRock = false;
                player.increaseexp(15.0);
                System.out.println("You mine the rock");
            } 
        }
        else
        {
            System.out.println("there is no rocks");
        }
    }
    /**
     * Shoveling the tile if the condition are met
     */
    public void doShovel()
    {
        
        if (isWithered || isRock || watered || fertilized)
        {
            if(player.changeCoin(-7.0))
            {
                isWithered = false;
                landReset();
                player.increaseexp(2.0);
                System.out.println("You shovel the tile");
            } 
        }
        else
        {
            System.out.println("Nothing to shovel");
        }
    }
    /**
     * Reset the tile for the next day
     */
    public void landReset()
    {
        watered = false;
        fertilized = false;
    }

    /**
     * To check if the plant has overgrown
     * @return true if the plant is overgrown
     */
    public boolean checkHarvestday()
    {
        if (farmPlant.getHdays() < farmPlant.getGrowth())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

   /**
     * To check if the plant has enough water
     * @return true if the plant doesn't have enough water
     */
    public boolean checkWater()
    {
        if((farmPlant.getwater() < farmPlant.getMinwater()) && (farmPlant.getGrowth() == farmPlant.getHdays()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * To check if the plant has enough fertilizer
     * @return true if the plant doesn't have enough fertilizer
     */
    public boolean checkFertil()
    {
        if((farmPlant.getfertil() < farmPlant.getMinfertil()) && (farmPlant.getGrowth() == farmPlant.getHdays()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Set to wither if the plant passed all condition
     */
    public void checkWither()
    {
        if ((checkHarvestday() || checkWater() || checkFertil()) && isPlant)
        {
            System.out.println("Oh no your plant has withered away");
            isPlant = false;
            isWithered = true;
        }
    }
    /**
     * Set the tile if there is a rock
     */
    public void setRock()
    {
        isRock = true;
    }
}
