package scalar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Searching {

	public int solve_peakEleent(ArrayList<Integer> A) {
		int low = 0;
		int high = A.size() - 1;
		while (low < high) {
			int mid = (high + low) / 2;
			if (A.get(mid) < A.get(mid + 1)) {
				low = mid + 1;
				continue;
			}
			if (mid - 1 >= 0 && A.get(mid) < A.get(mid - 1)) {
				high = mid - 1;
				continue;
			}
			return A.get(mid);
		}
		return A.get(low);
	}

	public int solve_single(ArrayList<Integer> A) {
		int low = 0;
		int high = A.size() - 1;
		while (low < high) {
			int mid = (high + low) / 2;
			if (A.get(mid).compareTo(A.get(mid + 1)) != 0 && A.get(mid).compareTo(A.get(mid - 1)) != 0) {
				return A.get(mid);
			}
			if (mid % 2 == 0) {
				if (A.get(mid).compareTo(A.get(mid + 1)) == 0) {
					low = mid + 2;
					continue;
				} else {
					high = mid;
					continue;
				}
			} else {
				if (A.get(mid).compareTo(A.get(mid - 1)) == 0) {
					low = mid + 1;
					continue;
				} else {
					high = mid - 1;
					continue;
				}
			}
		}
		return A.get(low);
	}

	public int solve(ArrayList<Integer> A, int B) {
		if (B == 0) {
			return 0;
		}
		Collections.sort(A);
		int s = A.size();
		int sum = 0;
		int i = s - 1;
		for (; i >= 0; i--) {
			sum += A.get(i);
			if (sum > B) {
				return s - i;
			}
		}
		return s - i;
	}

	public int search_rotated(final List<Integer> A, int B) {

		int low=0;
		 int high=A.size()-1;
		 while(low<high) {
			 int mid=(high+low)/2;
			 if(A.get(mid)==B) {return mid;}
			 if(A.get(mid)<A.get(high)) {
				 if(B>A.get(mid)) {
					 low=mid+1;
					 continue;
				 }else {
					 high=mid-1;
					 continue;
				 }
			 }if(A.get(mid)>A.get(low)) {
				 if(B>A.get(low) && B<A.get(mid)) {
					 high=mid-1;
					 continue;
				 }else {
					 low =mid+1;
					 continue;
				 }
			 }
		 }		 
		 return A.get(low)==B?low:-1;
	}

	public int searchMatrix(ArrayList<ArrayList<Integer>> A, int B) {
		int col = A.get(0).size();
		int row = A.size();
		int i = 0, j = col - 1;
		while (i < row && j >= 0 && A.get(i).get(j) != B) {
			if (A.get(i).get(j) > B) {
				j--;
			} else {
				i++;
			}
		}
		if (i < row && j >= 0) {
			return 1;
		} else {
			return 0;
		}
	}
	public int sqrt(int A) {
		long low=0;
		long high=A;
		int res=0;
		while(low<=high) {
			long mid =(low+high)/2;
			long sq=mid*mid;
			if(sq==A) {return (int) mid;}
			if(sq>A) {high=mid-1;}
			if(sq<A) {res=(int) mid;low=mid+1;}
		}
		return res;
    }
	public int solve_staircase(int A) {
		long low = 1;
		long high = A;
		long a = 2 * A;
		while (low <= high) {
			long mid = (low + high) / 2;
			if ((mid * (mid + 1)) == 2 * A)
				return (int) mid;
			if (mid > 0 && (mid * (mid + 1)) > a && (mid * (mid - 1)) <= a)
				return (int) (mid - 1);

			if ((mid * (mid + 1)) > a)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return 0;
//		int res = (int) ((Math.pow(8*A+1, 0.5)-1)/2);
//		return res;
	}

	public int solve_specialInteger(ArrayList<Integer> A, int B) {
		int min = A.size();
		int i = 0;
		int j = 0;
		int sum = 0;
		while (sum <= B && j < A.size()) {
			sum += A.get(j);
			while (sum > B) {
				min = Math.min(min, j - i + 1);
				sum -= A.get(i);
				i++;
			}
			j++;
		}
		return min-1;
	}
	
	public int solve_aggressiveCows(ArrayList<Integer> A, int B) {
		int low=0;
		Collections.sort(A);
		int high=A.get(A.size()-1)-A.get(0);
		int res=-1;
		while(low<=high) {
			int mid = (high+low)/2;
			int n=1;
			int last =A.get(0);
			for(int i=1;i<A.size();i++) {
				if(A.get(i)-last>=mid) {
					n++;
					last=A.get(i);
				}
				if(n==B) {
					res=mid;
					break;
				}
			}
			if(n==B) {
				low=mid+1;
			}else {
				high=mid-1;
			}
		}
		return res;
    }
	public String solve_goodBase(String A) {
		long a =Long.parseLong(A);
		long ans=1;
		for(int i=64;i>0;i--) {
			long low=2;
			long high=a-1;
			while(low<=high) {
				long mid =(high+low)/2;
				long b=1;int cnt=i;long sum=0;
				while(cnt>0) {
					sum+=b;
					if(sum>a) {break;}
					cnt--;
					b*=mid;
				}
				if(sum==a) {return Long.toString(mid);}
				if(sum>a) {
					high=mid-1;
				}else {
					low=mid+1;
				}
			}
		}
		return Long.toString(ans);
    }
	
	
	//A = [12, 34, 67, 90]
	//B = 2
	public int books(ArrayList<Integer> A, int B) {
		if(B>A.size()){return -1;}
        int low=0;
		int high=0;
		for(int i=0;i<A.size();i++) {
			low=Math.max(low,A.get(i));
			high+=A.get(i);
		}
		while(low<high) {
			int mid=(high+low)/2;
			int count=1;
			int s=0;
			for(int i=0;i<A.size();i++) {
				if(s+A.get(i)>mid) {
					count++;s=0;
				}
				s+=A.get(i);
			}			
			if(count==B) {high=mid;}
			else if(count>B) {low=mid+1;}
			else if(count<B) {
				high=mid;
			}
			
		}
		return low;
    }
	
	 public int paint(int A, int B, ArrayList<Integer> C) {
		 if(A>C.size()){A=C.size();}
	        long low=0;
	        long mod=10000003;
			long high=0;
			for(int i=0;i<C.size();i++) {
				low=Math.max(low,C.get(i));
				high+=C.get(i);
			}
			while(low<high) {
				long mid=(high+low)/2;
				int count=1;
				long s=0;
				for(int i=0;i<C.size();i++) {
					if(s+C.get(i)>mid) {
						count++;s=0;
					}
					s+=C.get(i);
				}			
				if(count==A) {high=mid;}
				else if(count>A) {low=mid+1;}
				else if(count<A) {
					high=mid;
				}
				
			}
			return (int)((low*B)%mod);
	    }

	public static void main(String[] args) {

		Searching s = new Searching();
		ArrayList<Integer> a = new ArrayList<Integer>();
		//5, 17, 100, 11 
		a.add(12);a.add(34);a.add(67);a.add(90);//a.add(5);
		System.out.println(s.books(a,2));
	}
}
