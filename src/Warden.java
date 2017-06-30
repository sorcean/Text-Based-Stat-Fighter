
public class Warden extends Character{
	/*Extra health and armor but not much strength. Instead of critting he can shield bash
	 * which reduces the target's armor permanently and deals damage equal to half of his strength*/
	public double bashChance;
	//constructs a Warden with his own stats
	public Warden(double maxHp, double str, double armor){
		super();
		this.setTitle("Warden");
		this.level = 1;
		this.maxHealth = maxHp * 1.2;
		this.currentHealth = this.maxHealth;
		this.roundHealth = this.maxHealth; 
		this.strength = str * 0.82;
		this.roundStrength = this.strength;
		this.critChance = 0;
		this.critMulti = 1;
		this.armor = armor * 1.45;
		this.reducedArmor = this.armor;
		this.roundArmor = this.armor;
		this.armorReductionPercent = (int)(100 * (1 - (this.reducedArmor / this.armor)));
		this.damageMulti = 100 / (100 + this.reducedArmor); 
		this.damageReductionPercent = 100 - (int)(this.damageMulti * 100);
		this.bleedStack = 0;
		//unique to Warden
		this.bashChance = 25;
	}
	//Warden's own attack method. Includes a chance to shield bash
	public double attack(Character target){
		double damage = this.strength;
		//armor calculation
		double postArmorDamage = target.damageReduce(damage);
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
		//check for shield bash
		if(shieldBashTrigger(this.bashChance)){
			this.shieldBash(target);
			//applying second attack damage at half power using new armor
			postArmorDamage = target.damageReduce(damage);
			target.currentHealth -= postArmorDamage * 0.5;
			//rounding target's new health for display
			tempHealth = (int)(target.currentHealth * 10);
			target.roundHealth = (double)tempHealth / 10;
			//rounding damage dealt to target for display
			tempDealt = (int)(postArmorDamage * 10);
			roundDealt = (double)tempDealt / 10;
			System.out.println(this.name + " attacks again for " + roundDealt * 0.5 + " damage to " + target.name + "!");
		}
		return postArmorDamage;
	}
	//shieldBash reduces the target's armor by 20% each time it is called
	public void shieldBash(Character target){
		//this if statement doesn't allow the target's armor to be reduced below 40% of it's original amount.
		if(target.reducedArmor >= target.armor * 0.4){
			target.reducedArmor -= target.armor * 0.211;
			if(target.reducedArmor < target.armor * 0.4){
				target.reducedArmor = target.armor * 0.4;
			}
			target.armorReductionPercent = (int)(100 * (1 - (target.reducedArmor / target.armor)));
			System.out.println("SHIELD BASH!! " + target.name + "'s armor is reduced by " + target.armorReductionPercent + "%!" );
			//recalculating and rounding target.damageReductionPercent for display
			target.damageMulti = 100 / (100 + target.reducedArmor); 
			target.damageReductionPercent = 100 - (int)(target.damageMulti * 100);
		}else{
			target.reducedArmor = target.armor * 0.4;
			//recalculating and rounding target.damageReductionPercent for display
			target.damageMulti = 100 / (100 + target.reducedArmor); 
			target.damageReductionPercent = 100 - (int)(target.damageMulti * 100);
		}
	}
	//determines if the shieldBash triggered based on a percent chance (without percent sign)
	public boolean shieldBashTrigger(double chance){
		double temp = chance/100;
		double d = Math.random();
		if(d < temp){
			return true;
		}
		return false;
	}
}
