package pack;

class Combinatorics {

	public static void main(String[] args) {
		long r = 2;		
		long lowerBound = 0;

		while (r <= 6) {
			long n = r;
			long power2r = new Double(Math.pow((double) 2, (double) r)).longValue();
			while (n <= 10) {
				lowerBound = combination(power2r - 1 + n, n) / getFact(r);
				System.out.println("B(r,n) = B(" + r + "," + n + ")= " + lowerBound);
				n += 1;
			}
			System.out.println("------------------------------------------");
			r += 1;
		}

	}

	public static long combination(long n, long r) {
		if (n >= r) {
			return permutation(n, r) / getFact(r);
		} else
			throw new RuntimeException("r cannot be greater than n");
	}

	public static long permutation(long n, long r) {
		if (n >= r) {
			long f = 1;

			for (long i = n; i >= 1; i--) {
				f *= i;
				r--;
				if (r == 0) {
					break;
				}
			}

			return f;
		} else
			throw new RuntimeException("r cannot be greater than n");
	}

	public static long getFact(long n) {
		long f = 1;

		for (long i = n; i >= 1; i--) {
			f *= i;
		}

		return f;
	}
}