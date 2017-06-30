
public class Berserker extends Character{
	/*less health and armor but more attack. Applies bleed upon crit which reduces the target's hp
	 * by a percent every turn after Berserker attacks.
	 * Can heal by consuming the target's bleed stacks.*/
	
	//constructs a Berserker with his own stats
	public Berserker(double maxHp, double str, double armor){
		super();
		this.setTitle("Berserker");
		this.level = 1;
		this.maxHealth = maxHp * 0.65;
		this.currentHealth = this.maxHealth;
		this.roundHealth = this.maxHealth; 
		this.strength = str * 1.501;
		this.roundStrength = this.strength;
		this.critChance = 20;
		this.critMulti = 1.5;
		this.armor = armor * 0.85;
		this.reducedArmor = this.armor;
		this.roundArmor = this.armor;
		this.armorReductionPercent = (int)(100 * (1 - (this.reducedArmor / this.armor)));
		this.damageMulti = 100 / (100 + this.reducedArmor); 
		this.damageReductionPercent = 100 - (int)(this.damageMulti * 100);
		this.bleedStack = 0;
	}
	//Warden's own attack method. Applies a bleed stack upon crit
	public double attack(Character target){
		double damage = this.strength;
		//armor calculation
		double postArmorDamage = target.damageReduce(damage);
		//critical hit calculation
		if(this.criticalHit(this.critChance)){
			postArmorDamage *= this.critMulti;
			System.out.println(this.name + " Made a CRITICAL HIT!! " + this.critMulti + "x damage!");
			target.bleedStack++;
		}
		//applying damage to target
		target.currentHealth -= postArmorDamage;
		//rounding health for display
		int tempHealth = (int)(target.currentHealth * 10);
		target.roundHealth = (double)tempHealth / 10;
		//rounding postArmorDamage for display
		int tempDealt = (int)(postArmorDamage * 10);
		double roundDealt = (double)tempDealt / 10;
		//damage log
		System.out.println(this.name + " dealt " + roundDealt + " damage to " + target.name + "!");
		this.applyBleed(target);
		return postArmorDamage;
	}
	//calculates damage target takes every turn based on their health and # of bleed stacks they have
	public void applyBleed(Character target){
		if(target.bleedStack != 0){
			target.currentHealth -= (target.bleedStack * (target.maxHealth * 0.05));
			System.out.println(target.name + " took " + (target.bleedStack * (target.maxHealth * 0.05)) + " bleed damage!");
		}
	}
}
