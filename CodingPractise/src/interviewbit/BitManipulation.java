package interviewbit;

import java.util.ArrayList;

public class BitManipulation {

	public int findMinXor(ArrayList<Integer> A) {
		A=mergeSort(A, 0, A.size()-1/2, A.size()-1);
			int min =Integer.MAX_VALUE;
			for(int i=0;i<A.size()-1;i++) {
				min =min<(A.get(i)^A.get(i+1))?min:A.get(i)^A.get(i+1);
			}
			return min;
	}
	
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> A,int start,int mid,int end) {
		ArrayList<Integer> r = new ArrayList<Integer>();
		if(start<end) {
			return merge(mergeSort(A, start, (mid+start)/2, mid),mergeSort(A, mid+1, (end+mid+1)/2, end));
		}
		r.add(A.get(start));
		return r;
	}
	
	public static ArrayList<Integer>merge(ArrayList<Integer> A,ArrayList<Integer> B) {
		int i=0,j=0;
		ArrayList<Integer> C = new ArrayList<Integer>();
		while(i<A.size() && j<B.size()) {
			if(A.get(i)<B.get(j)) {
				C.add(A.get(i));
				i++;
			}else {
				C.add(B.get(j));
				j++;
			}
		}
		while(i<A.size()) {
			C.add(A.get(i));
			i++;
		}
		while(j<B.size()) {
			C.add(B.get(j));
			j++;
		}
		return C;
		
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> A = new ArrayList<Integer>();
		A.add(1);A.add(7);A.add(2);A.add(7);A.add(5);
		System.out.println(mergeSort(A, 0, 2, 4));
	}
	
}
