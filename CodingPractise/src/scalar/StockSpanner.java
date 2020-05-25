package scalar;

import java.util.Stack;

class StockSpanner {
	
	public class tuple{
		int price;
		int val;
		public tuple(int p,int v) {
			price=p;
			val=v;
		}
	}
	Stack<tuple> stk;
    public StockSpanner() {
     stk = new Stack<tuple>();
     
    }
    
    public int next(int price) {
    	int res=1;
    	if(stk.isEmpty()) {
    		stk.push(new tuple(price,1));
    	}else {
    		if(stk.peek().price<=price) {
    			while(stk.peek().price<=price) {
        			tuple t=stk.pop();
        			res+=t.val;
        		}
    		}
    		stk.push(new tuple(price,res));
    	} 
    	return res;
        
    }
}
