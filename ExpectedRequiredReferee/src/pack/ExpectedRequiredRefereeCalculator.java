package pack;

import java.math.BigDecimal;

public class ExpectedRequiredRefereeCalculator {

	public static int numberOfProposals = 256;
	public static int expectedValueOfCapacity = numberOfProposals / 2;

	public static void main(String[] args) {

		System.out.println("numberOfProposals: " + numberOfProposals);
		System.out.println("expectedValueOfCapacity: " + expectedValueOfCapacity);
		System.out.println("C(n,2): " + Combinatorics.combination(numberOfProposals, 2));
		System.out.println("*************************************************************\n");

		int maxNumberOfReferees = 6;
		for (int numberOfReferees = 2; numberOfReferees <= maxNumberOfReferees; numberOfReferees++) {
			System.out.println("numberOfReferees: " + numberOfReferees);
			System.out.println("\nexpectedValueOfPairsOfProposals: " + principleOfInclusionExclusion(numberOfReferees));
			System.out.println("-----------------------------------------------------\n");
		}
		
	}

	// r tane hakemin ayni anda bir proposali okuma olasiligi
	// p=1 / (2 power r)
	public static BigDecimal propabilityOfCoinsidenceOfGivenReferees(int numberOfGivenReferees) {
		return BigDecimal.ONE.divide(new BigDecimal(Math.pow(2, numberOfGivenReferees)));
	}

	// r tane hakemin ortak okuduðu proposal sayisinin expected degeri.
	// E(k) = n.p
	public static long expectedValueOfNumberOfCoincidentProposals(int numberOfGivenReferees) {
		long v = (propabilityOfCoinsidenceOfGivenReferees(numberOfGivenReferees).multiply(new BigDecimal(numberOfProposals))).longValue();

		System.out.println("expectedValueOfNumberOfCoincidentProposals for " + numberOfGivenReferees + " referees: " + v);
		return v;
	}

	public static long principleOfInclusionExclusion(int numberOfReferees) {

		long sum = numberOfReferees * Combinatorics.combination(expectedValueOfCapacity, 2);
		//System.out.println("Sum: " + sum);
		int counter = 2;
		while (counter < numberOfReferees) {
			long sign = (long) Math.pow(-1, counter + 1);

			long expectedValue = expectedValueOfNumberOfCoincidentProposals(counter);
			if (expectedValue >= 2) {
				long subtotal = Combinatorics.combination(numberOfReferees, counter) * Combinatorics.combination(expectedValue, 2);
				sum += sign * subtotal;
				//System.out.println("Sum: " + sum);
			}

			writer(counter, sign, false);
			counter++;
		}

		long lastSign = (long) Math.pow(-1, counter + 1);

		long expectedValue = expectedValueOfNumberOfCoincidentProposals(counter);
		if (expectedValue >= 2) {
			sum += lastSign * Combinatorics.combination(expectedValue, 2);
			//System.out.println("Sum: " + sum);
		}

		writer(counter, lastSign, true);

		return sum;
	}

	private static void writer(int counter, long sign, boolean last) {
		if (sign > 0) {
			System.out.print("+|");
		} else {
			System.out.print("-|");
		}
		for (int i = 1; i <= counter; i++) {
			System.out.print("A" + i);
			if (i < counter) {
				System.out.print(" n ");
			}
		}

		System.out.println("|");
	}

}
