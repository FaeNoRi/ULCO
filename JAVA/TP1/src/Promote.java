public class Promote {
	public static void main(String args[]) {
		
		byte b = 42;
		char c = 'a';
		short s = 1024;
		int i = 50000;
		double f = 5.67;
		double d = .1234;
		double resultat = (f * b) + (i / c) - (d * s);
		
		System.out.print((f * b) + " + " + (i / c) + " - " + (d * s));
		System.out.println(" = " + resultat);
		
		byte b2 = 10;
		int b3 = b2 * b;
		
		System.out.println("b = " + b);
		System.out.println("b2 = " + b2);
		System.out.println("b3 = " + b3);
	}
}