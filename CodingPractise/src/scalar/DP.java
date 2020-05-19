package scalar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DP {

	public int climbStairs(int A) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		return waysToClimb(A, hm);
	}

	int waysToClimb(int a, HashMap<Integer, Integer> hm) {
		if (a == 0 || a == 1 || a == 2) {
			return a;
		}
		if (hm.containsKey(a)) {
			return hm.get(a);
		}
		int r = waysToClimb(a - 1, hm) + waysToClimb(a - 2, hm);
		hm.put(a, r);
		return r;
	}

	private static int findFib(int a, HashMap<Integer, Integer> hm) {
		if (a == 0) {
			return 0;
		}
		if (a == 1 || a == 2) {
			return 1;
		}
		if (hm.containsKey(a)) {
			return hm.get(a);
		}
		int r = findFib(a - 1, hm) + findFib(a - 2, hm);
		hm.put(a, r);
		return r;
	}

	public int solve_party(int A) {
		if (A == 0 || A == 1 || A == 2) {
			return A;
		}
		long mod = 10003;
		ArrayList<Integer> topDown = new ArrayList<Integer>();
		topDown.add(0);
		topDown.add(1);
		topDown.add(2);
		int i = A - 2;
		while (i > 0) {
			int len = topDown.size();
			long r = topDown.get(len - 1) % mod + (((len - 1) % mod) * (topDown.get(len - 2) % mod));
			topDown.add((int) (r % mod));
			i--;
		}
		return topDown.get(topDown.size() - 1);
	}

	long partyWays(int a, HashMap<Integer, Long> hm) {
		if (a == 0 || a == 1 || a == 2) {
			return a;
		}
		if (hm.containsKey(a)) {
			return hm.get(a);
		}
		long r1 = partyWays(a - 1, hm);
		hm.put(a - 1, r1 % 10003);
		long r2 = partyWays(a - 2, hm);
		hm.put(a, (r1 + (a - 1) * r2) % 10003);
		return hm.get(a);
	}

	public int countMinSquares(int A) {
		if (A == 0 || A == 1 || A == 2 || A == 3) {
			return A;
		}
		int[] mem = new int[A + 1];
		mem[0] = 0;
		mem[1] = 1;
		mem[2] = 2;
		mem[3] = 3;

		for (int i = 4; i < A + 1; i++) {
			mem[i] = i;
			for (int j = 1; j * j <= i; j++) {
				mem[i] = Math.min(mem[i], 1 + mem[i - j * j]);
			}
		}
		return mem[A];
	}

	public int adjacent(ArrayList<ArrayList<Integer>> A) {
		if (A.size() == 0) {
			return 0;
		}
		int n = A.get(0).size();
		int[][] mem = new int[n][2];
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Math.max(A.get(0).get(i), A.get(1).get(i));
		}
		mem[n - 1][0] = 0;
		mem[n - 1][1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			mem[i][0] = Math.max(mem[i + 1][0], mem[i + 1][1]);
			mem[i][1] = mem[i + 1][0] + arr[i];
		}
		return Math.max(mem[0][0], mem[0][1]);
	}

	public int maxProduct(final List<Integer> A) {
		int len = A.size();
		if (len == 0) {
			return 0;
		}
		if (len == 1) {
			return A.get(0);
		}
		int[] pProduct = new int[len];
		int[] nProduct = new int[len];
		int[] maxProduct = new int[len];
		pProduct[0] = nProduct[0] = maxProduct[0] = A.get(0);
		for (int i = 1; i < A.size(); i++) {
			int a = pProduct[i - 1] * A.get(i);
			int b = nProduct[i - 1] * A.get(i);
			pProduct[i] = Math.max(Math.max(a, b), A.get(i));
			nProduct[i] = Math.min(Math.min(a, b), A.get(i));
			maxProduct[i] = Math.max(pProduct[i], maxProduct[i - 1]);
		}
		return maxProduct[A.size() - 1];
	}

	public int numDecodings(String A) {
		if (A.startsWith("0")) {
			return 0;
		}
		if (A.length() <= 2) {
			int a = Integer.parseInt(A);
			if (a == 0 || A.startsWith("0")) {
				return 0;
			}
			if (a < 10) {
				return 1;
			}
			if (a <= 26 && a % 10 != 0) {
				return 2;
			}
			if (a <= 26) {
				return 1;
			}
		}

		int a1 = numDecodings(A.substring(1));
		if (Integer.parseInt(A.substring(0, 2)) <= 26) {
			int a2 = numDecodings(A.substring(2));
			return a1 + a2;
		}
		return a1;

	}

	public int countSquares(int A, HashMap<Integer, Integer> hm) {
		if (A == 1 || A == 0) {
			return A;
		}
		if (hm.containsKey(A)) {
			return hm.get(A);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 1; i * i <= A; i++) {
			min = Math.min(min, 1 + countMinSquares(A - (i * i)));
		}
		hm.put(A, min);
		return min;
	}

	public int solve_maxSum(ArrayList<Integer> A, int B, int C, int D) {
		int b[] = new int[A.size()];
		int d[] = new int[A.size()];
		if (B < 0) {
			b = findPremin(A);
		} else {
			b = findPremax(A);
		}

		if (D < 0) {
			d = findPostmin(A);
		} else {
			d = findPostmax(A);
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < A.size(); i++) {
			max = Math.max(max, B * b[i] + C * A.get(i) + D * d[i]);
		}
		return max;
	}

	int[] findPremax(ArrayList<Integer> a) {
		int[] res = new int[a.size()];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.size(); i++) {
			max = Math.max(max, a.get(i));
			res[i] = max;
		}
		return res;
	}

	int[] findPostmax(ArrayList<Integer> a) {
		int[] res = new int[a.size()];
		int max = Integer.MIN_VALUE;
		for (int i = a.size() - 1; i >= 0; i--) {
			max = Math.max(max, a.get(i));
			res[i] = max;
		}
		return res;
	}

	int[] findPremin(ArrayList<Integer> a) {
		int[] res = new int[a.size()];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < a.size(); i++) {
			min = Math.min(min, a.get(i));
			res[i] = min;
		}
		return res;
	}

	int[] findPostmin(ArrayList<Integer> a) {
		int[] res = new int[a.size()];
		int min = Integer.MAX_VALUE;
		for (int i = a.size() - 1; i >= 0; i--) {
			min = Math.min(min, a.get(i));
			res[i] = min;
		}
		return res;
	}

	public int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> A) {
		int n = A.size();
		int m = A.get(0).size();
		if ((int) A.get(0).get(0) == 1 || (int) A.get(n - 1).get(m - 1) == 1) {
			return 0;
		}
		int[][] dp = new int[n][m];
		dp[n - 1][m - 1] = 1;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if ((int) A.get(i).get(j) == 1) {
					dp[i][j] = 0;
				} else {
					if (j + 1 < m) {
						dp[i][j] += dp[i][j + 1];
					}
					if (i + 1 < n) {
						dp[i][j] += dp[i + 1][j];
					}
				}
			}
		}
		return dp[0][0];
	}

	public int minDistance(String A, String B) {
		int[][] dp = new int[A.length() + 1][B.length() + 1];
		for (int i = 0; i <= A.length(); i++) {
			dp[i][0] = i;
		}
		for (int i = 0; i <= B.length(); i++) {
			dp[0][i] = i;
		}
		for (int i = 1; i < A.length() + 1; i++) {
			for (int j = 1; j < B.length() + 1; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[A.length()][B.length()];
	}

	public int solve_longestPalindromeSubsequence(String A) {

		return solve_longestCommonSubsequence(A, new StringBuilder(A).reverse().toString());
	}

	public int minPathSum(ArrayList<ArrayList<Integer>> A) {
		int m = A.size();
		int n = A.get(0).size();
		int[][] dp = new int[m][n];
		dp[m - 1][n - 1] = A.get(m - 1).get(n - 1);
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (i + 1 < m && j + 1 < n) {
					dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]);
				} else if (i + 1 < m) {
					dp[i][j] = dp[i + 1][j];
				} else if (j + 1 < n) {
					dp[i][j] = dp[i][j + 1];
				}
			}
		}
		return dp[0][0];
	}

	public int isMatch(final String A, final String B) {
		int len = 0;
		for (int i = 0; i < B.length(); i++) {
			if (B.charAt(i) != '*' && B.charAt(i) != '?') {
				++len;
			}
		}
		int comLen = solve_longestCommonSubsequence(A, B);
		if (len != comLen) {
			return 0;
		}
		int m = A.length();
		int n = B.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < m; i++) {
			dp[i][0] = 1;
		}
		for (int i = 0; i < n; i++) {
			dp[0][1] = 1;
		}
		dp[0][0] = 1;

		for (int i = 0; i <= A.length(); i++) {
			for (int j = 0; j <= B.length(); j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = 1;
				} else if (j > 0 && B.charAt(j - 1) == '*') {
					dp[i][j] = Math.max(dp[i][j - 1], i > 0 ? dp[i - 1][j] : 0);
				} else if (i > 0 && j > 0 && (A.charAt(i - 1) == B.charAt(j - 1) || B.charAt(j - 1) == '?')) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 0;
				}
			}
		}

		return dp[A.length()][B.length()];

	}

	public int isMatch_1(final String A, final String B) {
		if (B.length() == 0)
			return A.length() == 0 ? 1 : 0;
		int si = 0, pi = 0, match = 0, star = -1;
		int sl = A.length(), pl = B.length();
		char[] sc = A.toCharArray(), pc = B.toCharArray();
		while (si < sl) {
			if (pi < pl && (pc[pi] == sc[si] || pc[pi] == '?')) {
				si++;
				pi++;
			} else if (pi < pl && pc[pi] == '*') {
				star = pi++;
				match = si;
			} else if (star != -1) {
				si = ++match;
				pi = star + 1;
			} else
				return false ? 1 : 0;
		}
		while (pi < pl && pc[pi] == '*')
			pi++;
		return pi == pl ? 1 : 0;
	}

	public int isMatch_2(final String A, final String B) {
		int m = A.length();
		int n = B.length();
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		for (int j = 1; j < dp[0].length; j++) {
			if (B.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j - 2];
			}
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1) || B.charAt(j - 1) == '.') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (B.charAt(j - 1) == '*') {
					dp[i][j] = dp[i][j - 2];
					if (A.charAt(i - 1) == B.charAt(j - 2) || B.charAt(j - 2) == '.') {
						dp[i][j] = dp[i][j] || dp[i - 1][j];
					}
				} else {
					dp[i][j] = false;
				}
			}
		}

		return dp[m][n] ? 1 : 0;
	}

	public int numDistinct(String A, String B) {

		if (A.length() < B.length()) {
			return 0;
		}
		int dp[][] = new int[B.length() + 1][A.length() + 1];

		for (int i = 1; i < B.length() + 1; i++) {
			dp[i][0] = 0;
		}
		for (int j = 0; j < A.length() + 1; j++) {
			dp[0][j] = 1;
		}
		for (int i = 1; i < B.length() + 1; i++) {
			for (int j = 1; j < A.length() + 1; j++) {

				if (B.charAt(i - 1) != A.charAt(j - 1)) {
					dp[i][j] = dp[i][j - 1];
				} else {
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
				}
			}
		}

		return dp[B.length()][A.length()];
	}

	public ArrayList<Integer> solve_oddPalindrome(String A) {

		ArrayList<Integer> res = new ArrayList<Integer>();
		long mod = 1000000007;
		if (A.isEmpty()) {
			return res;
		}
		char[] s = A.toCharArray();
		int n = s.length;
		long[][] dp = new long[n][n];
		for (int len = n - 1; len >= 0; --len) {
			for (int i = 0; i + len < n; ++i) {
				int j = i + len;
				if (i == 0 && j == n - 1) {
					if (s[i] == s[j])
						dp[i][j] = 2;
					else if (s[i] != s[j])
						dp[i][j] = 1;
				} else {
					if (s[i] == s[j]) {
						if (i - 1 >= 0) {
							dp[i][j] = (dp[i][j] + dp[i - 1][j]) % mod;
						}
						if (j + 1 <= n - 1) {
							dp[i][j] = (dp[i][j] + dp[i][j + 1]) % mod;
						}
						if (i - 1 < 0 || j + 1 >= n) {
							dp[i][j] += 1;
						}
					} else if (s[i] != s[j]) {
						if (i - 1 >= 0) {
							dp[i][j] = (dp[i][j] + dp[i - 1][j]) % mod;
						}
						if (j + 1 <= n - 1) {
							dp[i][j] = (dp[i][j] + dp[i][j + 1]) % mod;
						}
						if (i - 1 >= 0 && j + 1 <= n - 1) {
							dp[i][j] = (dp[i][j] - dp[i - 1][j + 1]) % mod;
						}
					}
				}
			}
		}
		for (int i = 0; i < n; ++i) {
			if (i == 0 || i == n - 1) {
				res.add(1);
			} else {
				int total = (int) (dp[i - 1][i + 1] % mod);
				res.add(total < 0 ? (int) (total + mod) : total);
			}
		}
		return res;
	}

	public ArrayList<Integer> solve_submatricsum(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B,
			ArrayList<Integer> C, ArrayList<Integer> D, ArrayList<Integer> E) {
		long mod = 1000000007;
		ArrayList<Integer> res = new ArrayList<Integer>();
		int n = A.size();
		int m = A.get(0).size();
		long[][] dp = new long[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			long s = 0;
			for (int j = 1; j <= m; j++) {
				s = (s + A.get(i - 1).get(j - 1)) % mod;
				dp[i][j] = s;
			}
		}
		for (int i = 1; i <= m; i++) {
			long s = 0;
			for (int j = 1; j <= n; j++) {
				s = (s + dp[j][i]) % mod;
				dp[j][i] = s;
			}
		}

		for (int i = 0; i < B.size(); i++) {
			long r = dp[D.get(i)][E.get(i)] - dp[D.get(i)][C.get(i) - 1] - dp[B.get(i) - 1][E.get(i)]
					+ dp[B.get(i) - 1][C.get(i) - 1];
			while (r < 0) {
				r += mod;
			}
			res.add((int) (r % mod));
		}

		return res;

	}

	public int coinchange2(ArrayList<Integer> A, int B) {
		int dp[] = new int[B + 1];
		dp[0] = 1;
		for (int i = 0; i < A.size(); i++) {
			for (int j = A.get(i); j <= B; j++) {
				dp[j] = dp[j] + dp[j - A.get(i)];
				dp[j] = dp[j] % 1000007;
			}
		}
		return dp[B];
	}

	public int minCut(String A) {
		int l = A.length();
		boolean dp[][] = new boolean[l][l];
		for (int i = 0; i < l; i++) {
			dp[i][i] = true;
		}
		int cut[][] = new int[l][l];
		for (int i = 0; i < l; i++) {
			cut[i][i] = 0;
		}
		for (int len = 2; len <= l; len++) {
			for (int i = 0; i <= l - len; i++) {
				int j = len + i - 1;
				if (len == 2) {
					dp[i][j] = (A.charAt(i) == A.charAt(j));
				} else {
					dp[i][j] = A.charAt(i) == A.charAt(j) && dp[i + 1][j - 1];
				}
				if (dp[i][j]) {
					cut[i][j] = 0;
				} else {
					cut[i][j] = Integer.MAX_VALUE;
					for (int k = i; k < j; k++) {
						cut[i][j] = Math.min(cut[i][j], 1 + cut[i][k] + cut[k + 1][j]);
					}
				}
			}
		}
		return cut[0][l - 1];
	}

	public int solve(ArrayList<Integer> A) {
		Set<Integer> s = new HashSet<>();
		for (int i = 0; i < A.size(); i++) {
			s.add(A.get(i));
		}
		int res = -1;
		for (int i = 0; i < A.size(); i++) {
			for (int j = i + 1; j < A.size(); j++) {
				int n1 = A.get(i);
				int n2 = A.get(j);
				int n3 = A.get(i) + A.get(j);
				int cnt = 2;
				while (s.contains(n3)) {
					n1 = n2;
					n2 = n3;
					n3 = n1 + n2;
					cnt++;
				}
				res = Math.max(res, cnt);
			}
		}

		return res > 2 ? res : 0;
	}

	public int calculateMinimumHP(ArrayList<ArrayList<Integer>> A) {

		int m = A.size();
		int n = A.get(0).size();
		int[][] dp = new int[m][n];
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (i == m - 1 && j == n - 1) {
					dp[i][j] = Math.max(1, 1 - A.get(i).get(j));
				} else if (i == m - 1) {
					dp[i][j] = Math.max(1, dp[i][j + 1] - A.get(i).get(j));
				} else if (j == n - 1) {
					dp[i][j] = Math.max(1, dp[i + 1][j] - A.get(i).get(j));
				} else {
					dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - A.get(i).get(j));
				}
			}
		}
		return dp[0][0];
	}

	public int anytwo(String A) {
		int n = A.length();
		int dp[][] = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (A.charAt(i - 1) == A.charAt(j - 1) && i != j)
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
			}
		}
		return dp[n][n] >= 2 ? 1 : 0;
	}

	public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
		int s = a.size();
		for (int i = s - 2; i >= 0; i--) {
			for (int j = 0; j < a.get(i).size(); j++) {
				a.get(i).set(j, a.get(i).get(j) + Math.min(a.get(i + 1).get(j), a.get(i + 1).get(j + 1)));
			}
		}
		return a.get(0).get(0);
	}

	public int chordCnt(int A) {
		long[] ways = new long[A + 1];

		ways[0] = 1;
		ways[1] = 1;

		for (int i = 2; i <= A; i++) {
			for (int j = 0; j < i; j++) {
				ways[i] = (ways[i] + ways[j] * ways[i - j - 1]) % 1000000007;
			}
		}

		return (int) ways[A];
	}

	public int solve_waysNumbers(int A, int B) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		if (A == 1) {
			if (B > 0 && B <= 9) {
				return 1;
			}
			return 0;
		}
		long res = 0;
		long mod = 1000000007;
		for (int i = 1; i <= 9; i++) {
			if (B - i >= 0 && (A - 1) * 9 >= B - i) {
				long a = solve_waysNumber(A - 1, B - i, hm);
				res = (res + a) % mod;
			}
		}
		return (int) res;
	}

	public int solve_waysNumber(int A, int B, HashMap<String, Integer> hm) {
		if (A == 1) {
			if (B >= 0 && B <= 9) {
				return 1;
			}
			return 0;
		}
		long res = 0;
		long mod = 1000000007;
		for (int i = 0; i <= 9; i++) {
			if (B - i >= 0 && (A - 1) * 9 >= B - i) {
				String chk = (A - 1) + "_" + (B - i);

				int a = hm.containsKey(chk) ? hm.get(chk) : solve_waysNumber(A - 1, B - i, hm);
				hm.put(chk, a);
				res = (res + a) % mod;
			}
		}
		return (int) res;
	}

	public int solve_matrixChain(ArrayList<Integer> A) {
		int dp[][] = new int[A.size()][A.size()];
		for (int i = 0; i < A.size(); i++) {
			dp[i][i] = 0;
		}

		for (int len = 2; len < A.size(); len++) {
			for (int i = 1; i < A.size() - len + 1; i++) {
				int j = i + len - 1;
				if (j == A.size()) {
					continue;
				}
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + A.get(i - 1) * A.get(k) * A.get(j));
				}
			}
		}
		return dp[1][A.size() - 1];
	}

	public int solve_envelope(ArrayList<ArrayList<Integer>> A) {
		if (A == null) {
			return 0;
		}
		if (A.size() < 2) {
			return A.size();
		}
		A.sort(new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return o1.get(0) - o2.get(0);
			}
		});
		int dp[] = new int[A.size()];
		dp[0] = 1;
		int max = 1;
		for (int i = 1; i < A.size(); i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (A.get(j).get(0) < A.get(i).get(0) && A.get(j).get(1) < A.get(i).get(1)) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	public int maximalRectangle(ArrayList<ArrayList<Integer>> A) {
		if (A.size() == 0) {
			return 0;
		}
		int rows = A.size();
		int cols = A.get(0).size();

		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				A.get(i).set(j, A.get(i).get(j) == 0 ? 0 : A.get(i).get(j) + A.get(i - 1).get(j));
			}
		}
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < rows; i++) {
			res = Math.max(res, getMaxArea(A.get(i)));
		}
		return res;
	}

	int getMaxArea(ArrayList<Integer> A) {
		Stack<Integer> stk = new Stack<Integer>();
		int i = 0;
		int res = Integer.MIN_VALUE;
		while (i < A.size()) {
			final int ai = A.get(i);
			if (stk.isEmpty() || A.get(stk.peek()) <= ai) {
				stk.push(i);
				i++;
			} else {
				int ind = stk.pop();
				res = Math.max(res, (stk.isEmpty() ? i : i - stk.peek() - 1) * A.get(ind));
			}
		}
		while (!stk.isEmpty()) {
			int ind = stk.pop();
			res = Math.max(res, (stk.isEmpty() ? i : i - stk.peek() - 1) * A.get(ind));
		}
		return res;
	}

	public int LBSlength(final String A) {
		int res[] = new int[A.length()];
		int max = 0;
		for (int i = 0; i < A.length(); i++) {
			char a = A.charAt(i);
			if (a == '(') {
				res[i] = 0;
			} else if (a == ')') {
				if (i > 0) {
					if (A.charAt(i - 1) == '(') {
						res[i] = i > 1 ? res[i - 2] + 2 : 2;
					} else if (i - res[i - 1] - 1 >= 0 && A.charAt(i - res[i - 1] - 1) == '(') {
						res[i] = res[i - 1] + 2;
						res[i] = i - res[i - 1] - 2 >= 0 ? res[i - res[i - 1] - 2] + res[i] : res[i];
					} else {
						res[i] = 0;
					}
					max = Math.max(max, res[i]);
				} else {
					res[i] = 0;
				}
			}
		}
		return max;
	}

	public int solve_longestCommonSubsequence(String A, String B) {
		int[][] dp = new int[A.length() + 1][B.length() + 1];
		for (int i = 1; i <= A.length(); i++) {
			dp[i][0] = 0;
		}
		for (int i = 1; i <= B.length(); i++) {
			dp[0][i] = 0;
		}
		for (int i = 1; i <= A.length(); i++) {
			for (int j = 1; j <= B.length(); j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[A.length()][B.length()];
	}

	public int firstUniqChar(String s) {
		char[] a = s.toCharArray();
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		for (int i = 0; i < a.length; i++) {
			hm.put(a[i], hm.getOrDefault(a[i], 0) + 1);
		}
		for (int i = 0; i < a.length; i++) {
			if (hm.get(a[i]) == 1) {
				return i;
			}
		}
		return -1;

	}

	public int isInterleave(String A, String B, String C) {
		char[] d = (A + B).toCharArray();
		Arrays.sort(d);
		char[] e = C.toCharArray();
		Arrays.sort(e);

		int a = solve_longestCommonSubsequence(A, C);
		int b = solve_longestCommonSubsequence(B, C);
		String D = new String(d);
		String E = new String(e);
		if (D.equals(E) && a == A.length() && b == B.length()) {
			return 1;
		}
		return 0;
	}

	// knapsack

	public int solve_knapsack(ArrayList<Integer> A, ArrayList<Integer> B, int C) {

		int dp[][] = new int[B.size() + 1][C + 1];

		for (int i = 1; i <= B.size(); i++) {
			for (int j = 1; j <= C; j++) {
				dp[i][j] = Math.max(dp[i - 1][j],
						j >= B.get(i - 1) ? (dp[i - 1][j - B.get(i - 1)] + A.get(i - 1)) : dp[i - 1][j]);
			}
		}
		return dp[B.size() + 1][C + 1];

	}

	public int solve_unboundedKnapsack(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
		int dp[] = new int[A + 1];

		for (int i = 1; i <= A; i++) {
			for (int j = 0; j < C.size(); j++) {
				if (C.get(j) <= i) {
					dp[i] = Math.max(dp[i], dp[i - C.get(j)] + B.get(j));
				}
			}
		}
		return dp[A];
	}

	public int solve_party(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
		int sum = 0;
		int ind_one=B.indexOf(1);
		int cost_one=C.get(ind_one);
		for (int i = 0; i < A.size(); i++) {
			int D = A.get(i);
			int dp[] = new int[D + 1];
			for (int j = 1; j <= D; j++) {
				dp[j]=j*cost_one;
				for (int k = 0; k < B.size(); k++) {
					if (B.get(k) <= j) {
						dp[j] = Math.min(dp[j], dp[j - B.get(k)] + C.get(k));
					}
				}
			}
			sum+=dp[D];
		}
		return sum;
	}

	public int solve_candies(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C, int D) {
		int dp[] = new int[D + 1];

		for (int i = 1; i <= D; i++) {
			for (int j = 0; j < C.size(); j++) {
				if (C.get(j) <= i) {
					dp[i] = Math.max(dp[i], dp[i - C.get(j)] + A.get(j) * B.get(j));
				}
			}
		}
		return dp[D];

	}

	public int solve_knapsack_fliparray(final List<Integer> A) {
		int sum = 0;
		for (int i = 0; i < A.size(); i++) {
			sum += A.get(i);
		}
		int dp[][] = new int[A.size() + 1][sum + 1];

		for (int i = 1; i <= A.size(); i++) {
			for (int j = 1; j <= sum; j++) {
				dp[i][j] = Math.max(dp[i - 1][j],
						j >= 2 * A.get(i - 1) ? (dp[i - 1][j - 2 * A.get(i - 1)] + 2 * A.get(i - 1)) : dp[i - 1][j]);
			}
		}
		return sum - dp[A.size()][sum];

	}

	public static void main(String[] args) {
		DP d = new DP();
		System.out.println(d.isInterleave("noUdRp97xFvpifeSXGwOjcVNhHo9N2D", "6iZItw9A3fj86uYx04tvWKLtl9BK",
				"n6ioUdRpZ97ItwxF9Av3fj86upYxif0eS4XtvWKLtlG9wOBKjcVNhHo9N2D"));
	}

}
