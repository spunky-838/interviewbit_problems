package interviewbit;

import java.util.ArrayList;

public class TwoPointers {
	public static void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
		int i=0;int j=0;
		while(i<a.size()&&j<b.size()) {
			if(a.get(i)<b.get(j)) {
				i++;
			}else {
				a.add(i,b.get(j));
				j++;i++;
			}
		}
		while(j<b.size()) {
			a.add(b.get(j));
			j++;
		}
    }
	public static void merge1(ArrayList<Integer> a, ArrayList<Integer> b) {
		int j=0;
        for(int i=0;i<a.size();i++) {
            for(;j<b.size();j++) {
                if(b.get(j)<=a.get(i)) {
                    a.add(i,b.get(j));
                }else {
                    break;
                }
            }
        }
        while(j<b.size()){
            a.add(b.get(j));
            j++;
        }
    }
	
	public static int solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
		int rmin =Integer.MAX_VALUE;
		int j=0,k=0;
		for(int i=0;i<A.size();i++) {
			for(;j<B.size();j++) {
				for(;k<C.size();k++) {
					int max =Math.max(Math.max(A.get(i), B.get(j)),C.get(k));
					int min =Math.min(Math.min(A.get(i), B.get(j)),C.get(k));
					rmin=rmin>max-min?max-min:rmin;
					if(min==C.get(k)) {
						continue;
					}else if(min==B.get(j)) {
						break;
					}else {
						break;
					}
				}
				if(B.get(j)<A.get(i)) {
					continue;
				}
				else {
					break;
				}
			}
		}
		return rmin;
		
    }
	// 1 4 5 6   2
	 public int diffPossible(ArrayList<Integer> A, int B) {
	        int j=1;
	        for(int i=0;i<A.size();i++) {
	        	for(;j<A.size();j++) {
	        		if(i==j) {continue;}
	        		if(A.get(j)-A.get(i)==B) {
	        			return 1;
	        		}else if(A.get(j)-A.get(i)>B) {
	        			break;
	        		}else {
	        			continue;
	        		}
	        	}
	        }
	        return 0;
	    }
	 //2, 0, 0, 1, 0, 0, 2, 2, 1, 1,
	 public static void sortColors(ArrayList<Integer> a) {
	    int i=0;
	    int j=1;
	    while(j<a.size()) {
	    	if(a.get(j)<a.get(i)) {
	    		a.add(0,a.remove(j));
	    		i=0;
	    		
	    	}else if(a.get(j)>a.get(i)) {
	    		if(a.get(j)<a.get(j-1)) {
	    			a.add(i+1,a.remove(j));
	    		}
	    	}else {
	    		a.add(i,a.remove(j));
	    		i++;
	    	}
	    	j++;
	    }	 
	 }
	 //[1, 2, 8, 3]
	 public int maxArea(ArrayList<Integer> A) {
		 long max=0;
		 int start=0,end=0;
		 if(A.size()<2) {
			 return 0;
		 }
		 while(start<end) {
			 long water = (end-start)*Math.min(A.get(start), A.get(end));
	          max = Math.max (water,max);
	          if(A.get(start)< A.get(end)) {
	        	  start++;
	          }else {
	        	  end--;
	          }
		 }
		 return (int) max;
	    }
	
	public static void main(String args[]) {
		//1, 1, 1, 0, 2, 0, 2, 2, 0
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		a.add(1);a.add(1);a.add(1);a.add(0);a.add(2);a.add(0);a.add(2);a.add(2);a.add(0);//a.add(1);
		b.add(6);b.add(9);b.add(15);
		c.add(2);c.add(3);c.add(6);c.add(6);
		sortColors(a);
	}
}
