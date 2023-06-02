package mycode;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/**
 * The class for the  GUI
 */
public class GUI
{
    private int row,col; //row and col of the farm
    private boolean isRockfile; //Check if there has been a rock file already
    private Farm farm; // the whole farm
    private JFrame frame; //the  main frame of the GUI
    private Container startGamePane; //The  start panel for the game
    private Container gameMenuPane; //The game menu Pane for the  game
    private Container farmPane; //The farm Pane for  the game
    private JLayeredPane farmLayeredPane; //The layered panel for the farm
    private JPanel startMenubtnPanel; //The start game button Panel
    private JPanel menubtnpanel; //The game menu button panel 
    private JPanel farmbtnPanel; //The actions buttons for the  farm
    private JPanel statpanel; //The stats for the game
    private JPanel farmDisplayPanel; //The Default/First layer of the layered panel
    private JPanel plantDisplayPanel; //The Palette/Second layer of the layered panel
    private JPanel fertilizedDisplayPanel; //The Modal/Third layer of the layered panel
    private JLabel namelbl; //The JLabel for the name of the player in statpanel
    private JLabel coinslbl; //The JLabel for the object coin of the player in statpanel
    private JLabel levellbl; //The JLabel for the level label of the player in the statpanel
    private JLabel titlelbl;//The JLabel for the title label of the player in the statpanel
    private JLabel daylbl;//The JLabel for the day label of the player in the statpanel
    private JLabel[][] farmLbls;//The Array of JLabel for the farmtile picture of the player in the farmDisplayPanel
    private JLabel[][] fertilizerLbls;//The Array of JLabel for the fertilizer picture of the player in the farmDisplayPanel
    private JLabel[][] plantLbls;//The Array of JLabel for the plant picture of the player in the farmDisplayPanel
    private Image untilled;//Image of the untilled land
    private Image tilled; //Image of the tilled land
    /**
     * Constructor for the  GUI
     */
    GUI()
    {
        //Setting up a new farm
        this.farm = new Farm();
        isRockfile = false;

        this.startGamePane = new Container();
        this.startGamePane.setLayout(new BorderLayout());
        this.gameMenuPane = new Container();
        this.gameMenuPane.setLayout(new BorderLayout());
        this.farmPane = new Container();
        this.farmPane.setLayout(new BorderLayout());
        
        this.startMenubtnPanel = new JPanel();
        this.menubtnpanel = new JPanel();
        this.farmbtnPanel = new JPanel();
        this.statpanel = new JPanel();
        this.statpanel.setBackground(Color.CYAN);
        
        this.farmDisplayPanel = new JPanel();
        this.farmDisplayPanel.setLayout(new GridLayout(0,10));
        this.farmDisplayPanel.setBackground(new Color(124, 252, 0));
        this.plantDisplayPanel = new JPanel();
        this.plantDisplayPanel.setOpaque(false);
        this.plantDisplayPanel.setLayout(new GridLayout(0,10));
        this.fertilizedDisplayPanel = new JPanel();
        this.fertilizedDisplayPanel.setOpaque(false);
        this.fertilizedDisplayPanel.setLayout(new GridLayout(0,10));

        this.farmLayeredPane = new JLayeredPane();
        this.farmLayeredPane.setSize(850,600);

        this.farmLayeredPane.add(fertilizedDisplayPanel,Integer.valueOf(2));
        this.farmLayeredPane.add(plantDisplayPanel,Integer.valueOf(1));
        this.farmLayeredPane.add(farmDisplayPanel,Integer.valueOf(0));

        this.fertilizedDisplayPanel.setSize(farmLayeredPane.getPreferredSize());
        this.plantDisplayPanel.setSize(farmLayeredPane.getPreferredSize());
        this.farmDisplayPanel.setSize(farmLayeredPane.getPreferredSize());
        
        this.namelbl = new JLabel();
        this.coinslbl = new JLabel();
        this.levellbl = new JLabel();
        this.titlelbl = new JLabel();
        this.daylbl = new JLabel();
        this.farmLbls = new JLabel[5][10];
        this.fertilizerLbls = new JLabel[5][10];
        this.plantLbls = new JLabel[5][10];
        //Setting up for the new JLabel being used  for display
        for (int i = 0; i < farm.getFarm().length; i++)
        {
            for (int j = 0; j < farm.getFarm()[i].length; j++)
            {
                farmLbls[i][j] = new JLabel();
                
                fertilizerLbls[i][j] = new JLabel();
                //The fertilizer should not be seen unless the tile is fertilzed
                fertilizerLbls[i][j].setVisible(false);
                //Setting up the new plant
                fertilizerLbls[i][j].setIcon(new ImageIcon(new ImageIcon("assets/tiles/dirtfertilizer.png").getImage()
                                                    .getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                fertilizedDisplayPanel.add(fertilizerLbls[i][j]);

                plantLbls[i][j] = new JLabel();
                //The plant should not be seen unless the tile is planted
                plantLbls[i][j].setVisible(false);
                plantLbls[i][j].setBackground(Color.green);
                plantDisplayPanel.add(plantLbls[i][j]);
            }
        }
        this.frame = new JFrame("MyFarm");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());
        this.frame.setSize(1000,800);
        this.frame.setLocationRelativeTo(null);

        this.untilled = new ImageIcon("assets/tiles/dirtblock.jpeg").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        this.tilled = new ImageIcon("assets/tiles/dirtfarmland.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);

        titlepanel();

        InitializedstartMenu();
        IntialziedgameMenubtns();
        initializedfarmbtns();

        this.frame.getContentPane().add(startGamePane,BorderLayout.CENTER);
         
        framesetVisible();
    }
    /**
     * Remove and replace the old container to a new one
     */
    public void refreshframe()
    {
        frame.revalidate();
        frame.repaint();
    }
    /**
     * Setting up for a change in the  panel
     */
    public void InitializedstartMenu()
    {
        Image titleImage;
        titleImage = new ImageIcon("assets/title/maintitle.png").getImage().getScaledInstance(514, 300, Image.SCALE_DEFAULT);
        startMenubtnPanel.setLayout(new GridLayout(4,0));

        JLabel gameNamelbl = new JLabel(new ImageIcon(titleImage),SwingConstants.CENTER);
        startGamePane.add(gameNamelbl, BorderLayout.NORTH);
        Initializedstartbtn();
        InitializedRockbtn();
        initializedmaingameExitbutton();
        
        startGamePane.add(startMenubtnPanel,BorderLayout.CENTER);
    }
    /**
     * Initialized the start game  for  the myfarm
     */
    public void Initializedstartbtn()
    {
        JButton startbtn = new JButton("Start");
        startbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().remove(startGamePane);
                frame.getContentPane().add(gameMenuPane);
                gameMenuPane.add(statpanel,BorderLayout.LINE_END);
                refreshframe();
            }
        });
        startMenubtnPanel.add(startbtn);
    }
    /**
     * Initiazlizing the exit button
     */
    public void initializedmaingameExitbutton()
    {
        JButton mainMenuexitbutton = new JButton("Exit");
        mainMenuexitbutton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        startMenubtnPanel.add(mainMenuexitbutton);
    }
    /**
     * The title for the farm and game menu
     */
    public void titlepanel()
    {
        JPanel menutitle = new JPanel();
        JPanel farmtitle = new JPanel();
        JLabel menutext = new JLabel("Welcome to Game Menu");
        JLabel farmtext = new JLabel("This is your farm");

        menutitle.add(menutext);
        farmtitle.add(farmtext);
        gameMenuPane.add(menutitle, BorderLayout.NORTH);
        farmPane.add(farmtitle, BorderLayout.NORTH);
    }
    /**
     * Combine all of the setting up the button for the main menu
     */
    public void IntialziedgameMenubtns()
    {
        menubtnpanel.setBackground(Color.yellow);
        menubtnpanel.setLayout(new GridLayout(0,1));
        menubtnpanel.setBounds(0,0,250,500);
        initializedfarmbtn();
        initializedregisterbtn();
        initializednextdaybtn();
        initializedstatpanel();
        initializedExitbutton();
        this.gameMenuPane.add(menubtnpanel, BorderLayout.CENTER);
    }

    /**
     * Setting up the farm button, which will send you to the farm
     */
    public void initializedfarmbtn()
    {
        JButton farmbtn;
        farmbtn = new JButton("Farm");
        farmbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().remove(gameMenuPane);
                frame.getContentPane().add(farmPane);
                farmPane.add(statpanel, BorderLayout.LINE_END);
                refreshframe();
            }
        });
        menubtnpanel.add(farmbtn);
    }

    /**
     * Setting up the register button, which will check and register you if you stats allows it
     */
    public void initializedregisterbtn()
    {
        JButton registerbtn;
        registerbtn = new JButton("Register to be a better farmer");
        registerbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (farm.getplayer().registerPlayer(farm.getPlantlist()))
                {
                    JOptionPane.showMessageDialog(frame, "You became a " + farm.getplayer().getTitle());
                }
                else
                {
                    switch(farm.getplayer().getTitle())
                    {
                        case "New Farmer": JOptionPane.showMessageDialog(frame, "Need 200 object coins and level: 5"); break;
                        case "Registered Farmer":  JOptionPane.showMessageDialog(frame, "Need 300 object coins and level: 10"); break;
                        case "Distinguished Farmer": JOptionPane.showMessageDialog(frame, "Need 400 object coins and level: 15"); break;
                        case "Legendary Farmer": JOptionPane.showMessageDialog(frame, "Max Title"); break;
                    }
                }
                updateStatpanel();
            }
        });
        menubtnpanel.add(registerbtn);
    }
    
    /**
     * Set up the next day button, and add image to the status of the tile
     */
    public void initializednextdaybtn()
    {
        JButton nextdaybtn;
        Image[] plantImage = new Image[8];
        nextdaybtn = new JButton("Next day");
        //Add the image for the plant
        plantImage[0] = new ImageIcon("assets/plant/PlantRadish.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        plantImage[1] = new ImageIcon("assets/plant/PlantCarrot.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        plantImage[2] = new ImageIcon("assets/plant/PlantPotato.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        plantImage[3] = new ImageIcon("assets/plant/PlantRose.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        plantImage[4] = new ImageIcon("assets/plant/PlantTulip.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        plantImage[5] = new ImageIcon("assets/plant/PlantSunflower.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        plantImage[6] = new ImageIcon("assets/plant/PlantMangotree.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        plantImage[7] = new ImageIcon("assets/plant/PlantAppletree.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        nextdaybtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Next day");
                JOptionPane.showMessageDialog(frame, "A day passed");
                farm.increaseDay();
                farm.farmReset();
                //Scan the whole farm
                for (int i = 0; i < farm.getFarm().length; i++)
                {
                    for (int j = 0; j < farm.getFarm()[i].length;j++)
                    {
                        //Set the farm tile to lose fertilizer and water
                        if (farm.getFarm()[i][j].getisPlowed())
                        {
                            fertilizerLbls[i][j].setVisible(false);
                            farmLbls[row][col].setIcon(new ImageIcon(tilled));
                        }
                        //Set the farm tile to wither if the plant get wither
                        if (farm.getFarm()[i][j].getisWithered())
                        {
                            JOptionPane.showMessageDialog(frame, "A plant in your farm died today");
                            farmLbls[row][col].setIcon(new ImageIcon(new ImageIcon("assets/tiles/dirtdead.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                            fertilizerLbls[i][j].setVisible(false);
                            plantLbls[i][j].setVisible(false);
                        }
                        //Set up the picture if the plant is ready
                        if (farm.getFarm()[i][j].getisPlant() && 
                        (farm.getFarm()[i][j].getPlanted().getGrowth() == farm.getFarm()[i][j].getPlanted().getHdays()))
                        {
                            for (int k = 0; k < farm.getPlantlist().size(); k++)
                            {
                                if (farm.getPlantlist().get(k).getname().equals(farm.getFarm()[i][j].getPlanted().getname()))
                                {
                                    plantLbls[i][j].setIcon(new ImageIcon(plantImage[k]));
                                }
                            }
                        }
                        
                    }
                }
                updateStatpanel();
            }
        });
        menubtnpanel.add(nextdaybtn);
    }
    /**
     * Initialized the exit button for the game
     */
    public void initializedExitbutton()
    {
        JButton gameMenuexitbutton = new JButton("Exit");
        gameMenuexitbutton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        menubtnpanel.add(gameMenuexitbutton);
    }

    /**
     * Just show the statistic of the player
     */
    public void initializedstatpanel()
    {
        statpanel.setLayout(new GridLayout(6,1));
        JLabel statlbl = new JLabel("Statistics");
        namelbl.setText("Name: " + farm.getplayer().getName());
        coinslbl.setText("Objectcoins: " + Double.toString(farm.getplayer().getCoin()));
        levellbl.setText("Level: " + Integer.toString((int)farm.getplayer().getExp()/100));
        titlelbl.setText("Title: " + farm.getplayer().getTitle());
        daylbl.setText("Day Passed: " + farm.getDays());
        statpanel.add(statlbl);
        statpanel.add(namelbl);
        statpanel.add(coinslbl);
        statpanel.add(levellbl);
        statpanel.add(titlelbl);
        statpanel.add(daylbl);
        
        gameMenuPane.add(statpanel, BorderLayout.LINE_END);
    }
    /**
     *  The statistic of the player
     */
    public void updateStatpanel()
    {
        namelbl.setText("Name: " + farm.getplayer().getName());
        coinslbl.setText("Objectcoins: " + Double.toString(farm.getplayer().getCoin()));
        levellbl.setText("Level: " + Integer.toString((int)farm.getplayer().getExp()/100));
        titlelbl.setText("Title: " + farm.getplayer().getTitle());
        daylbl.setText("Day Passed: " + farm.getDays());
    }

    /**
     * Group all of the farm button for initialization in the farm panel
     */
    public void initializedfarmbtns()
    {
        farmbtnPanel.setBackground(Color.BLACK);
        initializedFarmdisplay();
        initializedbackbtn();
        initializedaskFarmtile();
        initializedPlantbtn();
        initializedDoWater();
        initializedfertilized();
        initializedPlowbtn();
        initializedDoHarvest();
        initializedDoPickaxe();
        initializedDoShovel();
        askFarmtile();
        farmPane.add(farmLayeredPane, BorderLayout.CENTER);
        
    }
    /**
     * Initialized the display (specifically the rock and the  farmtile) for the farm
     */
    public void initializedFarmdisplay()
    {
        
        for (int i = 0; i < farmLbls.length; i++)
        {
            for (int j = 0; j < farmLbls[i].length; j++)
            {
                farmLbls[i][j].setIcon(new ImageIcon(untilled));
                farmDisplayPanel.add(farmLbls[i][j]);
            }
        }
        
    }

    /**
    * Initialized the button for plant
    */
public void initializedPlantbtn()
    {
        JButton plantbtn;
        plantbtn = new JButton("Plant");
        plantbtn.addActionListener(new ActionListener()
        {
            /* (non-Javadoc)
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Planting");
                if (farm.getFarm()[row][col].getisPlowed())
                {
                    choosingPlantPanel();
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Not yet Plowed");
                }
            }
        });
        farmbtnPanel.add(plantbtn);
    }
    /**
     * Choose the plant and set the image of the planted tile
     */
    public void choosingPlantPanel()
    {
        JButton[] plantbtn = new JButton[8];
        JButton backbtn = new JButton("<--");
        JPanel plantbtnpanel = new JPanel();
        Image seedImage = new ImageIcon("assets/plant/PlantSeed.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        Image saplingImage = new ImageIcon("assets/plant/PlantSapling.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        plantbtnpanel.setLayout(new FlowLayout());
        for (int i = 0; i < plantbtn.length; i++)
        {
            plantbtn[i] = new JButton();
            plantbtn[i].setText(farm.getPlantlist().get(i).getname() +": " + farm.getPlantlist().get(i).getBuyprice());
        }
        //Initialized a back button
        backbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                farmPane.add(farmbtnPanel,BorderLayout.SOUTH);
                farmPane.remove(plantbtnpanel);
                refreshframe();
            }
        });
        plantbtnpanel.add(backbtn);

        //Initialized the plant button
        for (int i = 0; i < plantbtn.length; i++)
        {
            final int j = i;
            plantbtn[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (farm.getFarm()[row][col].getisPlowed() && farm.getplayer().getCoin() - farm.getPlantlist().get(j).getBuyprice() >= 0)
                    {
                        if (!farm.getPlantlist().get(j).getType().equals("Fruit Tree"))
                        {
                            System.out.println("Planting a crop");
                            farm.getFarm()[row][col].doPlant(j);
                            plantLbls[row][col].setIcon(new ImageIcon(seedImage));
                            plantLbls[row][col].setVisible(true);
                            JOptionPane.showMessageDialog(frame,"You planted a plant");    
                        }
                        else if (farm.getPlantlist().get(j).getType().equals("Fruit Tree"))
                        {
                            try 
                            {
                                if (farm.canPlantTree(row, col))
                                {
                                    System.out.println("Planting a tree");
                                    farm.getFarm()[row][col].doPlant(j);
                                    plantLbls[row][col].setIcon(new ImageIcon(saplingImage));
                                    plantLbls[row][col].setVisible(true);               
                                    JOptionPane.showMessageDialog(frame,"You planted a tree");                      
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(frame,"There is a plant nearby, can't plant");
                                }
                            } 
                            catch (java.lang.ArrayIndexOutOfBoundsException event)
                            {
                                JOptionPane.showMessageDialog(frame,"Out of bound tree");
                            }
                            catch (Exception event)
                            {
                                JOptionPane.showMessageDialog(frame,"¯\\_(ツ)_/¯");
                            }
                        }
                        updateStatpanel();
                    }
                    else if (farm.getFarm()[row][col].getisRock())
                    {
                        JOptionPane.showMessageDialog(frame,"There is a rock");
                    }
                    else if (!farm.getFarm()[row][col].getisPlowed())
                    {
                        JOptionPane.showMessageDialog(frame,"Not yet Plowed");
                    }
                    else if (farm.getplayer().getCoin() - farm.getPlantlist().get(j).getBuyprice() >= 0)
                    {
                        JOptionPane.showMessageDialog(frame,"Can not afford");
                    }
                }
            });
            plantbtnpanel.add(plantbtn[i]);
        }
        plantbtnpanel.setBackground(Color.black);
        farmPane.add(plantbtnpanel,BorderLayout.SOUTH);
        farmPane.remove(farmbtnPanel);
    }
    /**
     * Intiazlied the water button
     */
    public void initializedDoWater()
    {
        JButton waterbtn;
        waterbtn = new JButton("Water");
        waterbtn.addActionListener(new ActionListener()
        {
            /* (non-Javadoc)
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             * Set the image to watered tile
             */
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (farm.getFarm()[row][col].getisPlowed())
                {
                    System.out.println("Watering");
                    farm.getFarm()[row][col].doWater();
                    farmLbls[row][col].setIcon(new ImageIcon(new ImageIcon("assets/tiles/dirtwetfarm.jpg").getImage()   
                                                            .getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                    JOptionPane.showMessageDialog(frame, "You earned 0.5 exp");
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Not yet Plowed");
                }
                updateStatpanel();
            }
        });
        farmbtnPanel.add(waterbtn);
    }
    /**
     * Initiazed the fertilized button
     */
    public void initializedfertilized()
    {
        JButton fertilizeButton;
        fertilizeButton = new JButton("Fertilizer");
        fertilizeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (farm.getFarm()[row][col].getisPlowed())
                {
                    System.out.println("Fertilized");
                    farm.getFarm()[row][col].doFertilize();
                    fertilizerLbls[row][col].setVisible(true);
                    JOptionPane.showMessageDialog(frame, "You earned 4 exp and spend 10 object coin");
                    updateStatpanel();
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Hasn't been plowed yet");
                }
            }
        });
        farmbtnPanel.add(fertilizeButton);
        
    }
    /**
     * Initialized the plow button
     */
    public void initializedPlowbtn()
    {
        JButton plowbtn;
        plowbtn = new JButton("Plow");
        plowbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!farm.getFarm()[row][col].getisRock())
                {
                    System.out.println("Plowed");
                    farm.getFarm()[row][col].doPlow();
                    farmLbls[row][col].setIcon(new ImageIcon(tilled));
                    JOptionPane.showMessageDialog(frame, "You earned 0.5 exp");
                    updateStatpanel();
                }
            }
        });
        farmbtnPanel.add(plowbtn);
    }
    /**
     * Initialized the harvest button
     */
    public void initializedDoHarvest()
    {
        JButton Hbtns;
        Hbtns = new JButton("Harvest");
        Hbtns.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Harvest");
                if (farm.getFarm()[row][col].getisPlant() && 
                    farm.getFarm()[row][col].getPlanted().getHdays() == farm.getFarm()[row][col].getPlanted().getGrowth())
                {
                    int produce = farm.getFarm()[row][col].doRNGProduce();
                    double finalTotal = farm.getFarm()[row][col].doHarvest(produce);
                    double finalExp = farm.getFarm()[row][col].getPlanted().getExp() * produce;
                    farm.getplayer().changeCoin(finalTotal);
                    plantLbls[row][col].setVisible(false);
                    JOptionPane.showMessageDialog(frame, "You harvested " + produce + " " + farm.getFarm()[row][col].getPlanted().getname()
                    + " and earned a total " + finalTotal + " object coins and earned " + finalExp  + " EXP");
                    farm.getFarm()[row][col].getPlanted().plantReset();
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,"Can not be harvested yet");
                }
                updateStatpanel();
            }
        });
        farmbtnPanel.add(Hbtns);
    }
    /**
     * Initialized the pickaxe button
     */
    public void initializedDoPickaxe()
    {
        JButton pickaxebtn;
        pickaxebtn = new JButton("Mine");
        pickaxebtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (farm.getFarm()[row][col].getisRock())
                {
                    if (farm.getplayer().getCoin() - 50 >= 0)
                    {
                        farm.getFarm()[row][col].doPickaxe();
                        farmLbls[row][col].setIcon(new ImageIcon(untilled));
                        JOptionPane.showMessageDialog(frame, "You earned 15 exp and spend 50 object coin");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Can not afford");
                    }
                    updateStatpanel();
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "No rock");
                }
            }
        });
        farmbtnPanel.add(pickaxebtn);
    }
    /**
     * Initialized the shovel button
     */
    public void initializedDoShovel()
    {
        JButton shovelButton;
        shovelButton = new JButton("Shovel");
        shovelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                if (farm.getFarm()[row][col].getisWithered() || farm.getFarm()[row][col].getisRock() 
                    || farm.getFarm()[row][col].getisWater() || farm.getFarm()[row][col].getisFertilized())
                {
                    if (farm.getplayer().getCoin() - 7 >= 0)
                    {
                        farm.getFarm()[row][col].doShovel();
                        farmLbls[row][col].setIcon(new ImageIcon(tilled));
                        fertilizerLbls[row][col].setVisible(false);
                        JOptionPane.showMessageDialog(frame, "You earned 2 exp and spend 7 object coin");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Can not afford");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Nothing to shovel");
                }
            }
        });
        farmbtnPanel.add(shovelButton);
    }
    /**
     * Initialized the back button to the main menu
     */
    public void initializedbackbtn()
    {
        JButton backbtn;
        backbtn = new JButton("<--");
        backbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().remove(farmPane);
                frame.getContentPane().add(gameMenuPane);
                gameMenuPane.add(statpanel, BorderLayout.LINE_END);
                refreshframe();
            }
        });
        farmbtnPanel.add(backbtn);
    }
   
    /**
     * Initialized the farm tile button
     */
    public void initializedaskFarmtile()
    {
        JButton farmTilebtn;
        farmTilebtn = new JButton("Select a new  tile");
        farmTilebtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                farmPane.remove(farmbtnPanel);
                askFarmtile();
                refreshframe();
            }
        });
        farmbtnPanel.add(farmTilebtn);
    }
    /**
     * Ask the player for the which tile the  player want
     */
    public void askFarmtile()
    {
        JPanel textPanel = new JPanel();
        JTextField rowField = new JTextField();
        JTextField colField = new JTextField();
        JLabel rowPrompt = new JLabel();
        JLabel colPrompt = new JLabel();
        JButton confirmButton = new JButton("Submit");
        rowField.setPreferredSize(new Dimension(250,40));
        colField.setPreferredSize(new Dimension(250,40));
        rowPrompt.setText("Input Row: ");
        colPrompt.setText("Input Column: ");
        confirmButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Try if the input right or  check the index
                try {
                    row = Integer.parseInt(rowField.getText());
                    col = Integer.parseInt(colField.getText());
                    farm.getFarm()[row][col].getisWater();
                    farmPane.remove(textPanel);
                    farmPane.add(farmbtnPanel,BorderLayout.SOUTH);
                    refreshframe();
                }
                catch(java.lang.NumberFormatException event)
                {
                    JOptionPane.showMessageDialog(frame,"Wrong input");
                }
                catch(java.lang.ArrayIndexOutOfBoundsException event)
                {
                    JOptionPane.showMessageDialog(frame,"Wrong/No index");
                }
                catch (Exception event)
                {
                    JOptionPane.showMessageDialog(frame,"¯\\_(ツ)_/¯");
                }
                
                
            }
        });

        textPanel.setLayout(new FlowLayout());
        textPanel.add(rowPrompt);
        textPanel.add(rowField);
        textPanel.add(colPrompt);
        textPanel.add(colField);
        textPanel.add(confirmButton);

        farmPane.add(textPanel,BorderLayout.SOUTH);
    }

    /**
     * Add the button for the input file of the rocks
     */
    public void InitializedRockbtn()
    {
        JButton rockButton = new JButton("Set Rocks");
        rockButton.addActionListener(new  ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!isRockfile)
                {
                    String  nameFile = JOptionPane.showInputDialog(frame, "What file?");
                    SetRock(nameFile);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,"Already Inputted a file");
                }
            }
        });
        startMenubtnPanel.add(rockButton);
    }
    /**
     * Set the rocks for the game
     * @param nameFile name of the file being passsed
     */
    public void SetRock(String nameFile)
    {
        int rowrock;
        int colrock;
        int rockcount = 0;
        int[][] rockPlace;
        Image rockpic;
        rockPlace = new int[30][2];
        FileReader rockFile;

        rockpic = new ImageIcon("assets/tiles/dirtrock.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        //Try to read the file
        try {
            rockFile = new FileReader(nameFile);
            Scanner rockscan = new Scanner(new BufferedReader(rockFile));
            int i = 0;
            while (rockscan.hasNextLine() && rockcount <= 30) 
            {

                String[] line = rockscan.nextLine().trim().split(" ");
                for (int j = 0; j < rockPlace[i].length; j++)
                {
                    rockPlace[i][j] = Integer.parseInt(line[j]);
                }
                i++;
                rockcount++;
            }
            isRockfile = true;
            rockFile.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame,"No file existed");
        }
        catch (java.util.NoSuchElementException e)
        {
            JOptionPane.showMessageDialog(frame, "¯\\_(ツ)_/¯");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(frame, "Error found");
        }
        //Set the area on the file to be a rock
        for (int i = 0; i < rockcount; i++)
        {
            rowrock = rockPlace[i][0];
            colrock = rockPlace[i][1];
            farm.getFarm()[rowrock][colrock].setRock();
            farmLbls[rowrock][colrock].setIcon(new ImageIcon(rockpic));
            if (i == 30)
            {
                break;
            }
        }
        //if rockcount is less than test
        while (rockcount < 10)
        {
            Random random = new Random();
            rowrock = random.nextInt(0, 5); 
            colrock = random.nextInt(0,10);
            if (!farm.getFarm()[rowrock][colrock].getisRock())
            {
                farm.getFarm()[rowrock][colrock].setRock();
                farm.getFarm()[rowrock][colrock].setRock();
                farmLbls[rowrock][colrock].setIcon(new ImageIcon(rockpic));
                rockcount++;
            }
        }
             
    }
    /**
     * Status of the game whether if the conditon are met or not
     */
    public void framesetVisible()
    {
        //Set the visibility to true while the condition of gameover are met
      while (farm.isPlant() || farm.getplayer().getCoin() >= 5)
        {
            frame.setVisible(true);
        }
        //When the game is done the visibility of the frame is set to false
        frame.setVisible(false);
        //Option for the string
        String[] option = {"Yes", "No"};
        int x = JOptionPane.showOptionDialog(frame, "Restart","Game Over", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
        if (x == 0)
        {
            new GUI();
        }
        else if (x == 1)
        {
            System.exit(0);
        }
        
    }
}
