
import java.util.Random;

public class Character {
	static Random rand = new Random();

	public String title;//title for the character's class (Warden, Berserker, etc.)
	public String name;//name of character (one of the random names contained inside the Character() constructor)
	public int level;//affects stat growth
	public double maxHealth;//maximum health
	public double currentHealth;//exact current health
	public double roundHealth;//current health rounded to one decimal place for display
	public double strength;//basic attack damage
	public double roundStrength;//rounded attack damage for display
	public double critChance;//percent without the percent sign
	public double critMulti;//by how much damage is multiplied upon getting a critical hit. default 2
	public double armor;//reduces damage taken
	public double reducedArmor;//armor after getting shield bashed
	public double roundArmor;//rounded reducedArmor for display
	public int armorReductionPercent;//percent armor is reduced by
	public double damageMulti;//multiplies initial damage received before applying damage
	public int damageReductionPercent;//inverse of damageMulti
	public int bleedStack;//applied onto target when Berserker crits. DOT and can be consumed by Berserker to heal

	//array of names that are applied randomly to player1 and player2
	public static String[] nameList = {"Geoff", "Steve", "Krogar", "Bob", "Steve", "Angelo", "Harry", "John", "Morgan", "Anthony", "Mallis", "Alex", "Christian", "Logan", "Josh"};
	//main constructor for Character
	public Character(){
		this.name = nameList[Arena.rand.nextInt(nameList.length)];
	}
	//
	public Character(double maxHp, int str, double armor){
		this();
		this.setTitle("Traveler");//normal characters have "Taveler" as their titles
		this.level = 1;
		this.maxHealth = maxHp;
		this.currentHealth = this.maxHealth;
		this.roundHealth = this.maxHealth; 
		this.strength = str;
		this.roundStrength = this.strength;
		this.critChance = 15;
		this.critMulti = 2;
		this.armor = armor;
		this.reducedArmor = this.armor;
		this.roundArmor = this.armor;
		this.armorReductionPercent = (int)(100 * (1 - (this.reducedArmor / this.armor)));
		this.damageMulti = 100 / (100 + this.reducedArmor); 
		this.damageReductionPercent = 100 - (int)(this.damageMulti * 100);
		this.bleedStack = 0;
	}
	/*sets a character's title to something and then combines the title with
	*the random name to create a new name*/
	public void setTitle(String title){
		this.title = title;
		this.name = title + " " + this.name;
	}
	/*Shows every stat (each one rounded for display) of a character*/
	public void showStats(){
		System.out.println(this.name + "'s stats:");
		System.out.println("Level: " + this.level);
		System.out.println("Health:" + this.roundHealth + " hp/" + this.maxHealth + " hp");
		//rounding strength for display
		int tempStrength = (int)(this.strength * 10);
		this.roundStrength = (double)tempStrength / 10;
		System.out.println("Strength: " + this.roundStrength);
		System.out.println("Crit Chance: " + this.critChance + "%");
		System.out.println("Crit Multiplier: " + this.critMulti + "x");
		//rouinding armor for display
		int tempArmor = (int)(this.reducedArmor * 10);
		this.roundArmor = (double)tempArmor / 10;
		System.out.println("Armor: " + this.roundArmor + " (This unit takes " + this.damageReductionPercent + "% reduced damage.)");
		System.out.println("Current Bleed Stacks: " + this.bleedStack);
	}
	//displays a character's rounded current health
	public void displayHealth(){
		//rounding health for display
		int tempHealth = (int)(this.currentHealth * 10);
		this.roundHealth = (double)tempHealth / 10;
		System.out.println(this.name + ": " + this.roundHealth + " hp");
	}
	/*the main way a character can attack. Takes into account armor and the attacker's strength
	 * has some Berserker-only stuff such as bleed even tho they should be in the Berserker subclass
	 * btw each subclass has its own overridden attack method with their own special effects*/
	public double attack(Character target){
		double damage = this.strength;
		//armor calculation
		double postArmorDamage = target.damageReduce(damage);
		//critical hit calculation + applying bleed stacks
		if(this.criticalHit(this.critChance) && this.title == "Berserker"){
			target.bleedStack++;
		}else if(this.criticalHit(this.critChance)){
			postArmorDamage *= this.critMulti;
			System.out.println(this.name + " Made a CRITICAL HIT!! " + this.critMulti + "x damage!");
		}
		//applying damage to target
		target.currentHealth -= postArmorDamage;
		//rounding health for display
		int tempHealth = (int)(target.currentHealth * 10);
		target.roundHealth = (double)tempHealth / 10;
		//rounding postArmorDamage for display
		int tempDealt = (int)(postArmorDamage * 10);
		double roundDealt = (double)tempDealt / 10;
		//damage log (who attacked whom and for how much)
		System.out.println(this.name + " dealt " + roundDealt + " damage to " + target.name + "!");
		return postArmorDamage;
	}
	//factors in target's armor and then returns the reduced damage. used in attack method
	public double damageReduce(double initialDamage){
		this.damageMulti = 100 / (100 + this.reducedArmor);
		double postArmorDamage = initialDamage * this.damageMulti;
		return postArmorDamage;
	}
	//enter in the percent chance for a crit (no percent sign). used in attack method
	public boolean criticalHit(double chance){
		double temp = chance/100;
		double d = Math.random();
		if(d < temp){
			return true;
		}
		return false;
	}
	/*Berserker-only. sets the target's bleed stacks to 0 and heals the attacker for a 
	 * percent of the attacker's maxHealth times the number of bleed stacks removed from the target.
	 * I really want to move this method to the Berserker sub-class but i can't find a way D:*/
	public void lifeSteal(Character target){
		double healthGained = target.bleedStack * (this.maxHealth * 0.2);
		this.currentHealth += healthGained;
		int tempHealth = (int)(healthGained * 10);
		double roundHealthGained = (double)tempHealth / 10;
		if(target.bleedStack == 1){
			System.out.println(this.name + " has consumed " + target.name + "'s " + target.bleedStack + " bleed stack to heal " + roundHealthGained + " hp!");
		}else if(target.bleedStack > 1 || target.bleedStack < 1){
			System.out.println(this.name + " has consumed " + target.name + "'s " + target.bleedStack + " bleed stacks to heal " + roundHealthGained + " hp!");
		}
		target.bleedStack = 0;
	}
	//determines if the attacker can lifesteal the target at all
	public boolean canLifeSteal(Character target){
		if(target.bleedStack > 0 && this.title == "Berserker"){
			return true;
		}else{
			return false;
		}
	}
	//returns if a player is alive or not. used to determine if the game is finished yet
	public boolean isAlive(){
		return this.currentHealth > 0;
	}
}
