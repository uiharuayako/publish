/*
 * 13．用数组计算复利。有$ 10000，年利率6.5%，假设每月记息一次，计算10年的复利。
输出要包括每年的利息、结余以及到该年为止的平均利息。
 * 
 */
package additional;

import java.text.DecimalFormat;

public class CompoundInterest {
	final static int NUM_YEARS = 10;
	final static int NUM_PERIODS = 12;
	final static double RATE = 6.5;
	final static double START_BALANCE = 10000;

	public static double interestPerYear(double amount) {
		double compoundInterest = 0.0;
		for (int i = 0; i < NUM_PERIODS; i++) {
			double interest = amount * RATE / 100 / NUM_PERIODS;
			amount += interest;
			compoundInterest += interest;
		}
		return compoundInterest;
	}

	public static void adjustBalances(double amount[], double interest[]) {
		for (int year = 1; year < NUM_YEARS; year++) {
			interest[year] = interestPerYear(amount[year - 1]);
			amount[year] = amount[year - 1] + interest[year];
		}
	}

	public static double[] prefixAvg(double A[]) {
		double avg[] = new double[A.length];
		for (int year = 0; year < A.length; year++) {
			double sum = 0.0;
			for (int j = 0; j <= year; j++) {
				sum += A[j];
			}
			avg[year] = sum / (year + 1);
		}
		return avg;
	}

	public static void showAmounts(double amount[], double interest[], double avg[]) {

		DecimalFormat formatTool = new DecimalFormat("#,###.00");
		for (int i = 0; i < NUM_YEARS; i++) {
			System.out.print("Year:\t" + (i + 1));
			System.out.print("\tInt: " + formatTool.format(interest[i]));
			System.out.print("\tAmt: " + formatTool.format(amount[i]));
			System.out.println("\tAvg. Int: " + formatTool.format(avg[i]));
		}
	}

	public static void main(String args[]) {
		double amount[] = new double[NUM_YEARS];
		double interest[] = new double[NUM_YEARS];
		interest[0] = interestPerYear(START_BALANCE);
		amount[0] = START_BALANCE + interest[0];

		adjustBalances(amount, interest);
		showAmounts(amount, interest, prefixAvg(interest));
	}

}