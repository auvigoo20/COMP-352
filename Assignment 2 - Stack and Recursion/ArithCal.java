//Written by: Auvigoo Ahmed (40128901)
//COMP 352
//Assignment 2 - Arithmetic calculator using stacks
//Due Date: May 31st 2020

import java.util.Stack;
import java.lang.IllegalArgumentException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
public class ArithCal {
	/**
	 * 
	 * @param op1 first operand
	 * @param op2 second operand
	 * @return true if op2 has higher or equal precedence to op1
	 */
	public static boolean precedence(String op1, String op2) {
		if(op2 == "(" || op2 == ")") {
			return false;
		}
		if((op1 == "^" || op1 == "*" || op1 == "/" || op1 == "+" || op1 == "-" || op1 == ">" || op1 == ">=" ||
				op1 == "<=" || op1 == "<" || op1 == "==" || op1 == "!=") && op2 == "^") {
			return true;
		}
		if((op1 == "*" || op1 == "/" || op1 == "+" || op1 == "-" || op1 == ">" || op1 == ">=" ||
				op1 == "<=" || op1 == "<" || op1 == "==" || op1 == "!=") && (op2 == "*" || op2 == "/")) {
			return true;
		}
		if((op1 == "+" || op1 == "-" || op1 == ">" || op1 == ">=" ||
				op1 == "<=" || op1 == "<" || op1 == "==" || op1 == "!=") && (op2 == "+" || op2 == "-")) {
			return true;
		}
		if((op1 == ">" || op1 == ">=" || op1 == "<=" || op1 == "<" || op1 == "==" || op1 == "!=") && (op2 == ">" || op2 == ">=" ||
				op2 == "<=" || op2 == "<")) {
			return true;
		}
		if((op1 == "==" || op1 == "!=") && (op2 == "==" || op2 == "!=" )) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static Stack<String> numberStack = new Stack<String>();
	private static Stack<String> operatorStack = new Stack<String>();
	
	/**
	 * 
	 * @param arithmetic expression
	 * @return answer of the expression
	 */
	public static double calculate(String expression) {
		String[] numAndOps = expression.split(" ");
		for(String token : numAndOps) {
			
			if(token.contains("!") && !token.contains("=")) {//deal with factorials directly 
				String strNum = token.substring(0, token.length()-1);
				double num = Double.parseDouble(strNum);
				double ans = factorial(num);
				numberStack.push(Double.toString(ans));
			}
			//negative numbers
			else if(token.contains("-") && (token.contains("1") || token.contains("2") || token.contains("3") || token.contains("4") || token.contains("5")
					|| token.contains("6") || token.contains("7") || token.contains("8") || token.contains("9") || token.contains("0"))) {
				numberStack.push(token);
			}
			//normal numbers
			else if(token.contains("1") || token.contains("2") || token.contains("3") || token.contains("4") || token.contains("5")
					|| token.contains("6") || token.contains("7") || token.contains("8") || token.contains("9") || token.contains("0")) {
				numberStack.push(token);
			}
			else if(token.contains("(")) {
				operatorStack.push(token);
			}
			//calculate the expression within parentheses
			else if(token.contains(")")) {
				while(!operatorStack.peek().contains("(")) {
					double answer = applyOperation();
					numberStack.push(Double.toString(answer));
				}
				operatorStack.pop();
			}

			else if(token.contains("+") || token.contains("-") || token.contains("*") || token.contains("/") || token.contains("^") || token.contains(">") || token.contains(">=") ||
					token.contains("<=") || token.contains("<") || token.contains("==") || token.contains("!=")) {
				
				//deal with higher priority operations before pushing the new operator on the stack
				while(!operatorStack.isEmpty() && precedence(token, operatorStack.peek())) {
					double answer = applyOperation();
					numberStack.push(Double.toString(answer));
					}
				
				operatorStack.push(token);
			}
			
		}
		//calculate the remaining operations on the stack
		while(operatorStack.isEmpty() == false) {
			double answer = applyOperation();
			numberStack.push(Double.toString(answer));
		}
		return Double.parseDouble(numberStack.pop());
	}

	/**
	 * 
	 * @return answer of the operation
	 */
	public static double applyOperation() {
		String op = operatorStack.pop();
		double num2 = Double.parseDouble(numberStack.pop());
		double num1 = Double.parseDouble(numberStack.pop());
		double answer = 0;
		
		if(op.contains("+")) {
			answer = num1 + num2;
			return answer;
		}
		if(op.contains("-")) {
			answer = num1 - num2;
			return answer;
		}
		if(op.contains("*")) {
			answer = num1 * num2;
			return answer;
		}
		if(op.contains("/")) {
			if(num2 == 0) {
				throw new UnsupportedOperationException();
			}
			answer = num1 / num2;
			return answer;
		}
		if(op.contains("^")) {
			answer = power(num1, num2);
			return answer;
		}
		if(op.contains(">")) {
			answer = (num1 > num2 ? 1 : 0);
			return answer;
		}
		if(op.contains(">=")) {
			answer = (num1 >= num2 ? 1 : 0);
			return answer;
		}
		if(op.contains("<")) {
			answer = (num1 < num2 ? 1 : 0);
			return answer;
		}
		if(op.contains("<=")) {
			answer = (num1 <= num2 ? 1 : 0);
			return answer;
		}
		if(op.contains("==")) {
			answer = (num1 == num2 ? 1 : 0);
			return answer;
		}
		if(op.contains("!=")) {
			answer = (num1 != num2 ? 1 : 0);
			return answer;
		}
		return -1;
		
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
	
	
	public static void main(String[] args) { 
		Scanner sc = null;
		PrintWriter pw = null;
		try {
			sc = new Scanner(new FileInputStream("expressionsStack.txt"));
			pw = new PrintWriter(new FileOutputStream("answersStack.txt"));
			while(sc.hasNext()) {
				String express = sc.nextLine();
				double ans = calculate(express);
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
		
		
		
		System.out.println(calculate("( ( ( 9 + 5 ) * 2 ) ^ 2 ) != 784"));
		
		
		
	}

}
