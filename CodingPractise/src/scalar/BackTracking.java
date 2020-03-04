package scalar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BackTracking {
	public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
		Collections.sort(a);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		HashSet<Integer> hs = new HashSet<Integer>();
		if (a.size() == 0) {
			return res;
		}
		if (a.size() == 1) {
			if (a.get(0) == b) {
				res.add(a);
			}
			return res;
		}
		while (a.size() > 0) {
			int k = a.remove(0);
			if (hs.contains(k)) {
				continue;
			}
			hs.add(k);
			if (k == b) {
				res.add(new ArrayList<Integer>(java.util.Arrays.asList(new Integer[] { k })));
			} else if (k < b) {
				ArrayList<Integer> dup = new ArrayList<Integer>();
				dup.add(k);
				dup.addAll(a);
				ArrayList<ArrayList<Integer>> c = combinationSum(dup, b - k);
				for (int j = 0; j < c.size(); j++) {
					c.get(j).add(0, k);
				}
				res.addAll(c);
			}
		}
		return res;
	}

	public int totalPaths = 0;

	public int solve_matrixPaths(ArrayList<ArrayList<Integer>> A) {
		int x = 0;
		int y = 0;
		int cntz = 0;
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < A.get(0).size(); j++) {
				if (A.get(i).get(j) == 0) {
					cntz++;
				} else if (A.get(i).get(j) == 1) {
					x = i;
					y = j;
				}
			}
		}
		A.get(x).set(y, -1);
		matrix_backtrack(x, y, cntz, A);
		return totalPaths;
	}

	public void matrix_backtrack(int x, int y, int cnt, ArrayList<ArrayList<Integer>> A) {

		if (A.get(x).get(y) == 2) {
			if (cnt == 0) {
				totalPaths++;
			}
			return;
		}
		if (x + 1 < A.size() && A.get(x + 1).get(y) != -1) {
			int d = A.get(x + 1).get(y);
			if (d == 0) {
				--cnt;
			}
			if (d != 2) {
				A.get(x + 1).set(y, -1);
			}
			matrix_backtrack(x + 1, y, cnt, A);
			A.get(x + 1).set(y, d);
			if (d == 0) {
				++cnt;
			}
		}
		if (x - 1 >= 0 && A.get(x - 1).get(y) != -1) {
			int d = A.get(x - 1).get(y);
			if (d == 0) {
				--cnt;
			}
			if (d != 2) {
				A.get(x - 1).set(y, -1);
			}
			matrix_backtrack(x - 1, y, cnt, A);
			A.get(x - 1).set(y, d);
			if (d == 0) {
				++cnt;
			}
		}
		if (y + 1 < A.get(0).size() && A.get(x).get(y + 1) != -1) {
			int d = A.get(x).get(y + 1);
			if (d == 0) {
				--cnt;
			}
			if (d != 2) {
				A.get(x).set(y + 1, -1);
			}
			matrix_backtrack(x, y + 1, cnt, A);
			A.get(x).set(y + 1, d);
			if (d == 0) {
				++cnt;
			}
		}
		if (y - 1 >= 0 && A.get(x).get(y - 1) != -1) {
			int d = A.get(x).get(y - 1);
			if (d == 0) {
				--cnt;
			}
			if (d != 2) {
				A.get(x).set(y - 1, -1);
			}
			matrix_backtrack(x, y - 1, cnt, A);
			A.get(x).set(y - 1, d);
			if (d == 0) {
				++cnt;
			}
		}
		return;
	}

	HashMap<Integer, HashSet<Integer>> rows = new HashMap<Integer, HashSet<Integer>>();
	HashMap<Integer, HashSet<Integer>> cols = new HashMap<Integer, HashSet<Integer>>();
	HashMap<Integer, HashSet<Integer>> cells = new HashMap<Integer, HashSet<Integer>>();
	boolean solved = false;

	public void solveSudoku(ArrayList<ArrayList<Character>> a) {
		for (int i = 0; i < 9; i++) {
			rows.put(i, new HashSet<Integer>());
			cols.put(i, new HashSet<Integer>());
			cells.put(i, new HashSet<Integer>());
		}
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < a.get(0).size(); j++) {
				if (a.get(i).get(j) != '.') {
					int d = Character.getNumericValue(a.get(i).get(j));
					rows.get(i).add(d);
					cols.get(j).add(d);
					cells.get((i / 3) * 3 + j / 3).add(d);
				}
			}
		}
		solve_Sudoku(a);
	}

	public boolean isPossible(int i, int x, int y) {
		if (!rows.get(x).contains(i) && !cols.get(y).contains(i) && !cells.get((x / 3) * 3 + y / 3).contains(i)) {
			return true;
		}
		return false;
	}

	public void solve_Sudoku(ArrayList<ArrayList<Character>> a) {
		int x = -1, y = -1;
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < a.get(0).size(); j++) {
				if (a.get(i).get(j) == '.') {
					x = i;
					y = j;
				}
			}
		}
		if (x == -1 && y == -1) {
			solved = true;
			return;
		}
		for (int i = 1; !solved && i < 10; i++) {
			if (isPossible(i, x, y)) {
				a.get(x).set(y, (char) (i + '0'));
				rows.get(x).add(i);
				cols.get(y).add(i);
				cells.get((x / 3) * 3 + y / 3).add(i);
				solve_Sudoku(a);
				if (solved) {
					return;
				}
				a.get(x).set(y, '.');
				rows.get(x).remove(i);
				cols.get(y).remove(i);
				cells.get((x / 3) * 3 + y / 3).remove(i);
			}
		}
		return;
	}

	ArrayList<ArrayList<String>> res_NQueens = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<String>> solveNQueens(int a) {
		boolean[][] V = new boolean[a][a];
		solve_queens(V, 0);
		return res_NQueens;
	}
	private void solve_queens(boolean[][] mat, int col) {
		if (col >= mat.length) {
			ArrayList<String> res = new ArrayList<>();
			for (boolean[] booleans : mat) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < mat.length; j++) {
					if (booleans[j]) {
						sb.append('Q');
					} else {
						sb.append('.');
					}
				}
				res.add(sb.toString());
			}
			if (!res_NQueens.contains(res)) {
				res_NQueens.add(res);
			}
			return;
		}
		for (int i = 0; i < mat.length; i++) {
			if (isPossible(mat, i, col)) {
				mat[i][col] = true;
				solve_queens(mat, col + 1);
				mat[i][col] = false;
			}
		}
	}

	private boolean isPossible(boolean[][] V, int i, int j) {
		int n = V.length;
		for (int k = 0; k < n; k++) {
			if (V[i][k] || V[k][j]) {
				return false;
			}
		}
		for (int k = 1; k <= n; k++) {
			if (i + k >= 0 && j + k >= 0 && i + k < n && j + k < n && V[i + k][j + k]) {
				return false;
			}
			if (i - k >= 0 && j + k >= 0 && i - k < n && j + k < n && V[i - k][j + k]) {
				return false;
			}
			if (i + k >= 0 && j - k >= 0 && i + k < n && j - k < n && V[i + k][j - k]) {
				return false;
			}
			if (i - k >= 0 && j - k >= 0 && i - k < n && j - k < n && V[i - k][j - k]) {
				return false;
			}
		}
		return true;
	}

	
	String[] dailpad = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
	ArrayList<String> ans_letterCombinations = new ArrayList<String>();
	public ArrayList<String> letterCombinations(String A) {
		buildString(A, 0, "");
		return ans_letterCombinations;
	}
	public void buildString(String A, int index, String str) {
		if (index >= A.length()) {
			ans_letterCombinations.add(str);
			return;
		}
		int idx = (int) (A.charAt(index) - '0');
		String temp = dailpad[idx];
		for (int j = 0; j < temp.length(); j++) {
			buildString(A, index + 1, str + temp.charAt(j));
		}
	}

	
	ArrayList<ArrayList<Integer>> res_combine = new ArrayList<>();
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		if (k > n) {
			return res_combine;
		}
		rec(n, 0, k, new ArrayList<Integer>());
		return res_combine;
	}
	public void rec(int n, int last, int k, ArrayList<Integer> ans) {
		if (k == 0) {
			res_combine.add(new ArrayList<Integer>(ans));
			return;
		}
		for (int i = last + 1; i <= n; i++) {
			ans.add(i);
			rec(n, i, k - 1, ans);
			ans.remove(ans.size() - 1);
		}
	}

	public String getPermutation(int A, int B) {
		int ind = 1;
		for (ind = 1; ind <= A; ind++) {
			if (fact(ind) >= B)
				break;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= A - ind; i++) {
			sb.append(i);
		}
		ArrayList<Integer> perm = new ArrayList<>(A);
		for (int i = 1; i <= ind; i++) {
			perm.add(i);
		}
		for (int i = 1; i <= ind; i++) {
			int t = (int) ((B - 1) / fact(ind - i));
			sb.append(perm.get(t) + (A - ind));
			perm.remove(t);
			B = (int) (B - t * (fact(ind - i)));
		}
		return sb.toString();
	}

	public String getPermutation0(int A, int B) {
		int ind = B;
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> perm = new ArrayList<>(A);
		for (int i = 1; i <= A; i++) {
			perm.add(i);
		}
		for (int i = 1; i <= A; i++) {
			ind = (int) ((B - 1) / fact(A - i));
			sb.append(perm.get(ind));
			perm.remove(ind);
			B = (int) (B - ind * fact(A - i));
		}
		return sb.toString();
	}

	public long fact(int n) {
		long f = 1;
		for (int i = 1; i <= n; i++) {
			f *= i;
		}
		return f;
	}

	public String getPermutation_(int n, int k) {
		if (k > fact(n)) {
			return "";
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			list.add(i);
		}
		return getPermutation(list, k - 1);
	}

	public String getPermutation(ArrayList<Integer> A, int B) {
		int n = A.size();
		if (n == 0)
			return "";
		long fact_n = fact(n - 1);
		int index = (int) (B / fact_n);
		int num = A.get(index);
		A.remove(index);
		B %= fact_n;
		return num + getPermutation(A, B);
	}
	public int res_sizlets=0;
	public int solve_sixlets(ArrayList<Integer> A, int B) {
		Collections.sort(A);
		if(A.size()<B) {
			return res_sizlets;
		}
		sixlets_backtrack(A, B, 0,0);
		return res_sizlets;
    }
	public void sixlets_backtrack(ArrayList<Integer> A,int size,int s,int ind) {
		if(size==0) {
			if(s<=1000) {
				++res_sizlets;
				return;
			}else {
				return;
			}
		}
		for(int i=ind;i<A.size();i++){
			if(s+A.get(i)<=1000) {
				s+=A.get(i);
				--size;
				sixlets_backtrack(A, size, s, i+1);
				++size;
				s-=A.get(i);
			}else {
				return;
			}
		}
	}
	
	public HashSet<String> res_invalidParanthesis= new HashSet<String>();
	public ArrayList<String> solve_invalidParanthesis(String A) {
		int min =findMin(A);
		invalidParanthesis_backtrack(A, min, 0);
	
		return new ArrayList<String>(res_invalidParanthesis);
    }
	
	public void invalidParanthesis_backtrack(String A,int ops,int ind) {
		if(ops==0) {
			if(isValid(A)) {
				res_invalidParanthesis.add(A);
				return;
			}else {
				return;
			}
		}
		for(int i=ind;i<A.length();i++){
			String c=A.substring(0,i)+A.substring(i+1);
			ops--;
			invalidParanthesis_backtrack(c, ops, i);
			ops++;
		}
	}
	public boolean isValid(String a) {
		int buf=0;
		for(int i=0;i<a.length();i++) {
			if(a.charAt(i)=='(') {
				buf++;
			}else if(a.charAt(i)==')'){
				buf--;
			}
			if(buf<0) {return false;}
		}
		return buf==0?true:false;
	}
	public int findMin(String a) {
		int min=0;
		int buf=0;
		for(int i=0;i<a.length();i++) {
			if(a.charAt(i)=='(') {
				buf++;
			}else if(a.charAt(i)==')'){
				if(buf==0) {
					min++;
				}else {
					buf--;
				}
			}
		}
		min+=buf;
		return min;
	}
	
	
	
	public static void main(String args[]) {
		HashMap<Integer, HashSet<Integer>> rows = new HashMap<Integer, HashSet<Integer>>();
		rows.getOrDefault(1, new HashSet<Integer>()).add(1);
		BackTracking b = new BackTracking();
		ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> r1 = new ArrayList<Integer>(Arrays.asList(new Integer[] { 2, -1 }));
		ArrayList<Integer> r2 = new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 0 }));
		ArrayList<Integer> r3 = new ArrayList<Integer>(Arrays.asList(new Integer[] { -1, 1 }));
		A.add(r1);
		A.add(r2);
		A.add(r3);
		ArrayList<Integer> a=new ArrayList<Integer>(Arrays.asList(new Integer[] {508, 503, 412, 895, 256, 89, 245, 567, 9, 123}));
		System.out.println(b.solve_invalidParanthesis(")((p"));
	}

}
