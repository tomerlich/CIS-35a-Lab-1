import java.text.NumberFormat;
import java.util.*;

public class CIS35ALAB1 {	
	
	//First we declare variables to hold our user data;
	static double loanAmount = 0.0;
	static double interestRate = 0.0;
	static double loanTimeYears = 0.0;
	static double monthlyPaymentVal = 0.0;
	static double loanTimeMonths = 0.0;
	static double monthlyRate = 0.0;
	
	//before we can output the data for the user we need to get our inputs from the user for this we will use a scanner
	static Scanner sc = new Scanner(System.in);
	
	public static void gatherData() {
		// Now we will gather our data
		System.out.printf("Please enter the following data and press enter between each entry\nLoan amount:");
		loanAmount = sc.nextDouble();
		
		//Gather the monthly interest rate
		System.out.printf("Interest rate:");
		interestRate = sc.nextDouble();
		
		//Gather the monthly interest rate
		System.out.printf("Loan time in years:");
		loanTimeYears = sc.nextDouble();
	}
	
	public static double monthlyPaymentCalc() {
		double monthlyPayment = 0.0;
		
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
	
	public static void printSchedule(NumberFormat currencyFormat, double monthlyPayment) {
		double interestAmount = 0.0;
		double principleAmount = 0.0;
		double acountBalance = loanAmount;
		
		System.out.printf("\n\nMonthly Payment:%s\nTotal Payment:%s", 
						currencyFormat.format(monthlyPayment),
						currencyFormat.format(monthlyPayment * (loanTimeYears * 12)));
		
		System.out.printf("\n\nPayment#\tInterest\tPrinciple\tBalance\n\n");
		
		for(int paymentNum = 0; paymentNum < loanTimeYears * 12; paymentNum++) {
			interestAmount = monthlyRate * acountBalance;
			principleAmount = monthlyPayment - interestAmount;
			acountBalance = acountBalance - principleAmount;
			System.out.printf("\t%d\t   %s\t    %s\t %s\n",
							paymentNum + 1,
							currencyFormat.format(interestAmount),
							currencyFormat.format(principleAmount),
							currencyFormat.format(acountBalance));
		}
		
	}
	
	public static void main(String[] Args) {
		//We will use these formatting tools to automate showing dollar values as well as percentages
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		NumberFormat interestFormat = NumberFormat.getPercentInstance();
		
		//we will call another method to gather data so that we can leave main uncluttered
		gatherData();
		
		//now we will calculate the monthly payment
		monthlyPaymentVal = monthlyPaymentCalc();
		
		//Now we will call a method to output all of our results in a nicely formatted way
		//this will take two number formats and all of the data.
		printSchedule(currencyFormat, monthlyPaymentVal);
		
		sc.close();
		return;
	};

}
