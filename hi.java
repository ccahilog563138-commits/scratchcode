package cahilog;
import java.util.*;
public class hi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
	
//		int [][] array = new int [4][3];
//		for (int x = 0; x < array.length; x++) {
//			for (int y=0; y < array[x].length; y++) {
//				System.out.print(array[x][y] + " ");
//			}
//			System.out.println("");
//		}
		

 int num [][]= {{120,250,789,7},{58,79,31,87},{8,1,2,3},{99,98,97,96}};
 for (int x = 0; x < num.length; x++) {
		for (int y=0; y < num[x].length; y++) {
			System.out.print(num[x][y] + " ");
		}
		System.out.println("");
	}
 
 for (int x = 0; x < num.length; x++) {
		for (int y=0; y < num[x].length; y++) {
			if (num[x][y]== 1) {
				System.out.println("numbers 1 is found at indices: " + x + " " + y);
			    num [x][y]= 50;
			}
 
		 }
 }
 
 for (int x = 0; x < num.length; x++) {
		for (int y=0; y < num[x].length; y++) {
			System.out.print(num[x][y]+ "\t");
		}
		System.out.println();
	
}
 
   for (int y= 0; y < num[2].length; y++) {
	   num[2][y]= 0;
   }
     System.out.println();
     for(int x =0; x<num.length; x++) {
    	 for (int y=0; y < num[x].length; y++) {
    		 System.out.print(num [x] [y] + "\t");
    	 }
    	 System.out.println();
     }
 }
}