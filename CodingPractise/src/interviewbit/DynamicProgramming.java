package interviewbit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DynamicProgramming {

	public int longestSubsequenceLength(final List<Integer> A) {
		int[] inc = new int[A.size()];
		int[] dec = new int[A.size()];

		for (int i = 0; i < A.size(); i++) {
			inc[i] = 1;
			for (int j = 0; j < i; j++) {
				if (A.get(j) < A.get(i) && inc[i] < inc[j] + 1) {
					inc[i] = inc[j] + 1;
				}
			}
		}
		for (int i = A.size() - 1; i >= 0; i--) {
			dec[i] = 1;
			for (int j = A.size() - 1; j > i; j--) {
				if (A.get(j) < A.get(i) && dec[i] < dec[j] + 1) {
					dec[i] = dec[j] + 1;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < A.size(); i++) {
			max = Math.max(max, inc[i] + dec[i] - 1);
		}
		return max;
	}

	public int jump(ArrayList<Integer> A) {
		int[] min = new int[A.size()];
		min[0] = 0;
		for (int i = 0; i < A.size(); i++) {
			min[i] = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (A.get(j) >= i - j && min[j] != Integer.MAX_VALUE && min[j] + 1 < min[i]) {
					min[i] = min[j] + 1;
				}
			}
		}
		return min[A.size() - 1] == Integer.MAX_VALUE ? -1 : min[A.size() - 1];

	}

	public int jump_2(ArrayList<Integer> A) {
		if (A.size() == 1) {
			return 0;
		}
		int jumps = 1;
		int mr = A.get(0);
		int lr = A.get(0);
		for (int i = 1; i < A.size(); i++) {
			if (i > mr) {
				return -1;
			}
			if (i > lr) {
				jumps++;
				lr = mr;
			}
			mr = Math.max(mr, A.get(i) + i);
		}
		return jumps;
	}

	public int solve_LAP(final List<Integer> A) {
		if (A.size() < 3) {
			return A.size();
		}
		int res = 2;
		for (int i = 0; i < A.size(); i++) {
			int cnt = 1;
			for (int j = i + 1; j < A.size(); j++) {
				int diff = A.get(j) - A.get(i);
				cnt++;
				int k = j + 1;
				while (k < A.size()) {
					if (A.get(k) - A.get(j) == diff) {
						cnt++;
						diff += A.get(j) - A.get(i);
					}
					k++;
				}
				res = Math.max(res, cnt);
				cnt = 1;
			}
		}
		return res;
	}

	public int solve_LAP_2(final List<Integer> A) {
		int n = A.size();
		int max = 1;
		if (n > 1) {
			max = 2;
		}
		ArrayList<HashMap<Integer, Integer>> dp = new ArrayList<HashMap<Integer, Integer>>();
		for (int i = 0; i < n; i++) {
			dp.add(new HashMap<Integer, Integer>());
		}
		for (int i = 1; i < A.size(); i++) {
			HashMap<Integer, Integer> hmi = dp.get(i);
			for (int j = 0; j < i; j++) {
				HashMap<Integer, Integer> hmj = dp.get(j);
				if (hmj.containsKey(A.get(i) - A.get(j))) {

					hmi.put(A.get(i) - A.get(j), hmj.get(A.get(i) - A.get(j)) + 1);
					max = Math.max(hmi.get(A.get(i) - A.get(j)), max);
				} else {
					// System.out.println("*");
					hmi.put(A.get(i) - A.get(j), 2);
				}
			}
		}
		return max;
	}

	public int findZeroSumSubArrays(int arr[]) {

		HashMap<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
		int sum = 0;
		int c = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (sum == 0)
				c++;
			if (sumMap.containsKey(sum)) {
				c += sumMap.get(sum);
			}
			if (sumMap.containsKey(sum)) {
				sumMap.put(sum, sumMap.get(sum) + 1);
			} else {
				sumMap.put(sum, 1);
			}
		}
		return c;
	}

	public int solve(ArrayList<ArrayList<Integer>> A) {
		ArrayList<ArrayList<Integer>> countArr = new ArrayList<>();
		for (int i = 0; i < A.size(); i++) {
			ArrayList<Integer> row = A.get(i);
			ArrayList<Integer> cRow = new ArrayList<>();
			for (int j = 0; j < row.size(); j++) {
				int count = 0;
				if (row.get(j) == 1) {
					count = 1;
					if (i != 0) {
						count += countArr.get(i - 1).get(j);
					}
				}
				cRow.add(count);
			}
			countArr.add(cRow);
		}
		for (ArrayList<Integer> row : countArr) {
			row.sort(Collections.reverseOrder());
		}
		int maxArea = 0;
		for (ArrayList<Integer> row : countArr) {
			for (int j = 0; j < row.size(); j++) {
				maxArea = Math.max(maxArea, (j + 1) * row.get(j));
			}
		}
		return maxArea;
	}

	private long MOD = 1000000007L;

	public int solve_nDigitSum(int A, int B) {
		long[][] dp = new long[A + 1][B + 1];
		for (int i = 0; i <= A; i++) {
			for (int j = 0; j <= B; j++) {
				dp[i][j] = -1;
			}
		}
		long count = 0;
		for (int i = 1; i <= 9; i++) {
			if (B - i >= 0) {
				count += solve_nDigitSum(A - 1, B - i, dp) % MOD;
			}
		}
		return (int) (count % MOD);
	}

	private long solve_nDigitSum(int A, int B, long[][] dp) {

		if (A == 0) {
			return B == 0 ? 1 : 0;
		}

		if (dp[A][B] != -1)
			return dp[A][B];

		long count = 0;

		for (int i = 0; i < 10; i++) {
			if (B - i >= 0) {
				count += solve_nDigitSum(A - 1, B - i, dp);
			}
		}

		dp[A][B] = count % MOD;
		return dp[A][B];

	}

	public int solve_zeromatrices(ArrayList<ArrayList<Integer>> A) {

		if (A.size() == 0)
			return 0;
		int c = 0;
		int rows = A.size();
		int cols = A.get(0).size();

		for (int left = 0; left < cols; left++) {
			int[] temp = new int[rows];
			java.util.Arrays.fill(temp, 0);
			for (int right = left; right < cols; right++) {
				for (int i = 0; i < rows; i++) {
					temp[i] += A.get(i).get(right);
				}
				int x = findZeroSumSubArrays(temp);
				c += x;
			}
		}

		return c;
	}
	
	public int adjacent(ArrayList<ArrayList<Integer>> A) {
        if(A.size()==0){
            return 0;
        }
        int n = A.get(0).size();
        int[][] dp = new int[n][2];
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Math.max(A.get(0).get(i),A.get(1).get(i));
        }
        dp[n-1][0] = 0;
        dp[n-1][1] = arr[n-1];
        for(int i=n-2;i>=0;i--){
            dp[i][0] = Math.max(dp[i+1][0],dp[i+1][1]);
            dp[i][1] = dp[i+1][0]+arr[i];
        }
        return Math.max(dp[0][0],dp[0][1]);
    }
	
	// editing strings
		public int minDistance(String A, String B) {
			int m = A.length() + 1;
			int n = B.length() + 1;
			int[][] db = new int[m][n];
			for (int i = 0; i < m; i++) {
				db[i][0] = i;
			}
			for (int j = 0; j < n; j++) {
				db[0][j] = j;
			}
			for (int i = 1; i < m; i++) {
				for (int j = 1; j < n; j++) {
					if (A.charAt(i - 1) == B.charAt(j - 1)) {
						db[i][j] = db[i - 1][j - 1];
					} else {
						db[i][j] = Math.min(Math.min(db[i - 1][j] + 1, db[i][j - 1] + 1), db[i - 1][j - 1] + 1);
					}
				}
			}
			return db[m - 1][n - 1];
		}

		int memo[][];
		public int minDistance_2(String a, String b) {
			memo = new int[a.length()][b.length()];
			for (int[] row : memo)
				java.util.Arrays.fill(row, -1);
			return editDist(a, b, 0, 0);
		}
		public int editDist(String a, String b, int i, int j) {
			if (i == a.length() && j == b.length())
				return 0;
			if (i == a.length())
				return b.length() - j;
			if (j == b.length())
				return a.length() - i;
			if (memo[i][j] != -1)
				return memo[i][j];
			int res = Integer.MAX_VALUE;
			if (a.charAt(i) == b.charAt(j))
				res = editDist(a, b, i + 1, j + 1);
			res = Math.min(res, 1 + editDist(a, b, i + 1, j + 1));
			res = Math.min(res, 1 + editDist(a, b, i + 1, j));
			res = Math.min(res, 1 + editDist(a, b, i, j + 1));
			return memo[i][j] = res;
		}
//		ArrayList<String> wordBreakres = new ArrayList<String>();
//		HashSet<String> words = new HashSet<String>();
//		public ArrayList<String> wordBreak(String A, ArrayList<String> B) {
//			for(int i=0;i<B.size();i++) {
//				words.add(B.get(i));
//			}
//			processWordBreak(new StringBuffer(),0,1,A);
//			return wordBreakres;
//	    }
//		
//		public void processWordBreak(StringBuffer a,int st,int ed,String p) {
//			if(st==p.length()) {
//				wordBreakres.add(a.toString());
//				return;
//			}
//			for(int i=ed;i<=p.length();i++) {
//				String s = p.substring(st, i);
//				if(words.contains(s)) {
//					StringBuffer b = new StringBuffer(a);
//					if(b.length()>0) {
//						b.append(" "+s);
//					}else {
//						b.append(s);
//					}					
//					processWordBreak(b,i,i+1, p);
//				}
//			}
//			return;
//		}
		HashSet<String> words = new HashSet<String>();
		public int wordBreak(String A, ArrayList<String> B) {
			 boolean[] dp = new boolean[A.length()];
			for(int i=0;i<B.size();i++) {
				words.add(B.get(i));
			}
			return processWordBreak(0,A,dp)?1:0;
	    }
		
		public boolean processWordBreak(int st,String p,boolean[] dp) {
			if(st==p.length()) {
				return true;
			}
			if(dp[st]) return dp[st];
			boolean res=false;
			for(int i=st+1;i<=p.length();i++) {
				String s = p.substring(st, i);
				if(words.contains(s) && processWordBreak(i, p,dp)) {
					res = true;
					break;
				}
			}
			dp[st]=res;
			return res;
		}

	public static void main(String args[]) {
		List<Integer> A = new ArrayList<Integer>(java.util.Arrays.asList(100, 10, 8, 300, 6, 1, 4, 2));
		DynamicProgramming dp = new DynamicProgramming();
//		catsanddog
//		5 cat cats and sand dog
		String a ="myinterviewtrainer";
		ArrayList<String> s = new ArrayList<String>(java.util.Arrays.asList(new String[] {"trainer","my","interview"})) ;
		System.out.println(dp.wordBreak(a, s));

	}

}
