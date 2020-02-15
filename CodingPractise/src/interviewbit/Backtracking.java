package interviewbit;

import java.util.ArrayList;
import java.util.Collections;

public class Backtracking {
	public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
		 ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		 res.add(new ArrayList<Integer>());
		if(A.size()==0) {
			return res;
		}else if(A.size()==1) {
			res.add(A);
			return res;
		}
		Collections.sort(A);
		int a =A.remove(0);
		res.addAll(formSets(a, subsets(A)));
		return res;
		
    }
	public ArrayList<ArrayList<Integer>> formSets(int a,ArrayList<ArrayList<Integer>> set){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>(set);
		for(int i=0;i<set.size();i++) {
			ArrayList<Integer> as = new ArrayList<Integer>(set.get(i));
			as.add(0,a);			
		}
		return res;
	}
	
	//palindrome partition
	public ArrayList<ArrayList<String>> partition(String a) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> res = new ArrayList<String>();
        backtrack(a, 0, result, res);
        return result;
    }
    public void backtrack(String A, int ind, ArrayList<ArrayList<String>> result, ArrayList<String> res){
        if(ind >= A.length()){
            result.add(new ArrayList<String>(res));
            return;
        }
        for(int i=ind; i<A.length(); i++){
            if(check(A.substring(ind, i+1))){
                res.add(new String(A.substring(ind, i+1)));
                backtrack(A, i+1, result, res);
                res.remove(res.size()-1);
            }
        }
    }
    public boolean check(String A){
        int L=0, R=A.length()-1;
        while(L <= R){
            if(A.charAt(L) != A.charAt(R)) return false;
            L++;
            R--;
        }
        return true;
    }
    
    //permute
    public static  class Solution {
        ArrayList<ArrayList<Integer>> result;
        Solution(){
            result = new ArrayList<>();
        }
        public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
            permuteUtil(A,0,A.size());
            return result;
        }
        
        void permuteUtil(ArrayList<Integer> A, int l, int r){
            if(l==r){
                result.add(new ArrayList(A));
            }
            for(int i=l;i<r;i++){
                swap(A,l,i);
                permuteUtil(A,l+1,r);
                swap(A,l,i);
            }
        }
        
        void swap(ArrayList<Integer> A, int l, int r){
            int temp = A.get(l);
            A.set(l,A.get(r));
            A.set(r,temp);
        }
                
    }
    
    public ArrayList<Integer> grayCode(int a) {
    	ArrayList<Integer> r = new ArrayList<Integer>();
    	r.add(0);
    	for(int i=0;i<a;i++) {
    		int s=r.size();
    		for(int j=s-1;j<=0;j--) {
    			r.add(r.get(j)+ (1<<i));
    		}
    	}
    	return r;
    }
    
    //sudoku
    public int[][] res =new int[9][9];
    
    public void solveSudoku(ArrayList<ArrayList<Character>> a) {
    	for(int i=0;i<9;i++) {
    		for(int j=0;j<9;j++) {
    			if(a.get(i).get(j)!='.') {
    				res[i][j]=Character.getNumericValue(a.get(i).get(j));
    			}
    		}
    	}
    	process(0,0);
    	for(int i=0;i<9;i++) {
    		for(int j=0;j<9;j++) {
    			if(a.get(i).get(j)=='.') {
    				char c = (char) (res[i][j] + 48);
    				a.get(i).set(j, c);
    			}
    		}
    	}
    }
    public boolean process(int row,int col) {
    	if(row==9) {return true;}
    	if(col==9) {return process(row+1,0);}
    	if(res[row][col]!=0)return process(row,col+1);
    	
    	for(int i=1;i<10;i++) {
    		if(isValid(i, row, col)) {
    			res[row][col]=i;
    			if(process(row, col+1)) {return true;}
    			res[row][col]=0;
    		}
    	}
    	return false;
    }
    
    public boolean isValid(int val, int row, int col) {
        for(int j = 0; j < 9; j++) {
            if(res[row][j] == val) return false;
        }
        for(int i = 0; i < 9; i++) {
            if(res[i][col] == val) return false;
        }
        row = (row / 3) * 3;
        col = (col / 3) * 3;
        for(int i = row; i < row + 3; i++) {
            for(int j = col; j < col + 3; j++) {
                if(res[i][j] == val) return false;
            }
        }    
        return true;
    }
	
	
	public static void main(String[]args) {
		ArrayList<Integer> A = new ArrayList<Integer>();
		A.add(1);A.add(2);A.add(3);
		Solution s = new Solution();
		System.out.println(s.permute(A));
		
	}
}
