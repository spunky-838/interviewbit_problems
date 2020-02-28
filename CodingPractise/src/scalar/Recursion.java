package scalar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Recursion {

	public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (A.size() == 0) {
			return res;
		}
		if (A.size() == 1) {
			res.add(A);
			return res;
		}

		for (int i = 0; i < A.size(); i++) {
			ArrayList<Integer> B = new ArrayList<Integer>(A);
			int a = B.remove(i);
			ArrayList<ArrayList<Integer>> r = permute(B);
			for (int j = 0; j < r.size(); j++) {
				r.get(j).add(0, a);
			}
			res.addAll(r);
		}

		return res;
	}

	public ArrayList<ArrayList<Integer>> permute_duplicates(ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		HashSet<Integer> hs = new HashSet<Integer>();
		if (A.size() == 0) {
			return res;
		}
		if (A.size() == 1) {
			res.add(A);
			return res;
		}

		for (int i = 0; i < A.size(); i++) {
			ArrayList<Integer> B = new ArrayList<Integer>(A);
			int a = B.remove(i);
			if (hs.contains(a)) {
				continue;
			}
			hs.add(a);
			ArrayList<ArrayList<Integer>> r = permute(B);
			for (int j = 0; j < r.size(); j++) {
				r.get(j).add(0, a);
			}
			res.addAll(r);
		}

		return res;
	}

	public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
		Collections.sort(A);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		res.add(new ArrayList<Integer>());
		if (A.size() == 0) {
			return res;
		}
		if (A.size() == 1) {
			res.add(A);
			return res;
		}
		int a = A.remove(0);
		ArrayList<ArrayList<Integer>> r = subsets(A);
		for (int i = 0; i < r.size(); i++) {
			ArrayList<Integer> each = new ArrayList<Integer>(r.get(i));
			each.add(0, a);
			res.add(each);
		}
		res.addAll(r);
		return res;
	}

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
				ArrayList<Integer> dup = new ArrayList<Integer>(a);
				ArrayList<ArrayList<Integer>> c = combinationSum(dup, b - k);
				for (int i = 0; i < c.size(); i++) {
					c.get(i).add(0, k);
				}
				res.addAll(c);
			}
		}
		return res;
	}

	
	ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	public ArrayList<ArrayList<Integer>> combinationSum_2(ArrayList<Integer> a, int b) {
		Collections.sort(a);
		ArrayList<Integer> current = new ArrayList<Integer>();
		sumProcess(a, b, 0, current);
		return res;
	}

	public void sumProcess(ArrayList<Integer> a, int b, int st, ArrayList<Integer> cur) {
		if (b == 0) {
			res.add(new ArrayList<Integer>(cur));
			return;
		}
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i = st; i < a.size() && b >= a.get(i); ++i) {
			if (hs.contains(a.get(i))) {
				continue;
			}
			hs.add(a.get(i));
			cur.add(a.get(i));
			sumProcess(a, b-a.get(i), i + 1, cur);
			cur.remove(cur.size() - 1);
		}
	}

	public static void main(String args[]) {
		Recursion r = new Recursion();
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		System.out.println();
	}
}
