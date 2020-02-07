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

}
