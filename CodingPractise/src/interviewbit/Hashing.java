package interviewbit;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Hashing {
	public int colorful(int A) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		HashSet<Integer> set = new HashSet<Integer>();
		while (A > 0) {
			digits.add(A % 10);
			A /= 10;
		}
		for (int i = 0; i < digits.size(); i++) {
			int m = digits.get(i);
			if (set.contains(m)) {
				return 0;
			} else {
				set.add(m);
			}
			for (int j = i + 1; j < digits.size(); j++) {
				m *= digits.get(j);
				if (set.contains(m)) {
					return 0;
				} else {
					set.add(m);
				}
			}
		}
		return 1;
	}

	public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
		HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
		for (int i = 0; i < A.size(); i++) {
			char[] a = A.get(i).toCharArray();
			java.util.Arrays.sort(a);
			String str = new String(a);
			ArrayList<Integer> itm = new ArrayList<Integer>();
			if (map.containsKey(str)) {
				itm.addAll(map.get(str));
			}
			itm.add(i + 1);
			map.put(str, itm);
		}
		return new ArrayList<ArrayList<Integer>>(map.values());
	}

	public String minWindow(String A, String B) {
		String res = "";
		HashMap<Character, Integer> hmb = new HashMap<Character, Integer>();
		HashMap<Character, Integer> hmp = new HashMap<Character, Integer>();
		for (int i = 0; i < B.length(); i++) {
			hmb.put(B.charAt(i), hmb.containsKey(B.charAt(i)) ? hmb.get(B.charAt(i)) + 1 : 1);
		}
		int cnt = 0, ind = 0, min = A.length() + 1;
		for (int i = 0; i < A.length(); i++) {
			hmp.put(A.charAt(i), hmp.containsKey(A.charAt(i)) ? hmp.get(A.charAt(i)) + 1 : 1);

			if (hmb.get(A.charAt(i)) != null && hmp.get(A.charAt(i)) <= hmb.get(A.charAt(i))) {
				cnt++;
			}
			if (cnt == B.length()) {
				while (hmb.get(A.charAt(ind)) == null || hmp.get(A.charAt(ind)) > hmb.get(A.charAt(ind))) {
					hmp.put(A.charAt(ind), hmp.get(A.charAt(ind)) - 1);
					ind++;
				}
				if (i - ind + 1 < min) {
					min = i - ind + 1;
					res = A.substring(ind, i + 1);
				}
			}
		}
		return res;
	}

	// 1,1 2,2 y=x+
	public int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
		if (a.isEmpty() || b.isEmpty()) {
			return 0;
		}
		HashMap<String, Integer> slope = new HashMap<String, Integer>();
		int max = 0;
		for (int i = 0; i < a.size(); i++) {
			int x1 = a.get(i);
			int y1 = b.get(i);
			int duplicates = 0;
			int localMax = 0;
			slope.clear();
			for (int j = i + 1; j < a.size(); j++) {
				String slp = "";
				int x2 = a.get(j);
				int y2 = b.get(j);
				if (x1 == x2 && y1 == y2) {
					duplicates++;
					continue;
				} else if (x1 == x2) {
					slp = "Vertical";
				} else if (y1 == y2) {
					slp = "Horizontal";
				} else {
					slp = String.valueOf((1.0) * (y2 - y1) / (x2 - x1));
				}
				int count = slope.getOrDefault(slp, 0);
				slope.put(slp, count + 1);
				localMax = Math.max(localMax, count + 1);
			}
			localMax += duplicates;
			max = Math.max(localMax, max);
		}
		return max + 1;
	}

	public ArrayList<Integer> findSubstring(String A, final List<String> B) {
		int l = B.size();
		int w = B.get(0).length();
		int n = A.length();
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (n < l * w) {
			return result;
		}
		for (int left = 0, right = l * w - 1; left < n && right < n; left++, right++) {
			String subString = A.substring(left, right + 1);
			HashMap<String, Integer> strHashMap = new HashMap<>();
			boolean broke = false;
			for (int i = 0; i < subString.length() / w; i++) {
				String sub = subString.substring(i * w, i * w + w);
				if (strHashMap.containsKey(sub)) {
					int incrementedFreq = strHashMap.get(sub) + 1;
					strHashMap.put(sub, incrementedFreq);
				} else {
					strHashMap.put(sub, 1);
				}
			}
			for (int i = 0; i < l; i++) {
				if (!strHashMap.containsKey(B.get(i)) || strHashMap.get(B.get(i)) == 0) {
					broke = true;
					break;
				} else {
					int derementedFreq = strHashMap.get(B.get(i)) - 1;
					strHashMap.put(B.get(i), derementedFreq);
				}
			}
			if (broke) {
				continue;
			}
			result.add(left);
		}
		return result;
	}

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(0);
		a.add(1);
		ArrayList<Integer> b = new ArrayList<Integer>();
		b.add(4);
		b.add(1);
		b.add(-1);
		System.out.println((new Hashing()).maxPoints(a, b));
	}
}
