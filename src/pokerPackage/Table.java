package pokerPackage;

public class Table {

	public static void main(String[] args) {
	
		Hand firstHand = new Hand("TD 7C 7H 7C 7D");
		Hand secondHand = new Hand("6H 3H 4H 5H 2H");
		
		System.out.println(firstHand.kind());
		System.out.println(secondHand.kind());
		System.out.println(firstHand.compareTo(secondHand));
		
	}

}
