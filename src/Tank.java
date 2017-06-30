
public class Tank extends Character{
	/*Has a massive amount of armor, extra strength, and not much health
	 * I really want to give him a special ability or something in the future*/
	
	//constructs a Tank with his own stats
	public Tank(double maxHp, double str, double armor){
		super();
		this.setTitle("Tank");
		this.level = 1;
		this.maxHealth = maxHp * 0.8;
		this.currentHealth = this.maxHealth;
		this.roundHealth = this.maxHealth; 
		this.strength = str * 1.15;
		this.roundStrength = this.strength;
		this.critChance = 15;
		this.critMulti = 2;
		this.armor = armor * 3;
		this.reducedArmor = this.armor;
		this.roundArmor = this.armor;
		this.armorReductionPercent = (int)(100 * (1 - (this.reducedArmor / this.armor)));
		this.damageMulti = 100 / (100 + this.reducedArmor); 
		this.damageReductionPercent = 100 - (int)(this.damageMulti * 100);
		this.bleedStack = 0;
	}
	//so far does not have his own unique attack method 
}
