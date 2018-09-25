
public class Ascii {

	public static void main (String args[]) {
		 int i;
		 char j;
		 
		 int min = 33;
		 int max = 127;
		 
		 for(i=min;i < max;i++) {
			 j = (char) i;
			 System.out.println(i +" -> "+ j);
		 }
	}
}
