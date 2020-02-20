package interviewbit;

import java.util.ArrayList;
import java.util.List;

public class DynamicProgramming {
	
	public int longestSubsequenceLength(final List<Integer> A) {
		int[] inc = new int[A.size()];
		int[] dec = new int[A.size()];
		
		for(int i=0;i<A.size();i++) {
			inc[i]=1;
			for(int j=0;j<i;j++) {
				if(A.get(j)<A.get(i)&& inc[i]<inc[j]+1) {
					inc[i]=inc[j]+1;
				}
			}
		}
		for(int i=A.size()-1;i>=0;i--) {
			dec[i]=1;
			for(int j=A.size()-1;j>i;j--) {
				if(A.get(j)<A.get(i)&& dec[i]<dec[j]+1) {
					dec[i]=dec[j]+1;
				}
			}
		}
		int max=0;
		for(int i=0;i<A.size();i++) {
			max=Math.max(max, inc[i]+dec[i]-1);
		}
		return max;
    }
	public int jump(ArrayList<Integer> A) {
		int[] min = new int[A.size()];
		min[0]=0;
		for(int i=0;i<A.size();i++) {
			min[i]=Integer.MAX_VALUE;
			for(int j=0;j<i;j++) {
				if(A.get(j)>=i-j && min[j]!=Integer.MAX_VALUE && min[j]+1<min[i]) {
					min[i]=min[j]+1;
				}
			}
		}
		return min[A.size()-1]==Integer.MAX_VALUE?-1:min[A.size()-1];
		
    }
	
	public int jump_2(ArrayList<Integer> A) {
		if(A.size() == 1){
            return 0;
        }
		int jumps=1;
		int mr=A.get(0);
		int lr=A.get(0);
		for(int i=1;i<A.size();i++) {
			if(i>mr) {
				return -1;
			}
			if(i>lr) {
				jumps++;
				lr=mr;
			}
			mr=Math.max(mr, A.get(i)+i);
		}
		return jumps;
    }
	
	public static void main(String args[]) {
		
	}

}
