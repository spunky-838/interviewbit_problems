package scalar;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class BitManuplation {

	public int findMinXor(ArrayList<Integer> A) {
		Collections.sort(A);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < A.size() - 1; i++) {
			min = Math.min(min, A.get(i) ^ A.get(i + 1));
		}
		return min;
	}

	public long reverse(long a) {
		long b = 0;
		for (int i = 0; i < 32; i++) {
			if ((a & (1 << i)) > 0) {
				b = (b | (1 << (32 - i)));
			}
		}
		return b;
	}

	public int singleNumber(final List<Integer> A) {
		int ans = 0;
		for (int i = 0; i < A.size(); i++) {
			ans ^= A.get(i);
		}
		return ans;
	}

	public int singleNumber_1(final List<Integer> A) {
		int ans = 0;
		for (int i = 0; i < 32; i++) {
			int cnt = 0;
			for (int j = 0; j < A.size(); j++) {
				if ((A.get(j) & (1 << i)) > 0) {
					cnt++;
				}
			}
			if (cnt % 3 != 0) {
				ans |= (1 << i);
			}
		}
		return ans;
	}
	
	public ArrayList<ArrayList<Integer>> solve_xorQueries(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
		//[1,0,0,0,1]
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int zArray[]=new int[A.size()];
		int tz=0;
		for(int i=0;i<A.size();i++) {
			if(A.get(i)==0) {tz++;}
			zArray[i]=tz;
		}
		for(int i=0;i<B.size();i++) {
			int st=B.get(i).get(0);
			int ed=B.get(i).get(1);
			int totalz=st==0?zArray[ed]:zArray[ed]-zArray[st-1];
			ArrayList<Integer> r = new ArrayList<Integer>();
			r.add((ed-st+1-totalz)%2==0?0:1);
			r.add(totalz);
			res.add(r);
		}
		return res;
    }
	
	public int cntBits(ArrayList<Integer> A) {
		long res=0;
		long size=A.size();
		long mod=1000000007;
		for(int i=0;i<32;i++) {
			long cnt=0;
			for(int j=0;j<A.size();j++) {
				if((A.get(j)&(1<<i))>0) {cnt++;}
			}
			res+=2*cnt*(size-cnt);
		}
		return (int) (res%mod);
    }
	public int divide(int A, int B) {
		int res=0;
		if (B == 0) return Integer.MAX_VALUE;
	    if (A == Integer.MIN_VALUE && B == -1) return Integer.MAX_VALUE;
	    if (A == B) return 1;
	    if (B == 1) return A;
	    if (B == -1) return -A;
	    if (B == 2) return (A>>1);
	    int sign=1;
	    if(A<0) {sign*=-1;}
	    if(B<0) {sign*=-1;}
	    A=A==Integer.MIN_VALUE?Integer.MAX_VALUE:Math.abs(A);
	    B=Math.abs(B);
	    if(A<B) {return 0;}
		while (A >= B) {
			int c = B;
			int bit = 0;
			while (A>=c && (c<<1>c)) {
				c = c << 1;
				bit++;
			}
			bit--;
			res += (1<<bit);
			A = A - (B<<bit);
		}
		return sign*res;
	    
    }

	public static void main(String[] args) {
		BitManuplation bm = new BitManuplation();
		System.out.println(bm.divide(-2147483648, -10000000));
	}
}
