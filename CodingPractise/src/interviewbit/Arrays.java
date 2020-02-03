package interviewbit;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class Arrays {

	// Min Steps in Infinite Grid
	public int coverPoints(ArrayList<Integer> A, ArrayList<Integer> B) {
		int x = A.get(0), y = B.get(0), steps = 0;
		for (int i = 1; i < A.size(); i++) {
			int x1 = Math.abs(A.get(i) - x);
			int y1 = Math.abs(B.get(i) - y);
			if (x1 >= y1) {
				steps += x1;
			} else {
				steps += y1;
			}
			x = A.get(i);
			y = B.get(i);
		}
		return steps;
	}

	// Add One To Number
	public ArrayList<Integer> plusOne(ArrayList<Integer> A) {
		int last = A.get(A.size() - 1);
		A.set((A.size() - 1), last + 1);
		int ex = 0;
		for (int i = A.size() - 1; i > -1; i--) {
			if (A.get(i) >= 10) {
				ex = A.get(i) / 10;
				A.set(i, A.get(i) % 10);
			}
			if (ex > 0 && i - 1 >= 0) {
				A.set(i - 1, A.get(i - 1) + ex);
				ex = 0;
			} else if (ex > 0 && i == 0) {
				ArrayList<Integer> B = new ArrayList<Integer>();
				B.add(ex);
				B.addAll(A);
				A = B;
			}
		}
		for (int i = 0; i < A.size(); i++) {
			if (A.get(i) <= 0) {
				A.remove(i);
				i--;
			} else {
				break;
			}
		}
		return A;
	}
	
	// Max Sum Contiguous Subarray <kadane>
	public int maxSubArray(final List<Integer> A) {
		int size = A.size(); 
        int max = Integer.MIN_VALUE, maxEnd = 0; 
  
        for (int i = 0; i < size; i++) 
        { 
            maxEnd = maxEnd + A.get(i); 
            if (max < maxEnd) 
                max = maxEnd; 
            if (maxEnd < 0) 
                maxEnd = 0; 
        } 
        return max; 
    }
	
	// Max Non Negative SubArray
	public static ArrayList<Integer> maxset(ArrayList<Integer> A) {
        int s=-1,sr=-1,e=-1,er=-1;
        long sum=0,sumr=0;
        for(int i=0;i<A.size();i++){
            if(A.get(i)>=0){
                if(s<0)
                    s=i;
                e=i;
                sum+=A.get(i);
            }else{
               if(sum>sumr || (sum==sumr && e-s>er-sr) ){
            	   
                   sr=s;
                   er=e;
                   sumr=sum;
               }
                sum=0;
                e=-1;
                s=-1;
            }
        }
        if(sum>=sumr || (sum==sumr && e-s>er-sr)){
            sr=s;
            er=e;
            sumr=sum;
        }
        ArrayList<Integer> B = new ArrayList<Integer>();
        if(sr!=-1 && er!=-1){
             B.addAll(A.subList(sr,er+1));
        }
        return B;
    }
	
	// Spiral Order Matrix II
	public static ArrayList<ArrayList<Integer>> generateMatrix(int A) {
        int r=0,c=0,n=1;
        ArrayList<ArrayList<Integer>> l = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < A; i++) {
			l.add(new ArrayList<Integer>(A));
			for (int j = 0; j < A; j++) {
				l.get(i).add(j,0);
			}
		}
       while(n<=A*A) {
    	   while(c<A && l.get(r).get(c)==0){
               l.get(r).set(c,n);
               n++;c++;
           }
    	   c--;r++;
           while(r<A && l.get(r).get(c)==0) {
           	l.get(r).set(c,n);
           	n++;r++;
           }
           r--;c--;
           while(c>=0 && l.get(r).get(c)==0) {
           	l.get(r).set(c,n);
           	n++;c--;
           }
           c++;r--;
           while(r>=0 && l.get(r).get(c)==0) {
           	l.get(r).set(c,n);
           	n++;r--;
           }
           r++;c++;
       }
        return l;
    }
	
	// Merge Intervals
	public class Interval {
		      int start;
		      int end;
		      Interval() { start = 0; end = 0; }
		      Interval(int s, int e) { start = s; end = e; }
		  }
	 public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
		 ArrayList<Interval> result = new ArrayList<Interval>();
		 boolean added=false;
		 int i=0;
		 for( ;i<intervals.size();i++) {
			 Interval in = intervals.get(i);
			 if(!added) {
				 if(in.start>newInterval.end && !added) {
					 result.add(newInterval);
					 result.add(in);
					 break;
				 }
				 if(in.start<newInterval.end && in.start>newInterval.start && in.end>newInterval.end  && !added) {
					 Interval nin=new Interval(newInterval.start,in.end);
					 result.add(nin);
					 break;
				 }
				 if(in.end<newInterval.start  && !added) {
					 result.add(in);
					 continue;
				 }
				 if(in.start<newInterval.start && in.end<newInterval.end && in.end>newInterval.start  && !added) {
					 Interval nin=new Interval(in.start,newInterval.end);
					 result.add(nin);
					 added=true;
					 continue;
				 }
				 if(in.start<newInterval.start && in.end>newInterval.end  && !added) {
					 result.add(in);
					 break;
				 }
				 if(newInterval.start<in.start && newInterval.end>in.end  && !added) {
					 Interval nin=new Interval(newInterval.start,newInterval.end);
					 result.add(nin);
					 added=true;
					 continue;
				 } 
			 }
			 if(i>0 && added) {
				 Interval pin = result.get(result.size()-1);
				 if(pin.end<in.start) {
					 result.add(in);
					break;
				 }else {
					 if(pin.end<in.end) {
						 Interval nin=new Interval(pin.start,in.end);
						 result.set(result.size()-1, nin);
						 break;
					 }
				 }				 
			 }			 
		 }
		 if(intervals.size()>0 && i<intervals.size()-1) {
			 result.addAll(intervals.subList(i+1,intervals.size()));
		 }
		 
		 Interval pin = result.size()>0? result.get(result.size()-1):new Interval();
		 if(pin.end<newInterval.start) {
			 result.add(newInterval);
		 }
		 return result;
	    }
	 
	 
	 // Noble Integer
	 public int solve(ArrayList<Integer> A) {
	     Collections.sort(A);
	     int i=0;
	     while(i<A.size()) {
	    	 int a=A.get(i);
	    	 int j=i+1;
	    	 while(j<A.size() && a==A.get(j)) {
	    		 j++;
	    	 }
	    	 if(a==A.size()-j) {
	    		 return 1;
	    	 }
	    	 i=j;
	     }
		 return -1;
	    }

	 
	 // MAXSPPROD
	public int maxSpecialProduct(ArrayList<Integer> A) {
		int[] rsp =new int[A.size()];
		int[] lsp =new int[A.size()];
		
		Stack<Integer> s = new Stack<Integer>();
		for(int i=0;i<A.size();i++) {
			while(s.size()>0 && A.get(i)>A.get(s.peek())) {
				int r =s.peek();
				s.pop();
				rsp[r]=i;
			}
			s.push(i);
		}
		
		Stack<Integer> s2 = new Stack<Integer>();
		for(int i=A.size()-1;i>=0;i--) {
			while(s2.size()>0 && A.get(i)>A.get(s2.peek())) {
				int r =s2.peek();
				s2.pop();
				lsp[r]=i;
			}
			s2.push(i);
		}
		long max =0;
		for(int i=0;i<A.size();i++) {
			long pr =(long)lsp[i]*(long)rsp[i];
			max=max>pr?max:pr;
		}
		return  (int) (max%1000000007);
	}
	
	// Largest Number
	public String largestNumber(final List<Integer> A) {
		String[] sa = new String[A.size()];
		for (int i = 0; i < A.size(); i++) {
			sa[i] = A.get(i).toString();
		}
		java.util.Arrays.sort(sa, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (Long.valueOf(o1 + o2) > Long.valueOf(o2 + o1)) {
					return 1;
				}
				if (Long.valueOf(o1 + o2) < Long.valueOf(o2 + o1)) {
					return -1;
				}
				if (o1.equals(o2)) {
					return 0;
				}
				return 0;
			}

		});
		StringBuilder sb = new StringBuilder();
		for (int i = sa.length - 1; i >= 0; i--) {
			if (Integer.valueOf(sa[i]) == 0 && sb.toString().isEmpty()) {
				continue;
			}
			sb.append(sa[i]);
		}
		return sb.toString().isEmpty() ? "0" : sb.toString();
	}
	
	
//	public void setZeroes(ArrayList<ArrayList<Integer>> a) {
//		for (int i = 0; i < a.size(); i++) {
//			ArrayList<Integer> r = a.get(i);
//			for (int j = 0; j < r.size(); j++) {
//				if (r.get(j) == 0) {
//					for (int p = 0; p < r.size(); p++) {
//						if (a.get(i).get(p) != 0) {
//							a.get(i).set(p, -1);
//						}
//					}
//					for (int q = 0; q < a.size(); q++) {
//						if (a.get(q).get(j) != 0) {
//							a.get(q).set(j, -1);
//						}
//					}
//				}
//			}
//		}
//		for (int i = 0; i < a.size(); i++) {
//			ArrayList<Integer> r = a.get(i);
//			for (int j = 0; j < r.size(); j++) {
//				if (r.get(j) == -1) {
//					a.get(i).set(j, 0);
//				}
//			}
//		}
//	}
	
	
	public static int multiplyFactors(int n) 
    { 	long M = 1000000007;
        long prod = 1; 
        for (int i = 1; i * i <= n; i++)  
        { 
            if (n % i == 0) 
            { 
                if (n / i == i) 
                    prod = (prod * i) % M; 
                else { 
                    prod = (prod * i) % M; 
                    prod = (prod * n / i) % M; 
                } 
            } 
        } 
        return (int) prod; 
    }
	 public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
		 ArrayList<Integer> res = new ArrayList<Integer>();
		 List<int[]> mp = new ArrayList<int[]>();
         for(int i=0;i<A.size();i++) {
        	 int rcount=0;
        	 for(int j=i;j<A.size();j++) {
        		 if(A.get(i)>=A.get(j)) {
        			 rcount++;
        		 }else {
        			 break;
        		 }
        	 }
        	 int lcount=0;
        	 for(int j=i;j>=0;j--) {
        		 if(A.get(i)>=A.get(j)) {
        			 lcount++;
        		 }else {
        			 break;
        		 }
        	 }
        	 mp.add(new int[]{multiplyFactors(A.get(i)), rcount*lcount});
         }
         Collections.sort(mp, new Comparator<int[]>(){
             public int compare(int[] o1, int[] o2) {
                 return o2[0] - o1[0];
             }
         });
         for(int i=0;i<B.size();i++) {
            int count=0;
            for(int[] ent : mp) {
                count+=ent[1];
                if(B.get(i)<=count) {
                    res.add(ent[0]);
                    break;
                }
            }
         }
         return res;
	    }
	 
	 
	 
	    
	 public ArrayList<Integer> allFactors(int A) {
		 ArrayList<Integer> b = new ArrayList<Integer>();
		 int count=0;
	        for(int i=1;i<=Math.sqrt(A);i++){
	        	if(A%i==0) {
	        		count++;
	        		if(count>1) {
	        			b.add(count-1,i);
	        		}else {
	        			b.add(i);
	        		}
	        		if(A/i!=i) {b.add(count,A/i);}
	        	}
	        }
	        return b;
	    }
	
	 public int isPower(int A) {
		 if(A==1) {
			 return 1;
		 }
		 for(int i=2;i*i<=A;i++) {
			 long g=A;
			 while(g%i==0) {
				 g=g/i;
			 }
			 if(g==1) {
				 return 1;
			 }
		 }
		 return 0;
	    }
	 
	 
	public int firstMissingPositive(ArrayList<Integer> A) {
		ArrayList<Integer> B =new ArrayList<Integer>(A.size());
		for (int i = 0; i < B.size(); i++) {
			B.add(0);
		}
        for(int i=0;i<=A.size();i++) {
        	int j =A.get(i);
            if(j>0 && j<=A.size()) {
                B.set(j-1, A.get(i));
            }
        }
        return B.indexOf(0);
    }
	
	
	public static int maximumGap(final List<Integer> A) {
		if(A.size()<2) {return 0;}
		if(A.size()==2) {return Math.abs(A.get(0)-A.get(1));}
		int max=A.get(0);int min=A.get(0);
		for(int i=0;i<A.size();i++) {
			int n = A.get(i);
			max=max>n?max:n;
			min=min>n?n:min;
		}
		double intv=(double)(A.size()-1)/(max-min);
		int minA[]=new int[A.size()];
		int maxA[]=new int[A.size()];
		java.util.Arrays.fill(minA, -1);
		java.util.Arrays.fill(maxA, -1);
		for(int i=0;i<A.size();i++) {
			int j = (int) ((A.get(i)-min)*intv);
			if(minA[j]==-1||maxA[j]==-1) {
				minA[j]=A.get(i);
				maxA[j]=A.get(i);
			}else {
				minA[j]=(minA[j]<A.get(i)?minA[j]:A.get(i));
				maxA[j]=(maxA[j]>A.get(i)?maxA[j]:A.get(i));
			}
		}
		int res=0;int maxP=maxA[0];
		for(int i=1;i<minA.length;i++) {
			if(minA[i]!=-1) {
				res =Math.max(res, minA[i]-maxP);
				maxP=maxA[i];
			}
		}
		return res;
	}
	
	public void setZeroes(ArrayList<ArrayList<Integer>> a) {
	    ArrayList<Integer> r = new ArrayList<Integer>();
	    ArrayList<Integer> c = new ArrayList<Integer>();
	    for(int i=0;i<a.size();i++){
	        for(int j=0;j<a.get(i).size();j++){
	            if(a.get(i).get(j)==0){
	                r.add(i);
	                c.add(j);
	            }
	        }
	    }
	    for(int i=0;i<r.size();i++){
	        int row =r.get(i);
	        for(int j=0;j<a.get(row).size();j++){
	            a.get(row).set(j,0);
	        }
	    }
	    
	    for(int i=0;i<c.size();i++){
	        int col =c.get(i);
	        for(int j=0;j<a.size();j++){
	            a.get(j).set(col,0);
	        }
	    }
	}
	
	public static int maxArr(ArrayList<Integer> A) {
		int res=0;
		if(A.size()>0) {
			int maxa=A.get(0)+0,mina=A.get(0)+0,maxb=A.get(0)-0,minb=A.get(0)-0;
			for(int i=0;i<A.size();i++) {
				maxa=maxa>A.get(i)+i?maxa:A.get(i)+i;
				mina=mina<A.get(i)+i?mina:A.get(i)+i;
				maxb=maxb>A.get(i)-i?maxb:A.get(i)-i;
				minb=minb<A.get(i)-i?minb:A.get(i)-i;
			}
			res=Math.max(maxa-mina, maxb-minb);
		}
		return res;
    }
	
	public static ArrayList<Integer> nextPermutation(ArrayList<Integer> A) {
		int i=A.size()-1;
        for(;i>0;i--){
            if(A.get(i)>A.get(i-1)){
                int j=i;
                for(;j<A.size();j++){
                    if(A.get(j)<A.get(i-1)){
                        break;
                    }
                }
                int temp = A.get(j-1);
                A.set(j-1,A.get(i-1));
                A.set(i-1,temp);
                break;
            }
        }
        if(i==0) {
        	for (int k = 0; k < A.size(); k++) {
    			Boolean swapped = false;
    			for (int j = 0; j < A.size() - k - 1; j++) {
    				if (A.get(j) >A.get(j+1))
    				{
    					int temp = A.get(j);
    					A.set(j,A.get(j+1));
    					A.set(j+1,temp);
    					swapped = true;
    				}
    			}
    			if (!swapped)
    				break;
    		}
        }else {
        	for(int j=i;j<A.size();j++){
                boolean swp = false;
                for(int k=i;k<A.size()+i-j-1 && k<A.size()-1;k++){
                    if(A.get(k)>A.get(k+1)){
                        int temp=A.get(k);
                        A.set(k,A.get(k+1));
                        A.set(k+1,temp);
                        swp = true;
                    }
                }
                if(!swp){
                    break;
                }
            }
        }
        return A;
    }
	
	

	public static void main(String[] args) {
		ArrayList<Integer> A = new ArrayList<Integer>();
		//891, 985, 905, 824, 507 
		A.add(891);A.add(985);A.add(905);A.add(824);A.add(507);
		System.out.println(nextPermutation(A));
	}
}
