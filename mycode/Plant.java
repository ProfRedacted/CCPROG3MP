package mycode;

/**
 * The class is for plant
 */
public class Plant
{
    private String name; //Plant name
    private String type; //name of the type
    private int hdays; //harvest days of the plant
    private int growth; //grow day of the plant
    private int minwater; //Mininum amount of water
    private int maxwater; //Maximim for the plant
    private int water; //Current water for the plant
    private int minfertil; //minimum fertilizer of the plant
    private int maxfertil; //maximum fertilizer
    private int fertil; //Current Fertil
    private int minproduce; //Minimum products produce by the plant
    private int maxproduce; //Minimum products produce by the plant
    private int buyprice; //Buy Price of the produce
    private int sellprice; //Sell price of the produce
    private double exp; //Exp produce by the Plant of plant

    /**
     * Constructor for the seed class
     * @param name  //Plant name
     * @param type //Name of the name 
     * @param hdays //Harvest Day of the plant
     * @param minwater //Minimum Fertilizer of the Plant
     * @param maxwater //Maximum Fertilizer of the Plant
     * @param minfertil //Minimum Fertilizer of the Plant
     * @param maxfertil //Maximum Fertilizer of the Plant
     * @param minproduce //Minimum Produce of the Plant
     * @param maxproduce //Maxminum Produce of the Plant
     * @param buyprice //Buy Price of the produce
     * @param sellprice //Sell Price of the Produce
     * @param exp //Exp produce of Plant
     */
    Plant(String name, String type, int hdays, int minwater, int maxwater, int minfertil, int maxfertil, 
        int minproduce, int maxproduce, int buyprice, int sellprice, double exp)
    {
        this.name = name;
        this.type = type;
        this.hdays = hdays;
        this.growth = 0;
        this.minwater = minwater;
        this.maxwater = maxwater;
        this.water = 0;
        this.minfertil = minfertil;
        this.maxfertil = maxfertil;
        this.fertil = 0;
        this.minproduce = minproduce;
        this.maxproduce = maxproduce;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
        this.exp = exp;
    }
    /**
     * Get the name of the Plant
     * @return name of the plant
     */
    public String getname() 
    {
        return name;
    }
    /**
     * Get the type of the Plant
     * 
     * @return type of  plant
     */
    public String getType() 
    {
        return type;
    }
    /**
     * Get the Harvest day of the Plant
     * @return When will the plant be ready 
     */
    public int getHdays() 
    {
        return hdays;
    }
    /**
     * Get the Growth date of the Plant
     * @return Day age of the plant
     */
    public int getGrowth() 
    {
        return growth;
    }
   /**
    * Get the min fertilizer
  * @return minimum fertilizer of the plant
  */
public int getMinfertil() 
   {
       return minfertil;
   }
/**
    * Get the max fertilizer
 * @return maximum fertilizer of the plant
 */
   public int getMaxfertil() 
   {
       return maxfertil;
   }
   /**
    * Get the current fertilizaer
 * @return current fertilizer of the plant
 */
   public int getfertil() 
   {
       return fertil;
   }
   /**
    * Get the minimum amount of water
 * @return mimumum amount  of water
 */
public int getMinwater() 
   {
       return minwater;
   }
   /**
    * Get the maximum amount of water
 * @return maximum amount  of water
 */
public int getMaxwater() 
   {
       return maxwater;
   }
   /**
    * Get the current amount of water
 * @return current amount  of water
 */
public int getwater() 
   {
       return water;
   }
   /**
    * Get the current amount of produce
 * @return current amount  of produce
 */
public int getMinproduce() 
   {
       return minproduce;
   }
   /**
    * Get the max amount of produce
 * @return max amount  of produce
 */
   public int getMaxproduce() 
   {
       return maxproduce;
   }
    /**
    * Get the buy price of the plant
 * @return buy price of the plant
 */
    public int getBuyprice() 
    {
        return buyprice;
    }
/**
    * Get the sell price of the plant
 * @return sell price of the plant
 */
    public int getSellprice() 
    {
        return sellprice;
    }

   /**
    * Get the exp amount of the plant
 * @return exp amount of the plant
 */
    public double getExp() 
    {
        return exp;
    }
    
    /**
     * Set the plant from another plant
     * @param name the name of the plant
     * @param type the type of the plant
     * @param hdays the harvest day of the plant
     * @param minwater the minimum water of the plant
     * @param maxwater the maximum water of the plant
     * @param minfertil the minimum fertilizer of the plant
     * @param maxfertil the maximum fertilizer of the plant
     * @param minproduce the minimum produce of the plant
     * @param maxproduce the maximum produce of the plant
     * @param buyprice the buy price of the seed
     * @param sellprice the sell produce of the plant
     * @param exp the exp earned from the plant
     */
    public void setPlant(String name, String type, int hdays, int minwater, int maxwater, int minfertil, int maxfertil, 
        int minproduce, int maxproduce, int buyprice, int sellprice, double exp)
    {
        this.name = name;
        this.type = type;
        this.hdays = hdays;
        this.growth = 0;
        this.minwater = minwater;
        this.maxwater = maxwater;
        this.water = 0;
        this.minfertil = minfertil;
        this.maxfertil = maxfertil;
        this.fertil = 0;
        this.minproduce = minproduce;
        this.maxproduce = maxproduce;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
        this.exp = exp;
    }
    /**  
     * Reset the plant
     */
    public void plantReset()
    {
        name = "None";
        type = "N/A";
        hdays = 0;
        growth = 0;
        minfertil = 0;
        maxfertil = 0;
        fertil = 0;
        minwater = 0;
        maxwater = 0;
        water = 0;
        minproduce = 0;
        maxproduce = 0;
        buyprice = 0;
        sellprice = 0;
        exp = 0;
    }
    /**
     * Copy the plant to another plant
     * @param plant a plant being copy
     */
    public void copyPlant(Plant plant)
    {
        this.setPlant(plant.getname(), plant.getType(), plant.getHdays(), plant.getMinwater(), plant.getMaxwater(), plant.getMinfertil(), 
                    plant.getMaxfertil(), plant.getMinproduce(), plant.getMaxproduce(), plant.getBuyprice(), plant.getSellprice(), 
                    plant.getExp());
    }
    /**
     * Add a growth to a plant
     */
    public void addGrowth()
    {
        growth++;
    }

    /**
     * Add currcent water to a plant
     */
    public void addwater()
    {
        water++;
    }

    /**
     * Add currcent fertilizer to a plant
     */
    public void addfertil()
    {
        fertil++;
    }

    /**
     * Increase the max bonus water of the plant
     * @param increase the amount of the maxwater increase
     */
    public void addBonusWater(int increase)
    {
        maxwater += increase;
    }

    /**
     *   Increase the max bonus Fertilizer of the plant
     * @param increase the amount of the max Fertilizer it can hold
     */
    public void addBonusFertil(int increase)
    {
        maxfertil += increase;
    }

    /**
     * Add  the bonus earning of the crop
     * @param increase the amount of the produce price  increase
     */
    public void addBonusEarning(double increase)
    {
        sellprice += increase;
    }

    /**
     * Minus the cost of the plant
     * @param reduce the amount it will reduce
     */
    public void addReduceCost(double reduce)
    {
        buyprice -= reduce;
    }
}