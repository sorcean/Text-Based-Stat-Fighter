import java.util.Scanner;
import java.util.Random;

public class Arena extends Character{

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();

		//CAN ONLY TAKE 2 PLAYERS!

		//Creates player1
		Character player1 = new Tank(1000, 100, 60);
		//Creates player2
		Character player2 = new Berserker(1000, 100, 60);

		//Announces the fighters and their health respectively
		System.out.println(player1.name + " vs. " + player2.name);
		System.out.println(player1.currentHealth + " hp vs. " + player2.currentHealth + " hp");
		//randomizes who goes first
		int firstTurn = rand.nextInt(2);
		if(firstTurn == 0){
			System.out.println("\n" + player1.name + " attacks first!");
		}else if(firstTurn == 1){
			System.out.println("\n" + player2.name + " attacks first!");
		}
		/*gameplay loop. Two massive if statements that are dependent on who goes first
		 * the parts with while(turnAgain) affect if an option ends the current player's turn or not
		 * switch statements are the player's options*/
		while(player1.isAlive() && player2.isAlive()){
			boolean turnAgain = true;
			if(firstTurn == 0){
				while(turnAgain){
					System.out.println("\nWhat you like to do, " + player1.name + "? (" + player1.roundHealth + " hp)\n1. Attack " + player2.name + " (" + player2.roundHealth + " hp) (Commit)\n2. " + player1.name + "'s Stats\n3. " + player2.name + "'s Stats\n4. Lifesteal (Commit, Berserker only)\n");
					switch(in.nextInt()){
					case 1: 
						turnAgain = false;
						player1.attack(player2);
						break;
					case 2:
						turnAgain = true;
						player1.showStats();
						break;
					case 3: 
						turnAgain = true;
						player2.showStats();
						break;
					case 4:
						if(player1.title == "Berserker" && player1.canLifeSteal(player2)){
							turnAgain = false;
							player1.lifeSteal(player2);
							break;
						}else if(player1.title == "Berserker" && !player1.canLifeSteal(player2)){
							turnAgain = true;
							System.out.println("There are no bleed stacks for " + player1.name + " to consume!");
							break;
						}else{
							turnAgain = true;
							System.out.println("That is not a valid option.");
							break;
						}
					default:
						turnAgain = true;
						System.out.println("That is not a valid option.");
						break;
					}
				}
				player1.displayHealth();
				player2.displayHealth();
				turnAgain = true;
				while(turnAgain){
					System.out.println("\nWhat you like to do, " + player2.name + "? (" + player2.roundHealth + " hp)\n1. Attack " + player1.name + " (" + player1.roundHealth + " hp) (Commit)\n2. " + player1.name + "'s Stats\n3. " + player2.name + "'s Stats\n4. Lifesteal (Commit, Berserker only)\n");
					switch(in.nextInt()){
					case 1: 
						turnAgain = false;
						player2.attack(player1);
						break;
					case 2:
						turnAgain = true;
						player1.showStats();
						break;
					case 3:
						turnAgain = true;
						player2.showStats();
						break;
					case 4:
						if(player2.title == "Berserker" && player2.canLifeSteal(player1)){
							turnAgain = false;
							player2.lifeSteal(player1);
							break;
						}else if(player2.title == "Berserker" && !player2.canLifeSteal(player1)){
							turnAgain = true;
							System.out.println("There are no bleed stacks for " + player2.name + " to consume!");
							break;
						}else{
							turnAgain = true;
							System.out.println("That is not a valid option.");
							break;
						}
					default:
						turnAgain = true;
						System.out.println("That is not a valid option.");
						break;
					}
				}
				player1.displayHealth();
				player2.displayHealth();
				turnAgain = true;
			}else if(firstTurn == 1){
				while(turnAgain){
					System.out.println("\nWhat you like to do, " + player2.name + "? (" + player2.roundHealth + " hp)\n1. Attack " + player1.name + " (" + player1.roundHealth + " hp) (Commit)\n2. " + player1.name + "'s Stats\n3. " + player2.name + "'s Stats\n4. Lifesteal (Commit, Berserker only)\n");
					switch(in.nextInt()){
					case 1: 
						turnAgain = false;
						player2.attack(player1);
						break;
					case 2:
						turnAgain = true;
						player1.showStats();
						break;
					case 3:
						turnAgain = true;
						player2.showStats();
						break;
					case 4:
						if(player2.title == "Berserker" && player2.canLifeSteal(player1)){
							turnAgain = false;
							player2.lifeSteal(player1);
							break;
						}else if(player2.title == "Berserker" && !player2.canLifeSteal(player1)){
							turnAgain = true;
							System.out.println("There are no bleed stacks for " + player2.name + " to consume!");
							break;
						}else{
							turnAgain = true;
							System.out.println("That is not a valid option.");
							break;
						}
					default:
						turnAgain = true;
						System.out.println("That is not a valid option.");
						break;
					}
				}
				player1.displayHealth();
				player2.displayHealth();
				turnAgain = true;
				while(turnAgain){
					System.out.println("\nWhat you like to do, " + player1.name + "? (" + player1.roundHealth + " hp)\n1. Attack " + player2.name + " (" + player2.roundHealth + " hp) (Commit)\n2. " + player1.name + "'s Stats\n3. " + player2.name + "'s Stats\n4. Lifesteal (Commit, Berserker only)\n");
					switch(in.nextInt()){
					case 1: 
						turnAgain = false;
						player1.attack(player2);
						break;
					case 2:
						turnAgain = true;
						player1.showStats();
						break;
					case 3:
						turnAgain = true;
						player2.showStats();
						break;
					case 4:
						if(player1.title == "Berserker" && player1.canLifeSteal(player2)){
							turnAgain = false;
							player1.lifeSteal(player2);
							break;
						}else if(player1.title == "Berserker" && !player1.canLifeSteal(player2)){
							turnAgain = true;
							System.out.println("There are no bleed stacks for " + player1.name + " to consume!");
							break;
						}else{
							turnAgain = true;
							System.out.println("That is not a valid option.");
							break;
						}
					default:
						turnAgain = true;
						System.out.println("That is not a valid option.");
						break;
					}
				}
				player1.displayHealth();
				player2.displayHealth();
				turnAgain = true;
			}
		}
		if(player1.isAlive()){
			System.out.println(player1.name + " wins!");
		}
		else if (player2.isAlive()){
			System.out.println(player2.name + " wins!");
		}
		else{
			System.out.println("It's a draw!");
		}
	}
	/*public void displayHealth(){
		System.out.println(player1.name + ": " + player1.roundHealth + " hp");
		System.out.println(player2.name + ": " + player2.roundHealth + " hp");
	}*/
}
