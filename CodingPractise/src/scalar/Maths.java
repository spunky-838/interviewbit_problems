package scalar;

import java.util.ArrayList;

public class Maths {
	public int gcd(int A, int B) {
		if (A == 0 && B == 0) {
			return 0;
		}
		if (B == 0) {
			return A;
		}
		if (B > A) {
			return gcd(B, A);
		}
		if (A % B == 0) {
			return B;
		} else {
			return gcd(B, A % B);
		}
	}

	public int solve(int A, int B, int C) {
		long m = ((long) A * (long) gcd(B, C)) / ((long) B * (long) C);
		return (int) m;
	}

	public int solve_minElementsToRemove(ArrayList<Integer> A) {
		int gcd = A.get(0);
		for (int i = 1; i < A.size(); i++) {
			if (gcd == 1) {
				return 0;
			}
			gcd = gcd(gcd, A.get(i));
		}
		return -1;
	}

	public int solve_deleteOne(ArrayList<Integer> A) {
		int[] postFixGcd = new int[A.size()];
		postFixGcd[0] = A.get(0);
		for (int i = 1; i < A.size(); i++) {
			postFixGcd[i] = gcd(postFixGcd[i - 1], A.get(i));
		}
		int max = Math.max(postFixGcd[A.size() - 1], postFixGcd[A.size() - 2]);
		int preFixGcd = A.get(A.size() - 1);
		for (int i = A.size() - 2; i >= 0; i--) {
			preFixGcd = gcd(preFixGcd, A.get(i - 1));
			int gcd = gcd(postFixGcd[i], preFixGcd);
			max = Math.max(max, gcd);
		}
		return max;
	}

	public int getFinal(int A, int B) {
		if (A == 0 || B == 0) {
			return A == 0 ? B : A;
		}
		int gcd = gcd(B, A);
		return 2 * gcd;
	}

	public String solve_enumeratingGCD(String A, String B) {
		if(A.equals(B)) {return A;}
		return "1";
	}
	
	public int solve_findingPosition(int A) {
		int d=(int) (Math.log(A)/Math.log(2));
		return (int) Math.pow(2, d);
    }
	
	public int solve_findOverlap(int A, int B, int C, int D, int E, int F, int G, int H) {
		if(A>=G || C<=E) {
			return 0;
		}
		if(D<=F || B>=H) {
			return 0;
		}
		return 1;
    }

	public static void main(String[] args) {
		int b = 100000000;
		int c = 100000000;
		long a = (long) b * (long) c;
		System.out.println(a);
	}
}
