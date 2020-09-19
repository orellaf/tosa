package fr.lta.tosa;
import java.util.*;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {

		Scanner sc = new Scanner(System.in);
		
		
		String binary = sc.nextLine();
		
		sc.close();
		
		String temp =  "";
		boolean stop = false;
		while(!stop){
    		temp = binary.replace("000","00");
    		temp =temp.replace("111","1");
		
		
    		String operation3= temp.replace("10","1");
    		if (operation3.equals(temp)) {
    		    stop = true;
    		}
    		temp = operation3;
        }
		
		System.out.println(temp);
	}
}