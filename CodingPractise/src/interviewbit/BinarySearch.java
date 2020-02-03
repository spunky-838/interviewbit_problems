package interviewbit;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

	// Matrix Median

	public static int findMedian(ArrayList<ArrayList<Integer>> A) {
		for (int i = 1; i < A.size(); i++) {
			int k = 0;
			while (A.get(i).size() > 0) {
				int r = search(A.get(0), k, A.get(0).size() - 1, A.get(i).get(0));
				A.get(0).add(r, A.get(i).remove(0));
				k = r;
			}
		}
		int mid = A.get(0).size() / 2;
		if (A.get(0).size() % 2 == 0) {
			return (A.get(0).get(mid) + A.get(0).get(mid - 1)) / 2;
		} else {
			return A.get(0).get(mid);
		}
	}
	// Median of Array   CHECKAGAIN
	public double findMedianSortedArrays(final List<Integer> A, final List<Integer> B) {
        int len = A.size() + B.size();
        if(len % 2 == 1) return findKth(A, 0, B, 0, len / 2 + 1);
        else return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
    }
    
    public int findKth(List<Integer> A, int A_start, List<Integer> B, int B_start, int k){
        if(A_start >= A.size()) return B.get(B_start + k - 1);
        if(B_start >= B.size()) return A.get(A_start + k - 1);
        if(k == 1) return Math.min(A.get(A_start), B.get(B_start));
        
        int A_key = A_start + k / 2 - 1 < A.size() ? A.get(A_start + k / 2 - 1) : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.size() ? B.get(B_start + k / 2 - 1) : Integer.MAX_VALUE;
        
        if(A_key < B_key){
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        }
        else
           return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
    }
	// Search for a Range
	public static ArrayList<Integer> searchRange(final List<Integer> A, int B) {
		List<Integer> res = new ArrayList<Integer>(2);
		int min = 0, max = 0;
		int mid = A.size() / 2;
		if (A.get(mid) == B) {
			min = search1(A, 0, mid - 1, B, true);
			min = min < 0 ? mid : min;
			max = search1(A, mid + 1, A.size() - 1, B, false);
			max = max < 0 ? mid : max;
		}
		if (A.get(mid) < B) {
			res = searchRange(A.subList(mid + 1, A.size()), B);
			res.set(0, res.get(0) + mid + 1);
			res.set(1, res.get(1) + mid + 1);
		}
		if (A.get(mid) > B) {
			res = searchRange(A.subList(0, mid), B);
		}
		res.add(min);
		res.add(max);
		return new ArrayList<Integer>(res.subList(0, 2));
	}

	public static int search1(List<Integer> row, int start, int end, int num, boolean sMin) {
		if (end < start) {
			return -1;
		}
		if (start == end) {
			if (row.get(start) != num) {
				return -1;
			}
		}
		int res = 0;
		int mid = (start + end) / 2;
		if (row.get(mid) == num) {
			res = sMin ? search1(row, start, mid - 1, num, sMin) : search1(row, mid + 1, end, num, sMin);
			res = res < 0 ? mid : res;
		}
		if (sMin && row.get(mid) < num) {
			res = search1(row, mid + 1, end, num, sMin);
		}
		if (sMin && row.get(mid) > num) {
			res = search1(row, start, mid - 1, num, sMin);
		}
		return res;
	}

	public static int search(ArrayList<Integer> row, int start, int end, int num) {
		if (start == end) {
			if (row.get(start) >= num) {
				return start;
			} else {
				return start + 1;
			}
		}
		int res = 0;
		int mid = (start + end) / 2;
		if (row.get(mid) == num) {
			return mid;
		}
		if (row.get(mid) > num) {
			if (start == mid) {
				return start;
			}
			res = search(row, start, mid - 1, num);
		}
		if (row.get(mid) < num) {
			res = search(row, mid + 1, end, num);
		}
		return res;
	}

	// Implement Power Function

	public static int pow(int x, int n, int d) {
		if(x==0||d==1||x%d==0) {
			return 0;
		}
		if(n==1) {
			return x%d<0?(x%d)+d:x%d;
		}
		if(n==0) {
			return 1%d;
		}
		long res=1;
		long a=x;
		int num =n;
		while(num>0) {
			if((num&1)==1) {
				res=res*a;
			}
			if(res<0) {
				res = d + (res%d);
			}else {
				res=res%d;
			}
			a=(a*a)%d;
			num =num>>1;
		}
		return (int) (res%d);
	}
	
//	A : [ -50, -41, -40, -19, 5, 21, 28 ]
//			B : [ -50, -21, -10 ]
	public static void main(String args[]) {
		
	}
}
