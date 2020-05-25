package contest;

import java.util.ArrayList;
import java.util.List;

public class BigBasket {

	public static int countTeams(List<Integer> skills, int minPlayers, int minLevel, int maxLevel) {
		// Write your code here
		int cnt = 0;
		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i) >= minLevel && skills.get(i) <= maxLevel) {
				cnt++;
			}
		}
		if (cnt < minPlayers) {
			return 0;
		}
		int res=0;
		for(int i=minPlayers;i<=cnt;i++) {
			res+=getncr(cnt, i);
		}
		return res;

	}
	
	public static int getncr(int n, int r){ 
        int val = 1;
        if ( r > n - r ) {
        	r = n - r;
        }
        for (int i = 0; i < r; ++i){ 
	        val =val*(n - i); 
	        val =val/(i + 1);
        }
        return val; 
    } 
	
	
	public static int findSmallestDivisor(String s, String t) {
	    // Write your code here
	 if(s.length()<t.length()  && s.length()%t.length()!=0) {
		 return -1;
	 }
	 for(int i=0;i<=s.length()-t.length();i+=t.length()) {
		 String a = s.substring(i, i+t.length());
		 if(!a.equals(t)) {
			 return -1;
		 }
	 }
	
	 for(int len=1;len<=t.length()/2;len++) {
		 String a=t.substring(0,len);
		 boolean success=true;
		 for(int j=len;j<=t.length()-len;j+=len) {
			 String b=t.substring(j,j+len);
			 if(!a.equals(b)) {
				 success=false;
				 break;
			 }
		 }
		 if(success) {
			 return len;
		 }
	 }
	 return t.length();

 }
	
	public static List<List<Integer>> findBeforeMatrix(List<List<Integer>> after) {
	    // Write your code here
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(after==null || after.size()>0) {
			return res;
		}
		int r=after.size();
		int c= after.get(0).size();
		for(int i=0;i<r;i++) {
			res.add(new ArrayList<Integer>());
			for(int j=0;j<c;j++) {
				int a = after.get(i).get(j);
				if(j-1>=0) {
					a-=after.get(i).get(j-1);
				}
				if(i-1>=0) {
					a-=after.get(i-1).get(j);
				}
				if(j-1>=0 && i-1>=0) {
					a+=after.get(i-1).get(j-1);
				}	
				res.get(i).add(a);
			}
		}
		return res;
	 }
	
	
	 public void main(String[] args) {
		 findSmallestDivisor("lrbb", "lrbb");
	 }
}
