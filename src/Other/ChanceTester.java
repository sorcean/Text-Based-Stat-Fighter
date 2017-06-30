package Other;

public class ChanceTester {

	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			if(chance(1)){
				System.out.println("TRUE");
			}else{
				System.out.println("false");
			}
		}
	}
	public static boolean chance(double chance){
		double temp = chance/100;
		double d = Math.random();
		if(d < temp){
			return true;
		}
		return false;
	}
}
