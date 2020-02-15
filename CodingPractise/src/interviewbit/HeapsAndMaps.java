package interviewbit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HeapsAndMaps {

	public void sort(int A[]) {
		int n = A.length;
		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(A, n, i); // To heapify a subtree rooted with node i which is
		// Heapify:- A process which helps regaining heap properties

		// One by one extract an element from heap
		for (int i = n - 1; i >= 0; i--) {
			// Move current root to end
			int temporary = A[0];
			A[0] = A[i];
			A[i] = temporary;

			// call max heapify on the reduced heap
			heapify(A, i, 0);
		}
	}

	// To heapify a subtree rooted with node i which is
	// an index in arr[]. n is size of heap
	void heapify(int A[], int n, int i) {
		int largest = i; // Initialize largest as root
		int left_child = 2 * i + 1; // left = 2*i + 1
		int right_child = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (left_child < n && A[left_child] > A[largest])
			largest = left_child;

		// If right child is larger than largest so far
		if (right_child < n && A[right_child] > A[largest])
			largest = right_child;

		// If largest is not root
		if (largest != i) {
			int swap = A[i];
			A[i] = A[largest];
			A[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(A, n, largest);
		}
	}

	/* A utility function to print array of size n */
	static void print_array(int A[]) {
		int n = A.length;
		for (int i = 0; i < n; ++i)
			System.out.print(A[i] + " ");
		System.out.println();
	}

	// ------------------------problems--------------------------------

	public ArrayList<Integer> solve_pairCombinations(ArrayList<Integer> A, ArrayList<Integer> B) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		Collections.sort(A);
		Collections.sort(B);
		int l=A.size();
		for(int i=l-1;i>=0;i--) {
			for(int j=l-1;j>=0;j--) {
				if(pq.size()<l) {
					pq.add(A.get(i)+B.get(j));
				}else {
					if(A.get(i)+B.get(j)<pq.peek()) {
						break;
					}else {
						pq.poll();
						pq.add(A.get(i)+B.get(j));
					}
				}
			}
		}
		ArrayList<Integer> res = new ArrayList<Integer>();
		while(pq.size()>0) {
			res.add(0,pq.poll());
		}
		return res;
	}
	public int solve_totalHeaps(int A) {
		long ans=1;
		long mod=1000000007;
		int p=0;
		long val=1;
		while(val<=A) {
			ans*=findFactMod((long) Math.pow(2,p), mod);
		    p++;
			val+=Math.pow(2,p);
		}
		if(val>A) {
			val=(long) (A-(val-Math.pow(2,p)));
			ans*=val>0?findFactMod(val, mod):1;
		}
		return (int) (ans%mod);
		


//L = 2h - 1 if p >= m/2
//  = 2h - 1 - (m/2 - p) if p<(m/2)
		
    }
	
	public int solve_totalHeaps1(int A) {
		int h = (int)(Math.log(A) / Math.log(2));
		int lastlevel =A-(int)Math.pow(2, h)+1;
		int tLast =(int)Math.pow(2, h);
		int Left =lastlevel>=tLast/2?(int)Math.pow(2, h)-1:(int)Math.pow(2, h)-1-tLast/2+lastlevel;
		int mod=1000000007;
		long ans =((solve_ncr(A-1,Left,mod) *solve_totalHeaps1(Left))%mod)* solve_totalHeaps1(A-1-Left);
		
		return (int) (ans%mod);		
    }
	
	public long solve_ncr(int A, int B, int C) {
		long mod_n = findFactMod(A, C);
		long mod_r = findFactMod(B, C);
		long mod_nr = findFactMod(A - B, C);
		long mod_r2 = power(mod_r, C - 2, C);
		long mod_nr2 = power(mod_nr, C - 2, C);
		return (((((mod_n % C) * mod_r2) % C) * mod_nr2) % C);
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
	
	public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
		HashMap<Integer,Integer> m = new HashMap<Integer, Integer>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int i=0;i<B;i++) {
			m.put(A.get(i), m.getOrDefault(A.get(i), 0)+1);
		}
		res.add(m.size());
		int k=B;
		while(k<A.size()) {
			int ai=k-B;
			if(m.get(A.get(ai))==1) {
				m.remove(A.get(ai));
			}else {
				m.put(A.get(ai), m.get(A.get(ai))-1);
			}
			m.put(A.get(k), m.getOrDefault(A.get(k), 0)+1);
			res.add(m.size());
			k++;
		}
		return res;
    }
	public static void main(String args[]) {
		//A : [ 1, 2, 1, 3, 4, 3 ]
		//B : 3
		HeapsAndMaps ob = new HeapsAndMaps();
		ArrayList<Integer> A = new ArrayList<Integer>();
		A.add(1);A.add(2);A.add(1);A.add(3);A.add(4);A.add(3);
		System.out.println(ob.dNums(A,3));
	}

}
