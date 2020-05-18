//Written by: Auvigoo Ahmed (40128901)
//COMP 352
//Assignment 1 - Programming Question Version 2 (FAST)
//Due Date: May 15th 2020

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
public class VersionFast {

	/**
	 * This method will print the permutations that exist within longStr. It works by first counting the number of each character in shortStr, storing
	 * it in an integer array, where each index is for an ASCII character. Then, a "window" of the same size of shortStr iterates throughout longStr,
	 * storing each character of the window in a similar array. If the contents of both arrays are equal, this means that a permutation exists at this index.
	 * @param shortStr string in which we want to find permutations within longStr
	 * @param longStr string that will be searched for permutations of shortStr
	 */
	public static void permu(String shortStr, String longStr, PrintWriter pw) {

        int[] shortStrArr = new int[256];//array to store the number of each character of shortStr in its respective ASCII index
        int[] longStrArr = new int[256];//array to store the number of each character of longStr in its respective ASCII index
        int shortStrLen = shortStr.length();
        int longStrLen = longStr.length();
        
        for (int i = 0; i < shortStrLen; i++) {
            shortStrArr[shortStr.charAt(i)]++;//filling up the array with the amount of each character in shortStr
            longStrArr[longStr.charAt(i)]++;//filling up the array within the window of the length of shortStrLen to start search
        }
        
        for (int i = 0; i < longStrLen - shortStrLen; i++) {//stopping the loop when the window reaches the end of longStr
            if (alphaArrMatch(shortStrArr, longStrArr)) {
            	String permutation = longStr.substring(i, i+shortStrLen);
            	pw.println("Found one match: "+permutation+" is at index "+i);//printing out the permutation and index if there is a match
            }
                
            longStrArr[longStr.charAt(i + shortStrLen)]++;//expanding the window to search
            longStrArr[longStr.charAt(i)]--;//removing the last character of the window to always match the length of shortStr
        }
        
        if (alphaArrMatch(shortStrArr, longStrArr)) {//when the window is at the end of the longStr
        	String permuEnd = longStr.substring(longStrLen - shortStrLen);
        	pw.println("Found one match: "+permuEnd+" is at index "+(longStrLen - shortStrLen));//printing out the permutation and index if there is a match
        }
        
    }
	/**
	 * This helper method checks if 2 arrays have the same contents. If they do, this means that a permutation of shortStr exists in longStr
	 * @param shortStrArr array containing the number of each character present in shortStr
	 * @param longStrArrarray containing the number of each character present in longStr
	 * @return boolean saying if there is a match or not
	 */
    private static boolean alphaArrMatch(int[] shortStrArr, int[] longStrArr) {
        for (int i = 0; i < 256; i++) {
            if (shortStrArr[i] != longStrArr[i])
                return false;
        }
        return true;
    }
	

	public static void main(String[] args) {
		//strings to test speed of method
		String five = "abcde";
		String ten = "abcdefghij";
		String fifteen = "daegdjchfaibegd";
		String longStr = "abcdefghijjihgfecabddjihgfcebdaegdjchfaibegdifjacbhgefcdabgcebfadjacbhgefcdabgcebfadjacbhgefcdabgcebfad";
		PrintWriter pw = null;
		try {
		
		pw = new PrintWriter(new FileOutputStream("fastFile.txt", true));
		pw.println("Run #3: 15 characters\n");
		
		long startTime = System.nanoTime();
		permu(fifteen, longStr, pw);
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		
		pw.println("\nExecution time of method permu in nanoseconds: "+timeElapsed);
		pw.println("------------------------------------------------------------------------");	
		pw.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found!");
			System.exit(0);
		}
	}

}
