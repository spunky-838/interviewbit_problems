package interviewbit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;

public class GreedyAlgo {

	public int maxp3(ArrayList<Integer> A) {
		if (A.size() < 0) {
			return 0;
		}
		Collections.sort(A);
		int s = A.size();
		int a = A.get(s - 1) * A.get(s - 2) * A.get(s - 3);
		int b = A.get(0) * A.get(1) * A.get(s - 1);
		return a > b ? a : b;
	}

	public int bulbs(ArrayList<Integer> A) {
		int cnt = 0;
		for (int i = 0; i < A.size(); i++) {
			if ((A.get(i) + cnt) % 2 == 0) {
				cnt++;
			}
		}
		return cnt;
	}

	public int majorityElement(final List<Integer> A) {
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
		int res = 0;
		for (int i = 0; i < A.size(); i++) {
			m.put(A.get(i), m.getOrDefault(A.get(i), 0) + 1);
			if (m.get(A.get(i)) > A.size() / 2) {
				res = A.get(i);
				break;
			}
		}
		return res;
	}

	public int majorityElement_2(final List<Integer> A) {

		int res = 0;
		int cnt = 0;
		for (int i = 1; i < A.size(); i++) {
			if (cnt == 0) {
				res = A.get(i);
			}
			if (res != A.get(i)) {
				cnt--;
			} else {
				cnt++;
			}
		}
		return res;
	}

	public int mice(ArrayList<Integer> A, ArrayList<Integer> B) {
		Collections.sort(A);
		Collections.sort(B);
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < A.size(); i++) {
			max = Math.max(max, Math.abs(B.get(i) - A.get(i)));
		}
		return max;
	}
	// . . . . x . . x x . . . x . .

	public int seats(String a) {
		long mod = 10000003;
		int numLeft = 0;
		int numRight = 0;
		for (int i = 0; i < a.length(); ++i) {
			if (a.charAt(i) == 'x') {
				++numRight;
			}
		}
		long moves = 0;
		for (int i = 0; i < a.length(); ++i) {
			if (numRight == 0) {
				break;
			} else if (a.charAt(i) == 'x') {
				++numLeft;
				--numRight;
			} else {
				moves += Math.min(numLeft, numRight);
			}
		}
		return (int) (moves % mod);
	}
	
	public int canCompleteCircuit(final List<Integer> A, final List<Integer> B) {
		int sum=0;
		int st=0;
		int suma=0;
		int sumb=0;
		boolean isSet=false;
		for (int i = 0; i < A.size(); i++) {
			suma+=A.get(i);
			sumb+=B.get(i);
			int dif=A.get(i)-B.get(i);
			if(sum+dif>=sum&&!isSet) {
				st=i;
				isSet=true;
				sum=dif;
			}else if(isSet) {
				sum+=dif;
			}
			if(sum<0){
				sum=0;
				isSet=false;
			}
		}
		return isSet&&(suma>=sumb)?st:-1;
		
    }

	public static void main(String[] args) {
		GreedyAlgo g = new GreedyAlgo();
		System.out.println(g.seats("x.......x.....x"));
	}
}
