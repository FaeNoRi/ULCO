public class TestArgu {

		public static void main(String[] args) {
			
			if(args.length != 3) {
				System.out.println("Il n'y a pas 3 arguments !");
			}else if(args.length == 3){
				System.out.println("Il y a 3 arguments !");
			}
		}
}
