package mycode;

import java.util.Scanner;
/**
 * The main class or just driver aka allows the file to run
 */ 
public class Main
{
    /**
     * The main constructor
     * @param args the args
     */
    public static void main(String[] args)
    {
        Scanner act = new Scanner(System.in); //Scanner for the cmd line
        Farm farm = new Farm(); //Whole farm for the cmd line
        
        GUI gui = new GUI();
        while (farm.isPlant() || farm.getplayer().getCoin() >= 5)
        {
            
            switch (farm.displayMenu())
            {
                case 1:
                {
                    farm.showFarmTile(farm);
                    int row = farm.displayRowtile();
                    int column = farm.displayColumntile();
                    switch(farm.displayFarm())
                    {
                        case 1: 
                        int plantnum = act.nextInt();
                        if (farm.canPlantTree(row, column))
                        {
                            farm.getFarm()[row][column].doPlant(plantnum);
                        }

                        break;
                        case 2: farm.getFarm()[row][column].doWater(); break;
                        case 3: farm.getFarm()[row][column].doFertilize(); break;
                        case 4: farm.getFarm()[row][column].doPlow(); break;
                        case 5: 
                        int produce = farm.getFarm()[row][column].doRNGProduce();
                        farm.getFarm()[row][column].doHarvest(produce);break;
                    }
                    break;
                }
                case 2:
                {
                    farm.getplayer().DisplayStat();
                    break;
                }
                case 3:
                {
                    farm.getplayer().registerPlayer(farm.getPlantlist());
                    break;
                }
                case 4:
                {
                    farm.farmReset();
                    break;
                }
            }
        }
        act.close();
    }
}

