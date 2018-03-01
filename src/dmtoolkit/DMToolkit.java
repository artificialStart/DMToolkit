package dmtoolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class DMToolkit
{
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        /*
        What does the main method do and how does it do it?
        
        1) Check to see if there is existing save data
        2) Create runtime data structures to store the pcs, monsters, and npcs
        3) If there is no existing save data, create save files
        4) Open the main menu, where user can view and edit the participants
        5) Once user starts combat, add chosen members to combat
        6) Roll initiative for monsters, npcs, and maybe pcs
        
        */
        
        //Access the saveDataCheck method to determine if there is existing save data
        boolean hasExistingSaveData = saveDataCheck();
        
        Scanner kb = new Scanner(System.in);
        
        //Create the ArrayLists to hold the combat Entities
        ArrayList<PlayerChar> pcs = new ArrayList<>();
        ArrayList<Monster> monsters = new ArrayList<>();
        ArrayList<NPC> npcs = new ArrayList<>();
        
        //If save data does not exist, create save files in directory...
        //C:/Users/Public/Documents/DMToolkit/
        if (hasExistingSaveData == false) {
            try {
                FileInputStream fis1 = new FileInputStream("C:/Users/Public/Documents/DMToolkit/PCList.txt");
                ObjectInputStream ois1 = new ObjectInputStream(fis1);
                pcs = (ArrayList<PlayerChar>) ois1.readObject();
                ois1.close();
            
                FileInputStream fis2 = new FileInputStream("C:/Users/Public/Documents/DMToolkit/MonsterList.txt");
                ObjectInputStream ois2 = new ObjectInputStream(fis2);
                monsters = (ArrayList<Monster>) ois2.readObject();
                ois2.close();
            
                FileInputStream fis3 = new FileInputStream("C:/Users/Public/Documents/DMToolkit/NPCList.txt");
                ObjectInputStream ois3 = new ObjectInputStream(fis3);
                npcs = (ArrayList<NPC>) ois3.readObject();
                ois3.close();
            } catch (ClassNotFoundException e) {
                System.out.println("Error: Could not read data");
            }
        }

        boolean dontQuit = true;
        
        //Access the Main Menu from the mainMenu() method until the user quits out
        /*
        From here, the user can add or remove PlayerChars, Monsters, and
        NPCs from their respective ArrayLists. 
        Additionally, they can access a specific member from the save directory 
        and display their current information and status.
        
        After quitting from the setup mainMenu, the combat() method will be called
        */
        do {
            switch (mainMenu()) {
                case 11:
                    pcs = PCEntry(pcs);
                    break;
                case 12:
                    monsters = monsterEntry(monsters);
                    break;
                case 13:
                    npcs = NPCEntry(npcs);
                    break;
                case 21:
                    pcs = PCRemove(pcs);
                    break;
                case 22:
                    monsters = monsterRemove(monsters);
                    break;
                case 23:
                    npcs = NPCRemove(npcs);
                    break;
                case 3:
                    FileOutputStream fos1 = new FileOutputStream("C:/Users/Public/Documents/DMToolkit/PCList.txt");
                    ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
                    oos1.writeObject(pcs);
                    oos1.close();
                    
                    FileOutputStream fos2 = new FileOutputStream("C:/Users/Public/Documents/DMToolkit/MonsterList.txt");
                    ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
                    oos2.writeObject(monsters);
                    oos2.close();
                    
                    FileOutputStream fos3 = new FileOutputStream("C:/Users/Public/Documents/DMToolkit/NPCList.txt");
                    ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
                    oos3.writeObject(npcs);
                    oos3.close();
                    break;
                case 4:
                    
                    break;
                case 5:
                    System.out.println("");
                    dontQuit = false;
                    break;
                default:
                    System.out.println("\nFeature not yet implemented.");
            }//end switch
        } while (dontQuit); //end do while

        for (PlayerChar pc : pcs) {
            pc.display();
            System.out.println("");
        }
        for (Monster mon : monsters) {
            mon.display();
            System.out.println("");
        }
        for (NPC npc : npcs) {
            npc.display();
            System.out.println("");
        }
        
        pcs = combat(pcs, monsters, npcs);
        
        for (PlayerChar pc : pcs) {
            pc.display();
            System.out.println("");
        }            
    }
    
    //PCEntry 
    //adds members to the pcs ArrayList
    public static ArrayList<PlayerChar> PCEntry(ArrayList<PlayerChar> playerCharacters) {
        PlayerChar newPC = new PlayerChar();
        newPC.readIn();
        playerCharacters.add(newPC);
        return playerCharacters;
    }//end PCEntry

    //monsterEntry 
    //adds members to the monsters ArrayList
    public static ArrayList<Monster> monsterEntry(ArrayList<Monster> monsters) {
        Monster newMonster = new Monster();
        newMonster.readIn();
        monsters.add(newMonster);
        return monsters;
    }//end monsterEntry

    //NPCEntryadds 
    //members to the npcs ArrayList
    public static ArrayList<NPC> NPCEntry(ArrayList<NPC> npcs) {
        NPC newNPC = new NPC();
        newNPC.readIn();
        npcs.add(newNPC);
        return npcs;
    }//end NPCEntry

    //PCRemove 
    //deletes a PlayerChar from the pcs ArrayList
    public static ArrayList<PlayerChar> PCRemove(ArrayList<PlayerChar> playerCharacters) {
        Scanner kb = new Scanner(System.in);
        System.out.println("\nEnter the name of the Player Character you want to remove.");
        String name = kb.nextLine();
        for (PlayerChar p : playerCharacters) {
            if (p.getName().equals(name)) {
                System.out.println(name + " found at index " + playerCharacters.indexOf(p));
                playerCharacters.remove(p);
                System.out.println(name + " was removed from the Database.");
                continue;
            }//end if
        }// end for
        return playerCharacters;
    }

    //monsterRemove
    //deletes a Monster from the monsters ArrayList
    public static ArrayList<Monster> monsterRemove(ArrayList<Monster> monsters) {
        Scanner kb = new Scanner(System.in);
        System.out.println("\nEnter the name of the monster you want to remove.");
        String name = kb.nextLine();
        for (int i = 0; i < monsters.size(); i++) {
            final Monster e = monsters.get(i);
            if (e.getName().equals(name)) {
                System.out.println(name + " found at index " + i);
                monsters.remove(i);
                System.out.println(name + " was removed from the Database.");
            }//end if
        }// end for
        return monsters;
    }

    //NPCRemove 
    //deletes an NPC from the npcs ArrayList
    public static ArrayList<NPC> NPCRemove(ArrayList<NPC> npcs) {
        Scanner kb = new Scanner(System.in);
        System.out.println("\nEnter the name of the NPC you want to remove.");
        String name = kb.nextLine();
        for (int i = 0; i < npcs.size(); i++) {
            final NPC n = npcs.get(i);
            if (n.getName().equals(name)) {
                System.out.println(name + " found at index " + i);
                npcs.remove(i);
                System.out.println(name + " was removed from the Database.");
            }//end if
        }// end for
        return npcs;
    }

    //saveDataCheck 
    /*looks to see if the save files and destination folders exist.
    If they are not found, the files are created.*/
    public static boolean saveDataCheck() throws IOException {
        File tempThingy = new File("C:/Users/Public/Documents/DMToolkit");
        boolean created = tempThingy.mkdirs();
        if (created == false) {
            System.out.println("Directory found!");
        } else {
            System.out.println("No data folder found. Creating directory...");
        }
        File fPC = new File("C:/Users/Public/Documents/DMToolkit/PCList.txt");
        created = fPC.createNewFile();
        if (created == false) {
            System.out.println("Data found!");
        } else {
            System.out.println("No data found. Creating save files...");
        }
        File fMonster = new File("C:/Users/Public/Documents/DMToolkit/MonsterList.txt");
        created = fMonster.createNewFile();
        if (created == false) {
            System.out.println("Data found!");
        } else {
            System.out.println("No data found. Creating save files...");
        }
        File fNPC = new File("C:/Users/Public/Documents/DMToolkit/NPCList.txt");
        created = fNPC.createNewFile();
        if (created == false) {
            System.out.println("Data found!");
        } else {
            System.out.println("No data found. Creating save files...");            
        }
        
        return created;
    }
    
    //mainMenu 
    //returns an int which dictates an action in the run loop of the main
    public static int mainMenu() {
        Scanner kb = new Scanner(System.in);
        System.out.println("\nWhat would you like to do?\n[1] Add Entities to Database"
                + "\n[2] Remove Entities from Database\n[3] Save Data"
                + "\n[4] Not yet implemented\n[5] Quit");
        switch (kb.nextLine().toUpperCase()) {
            case "1":
                System.out.println("\nWhat would you like to add to the Database?"
                        + "\n[1] Player Character\n[2] Monster\n[3] NPC");
                switch (kb.nextLine().toUpperCase()) {
                    case "1":
                        return 11;
                    case "2":
                        return 12;
                    case "3":
                        return 13;
                }
                break;
            case "2":
                System.out.println("\nWhat would you like to remove from the Database?"
                        + "\n[1] PlayerChar\n[2] Monster\n[3] NPC");
                switch (kb.nextLine().toUpperCase()) {
                    case "1":
                        return 21;
                    case "2":
                        return 22;
                    case "3":
                        return 23;
                }
                break;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
        }//end switch
        return 0;
    }
    
    //combat 
    //handles all turn based combat functions
    public static ArrayList<PlayerChar> combat(ArrayList<PlayerChar> pcs, 
            ArrayList<Monster> monsters, ArrayList<NPC> npcs) {
        
        /*Need to create instances of the players, instances of the NPCs, and 
        instances of the monsters. There may be multiple instances of a single
        monster.
        */
        
        @SuppressWarnings("UnusedAssignment")
        List<Entity> theList = new ArrayList<>();
        
        theList = makeCombatList(pcs, monsters, npcs);
        
        for (int i = 0; i < theList.size(); i++) {
            if (theList.get(i) instanceof PlayerChar) {
                ((PlayerChar) theList.get(i)).setInit(rollInit());
            } else if(theList.get(i) instanceof Monster){
                ((Monster) theList.get(i)).setInit(rollInit());
            } else if (theList.get(i) instanceof NPC) {
                ((NPC) theList.get(i)).setInit(rollInit());
            }    
        }//end for loop
        
        Collections.sort(theList, new InitiativeComparator());
        
        for (int i = 0; i < theList.size(); i++) {
            if (theList.get(i) instanceof PlayerChar) {
                System.out.println(((PlayerChar) theList.get(i)).getName() 
                + " rolled a " + ((PlayerChar) theList.get(i)).getInit());
            } else if (theList.get(i) instanceof Monster) {
                System.out.println(((Monster) theList.get(i)).getName() 
                + " rolled a " + ((Monster) theList.get(i)).getInit());
            } else if (theList.get(i) instanceof NPC) {
                System.out.println(((NPC) theList.get(i)).getName() 
                + " rolled a " + ((NPC) theList.get(i)).getInit());
            }
        }
        System.out.println("");
        
        
        
        return pcs;
    }
    
    //makeCombatList method
    public static ArrayList<Entity> makeCombatList(ArrayList<PlayerChar> 
                            pcs, ArrayList<Monster> monsters, ArrayList<NPC> npcs) {
        
        Scanner kb = new Scanner(System.in);
        ArrayList<Entity> theList = new ArrayList<>();
        boolean dontQuit = true;
        String name;
        do {
            System.out.println("Who is participating in combat?"
                    + "\n[1] Player Character\n[2] Monster\n[3] NPC");
            switch(kb.nextLine()) {
                
                case "1":
                    System.out.println("Enter the name of the PC you want to add.");
                    name = kb.nextLine();
                    for (int i = 0; i < pcs.size(); i++) {
                        final PlayerChar p = pcs.get(i);
                        if (p.getName().equals(name)) {
                            theList.add(pcs.get(i));
                        }//end if
                    }// end for
                    System.out.println("Would you like to add another entity? Y/N");
                    switch (kb.nextLine().toUpperCase()) {
                        case "Y":
                            break;
                        case "N":
                            dontQuit = false;
                            break;
                    }//end nested switch
                    break;
                    
                case "2":
                    System.out.println("Enter the name of the monster you want to add.");
                    name = kb.nextLine();
                    for (int i = 0; i < monsters.size(); i++) {
                        final Monster e = monsters.get(i);
                        if (e.getName().equals(name)) {
                            theList.add(monsters.get(i));
                        }//end if
                    }// end for
                    System.out.println("Would you like to add another entity? Y/N");
                    switch (kb.nextLine().toUpperCase()) {
                        case "Y":
                            break;
                        case "N":
                            dontQuit = false;
                            break;
                    }//end nested switch
                    break;
                    
                case "3":
                    System.out.println("Enter the name of the NPC you want to add.");
                    name = kb.nextLine();
                    for (int i = 0; i < npcs.size(); i++) {
                        final NPC n = npcs.get(i);
                        if (n.getName().equals(name)) {
                            theList.add(npcs.get(i));
                        }//end if
                    }// end for
                    System.out.println("Would you like to add another entity? Y/N");
                    switch (kb.nextLine().toUpperCase()) {
                        case "Y":
                            break;
                        case "N":
                            dontQuit = false;
                            break;
                    }//end nested switch
                    break;
                    
            }//end switch
        } while (dontQuit);
        return theList;
    }
    
    public static int rollInit() {
        Random r = new Random();
        int init = r.nextInt(21);
        
        return init;
    }
}
