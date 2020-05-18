//Written by: Auvigoo Ahmed (40128901)
//COMP 352
//Assignment 1 - Programming Question Version 1 (SLOW)
//Due Date: May 15th 2020

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
public class VersionSlow {

	//VERSION 1: SLOWER CODE
	/**
	 * This method works by fixing a character of shortStr and storing the remaining characters in another string. Then, a recursive call takes 
	 * place with these two strings as inputs. This goes on until the shortStr becomes empty, meaning that permutation now stores the permutation
	 * string at this instant. In order to print distinct permutations, a simple if statement is used by checking if the permutation at the ith character
	 * has already been printed or not with a boolean array of size 26, one for each letter of the alphabet. Therefore, the recursive case occurs only if
	 * the permutation has not been printed yet.
	 * @param shortStr	String that will generate permutations
	 * @param permutation	String of the permutation	
	 * @param longStr	String in which the permutation will be matched
	 * @param pw	Printwriter to write on file
	 * @throws FileNotFoundException
	 */
	public static void permu(String shortStr, String permutation, String longStr, PrintWriter pw) throws FileNotFoundException{
		String newShortStr, newPermutation;//inputs for recursive call
		
		if(shortStr.length() == 0) {//base case: when the input string becomes empty, then the variable "permutation" contains the valid string
			pw.println(permutation);//write the permutation on the file
			if(longStr.contains(permutation)) {
				int index = longStr.indexOf(permutation);
				pw.println("Found one match: "+permutation+" is at location "+ index);//write the index of the permutation from the longStr, if it exists
			}
		}
		else {
			//initializing boolean array to store a boolean for each letter of the alphabet
			boolean alphabets[] = new boolean[26];
			for(int i = 0; i < alphabets.length; i++) {
				alphabets[i] = true;
			}
			
			for(int i = 0; i < shortStr.length() ; i++) {
				newPermutation = permutation + shortStr.charAt(i);//adding the character at the ith index of shortStr to permutation and storing it to a new variable
				newShortStr = shortStr.substring(0, i).concat(shortStr.substring(i+1));//removing the character at the ith index of shortStr and storing it to a new variable
				int alphabetIndex = (int)shortStr.charAt(i) - (int)'a';//getting the ASCII number of the alphabet that i currently holds
				if (alphabets[alphabetIndex] == true) {//when the position of the letter in the array is True, this means that this letter has not been used before for recursion
					permu(newShortStr, newPermutation, longStr, pw);//recursively calling on the method to perform the two above operations until shortStr becomes an empty string
				}
				alphabets[alphabetIndex] = false;//making the boolean at that position false so in the next call, if i is at the same previous letter, it won't call the recursive method
				}
				
		}
	}
	
	public static void main(String[] args) {
		//strings to test speed of method
		String five = "abcde";
		String ten = "abcdefghij";
		String twelve = "abcdefghijkl";
		String longStr = "abcdefghijjihgfecabddjihgfcebdaegdjchfaibegdifjacbhgefcdabgcebfad";
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new FileOutputStream("slowFile.txt", true));
			pw.println("Run #2: 10 characters\n");
			long startTime = System.nanoTime();
			permu(ten, "", longStr, pw);
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			pw.println("\nExecution time of method permu in nanoseconds: "+timeElapsed);
			pw.println("------------------------------------------------------------------------");
			pw.close();
			System.out.println("Version 1 (SLOW) Writing Complete. Exiting program.");
			System.exit(0);
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
