package dmtoolkit;

import java.util.Scanner;
import java.io.Serializable;

public class Monster extends Entity implements Serializable
{
    
	private static final long serialVersionUID = 1L;
	
	//Basic Monster fields
    private String name;
    private String type;
    private String castAbil;
    private String alignment;
    private String size;
    private double CR;
    private int exp;
    
    //Monster Base Stats
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    
    //Monster Stat Modifiers
    private int STR = modCalc(strength);
    private int DEX = modCalc(dexterity);
    private int CON = modCalc(constitution);
    private int INT = modCalc(intelligence);
    private int WIS = modCalc(wisdom);
    private int CHA = modCalc(charisma);
    
    //PC Armor Class and Hit Points
    private int armorClass;
    private int maxHP;
    private int currHP;
    private int tempHP;
    private int init;
    
    //No-Arg Constructor
    public Monster() {
        name = ""; 
        type = ""; 
        castAbil = "";
        alignment = "";
        size = "";
        CR = 1.0;
        exp = 0;
        
        strength = 1; 
        dexterity = 1; 
        constitution = 1; 
        intelligence = 1; 
        wisdom = 1; 
        charisma = 1;
        
        armorClass = 0;
        maxHP = 0;
        currHP = 0;
        tempHP = 0;
        init = 0;
        
    }
    
        
    /*public PlayerChar(String name, String charClass, int level, int exp,
            int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, int armorClass) {
        
    }*/
    
    /*Mutator methods
    NOTE: mutators have all been written on one line to save space */
    public void setName(String name) {this.name = name;}
    public void setType(String type) {this.type = type;}
    public void setCastAbil(String castAbil) {this.castAbil = castAbil;}
    public void setAlignment(String alignment) {this.alignment = alignment;}
    public void setSize(String size) {this.size = size;}
    public void setCR(double CR) {this.CR = CR;}
    public void setExp(int exp) {this.exp = exp;}
    
    public void setStrength(int strength) {this.strength = strength;}
    public void setDexterity(int dexterity) {this.dexterity = dexterity;}
    public void setConstitution(int constitution) {this.constitution = constitution;}
    public void setIntelligence(int intelligence) {this.intelligence = intelligence;}
    public void setWisdom(int wisdom) {this.wisdom = wisdom;}
    public void setCharisma(int charisma) {this.charisma = charisma;}
    
    public void setAC(int armorClass) {this.armorClass = armorClass;}
    public void setMaxHP(int maxHP) {this.maxHP = maxHP;}
    public void setCurrHP(int currHP) {this.currHP = currHP;}
    public void setTempHP(int tempHP) {this.tempHP = tempHP;}
    public void setInit(int init) {this.init = init;}
    
    /*Accessor methods
    NOTE: accessors have all been written on one line to save space*/
    public String getName() {return name;}
    public String getType() {return type;}
    public String getCastAbil() {return castAbil;}
    public String getAlignment() {return alignment;}
    public String getSize() {return size;}
    public double getCR() {return CR;}
    public int getExp() {return exp;}
    
    public int getStrength() {return strength;}
    public int getDexterity() {return dexterity;}
    public int getConstitution() {return constitution;}
    public int getIntelligence() {return intelligence;}
    public int getWisdom() {return wisdom;}
    public int getCharisma() {return charisma;}
    
    public int getSTR() {return STR;}
    public int getDEX() {return DEX;}
    public int getCON() {return CON;}
    public int getINT() {return INT;}
    public int getWIS() {return WIS;}
    public int getCHA() {return CHA;}
    
    public int getAC() {return armorClass;}
    public int getMaxHP() {return maxHP;}
    public int getCurrHP() {return currHP;}
    public int getTempHP() {return tempHP;}
    public int getInit() {return init;}
    
    //abilityMod Calculator
    public int modCalc(int score) {
        Double abScore = Double.valueOf(score);
        Double abilityMod = Math.floor((abScore - 10)/2);
        int abMod = abilityMod.intValue(); 
        
        return abMod;
    }
    
    //CR fraction parser
    public double fractionParse(String fraction) {
                
        if (fraction.contains("/")) {
            String[] frac = fraction.split("/");
            return Double.parseDouble(frac[0]) / Double.parseDouble(frac[1]); 
        } else {
            return Double.parseDouble(fraction);
        }
    }
    
    //readIn method
    //NOTE: requests and newline consumptions on one line to save space
    @Override
    public void readIn() {
        Scanner kb = new Scanner(System.in);
        
        //Character Descriptors
        System.out.print("Name: "); setName(kb.nextLine());
        System.out.print("Type: "); setType(kb.nextLine());
        System.out.print("Spellcasting Ability: "); setCastAbil(kb.nextLine());
        System.out.print("Alignment: "); setAlignment(kb.nextLine());
        System.out.print("Size: "); setSize(kb.nextLine());
        System.out.print("CR: "); setCR(fractionParse(kb.nextLine()));
        System.out.print("Experience: "); setExp(kb.nextInt()); kb.nextLine();
        
        //Ability Scores
        System.out.print("Strength: "); setStrength(kb.nextInt()); kb.nextLine();
        System.out.print("Dexterity: "); setDexterity(kb.nextInt()); kb.nextLine();
        System.out.print("Constitution: "); setConstitution(kb.nextInt()); kb.nextLine();
        System.out.print("Intelligence: "); setIntelligence(kb.nextInt()); kb.nextLine();
        System.out.print("Wisdom: "); setWisdom(kb.nextInt()); kb.nextLine();
        System.out.print("Charisma: "); setCharisma(kb.nextInt()); kb.nextLine();
        
        //Character Extras
        System.out.print("Armor Class: "); setAC(kb.nextInt()); kb.nextLine();
        System.out.print("Max HP: "); setMaxHP(kb.nextInt()); kb.nextLine();
        System.out.print("Current HP: "); setCurrHP(kb.nextInt()); kb.nextLine();
        setTempHP(0);
    }
    
    //Display Character Information
    @Override
    public void display() {
        System.out.println("Name: " + getName());
        System.out.println("Type: " + getType());
        System.out.println("Spellcasting Ability: " + getCastAbil());
        System.out.println("Alignment: " + getAlignment());
        System.out.println("Size: " + getSize());
        System.out.println("CR: " + getCR());
        System.out.println("Experience: " + getExp());
        
        System.out.println("Strength: " + getStrength());
        System.out.println("Dexterity: " + getDexterity());
        System.out.println("Constitution: " + getConstitution());
        System.out.println("Intelligence: " + getIntelligence());
        System.out.println("Wisdom: " + getWisdom());
        System.out.println("Charisma: " + getCharisma());
        
        System.out.println("AC: " + getAC());
        System.out.println("Max HP: " + getMaxHP());
        System.out.println("Current HP: " + getCurrHP());
        System.out.println("Temporary HP: " + getTempHP());
        System.out.println("\n");
    }
}
