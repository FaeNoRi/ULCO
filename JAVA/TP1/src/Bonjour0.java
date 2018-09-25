import java.util.Scanner;

public class Bonjour0 {
	public static void main(String[] args) {
		final String FRANCAIS = "BONJOUR !";
		final String ANGLAIS = "HELLO !";
		int lu;
		String message;
		Scanner scanner = new Scanner(System.in);
		System.out.print("(1) Français / (AUTRE) Anglais > ");
		lu = scanner.nextInt();
		if(lu == 1) {
			message = FRANCAIS;
		}else {
			message = ANGLAIS;
		}
		System.out.println(message);
	}
}