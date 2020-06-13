//Written by: Auvigoo Ahmed (40128901)
//COMP 352
//Assignment 2 - Arithmetic calculator using recursion
//Due Date: May 31st 2020

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class version2 {

	/**
	 * 
	 * @param expression to be evaluated
	 * @return result of the expression
	 */
	public static double evaluate(String expression) {
		
		//if the expression doesn't contain operators, return the result since this is the answer
		if(!expression.contains("+") && !expression.contains("-") && !expression.contains("*") && !expression.contains("/")
				&& !expression.contains("^") && !expression.contains(">") && !expression.contains("≥")
				&& !expression.contains("≤") && !expression.contains("<") && !expression.contains("=")
				&& !expression.contains("~")) {
			return Double.parseDouble(expression);
		}
		//deal with expressions within parentheses
		while(expression.contains("(") && expression.contains(")")) {
			int first = expression.indexOf("(");
			int end;
			for(end = expression.length() -1; end>=0; end--) {
				if(expression.charAt(end)==')') {
					break;
				}
			}
			String newExpression = expression.substring(first+1, end);
			double ans = evaluate(newExpression);
			String newExpWParen = expression.substring(first, end+1);
			String answer = Double.toString(ans);
			expression = expression.replace(newExpWParen, answer);
		}
		//if the expression doesn't contain operators, return the result since this is the answer
		if(!expression.contains("+") && !expression.contains("-") && !expression.contains("*") && !expression.contains("/")
				&& !expression.contains("^") && !expression.contains(">") && !expression.contains("≥")
				&& !expression.contains("≤") && !expression.contains("<") && !expression.contains("=")
				&& !expression.contains("~")) {
			return Double.parseDouble(expression);
		}
		
		//the following if statements are placed in this specific order to maintain operator priority
		int i;
		for(i = expression.length() - 1; i >=0; i--) {
			if(expression.charAt(i) == '=' || expression.charAt(i) == '~') {
				break;
			}
		}
		if(i < 0) {
			for(i = expression.length() - 1; i >=0; i--) {
				if(expression.charAt(i) == '>' ||expression.charAt(i) =='≥' || expression.charAt(i) =='≤' || expression.charAt(i) =='<') {
					break;
				}
			}
		}
		if(i < 0) {
			for(i = expression.length() - 1; i >=0; i--) {
				if(expression.charAt(i) == '+' || expression.charAt(i) == '-') {
					break;
				}
			}
		}
		if(i < 0) {
			for(i = expression.length() - 1;i>=0; i--) {
				if(expression.charAt(i) == '*' || expression.charAt(i) == '/') {
					break;
				}
			}
		}
		if(i < 0) {
			for(i = expression.length() - 1;i>=0; i--) {
				if(expression.charAt(i) == '^' ) {
					break;
				}
			}
		}

		//getting the operands
		String r1 = expression.substring(0, i);
		String r2 = expression.substring(i+1, expression.length());
		
		double result = 0;
		
		//evaluating the operations
		if(expression.charAt(i) == '+') {
			result = evaluate(r1) + evaluate(r2);
		}
		else if(expression.charAt(i) == '-') {
			result = evaluate(r1) - evaluate(r2);
		}
		else if(expression.charAt(i) == '*') {
			result = evaluate(r1) * evaluate(r2);
		}
		else if(expression.charAt(i) == '/') {
			if(evaluate(r2) == 0) {
				System.out.println("Invalid");
			}
			else {
				result = evaluate(r1) / evaluate(r2);
			}
		}
		else if(expression.charAt(i) == '^') {
			result = power(evaluate(r1) , evaluate(r2));
		}
		else if(expression.charAt(i) == '>') {
			boolean op = evaluate(r1) > evaluate(r2);
			if(op) {
				result = 1;
			}
			else {
				result = 0;
			}
		}
		else if(expression.charAt(i) == '≥') {
			boolean op = evaluate(r1) >= evaluate(r2);
			if(op) {
				result = 1;
			}
			else {
				result = 0;
			}
		}
		else if(expression.charAt(i) == '≤') {
			boolean op = evaluate(r1) <= evaluate(r2);
			if(op) {
				result = 1;
			}
			else {
				result = 0;
			}
		}
		else if(expression.charAt(i) == '<') {
			boolean op = evaluate(r1) < evaluate(r2);
			if(op) {
				result = 1;
			}
			else {
				result = 0;
			}
		}
		else if(expression.charAt(i) == '=') {
			boolean op = evaluate(r1) == evaluate(r2);
			if(op) {
				result = 1;
			}
			else {
				result = 0;
			}
		}
		else if(expression.charAt(i) == '~') {
			boolean op = evaluate(r1) != evaluate(r2);
			if(op) {
				result = 1;
			}
			else {
				result = 0;
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param x 
	 * @param n exponent
	 * @return result of the power of x to the n
	 */
	public static double power(double x, double n) {
		if(n < 0) {
			throw new IllegalArgumentException("Invalid");
		}
		if(n == 0) {
			return 1;
		}
		if(n % 2 == 1) {
			double y = power(x, (n-1)/2);
			return x * y * y;
		}
		else {
			double y = power(x, n/2);
			return y * y;
		}
	}
	
	/**
	 * 
	 * @param num to calculate the factorial
	 * @return result of the factorial of num
	 */
	public static double factorial(double num) {
		if(num < 0) {
			throw new IllegalArgumentException();
		}
		if(num == 0) {
			return 1;
		}
		if(num == 1) {
			return 1;
		}
		else {
			return num*factorial(num - 1);
		}
		
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(evaluate("65≤(99-31)+56/2"));
		
		Scanner sc = null;
		PrintWriter pw = null;
		try {
			sc = new Scanner(new FileInputStream("expressionsRecursion.txt"));
			pw = new PrintWriter(new FileOutputStream("answersRecursion.txt"));
			while(sc.hasNext()) {
				String express = sc.nextLine();
				double ans = evaluate(express);
				pw.println("\n"+express);
				pw.println("Answer: "+ans);
				pw.println("--------------------------");
			}
			sc.close();
			pw.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found!");
		}
		
	}

}
