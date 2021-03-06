package scalar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Maths {
	public static int gcd(int A, int B) {
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
		if (A.equals(B)) {
			return A;
		}
		return "1";
	}

	public int solve_findingPosition(int A) {
		int d = (int) (Math.log(A) / Math.log(2));
		return (int) Math.pow(2, d);
	}

	public int solve_findOverlap(int A, int B, int C, int D, int E, int F, int G, int H) {
		if (A >= G || C <= E) {
			return 0;
		}
		if (D <= F || B >= H) {
			return 0;
		}
		return 1;
	}

	public int solve_openDoors(int A) {
		return (int) Math.sqrt(A);
	}

	public ArrayList<Integer> solve_primeFactors(int A, ArrayList<Integer> B) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int spf[] = new int[1000001];
		int pc[] = new int[1000001];
		spf[1] = 1;
		for (int i = 2; i < 1000001; i++) {
			spf[i] = i;
		}

		for (int i = 2; i * i < 1000001; i++) {
			if (spf[i] == i) {
				for (int j = i * i; j < 1000001; j += i) {
					if (spf[j] == j) {
						spf[j] = i;
						pc[i]++;
					}
				}

			}
		}
		for (int i = 0; i < B.size(); i++) {
			res.add(pc[B.get(i)] + 1);
		}
		return res;
	}

	public static int solve_luckyNumbers(int A) {
		ArrayList<Integer> pNums = new ArrayList<Integer>();
		int a = A / 2;
		int prime[] = new int[a + 1];
		for (int p = 2; p * p <= a; p++) {
			if (prime[p] == 0) {
				for (int i = p * p; i <= a; i += p)
					prime[i] = 1;
			}
		}
		for (int i = 2; i <= a; i++) {
			if (prime[i] == 0)
				pNums.add(i);
		}
		int cnt = 0;
		for (int i = 0; i < pNums.size(); i++) {
			for (int j = i + 1; j < pNums.size(); j++) {
				if (pNums.get(i) * pNums.get(j) <= A) {
					cnt++;
				} else {
					break;
				}
			}
		}
		return cnt;
	}

	public static int solve_luckyNumbers1(int A) {
		int res = 0;
		for (int i = 2; i < A; i++) {
			int k = i;
			int d = 2;
			int cnt = 0;
			while (d <= k && cnt <= 2) {
				if (k % d == 0) {
					cnt++;
					while (k % d == 0) {
						k /= d;
					}
				}
				d++;
			}
			if (cnt == 2) {
				res++;
			}
		}
		return res;
	}

	public static ArrayList<Integer> solve_multipleQueries(ArrayList<Integer> A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int spf[] = new int[1000001];
		spf[1] = 1;
		for (int i = 2; i < 1000001; i++)
			spf[i] = i;
		for (int i = 4; i < 1000001; i += 2)
			spf[i] = 2;

		for (int i = 3; i * i < 1000001; i++) {
			if (spf[i] == i) {
				for (int j = i * i; j < 1000001; j += i)
					if (spf[j] == j)
						spf[j] = i;
			}
		}

		for (int i = 0; i < A.size(); i++) {
			int x = A.get(i);
			int r = 1;
			int y = 0;
			int cnt = 0;
			while (x != 1) {
				if (y != spf[x]) {
					r = r * (cnt + 1);
					cnt = 0;

				}
				y = spf[x];
				cnt++;
				x = x / spf[x];

			}
			r = r * (cnt + 1);
			res.add(r);
		}
		return res;
	}

	public int solve_coprime(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
		int a = 0;
		for (int i = 0; i < A.size(); i++) {
			if (gcd(C, A.get(i)) == 1) {
				a++;
			}
		}
		int b = 0;
		for (int i = 0; i < B.size(); i++) {
			if (gcd(C, B.get(i)) == 1) {
				b++;
			}
		}
		return a * b;
	}

	public int solve_magicNum(int A) {
		int res = 0;
		int p = 5;
		while (A != 0) {
			if ((A & 1) == 1) {
				res += p;
			}
			p *= 5;
			A = A >> 1;
		}
		return res;
	}

	public int trailingZeroes(int A) {
		int res = 0;
		while (A != 0) {
			res += A / 5;
			A = A / 5;
		}
		return res;
	}

	public static int cpFact(int A, int B) {
		if (gcd(A, B) == 1) {
			return A;
		}
		int max = 0;
		for (int i = 2; i * i < A; i++) {
			if (A % i == 0 && gcd(i, B) == 1) {
				max = Math.max(max, i);
			}
		}
		return max;
	}

	public static String convertToTitle(int A) {
		StringBuffer s = new StringBuffer("");
		while (A > 0) {
			int a = A % 26 == 0 ? 26 : A % 26;
			s.insert(0, (char) (64 + a));
			A = A / 26;
		}
		return s.toString();
	}

	public static int uniquePaths(int A, int B) {
		if ((A - 1 == 1 && B == 1) || (B - 1 == 1 && A == 1)) {
			return 1;
		}
		return uniquePaths(A - 1, B) + uniquePaths(A, B - 1);
	}

	public int solve_cutTheChocolate(int A, int B) {
		int a = 0;
		while (A > 1 || B > 1) {
			if (A > B) {
				A = A % 2 == 0 ? A / 2 : A / 2 + 1;
			} else {
				B = B % 2 == 0 ? B / 2 : B / 2 + 1;
			}
			a++;
		}
		return a % 2;
	}

	public static int reverse(int A) {
		long i = 0;
		boolean isN = A < 0 ? true : false;
		long B = Math.abs(A);
		while (B > 0) {
			i = i * 10 + B % 10;
			B = B / 10;
		}
		if (i > Integer.MAX_VALUE) {
			return 0;
		}
		return (int) (isN ? i - 2 * i : i);
	}

	public ArrayList<Integer> solve(ArrayList<Integer> A) {
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
		ArrayList<Integer> res = new ArrayList<>();
		int size = (int) Math.sqrt(A.size());
		int k = 0;
		for (int i = 0; i < A.size(); i++) {
			m.put(A.get(i), m.getOrDefault(A.get(i), 0) + 1);
		}
		Collections.sort(A, Collections.reverseOrder());
		for (int i = 0; i < A.size(); i++) {
			if (m.get(A.get(i)) > 0 && k < size) {
				res.add(A.get(i));
				k++;
				m.put(A.get(i), m.get(A.get(i)) - 1);
				for (int j = 0; j < k; j++) {
					if (i != j) {
						int g = gcd(A.get(i), res.get(j));
						m.put(g, m.get(g) - 2);
					}
				}
			}
		}
		return res;
	}

	public int solve_ncr(int A, int B, int C) {
		long mod_n = findFactMod(A, C);
		long mod_r = findFactMod(B, C);
		long mod_nr = findFactMod(A - B, C);
		long mod_r2 = power(mod_r, C - 2, C);
		long mod_nr2 = power(mod_nr, C - 2, C);
		return (int) (((((mod_n % C) * mod_r2) % C) * mod_nr2) % C);
	}

	public long findFactMod(long a, long mod) {
		long ans = 1;
		int i = 1;
		while (i <= a) {
			ans = (ans * (i % mod)) % mod;
			i++;
		}
		return ans;
	}

	public long power(long x, long y, long p) {
		long res = 1;
		x = x % p;
		while (y > 0) {
			if (y % 2 == 1)
				res = (res * x) % p;
			y = y / 2;
			x = (x * x) % p;
		}
		return res;
	}

//	public int res = 0;
//	public int solve(ArrayList<Integer> A, int B) {
//		Collections.sort(A);
//		solve(A, B, 0, 3);
//		return  res;
//	}
//	public void solve(ArrayList<Integer> A, int B, int ind, int places) {
//		if (places == 1) {
//			res += A.get(ind + B - 1);
//			return;
//		}
//		int len = A.size() - ind;
//		int d = getNCR(len - 1, places - 1);
//		if (B > d) {
//			solve(A, B - d, ++ind, places);
//			
//		} else if (d == B) {
//			int k = A.size()-1;
//			res += A.get(ind);
//			places--;
//			while (places > 0) {
//				res += A.get(k);
//				places--;
//				k--;
//			}
//		} else {
//			res += A.get(ind);
//			solve(A, B, ++ind, places-1);
//		}
//		return;
//	}
//	public int getNCR(int n, int r) {
//		int res = 1;
//		if (r > n - r) {
//			r = n - r;
//		}
//		for (int i = 0; i < r; i++) {
//			res *= (n - i);
//			res /= (i + 1);
//		}
//		return res;
//	}

	public int solve_sumPairs(ArrayList<Integer> A, int B) {
		long res=0;
		long mod=1000000007;
		long b[] = new long[B];
		for(int i=0;i<A.size();i++) {
			b[A.get(i)%B]++;
		}
		for(int i=0;i<=B/2;i++) {
			if(i==0||i==B-i) {
				res+=(b[i]*(b[i]-1))/2;
			}else {
				res+=(b[i]*b[B-i]);
			}	
		}
		return (int) (res%mod);
    }
	
	public ArrayList<Integer> solve_sumPowers3(int A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int p=1;
		while(A>0) {
			int d = A%3;
			while(d>0) {
				res.add(p);
				d--;
			}
			A=A/3;
			p*=3;
		}
		return res;
    }
	
	public int solve_xorprime(ArrayList<Integer> A) {
		long mod=1000000007;
		long res=0;
		for(int i=0;i<A.size();i++) {
			int num = A.get(i);
			ArrayList<Integer> pf = new ArrayList<Integer>();
			if(num%2==0) {
				pf.add(2);
				while (num % 2 == 0) { 
	                num /= 2; 
	            } 
			}
			for (int j = 3; j <= Math.sqrt(num); j += 2) { 
				if(num%j==0) {
					pf.add(j);
					while (num % j == 0) { 
		                num /= j; 
		            } 
				}
	        }
			if (num > 2) {
				pf.add(num);
			}
			long gv=0;
			int nof=pf.size();
			for(int k=0;k<(1<<nof);++k) {
				int temp =0;
				for(int j=0;j<nof;j++) {
					if((k & (1<<j))>0) {
						temp=temp^pf.get(j);
					}
				}
				gv=(gv+temp)%mod;
			}
			res=(res+gv)%mod;
		}
		return (int) res;
    }
	public boolean check(ArrayList<Integer> A,int mid,int B) {
		int n=A.size();
		int cnt=0;
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				int val=mid-(A.get(i)+A.get(j));
				if(val<=0) {
					break;
				}
				 int b=getLowerbound(A, val);
				 if(b>j) {
					cnt+=b-j; 
				 }
			}
		}
		if(cnt<B) {
			return true; 
		}
		return false;
	}
	public int getLowerbound(ArrayList<Integer> A,int B) {
		int low=0;
		int high =A.size()-1;
		while(low<high) {
			int mid=(low+high)/2;
			if(A.get(mid)<=B) {
				low=mid+1;
			}
			else {
				high=mid-1;
			}
		}
		return A.get(low)>B?low-1:low;
	}
	public int solve_smallestAgain	(ArrayList<Integer> A, int B) {
		if(A.size()<3) {return -1;}
		Collections.sort(A);
		int l=A.size();
		int low=A.get(0)+A.get(1)+A.get(2);
		int high=A.get(l-1)+A.get(l-2)+A.get(l-3);
		while(low<high) {
			int mid =(low+high)/2;
			if(check(A, mid, B)) {
				low=mid+1;
			}else {
				high=mid-1;
			}
		}
		return check(A, high, B)?high:high-1;
    }
	

	public static void main(String[] args) {
	}

}
