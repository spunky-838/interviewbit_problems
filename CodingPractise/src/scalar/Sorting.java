package scalar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Sorting {
	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	public ArrayList<Interval> merge_intervals(ArrayList<Interval> intervals) {
		ArrayList<Interval> res = new ArrayList<Sorting.Interval>();
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});

		for (int i = 0; i < intervals.size(); i++) {
			if (res.isEmpty()) {
				res.add(intervals.get(i));
			} else {
				Interval last = res.get(res.size() - 1);
				if (last.end >= intervals.get(i).start) {
					last.end = last.end > intervals.get(i).end ? last.end : intervals.get(i).end;
					res.set(res.size() - 1, last);
				} else {
					res.add(intervals.get(i));
				}
			}
		}
		return res;

	}

	public int countInversions(ArrayList<Integer> A) {
		ArrayList<Integer> B = new ArrayList<Integer>(A);
		Collections.sort(B);
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
		for (int i = 0; i < B.size(); i++) {
			m.put(B.get(i), i);
		}
		int inversions = 0;
		for (int i = 0; i < A.size(); i++) {
			inversions += m.get(A.get(i)) < i ? i - m.get(A.get(i)) : 0;
		}
		return inversions;

	}

	class ListNode {
		public int val;
		public ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode mergeTwoLists(ListNode A, ListNode B) {
		ListNode res = null;
		if (A != null && B != null) {
			if (A.val <= B.val) {
				res = new ListNode(A.val);
				A = A.next;
			} else {
				res = new ListNode(B.val);
				B = B.next;
			}
		}
		ListNode res1 = res;
		while (A != null || B != null) {
			if (A != null && B != null) {
				if (A.val <= B.val) {
					res1.next = A;
					A = A.next;
				} else {
					res1.next = B;
					B = B.next;
				}
			} else if (A != null) {
				res1.next = A;
				A = A.next;
			} else if (B != null) {
				res1.next = B;
				B = B.next;
			}
			res1 = res1.next;
		}
		return res;

	}

	public int solve(ArrayList<String> A) {
		double a = Double.parseDouble(A.get(0));
		double b = Double.parseDouble(A.get(1));
		double c = Double.parseDouble(A.get(2));
		for (int i = 3; i < A.size(); i++) {
			if((a + b + c) > 1 && (a + b + c) < 2){
				return 1;
			} else if ((a + b + c) >= 2) {
				if (a > b && a > c) {
					a = Double.parseDouble(A.get(i));
				} else if (b > c) {
					b = Double.parseDouble(A.get(i));
				} else {
					c = Double.parseDouble(A.get(i));
				}
			} else {
				if (a < b && a < c) {
					a = Double.parseDouble(A.get(i));
				} else if (b < c) {
					b = Double.parseDouble(A.get(i));
				} else {
					c = Double.parseDouble(A.get(i));
				}
			}
		}
		return (a + b + c) > 1.0 && (a + b + c) < 2.0 ? 1 : 0;
	}
	
	public ArrayList<Integer> solve_flightRangeBookings(int A, ArrayList<ArrayList<Integer>> B) {
		Integer[] a = new Integer[A];
		for(int i=0;i<B.size();i++) {
			if(!B.get(i).isEmpty()) {
				int st=B.get(i).get(0)-1;
				int ed=B.get(i).get(1)-1;
				int k=B.get(i).get(2);
				a[st]+=k;
				if(ed+1<A) {
					a[ed+1]-=k;
				}
			}
		}
		int sum=a[0];
		for(int i=1;i<a.length;i++) {
			a[i]+=sum;
			sum=a[i];
		}
		return new ArrayList<Integer>(java.util.Arrays.asList(a));
    }
	
	public int solve_sumofdiff(ArrayList<Integer> A) {
        Collections.sort(A);
        long mod=1000000007;
		long diff=0;
		for(int i=0;i<A.size();i++) {
			for(int j=i+1;j<A.size();j++) {
				diff+=((Math.pow(2, j-i)%mod)*(A.get(j)-A.get(i)))%mod;
			}
		}
		return (int) (diff%mod);
    }
	
	
	public static void main(String args[]) {

	}

}
