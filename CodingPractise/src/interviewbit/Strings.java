package interviewbit;

import java.util.ArrayList;

public class Strings {

	public int isPalindrome(String A) {
		A = A.toLowerCase();
		A = A.replaceAll("[^a-z0-9]", "");
		int l = A.length();
		for (int i = 0; i < l / 2; i++) {
			if (A.charAt(i) != A.charAt(l - i - 1)) {
				return 0;
			}
		}
		return 1;
	}

	public int isPalindrome2(String a) {
		char[] arr = a.toLowerCase().toCharArray();
		int i = 0;
		int j = arr.length - 1;
		while (i < j) {
			if (!((arr[i] >= 'a' && arr[i] <= 'z') || (arr[i] >= '0' && arr[i] <= '9'))) {
				i++;
				continue;
			}
			if (!((arr[j] >= 'a' && arr[j] <= 'z') || (arr[j] >= '0' && arr[j] <= '9'))) {
				j--;
				continue;
			}
			if (arr[i++] != arr[j--])
				return 0;
		}
		return 1;
	}
	// stringholic 1

	public int solve(ArrayList<String> A) {
		int[] in = new int[A.size()];
		for (int i = 0; i < A.size(); i++) {
			int l = A.get(i).length();
			int k = (int) Math.sqrt(l);
			while ((k * (k + 1) / 2) % l != 0) {
				k++;
			}
			in[i] = k;
		}
		return lcm(in);

	}

	public static int lcm(int[] element_array) {
		long lcm_of_array_elements = 1;
		int divisor = 2;
		while (true) {
			int counter = 0;
			boolean divisible = false;
			for (int i = 0; i < element_array.length; i++) {
				if (element_array[i] == 0) {
					return 0;
				} else if (element_array[i] < 0) {
					element_array[i] = element_array[i] * (-1);
				}
				if (element_array[i] == 1) {
					counter++;
				}
				if (element_array[i] % divisor == 0) {
					divisible = true;
					element_array[i] = element_array[i] / divisor;
				}
			}
			if (divisible) {
				lcm_of_array_elements = lcm_of_array_elements * divisor;
			} else {
				divisor++;
			}
			if (counter == element_array.length) {
				return (int) (lcm_of_array_elements % 1000000007);
			}
		}
	}

	// stringholic 2

	private final static long base = (long) (1e9 + 7);

	public int solve1(ArrayList<String> A) {
		int[] primeCnt = new int[200000];
		for (String a : A) {
			long c = repetition(a);
			if (c <= 1)
				continue;
			int d = 2;
			do {
				int cnt = 0;
				while (c % d == 0) {
					c /= d;
					++cnt;
				}
				if (cnt > 0 && cnt > primeCnt[d])
					primeCnt[d] = cnt;
				++d;
			} while (c > 1);
		}
		long res = 1L;
		for (int i = 2; i < primeCnt.length; ++i) {
			int cnt = primeCnt[i];
			if (cnt <= 0)
				continue;
			for (int j = 0; j < cnt; ++j)
				res = (res * i) % base;
		}
		return (int) (res);
	}

	private int repetition(String a) {
		int l = a.length();
		int[] lps = new int[a.length()];
		int len = 0;
		int i = 1;
		lps[0] = 0;
		while (i < a.length()) {

			if (a.charAt(i) == a.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[i - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
		int n = lps[a.length() - 1];
		int rep = l % (l - n) == 0 ? l - n : l;

		for (int t = 1; t <= 2 * l; ++t) {
			int r = (int) ((t * (t + 1L) / 2L) % l);
			if (r % rep == 0)
				return t;
		}
		return -1;
	}
	
	public static int solveP(String A) {
		int k=A.length()%2==0?A.length()/2:(A.length()+1)/2;
		int l=A.length();
		int req=A.length()-1;
		for(int i=1;i<k;i++) {
			int j=1; 
			while(i-j>=0) {
				if(A.charAt(i-j)!=A.charAt(i+j)) {
					break;
				}
				j++;
			}
			if(i-j<0) {
				req=req>(l-i-j)?(l-i-j):req;
			}
			if(A.length()%2!=0) {
				
			}
			j=0;
			while(i-j>=0 && 2*i<l-1) {
				if(A.charAt(i-j)!=A.charAt(i+j+1)) {
					break;
				}
				j++;
			}
			if(i-j<0) {
				req=req>(l-i-j-1)?(l-i-j-1):req;
			}
			j=1;
			while(i-j>=0) {
				if(A.charAt(i-j)!=A.charAt(i+j-1)) {
					break;
				}
				j++;
			}
			if(i-j<0) {
				req=req>(l-i-j)?(l-i-j):req;
			}
		}
		return req;
    }
	
	public int solveP1(String A) {
        int l = A.length();
        int res = l;
        while(l>1 && !isPal(A, l)) {
            l--;
        }
        return res-l;
    }
    public boolean isPal(String A, int l) {
        int i=0, j=l-1;
        while(i<=j && (A.charAt(i) == A.charAt(j))) {
            i++;
            j--;
        }
        if(i>j) {
            return true;
        }
        return false;
    }
    public int lengthOfLastWord(final String A) {
        int l=0;
        for(int i=A.length();i>=0;i++){
            if(A.charAt(i)==' ') {
            	break;
            }
            l++;
        }
        return l;
    }
    public static int romanToInt(String A) {
    	String[] th= getThousands(A);
    	String[] h = getHundreds(th[1]);
    	String[] t = getTens(h[1]);
    	String[] o =getOnes(t[1]);
    	return Integer.parseInt(th[0])+ Integer.parseInt(h[0])+Integer.parseInt(t[0])+Integer.parseInt(o[0]);
    }
    
	public static String[] getThousands(String A) {
		String[] res = new String[2];int r=0;int i=0;
		while(i<A.length() && A.charAt(i)=='M') {
			r++;
			i++;
		}
		res[0]=Integer.toString(r*1000);
		res[1]=i<A.length()?A.substring(i):"";
		return res;
	}

	public static String[] getHundreds(String A) {
		String[] res = new String[2];int r=0;int i=0;
		if(A.indexOf("CM")==0) {
			r+=9;
			i+=2;
		}
		else if(A.indexOf("CD")==0) {
			r+=4;
			i+=2;
		}
		while(i<A.length() && (A.charAt(i)=='D'||A.charAt(i)=='C')) {
			if(A.charAt(i)=='D') {
				r+=5;
			}else if(A.charAt(i)=='C') {
				r+=1;
			}
			i+=1;
		}
		res[0]=Integer.toString(r*100);
		res[1]=i<A.length()?A.substring(i):"";
		return res;
		
	}

	public static String[] getTens(String A) {
		String[] res = new String[2];int r=0;int i=0;
		if(A.indexOf("XC")==0) {
			r+=9;
			i+=2;
		}
		else if(A.indexOf("XL")==0) {
			r+=4;
			i+=2;
		}
		while(i<A.length() && (A.charAt(i)=='L'||A.charAt(i)=='X')) {
			if(A.charAt(i)=='L') {
				r+=5;
			}else if(A.charAt(i)=='X') {
				r+=1;
			}
			i+=1;
		}
		res[0]=Integer.toString(r*10);
		res[1]=i<A.length()?A.substring(i):"";
		return res;
	}

	public static String[] getOnes(String A) {
		String[] res = new String[2];int r=0;int i=0;
		if(A.indexOf("IX")==0) {
			r+=9;
			i+=2;
		}
		else if(A.indexOf("IV")==0) {
			r+=4;
			i+=2;
		}
		while(i<A.length() && (A.charAt(i)=='V'||A.charAt(i)=='I')) {
			if(A.charAt(i)=='V') {
				r+=5;
			}else if(A.charAt(i)=='I') {
				r+=1;
			}
			i+=1;
		}
		res[0]=Integer.toString(r);
		res[1]=i<A.length()?A.substring(i):"";
		return res;
	}

	
	public static ArrayList<String> fullJustify(ArrayList<String> A, int B) {
		int space = 0;
		int lenstr = B;
		int ltrcount = 0;
		ArrayList resul = new ArrayList();
		ArrayList temp = new ArrayList();
		for (int i = 0; i < A.size(); i++) {
			int ext = 0;
			int count = 0;
			space = 0;
			temp.clear();
			String ltr = A.get(i);
			count = ltr.length();
			temp.add(ltr);
			while (count <= B) {
				space++;
				count++;
				if (i == A.size() - 1) {
					ext = 0;
					break;
				}
				i++;
				String mtr = A.get(i);
				count += mtr.length();
				temp.add(mtr);
				ext = 1;
			}
			if (ext == 1) {
				count -= A.get(i).length();
				temp.remove(temp.size() - 1);
				space--;
				i--;
			}
			count--;
			int val = count - space;
			String calval = "";
			int ecspace = 0;
			if (space != 0)
				ecspace = (B - val) % space;
			int spreq = 0;
			if (space != 0)
				spreq = (B - val) / space;
			if (i == A.size() - 1) {
				ecspace = 0;
				spreq = 1;
			}
			int k;
			for (k = 0; k < temp.size(); k++) {
				calval += temp.get(k);
				int p = 0;
				if (k != temp.size() - 1) {
					while (p < spreq) {
						calval += " ";
						p++;
					}
				}
				if (ecspace > 0) {
					calval += " ";
					ecspace--;
				}
			}
			while (calval.length() < B) {
				calval += " ";
			}
			resul.add(calval);
		}
		return resul;
	}
	
	public static void main(String[] args) {
		ArrayList<String> A = new ArrayList<String>();
		//A : [ "What", "must", "be", "shall", "be." ]
		//B : 12
		A.add("What");A.add("must");A.add("be");A.add("shall");A.add("be.");
		System.out.println(fullJustify(A,12));
	}
}
