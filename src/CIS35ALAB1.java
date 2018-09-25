import java.text.NumberFormat;
import java.util.Scanner;

public class CIS35ALAB1 {	
	
	//First we declare variables to hold our user data;
	static double loanAmount = 0.0;
	static double interestRate = 0.0;
	static double loanTimeYears = 0.0;
	static double monthlyPaymentVal = 0.0;
	
	//before we can output the data for the user we need to get our inputs from the user for this we will use a scanner
	static Scanner sc = new Scanner(System.in);
	
	public static void gatherData() {
		// Now we will gather our data
		System.out.printf("Please enter the following data and press enter between each entry\nHow much is the loan?");
		loanAmount = sc.nextDouble();
		
		//Gather the monthly interest rate
		System.out.printf("What is the interest rate?");
		interestRate = sc.nextDouble();
		
		//Gather the monthly interest rate
		System.out.printf("What is the period of the loan in years?");
		loanTimeYears = sc.nextDouble();
	}
	
	public static double monthlyPaymentCalc() {
		double monthlyPayment = 0.0;
		double loanTimeMonths = 0.0;
		double monthlyRate = 0.0;
		
		//here we will do the calculations
		//first convert rate to percent
		interestRate /= 100;
		
		//now we convert it to the monthly
		monthlyRate = interestRate / 12;
		
		//convert years to months
		loanTimeMonths = loanTimeYears * 12;
		
		//now we can calculate the monthly payment.
		monthlyPayment = (loanAmount * monthlyRate)/(1 - Math.pow(1 + monthlyRate, -loanTimeMonths));
		
		return monthlyPayment;
	}
	
	public static void main(String[] Args) {
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		NumberFormat interestFormat = NumberFormat.getPercentInstance();
		
		gatherData();
		monthlyPaymentVal = monthlyPaymentCalc();
		
		System.out.printf(
				"monthly payment:%s\ninterest rate:%s",
				currencyFormat.format(monthlyPaymentVal),
				interestFormat.format(interestRate));
		
		sc.close();
		return;
	};

}
