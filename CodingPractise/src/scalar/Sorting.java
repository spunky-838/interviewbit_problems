package scalar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

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
			if ((a + b + c) > 1 && (a + b + c) < 2) {
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
		for (int i = 0; i < B.size(); i++) {
			if (!B.get(i).isEmpty()) {
				int st = B.get(i).get(0) - 1;
				int ed = B.get(i).get(1) - 1;
				int k = B.get(i).get(2);
				a[st] += k;
				if (ed + 1 < A) {
					a[ed + 1] -= k;
				}
			}
		}
		int sum = a[0];
		for (int i = 1; i < a.length; i++) {
			a[i] += sum;
			sum = a[i];
		}
		return new ArrayList<Integer>(java.util.Arrays.asList(a));
	}

	public int solve_sumofdiff(ArrayList<Integer> A) {
		Collections.sort(A);
		long mod = 1000000007;
		long diff = 0;
		for (int i = 0; i < A.size(); i++) {
			for (int j = i + 1; j < A.size(); j++) {
				diff += ((Math.pow(2, j - i) % mod) * (A.get(j) - A.get(i))) % mod;
			}
		}
		return (int) (diff % mod);
	}
	
	public static int res_countInversions = 0;
	public int countInversions(ArrayList<Integer> A) {
		if(A.size()<2) {return 0;}
		mergesort(A, 0, A.size()-1);
		return res_countInversions;
	}
	
	public ArrayList<Integer> mergesort(ArrayList<Integer> A,int start,int end) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(start==end) {
			res.add(A.get(start));
		}
		else if(start<end) {
			int mid=(start+end)/2;
			res =merge(mergesort(A, start, mid),mergesort(A, mid+1, end) );
		}
		return res;
	}
	
	public ArrayList<Integer> merge(ArrayList<Integer> A,ArrayList<Integer> B) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int i=0;
		int j=0;
		while(i<A.size()&&j<B.size()) {
			if(A.get(i)>B.get(j)) {
				res_countInversions+=B.size()-j;
				res.add(A.get(i));
				i++;
			}else{
				res.add(B.get(j));
				j++;
			}
		}
		while(i<A.size()) {
			res.add(A.get(i));
			i++;
		}
		while(j<B.size()) {
			res.add(B.get(j));
			j++;
		}
		return res;
	}
	public int solve_bottles(ArrayList<Integer> A) {
        Collections.sort(A);
        int max=1;int cnt=1;
        for(int i=1;i<A.size();i++){
            if(A.get(i)==A.get(i-1)){
                cnt++;
            }else{
                max=Math.max(max,cnt);
                cnt=1;
            }
        }
        max=Math.max(max,cnt);
        return max;
    }
	
	public ArrayList<Integer> solve_minmax(ArrayList<Integer> A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		 Collections.sort(A);
		 long mod=1000000007;
		 long max=0;
		 for(int i=0;i<A.size()/2;i++) {
			 max=((max%mod)+((A.get(A.size()-i-1)-A.get(i))%mod))%mod;
		 }
		 long min=0;
		 for(int i=1;i<A.size();i=i+2) {
			 min=((min%mod)+((A.get(i)-A.get(i-1))%mod))%mod;
		 }
		 res.add((int) max);
		 res.add((int) min);
		 return res;
    }
	
	public ArrayList<ArrayList<Integer>> solve_closeToOrigin(ArrayList<ArrayList<Integer>> A, int B) {
		Collections.sort(A,new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				double a=Math.sqrt((long)o1.get(0)*(long)o1.get(0)+(long)o1.get(1)*(long)o1.get(1));
				double b=Math.sqrt((long)o2.get(0)*(long)o2.get(0)+(long)o2.get(1)*(long)o2.get(1));
				return a>=b?1:-1;
			}
		});
		ArrayList<ArrayList<Integer>> res= new ArrayList<ArrayList<Integer>>( A.subList(0, B));
		Collections.sort(res, new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return o1.get(0)!=o2.get(0)?o1.get(0)-o2.get(0):o1.get(1)-o2.get(1);
			}
		});
		return res;
    }
	
	public ArrayList<Integer> solve_alternatePosAndNeg(ArrayList<Integer> A) {
        int pind=-1;
        for(int i=0;i<A.size();i++){
            if(A.get(i)>-1 && pind==-1){
                pind=i;
            }
            if(A.get(i)<0 && pind!=-1){
                int a = A.remove(i);
                A.add(pind,a);
                pind++;
            }
        }
        int i=1,j=pind;
        while(i<j && j<A.size()){
            int a = A.remove(j);
            A.add(i,a);
            i+=2;
            j++;
        }
        return A;
    }
	
	public int solve_maxChunks(ArrayList<Integer> A) {
		int max=0;
		for(int i=0;i<A.size();i++) {
			if(A.get(i)==i) {
				max++;
			}
		}
		return max;
    }
	
	public int solve_minswaps(ArrayList<Integer> A) {
		HashMap<Integer,Integer> hm = new HashMap<Integer, Integer>();
		int res=0;
		for(int i=0;i<A.size();i++) {
			hm.put(A.get(i), i);
		}
		for(int i=0;i<A.size();i++) {
			if(A.get(i)!=i) {
				int ind=hm.get(i);
				int temp=A.get(i);
				A.set(i, i);
				A.set(ind, temp);
				hm.put(i, i);
				hm.put(temp, ind);
				res++;
			}
		}
		return res;
    }
	
	public int solve_uniqueElements(ArrayList<Integer> A) {
		Collections.sort(A);
		int cnt=0;
		for(int i=1;i<A.size();i++) {
			if(A.get(i)<=A.get(i-1)) {
				cnt+=A.get(i-1)-A.get(i)+1;
				A.set(i, A.get(i-1)+1);
			}
		}
		return cnt;
    }

	public static void main(String args[]) {
		Sorting s = new Sorting();
		ArrayList<Integer> A = new ArrayList<Integer>();
		A.add(1);A.add(1);A.add(2);
		System.out.println(s.solve_uniqueElements(A));
	}

}
