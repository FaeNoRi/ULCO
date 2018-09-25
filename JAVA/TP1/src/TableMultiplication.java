import java.util.Scanner;

public class TableMultiplication {

	public static void main(String[] args) {

		int i = 0, j = 0, tableSize ;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Entrez un chiffre : ");
		tableSize  = scanner.nextInt();
		
		AffichageTable(tableSize);
		
	}

	public static void AffichageTable(int tableSize) {
		System.out.format("   - ");
		for (int i = 0; i <= tableSize; i++) {
			System.out.format("%4d", i);
			
		}
		
		System.out.println();
		System.out.println("_______________________________________________");
		
		for (int i = 0; i <= tableSize; i++) {
			System.out.format("%4d|", i);

			for (int j = 0; j <= tableSize; j++) {
				System.out.format("%4d", i * j);
			}
		System.out.println();
		}

	}

}