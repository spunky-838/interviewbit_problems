package interviewbit;

import java.util.ArrayList;
import java.util.Stack;

public class StacksnQueues {
	///a/./b/../../c/
	public String simplifyPath(String A) {
		A=A+"/";
		char[] a=A.toCharArray();
        int s=a.length;
        Stack<Character> r = new Stack<Character>();
        int i=0;
        while(i<s) {
            if(i!=0 && a[i]=='/' && r.peek()=='.') {
                int cnt=0;
                while(r.peek()=='.') {
                    r.pop();
                    cnt++;
                }
                if(cnt==1) {
                    continue;
                }else if(cnt==2 && r.size()>1) {
                    r.pop();
                    while(r.peek()!='/') {
                        r.pop();
                    }
                }else if(cnt>2) {
                    while(cnt>0) {
                        r.push('.');
                        cnt--;
                    }
                    r.push(a[i]);
                }
            }else if(i!=0 && a[i]=='/' && r.peek()=='/'){
               
            }else{
                r.push(a[i]);
                if(i==s-1 && a[i]=='/' && s!=1) {
                    r.pop();
                }   
            }
            i++;
        }
        char[] b=new char[r.size()];;
        for(int j=r.size()-1;j>=0;j--) {
            b[j]=r.pop();
        }
        String c= String.valueOf(b);
        c=c.endsWith("/")&&c.length()>1?c.substring(0,c.length()-1):c;
        return c;
    }
	
	public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
		Stack<Integer> s = new Stack<Integer>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int i=A.size()-1;i>=0;i--) {
			while(!s.empty()&& s.peek()>A.get(i)) {
				s.pop();
			}
			if (s.empty()) { 
                res.add(-1); 
            }else{ 
                res.add(s.peek());
            }
			s.push(A.get(i));
		}
		return res;
    }
	public static int evalRPN(ArrayList<String> A) {
		Stack<Integer> s = new Stack<Integer>();
		String op ="+-/*";
		for(int i=0;i<A.size();i++) {
			if(s.size()>1 && op.contains(A.get(i))) {
				int b =s.pop();
				int a= s.pop();
				s.push(doOperation(a,b,A.get(i)));
			}else {
				s.push(Integer.valueOf(A.get(i)));
			}
		}
		return s.empty()?0: s.pop();
    }
	
	class Solution {
		ArrayList<obj> stack;
		int min;
		public Solution(){
			stack =new ArrayList<obj>();
			min=Integer.MIN_VALUE;
		}
	    public class obj{
	    	private int A;
	    	private int m;
	        public obj(int a,int b){
	        	A=a;
	        	m=b;
	        }
	        public int getNum() {
	        	return A;
	        }
	        public int getMin() {
	        	return m;
	        }
	    }
	    public void push(int x) {
	    	min=Math.min(min, x);
	        stack.add(new obj(x,min));
	    }

	    public void pop() {
	        if(!stack.isEmpty()) {
	        	stack.remove(stack.size()-1);
	        }
	    }

	    public int top() {
	    	if(!stack.isEmpty()) {
	        	obj o =stack.get(stack.size()-1);
	        	return o.getNum();
	        }else {
	        	return -1;
	        }
	    }

	    public int getMin() {
	    	if(!stack.isEmpty()) {
	        	obj o =stack.get(stack.size()-1);
	        	return o.getMin();
	        }else {
	        	return -1;
	        }
	    }
	}
	 public static int doOperation(int a,int b,String o) {
		switch (o) {
			case "+":
				return a+b;
			case "-":
				return a-b;
			case "*":
				return a*b;
			case "/":
				return a/b;
			default:
				return 0;
		}	 
	 }
	 public static void main(String[] args) {
		 ArrayList<String> A = new ArrayList<String>();
		 A.add("2");A.add("2");A.add("/");
		System.out.println(evalRPN(A)); 
	 }

}
