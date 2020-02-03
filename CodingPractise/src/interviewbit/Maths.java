package interviewbit;

import java.util.ArrayList;

public class Maths {
	public int titleToNumber(String A) {
		char[] c = A.toCharArray();
		int sum=0,size=c.length;
		for(int i=0;i<size;i++) {
			sum+=(int)c[i]*Math.pow(26,size-1-i );
		}
		return sum;
    }
	
	public int isPalindrome(int A) {
		char[] c = (""+A).toCharArray();
		int l=c.length;
		for(int i=0;i<l/2;i++) {
			while(c[i]!=c[l-1-i]) {
				return 0;
			}
		}
		return 1;
    }
	
	public int isPalindrome_2(int A) {
	    
		int num =A;
		int rev=0;
		while (num > 0) {
            rev = rev*10 + num%10;
            num /= 10;
        }
		if(A == rev) {
			return 1;
		}
	   return 0;
	}
	
	public int reverse(int A) {
		int num =Math.abs(A);
        long rev=0;
        while (num > 0) {
            rev = rev*10 + num%10;
            num /= 10;
        }
        if(A>=0) {
            if (rev>Integer.MAX_VALUE)
                return 0;
            else
                return (int) rev;
                
        }else {
            rev=rev-(2*rev);
            if (rev<Integer.MIN_VALUE)
                return 0;
            else
                return (int) rev;
        }
	}
	
	public int gcd(int A, int B) {
        if(A==0||B==0){
            return A>B?A:B;
        }
        int c=A>B?A:B,d=A>B?B:A;
        if(c%d==0) {
            return d;
        }else {
            return gcd(d,c%d);
        }
    }
//	Input:
//		  0 1 2 5  
//		  2  
//		  21  
//		Output:
//		  5 (10, 11, 12, 15, 20 are possible)
	public static int solve(ArrayList<Integer> A, int B, int C) {
		if(C<Math.pow(10, B-1)) {
			return 0;
		}
		if(C>=Math.pow(10, B+1)) {
			if(A.contains(0)&& B!=1) {
				return (int) (Math.pow(A.size(), B-1))*(A.size()-1);
			}
			return (int) Math.pow(A.size(), B);
		}
		return solve(A, B,0,C);
    }
	public static int solve(ArrayList<Integer> A, int B, int C,int D) {
		int pos =0;		
		int num=C;
		if(num>0) {
			int p =(int) Math.log10(num);
			int d = (int) (num/Math.pow(10, p));
			int no =possibleDigits(d,A);
			num = (int) (num%Math.pow(10, p));
			if(B==1) {
				pos=no;
				if(A.contains(d)) {
					pos--;
				}
				return pos;
			}else {
				int it =(int)Math.pow(A.size(), B-1);
				pos = no*it;
				if(C==D && A.contains(0)) {
					pos=pos-it;
				}
				if(A.contains(d)&& d!=0) {
					int nxt=solve(A, (int)(Math.log10(num)+1),num,D);
					pos=pos-it+nxt;
				}
				return pos;
			}			
		}
		return 0;
	}
	
	public static int possibleDigits(int b,ArrayList<Integer> k) {
		int c=0;
		for(int i=0;i<k.size();i++) {
			if(b>=k.get(i)) {
				c++;
			}
			else
				break;
		}
		return c;
	}
	
	public static void arrange(ArrayList<Integer> a) {
		int s = a.size();
		for(int i=0;i<a.size();i++) {
			if(a.get(i)==i) {
				continue;
			}
			if(a.get(i)>i) {
				a.set(i, a.get(a.get(i))+a.get(i)*s);
			}
			if(a.get(i)<i) {
				a.set(i, (a.get(a.get(i))/s)+a.get(i)*s);
			}	
		}
		for(int i=0;i<a.size();i++) {
			a.set(i, a.get(i)%s);
		}
		
    }
	
	public static int uniquePaths(int A, int B) {
		if(A<=0||B<=0) {
            return 0;
        }
        if(A==1||B==1) {
            return 1;
        }
        
        return uniquePaths(A-1,B)+uniquePaths(A,B-1);
		
    }
	
	public static long factorial(long n) {
		return (n==1||n==0)?1:n*factorial(n-1);
	}
	
	public static void main(String[] args) {
		//int r =uniquePaths(100, 1);
		System.out.println(factorial(24));
	}
}
