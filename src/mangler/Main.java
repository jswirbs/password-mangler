package mangler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Simple password mangler written to crack passwords for a pentest on a client.
 * There were a few recognizable patterns in a couple of passwords I obtained through
 * phishing and general OSINT, so I created this to crack other employees passwords
 * assuming some would also follow a similar pattern of a word surrounded by numbers
 * and one or two symbols. This helped create password lists that were used to 
 * successfully crack additional credentials.
 * 
 * Takes in a word, a max number to use, and as many independent symbols as you want
 * and then outputs all possible permutations to a txt file.
 * 
 * 
 * @author justinswirbul
 *
 */


public class Main {
	
    private static String FILENAME = "/Users/justinswirbul/Desktop/password_list.txt";
	
	    public static void main(String [] args) {
	        String input = "";
	        int max_n = 0;
	        int num_symbols = 0;
		
	        // Takes in user input and eventually writes all password permutations to a txt file
	        Scanner in = new Scanner(System.in);
	        // Word to mangle
	        System.out.print("Initial word to mangle: ");
	        input = in.next();
	        // Max number to use in password permutations
	        System.out.print("Maximum number to use (non-inclusive) (0 = no numbers): ");
	        max_n = in.nextInt();
	        // Number of symbols to use (symbols themselves are input next)
	        System.out.print("Number of symbols to use: ");
	        num_symbols = in.nextInt();
	        String [] symbols = new String[num_symbols];
	        // Individually input each symbol to use (can be any char)
	        for (int x = 0; x < num_symbols; x++) {
	            System.out.print("Symbol " + (x+1) + ": ");
	            symbols[x] = in.next();
	        }
	        // Name of txt file to output password permutations to (if it doesn't exist it will create a new output file)
	        System.out.print("Name of output file (with .txt extension): ");
	        FILENAME = "/Users/justinswirbul/Desktop/" + in.next();
	        in.close();
		
	        
	        // Create arr of all possible password permutations
	        
	        int len = (symbols.length*max_n*3) + (symbols.length*symbols.length*max_n*3);
	        if (max_n == 0) {
	            len = 3*symbols.length*symbols.length;
	        }
	        String [] arr = new String[len];
	        int index = 0;
		
	        if (max_n == 0) {
	            for (String s : symbols) {
	                arr[index++] = input + s;
	                arr[index++] = s + input;
	                
	                for (String s2 : symbols) {
	                    arr[index++] = input + s + s2;
	                    arr[index++] = s + input + s2;
	                    arr[index++] = s + s2 + input;
	                }
	            }
	        } else {
	            for (int n = 0; n < max_n; n++) {
	                for (String s : symbols) {
	                    arr[index++] = input + String.valueOf(n) + s;
	                    arr[index++] = input + s + String.valueOf(n);
	                    arr[index++] = s + input + String.valueOf(n);
	                    
	                    for (String s2 : symbols) {
	                        arr[index++] = input + s + String.valueOf(n) + s2;
	                        arr[index++] = s + input + s2 + String.valueOf(n);
	                        arr[index++] = s + input + String.valueOf(n) + s2;
	                    }
	                }		
	            }
	        }
		
		
	        // write arr to output file
		
	        BufferedWriter bw = null;
	        FileWriter fw = null;

	        try {
	            File file = new File(FILENAME);
	            // if file doesn't exists, then create it
	            if (!file.exists()) {
	                file.createNewFile();
	            }

	            // true = append file
	            fw = new FileWriter(file.getAbsoluteFile(), true);
	            bw = new BufferedWriter(fw);

	            for (int x = 0; x < arr.length; x++) {
	                bw.write(arr[x] + "\n");
	            }

	            System.out.println("Done");

	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (bw != null)
	                    bw.close();
	                if (fw != null)
	                    fw.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
		
	    }

}
