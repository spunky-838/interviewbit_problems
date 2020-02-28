package scalar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Arrays_ {
	// A[i] = j is changed to A[j] = i [2, 1, 3, 4, 0] [4, 1, 0, 2, 3]
	public static ArrayList<Integer> solve(ArrayList<Integer> A) {
		int ind = A.get(0);
		int val = 0;
		while (ind != 0) {
			int temp = A.get(ind);
			A.set(ind, val);
			val = ind;
			ind = temp;
		}
		A.set(ind, val);
		return A;
	}

	public static ArrayList<Integer> solve3(ArrayList<Integer> A) {
		int n = A.size();
		int i;
		for (i = 0; i < n; i++)
			A.set(i, A.get(i) + 1);

		for (i = 0; i < n; i++) {
			if (A.get(i) > 0)
				rearrangeUtil(A, n, i);
		}

		for (i = 0; i < n; i++)
			A.set(i, (-A.get(i)) - 1);

		return A;
	}

	public static void rearrangeUtil(ArrayList<Integer> A, int n, int i) {
		int val = -(i + 1);
		i = A.get(i) - 1;
		while (A.get(i) > 0) {
			int new_i = A.get(i) - 1;
			A.set(i, val);
			val = -(i + 1);
			i = new_i;
		}
	}

	public ArrayList<Integer> solve(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B, ArrayList<Integer> C,
			ArrayList<Integer> D, ArrayList<Integer> E) {
		int rows = A.size();
		Long m = 1000000007l;
		int col = A.get(0).size();
		ArrayList<Integer> res = new ArrayList<Integer>();
		ArrayList<ArrayList<Long>> a = new ArrayList<ArrayList<Long>>();
		for (int i = 0; i < rows; i++) {
			Long[] data = new Long[col];
			java.util.Arrays.fill(data, 0l);
			ArrayList<Long> ar = new ArrayList<Long>();
			ar.addAll(java.util.Arrays.asList(data));
			a.add(ar);
		}
		for (int i = 0; i < rows; i++) {
			Long sum = 0l;
			for (int j = 0; j < col; j++) {
				sum = (sum + A.get(i).get(j));
				a.get(i).set(j, sum);
			}
		}
		for (int i = 0; i < col; i++) {
			Long sum = 0l;
			for (int j = 0; j < rows; j++) {
				sum = (sum + a.get(j).get(i));
				a.get(j).set(i, sum);
			}
		}
		for (int i = 0; i < B.size(); i++) {
			Long k = 0l;
			if (B.get(i) != 1 && C.get(i) != 1) {
				k = a.get(D.get(i) - 1).get(E.get(i) - 1) - a.get(B.get(i) - 2).get(E.get(i) - 1)
						- a.get(D.get(i) - 1).get(C.get(i) - 2) + a.get(B.get(i) - 2).get(C.get(i) - 2);
			}
			if (B.get(i) == 1 && C.get(i) != 1) {
				k = a.get(D.get(i) - 1).get(E.get(i) - 1) - a.get(D.get(i) - 1).get(C.get(i) - 2);
			}
			if (C.get(i) == 1 && B.get(i) != 1) {
				k = a.get(D.get(i) - 1).get(E.get(i) - 1) - a.get(B.get(i) - 2).get(E.get(i) - 1);
			}
			if (B.get(i) == 1 && C.get(i) == 1) {
				k = a.get(D.get(i) - 1).get(E.get(i) - 1);
			}
			k = k % m;
			res.add((int) (k < 0 ? k + m : k));
		}
		return res;

	}

	public int solve1(ArrayList<ArrayList<Integer>> A) {
		int sum = 0;
		int rows = A.size();
		int col = A.get(0).size();
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < A.size(); j++) {
				sum += A.get(i).get(j) * (i + 1) * (j + 1) * (rows - i) * (col - j);
			}
		}
		return sum;
	}

	public int solve2(ArrayList<ArrayList<Integer>> A, int B) {
		int i = 0;
		int j = A.get(0).size() - 1;
		while (A.get(i).get(j) != B) {
			if (A.get(i).get(j) > B) {
				i++;
			} else {
				j--;
			}
		}
		return i * 1009 + j;
	}

	public int minimumOperations(ArrayList<ArrayList<Integer>> A, int B) {
		int max = 0;
		int min = Integer.MAX_VALUE;
		ArrayList<Integer> a=new ArrayList<Integer>();
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < A.get(0).size(); j++) {
				min = Math.min(min, A.get(i).get(j));
				max = Math.max(max, A.get(i).get(j));
				a.add(A.get(i).get(j));
			}
		}
		if ((max - min) % B != 0) {
			return -1;
		}
		Collections.sort(a);
		int r=a.get(a.size()/2);
		int cnt=0;
		for (int i = 0; i < a.size(); i++) {
			if(Math.abs(a.get(i)-r)%B!=0) {
				return -1;
			}else {
				cnt+=Math.abs(a.get(i)-r)/B;
			}
		}
		return cnt;
	}

	public ArrayList<String> fizzBuzz(int A) {
		ArrayList<String> res = new ArrayList<String>();
		for(int i=1;i<=A;i++) {
			if(i%15==0) {
				res.add("FizzBuzz");
			}else if(i%5==0) {
				res.add("Buzz");
			}else if(i%3==0) {
				res.add("Fizz");
			}else {
				res.add(String.valueOf(i));
			}
		}
		return res;

	}
	
	public static int chunkSolve(ArrayList<Integer> A) {
		ArrayList<Integer> preMax = new ArrayList<Integer>();
		int max=Integer.MIN_VALUE;int min= A.get(A.size()-1);
		for(int i=0;i<A.size();i++) {
			max=Math.max(max, A.get(i));
			preMax.add(max);
		}
		int chunks=0;
		for(int i=A.size()-1;i>0;i--) {
			min=Math.min(min, A.get(i));
			if(min>preMax.get(i-1)) {
				chunks++;
			}
		}
		chunks=chunks+1;
		return chunks;
		
    }
	
	public ArrayList<Integer> merge(ArrayList<Integer> A, ArrayList<Integer> B) {
		ArrayList<Integer> r= new ArrayList<Integer>();
		int i=0,j=0;
		while(i<A.size() && j<B.size()) {
			if(A.get(i)<=B.get(j)) {
				r.add(A.get(i));
				i++;
			}else {
				r.add(B.get(j));
				j++;
			}
		}
		while(i<A.size()) {
			r.add(A.get(i));
			i++;
		}
		while(j<B.size()) {
			r.add(B.get(j));
			j++;
		}
		return r;
	}
	public ArrayList<Integer> mergeSort(ArrayList<Integer> A, int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(A, start, mid);
			mergeSort(A, mid+1, end);
			return merge(mergeSort(A, start, mid),mergeSort(A, mid+1, end));
		}
		return new ArrayList<Integer>(A.subList(start, end+1));
	}
	public ArrayList<Integer> wave(ArrayList<Integer> A) {
		A=mergeSort(A, 0, A.size()-1);
		for(int i=1;i<A.size()-1;i+=2) {
			int temp = A.get(i+1);
			A.set(i+1, A.get(i));
			A.set(i, temp);
		}
		return A;
    }

	public static void main(String[] args) {
		System.out.println(-22%22);
	}
	
	public int solve(ArrayList<Integer> A,int B) {
		if(A.size()==0||B==0) {
			return 0;
		}
		long mod = 1000000007;
		long [] c = new long[B];
		for(int i=0;i<A.size();i++) {
			int d = A.get(i)%B;
			c[d]++;
		}
		long res=((c[0]*(c[0]-1))/2)%mod;
		
		for(int i=1;i<B;i++) {
			if(i==B-i) {
				res=(res+(c[i]*(c[i]-1)/2))%mod;
			}
			res=(res+(c[i]*c[B-i]))%mod;
		}
		return (int) (res%mod);
		
	}
}
