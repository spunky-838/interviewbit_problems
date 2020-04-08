package scalar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class StacksAndQueues {

	class Node {
		int val, min;
		Node next;

		Node(int val, int min) {
			this.val = val;
			this.next = null;
			this.min = min;
		}
	}

	Node top;

	public void push(int x) {
		Node n;
		if (top == null) {
			n = new Node(x, x);
		} else {
			n = new Node(x, Math.min(x, top.min));
			n.next = top;
		}
		top = n;
	}

	public void pop() {
		if (top != null) {
			top = top.next;
		}
	}

	public int top() {
		if (top == null) {
			return -1;
		}
		return top.val;
	}

	public int getMin() {
		if (top == null) {
			return -1;
		}
		return top.min;
	}

	public ArrayList<Integer> solve_sortStack(ArrayList<Integer> A) {
		Stack<Integer> stk1 = new Stack<Integer>();
		Stack<Integer> stk2 = new Stack<Integer>();
		for (int i = 0; i < A.size(); i++) {
			stk1.push(A.get(i));
		}
		while (!stk1.isEmpty()) {
			int a = stk1.pop();
			while (!stk2.isEmpty() && a > stk2.peek()) {
				stk1.push(stk2.pop());
			}
			stk2.push(a);
		}
		for (int i = 0; i < A.size(); i++) {
			A.set(i, stk2.pop());
		}
		return A;
	}

	public int solve_otherThem(ArrayList<Integer> A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Stack<Integer> stk1 = new Stack<Integer>();
		for (int i = 0; i < A.size() - 1; i++) {
			int j = A.get(i);
			if (j > A.get(i + 1)) {
				while (!stk1.isEmpty() && j > stk1.peek()) {
					res.add(stk1.pop());
				}
				stk1.push(j);
			} else {
				if (res.isEmpty()) {
					res.add(j);
				} else {
					if (j > res.get(res.size() - 1)) {
						res.add(j);
					} else {
						return 0;
					}
				}
			}
		}
		return res.isEmpty() || A.get(A.size() - 1) > res.get(res.size() - 1) ? 1 : 0;
	}

	public int maxFreq = 0;
	public Map<Integer, Integer> fm = new HashMap<>();
	public Map<Integer, Stack<Integer>> stkm = new HashMap<>();

	public ArrayList<Integer> solve_maxFreqStack(ArrayList<ArrayList<Integer>> A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < A.size(); i++) {
			int j = A.get(i).get(0);
			int k = A.get(i).get(1);
			if (j == 1) {
				push_maxFreqStack(k);
				res.add(-1);
			} else {
				res.add(pop_maxFreqStack());
			}
		}
		return res;
	}

	public void push_maxFreqStack(int a) {
		int f = fm.getOrDefault(a, 0) + 1;
		fm.put(a, f);
		if (f > maxFreq) {
			maxFreq = f;
		}
		stkm.computeIfAbsent(f, z -> new Stack<Integer>()).push(a);
	}

	public int pop_maxFreqStack() {
		int pop = stkm.get(maxFreq).pop();
		fm.put(pop, fm.get(pop) - 1);
		if (stkm.get(maxFreq).size() == 0) {
			maxFreq--;
		}
		return pop;
	}

	public String simplifyPath(String a) {
		if (a.isEmpty() || a.equals("/"))
			return "/";

		String[] segments = a.split("/");

		Stack<String> nameStack = new Stack<>();

		for (int i = 0; i < segments.length; i++) {
			String curr = segments[i];
			if (curr.isEmpty() || curr.equals(".")) {
				continue;
			}
			if (curr.equals("..")) {
				if (!nameStack.isEmpty()) {
					nameStack.pop();
				}
				continue;
			}
			nameStack.push(curr);
		}

		StringBuilder path = new StringBuilder();
		for (String name : nameStack) {
			path.append("/");
			path.append(name);
		}
		if (path.length() == 0)
			path.append("/");

		return path.toString();
	}

	class ListNode {
		public int val;
		public ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public int lPalin(ListNode A) {
		if (A == null || A.next == null)
			return 1;
		int len = 0;
		ListNode cur = A;
		while (cur != null) {
			len++;
			cur = cur.next;
		}
		Stack<Integer> stack = new Stack<Integer>();
		cur = A;
		for (int i = 0; i < len / 2; i++) {
			stack.push(cur.val);
			cur = cur.next;
		}
		if (len % 2 == 1) {
			cur = cur.next;
		}
		while (cur != null) {
			if (cur.val != stack.pop()) {
				return 0;
			}
			cur = cur.next;
		}
		return 1;

	}

	public int largestRectangleArea(ArrayList<Integer> A) {
		ArrayList<Integer> lm = new ArrayList<Integer>();
		ArrayList<Integer> rm = new ArrayList<Integer>();
		Stack<Integer> stk = new Stack<Integer>();
		for (int i = 0; i < A.size(); i++) {
			if (stk.isEmpty()) {
				lm.add(-1);
			} else {
				if (A.get(i) <= A.get(stk.peek())) {
					while (!stk.isEmpty() && A.get(i) <= A.get(stk.peek())) {
						stk.pop();
					}
					lm.add(stk.isEmpty() ? -1 : stk.peek());
				} else {
					lm.add(stk.peek());
				}
			}
			stk.push(i);
		}
		stk.clear();
		for (int i = A.size() - 1; i >= 0; i--) {
			if (stk.isEmpty()) {
				rm.add(A.size());
			} else {
				if (A.get(i) <= A.get(stk.peek())) {
					while (!stk.isEmpty() && A.get(i) <= A.get(stk.peek())) {
						stk.pop();
					}
					rm.add(stk.isEmpty() ? A.size() : stk.peek());
				} else {
					rm.add(stk.peek());
				}
			}
			stk.push(i);
		}
		int res = 0;
		for (int i = 0; i < A.size(); i++) {
			int j = (rm.get(A.size() - i - 1) - lm.get(i) - 1) * A.get(i);
			res = res > j ? res : j;
		}
		return res;
	}

	public int solve_maxRectangle(ArrayList<ArrayList<Integer>> A) {
		if (A.size() == 0) {
			return 0;
		}
		int rlen = A.size();
		int clen = A.get(0).size();
		ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < rlen; i++) {
			B.add(new ArrayList<Integer>());
			for (int j = 0; j < clen; j++) {
				B.get(i).add(0);
			}
		}
		for (int i = 0; i < clen; i++) {
			int s = 0;
			for (int j = 0; j < rlen; j++) {
				if (A.get(j).get(i) == 0) {
					s = 0;
				} else {
					s += A.get(j).get(i);
				}
				B.get(j).set(i, s);
			}
		}
		int res = 0;
		for (int i = 0; i < B.size(); i++) {
			int r = largestRectangleArea(B.get(i));
			res = res > r ? res : r;
		}
		return res;
	}

	public String solve_lexographicallySmallest(String A) {
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		HashSet<Character> hs = new HashSet<Character>();
		for (int i = 0; i < A.length(); i++) {
			hm.put(A.charAt(i), hm.getOrDefault(A.charAt(i), 0) + 1);
		}
		Stack<Character> stk = new Stack<Character>();
		for (int i = 0; i < A.length(); i++) {
			char a = A.charAt(i);
			if (stk.isEmpty()) {
				stk.add(a);
				hm.put(a, hm.get(a) - 1);
				hs.add(a);
			} else {
				if (hs.contains(a)) {
					hm.put(a, hm.get(a) - 1);
				} else {
					int an = a;
					int sn = stk.peek();
					if (an > sn) {
						hm.put(a, hm.get(a) - 1);
						stk.push(a);
						hs.add(a);
					} else {
						while (!stk.isEmpty() && an < (int) stk.peek() && hm.get(stk.peek()) > 0) {
							hs.remove(stk.pop());
						}
						hm.put(a, hm.get(a) - 1);
						stk.push(a);
						hs.add(a);
					}
				}

			}
		}
		char[] c = new char[stk.size()];
		for (int i = c.length - 1; i >= 0; i--) {
			c[i] = stk.pop();
		}
		return new String(c);

	}

	public Stack<Integer> rStack = new Stack<Integer>();

	public ArrayList<Integer> solve_reverseStack(ArrayList<Integer> A) {
		for (int i = A.size() - 1; i >= 0; i++) {
			revereStack(A.get(i));
		}
		for (int i = 0; i < A.size(); i++) {
			A.set(i, rStack.pop());
		}
		return A;
	}

	public void revereStack(int a) {
		if (rStack.isEmpty()) {
			rStack.push(a);
			return;
		} else {
			int b = rStack.pop();
			revereStack(a);
			rStack.push(b);
		}
	}

//	 A = "ab2c3"
//     B = 8
	public String solve_encrptedString(String A, int B) {
		Stack<Character> cstk = new Stack<Character>();
		Stack<Long> nstk = new Stack<Long>();
		long len = 0;
		for (int i = 0; i < A.length() && len <= B; i++) {
			char a = A.charAt(i);
			if ((int) a >= 97) {
				len++;
				cstk.push(a);
				nstk.push(len);
			} else {
				String num = Character.toString(a);
				int j = i + 1;
				while (j < A.length() && (int) A.charAt(j) < 97) {
					num += Character.toString(A.charAt(j));
					j++;
				}
				len += Long.parseLong(num) * nstk.peek() - nstk.peek();
				i = --j;
			}
		}

		while (B % nstk.peek() != 0) {
			B = (int) (B % nstk.peek());
			cstk.pop();
			nstk.pop();
		}
		return Character.toString(cstk.pop());
	}

	public int braces(String A) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < A.length(); i++) {
			if (A.charAt(i) == '(' || A.charAt(i) == '+' || A.charAt(i) == '-' || A.charAt(i) == '*'
					|| A.charAt(i) == '/') {
				stack.push(A.charAt(i));
			} else if (A.charAt(i) == ')') {
				boolean isOkay = false;
				while (stack.peek() != '(') {
					isOkay = true;
					stack.pop();
				}
				if (!isOkay)
					return 1;
				stack.pop();
			}
		}
		return 0;
	}

	// QUEUE

	public int canCompleteCircuit(final List<Integer> gas, final List<Integer> cost) {
		int currentFuel = 0;
		int remaining = 0;
		int total = 0;
		int start = 0;
		for (int i = 0; i < gas.size(); i++) {
			remaining = gas.get(i) - cost.get(i);
			if (currentFuel >= 0)
				currentFuel += remaining;
			else {
				currentFuel = remaining;
				start = i;
			}
			total += remaining;
		}
		return total >= 0 ? start : -1;
	}

	public ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
		Deque<Integer> dq = new LinkedList<Integer>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < A.size(); i++) {
			int val = A.get(i);
			while (!dq.isEmpty() && val >= A.get(dq.getLast())) {
				dq.removeLast();
			}
			dq.add(i);
			if (dq.getFirst() < (i + 1) - B) {
				dq.removeFirst();
			}
			if (i >= B - 1) {
				res.add(A.get(dq.getFirst()));
			}
		}
		return res;
	}
	
	public ArrayList<Integer> firstNegative(final List<Integer> A, int B) {
		Deque<Integer> dq = new LinkedList<Integer>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < A.size(); i++) {
			int val = A.get(i);
			if(val<0) {
				dq.add(i);
			}
			
			if (!dq.isEmpty() && dq.getFirst() < (i + 1) - B) {
				dq.removeFirst();
			}
			if (i >= B - 1) {
				res.add(dq.isEmpty()?0:A.get(dq.getFirst()));
			}
		}
		return res;
	}
	
	public int slidingMaximum_sumarrays(final List<Integer> A, int B) {
		long sum = 0;
		long mod=1000000007;
		Deque<Integer> dgMax = new LinkedList<Integer>();
		Deque<Integer> dgMin = new LinkedList<Integer>();
		for (int i = 0; i < A.size(); i++) {
			int val = A.get(i);
			while (!dgMax.isEmpty() && val >= A.get(dgMax.getLast())) {
				dgMax.removeLast();
			}
			while (!dgMin.isEmpty() && val <= A.get(dgMin.getLast())) {
				dgMin.removeLast();
			}
			
			dgMax.add(i);
			dgMin.add(i);
			
			if (dgMax.getFirst() < (i + 1) - B) {
				dgMax.removeFirst();
			}
			
			if (dgMin.getFirst() < (i + 1) - B) {
				dgMin.removeFirst();
			}
			
			if (i >= B - 1) {
				sum+=A.get(dgMax.getFirst())+A.get(dgMin.getFirst());
			}
		}
		int r =(int) (sum%mod);
		return (int) (r<0?r+mod:r);
	}
	public static int SumOfKsubArray(int arr[], int k) {
		int sum = 0; // Initialize result

		Deque<Integer> S = new LinkedList<>(), G = new LinkedList<>();

		// Process first window of size K
		int i = 0;
		for (i = 0; i < k; i++) {
			while (!S.isEmpty() && arr[S.peekLast()] >= arr[i])
				S.removeLast(); // Remove from rear

			while (!G.isEmpty() && arr[G.peekLast()] <= arr[i])
				G.removeLast(); // Remove from rear

			// Add current element at rear of both deque
			G.addLast(i);
			S.addLast(i);
		}

		for (; i < arr.length; i++) {

			sum += arr[S.peekFirst()] + arr[G.peekFirst()];
			// Remove all elements which are out of this window
			while (!S.isEmpty() && S.peekFirst() <= i - k)
				S.removeFirst();
			while (!G.isEmpty() && G.peekFirst() <= i - k)
				G.removeFirst();
			// remove all previous greater element that are useless
			while (!S.isEmpty() && arr[S.peekLast()] >= arr[i])
				S.removeLast(); // Remove from rear
			// remove all previous smaller that are elements
			// are useless
			while (!G.isEmpty() && arr[G.peekLast()] <= arr[i])
				G.removeLast(); // Remove from rear

			// Add current element at rear of both deque
			G.addLast(i);
			S.addLast(i);
		}
		// Sum of minimum and maximum element of last window
		sum += arr[S.peekFirst()] + arr[G.peekFirst()];
		return sum;
	}
	
	public ArrayList<Integer> solve_reverse(ArrayList<Integer> A, int B) {
        Queue<Integer> q = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();
        for(int i=0;i<B;i++){
            q.add(A.get(i));
        }
        for(int i=B-1;i>=0;i--) {
        	int j=i;
        	while(j>0) {
        		q.add(q.poll());
        		j--;
        	}
        	q2.add(q.poll());
        }
        for(int i=0;i<B;i++){
            A.set(i, q2.poll());
        }
        return A;
    }
	
	public ArrayList<Integer> solve_123num(int A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		 Queue<Integer> q = new LinkedList<Integer>();
		 for(int i=1;i<4;i++) {
			 q.add(i);
		 }
		 while(!q.isEmpty()) {
			 int a = q.poll();
			 res.add(a);
			 A--;
			 if(A<=0) {
				 break;
			 }
			 for(int i=1;i<4;i++) {
				 q.add(a*10+i);
			 }
		 }
		return res;
    }
	
	
	public String solve_nonRepeatingChars(String A) {
		
		HashMap<Character,Integer> hm = new HashMap<Character, Integer>();
		char[] res = new char[A.length()];
		Queue<Character> q = new LinkedList<Character>();
		for(int i=0;i<A.length();i++) {
			hm.put(A.charAt(i),hm.getOrDefault(A.charAt(i), 0)+1);
			if(hm.get(A.charAt(i))<2) {
				q.add(A.charAt(i));
			}
			while(!q.isEmpty()&& hm.get(q.element())>1) {
				q.poll();
			}
			res[i]=q.isEmpty()?'#':q.peek();
		}
		
		return new String(res);
    }

	public static void main(String[] args) {
		StacksAndQueues snq = new StacksAndQueues();
		ArrayList<Integer> a = new ArrayList<Integer>(
				Arrays.asList(new Integer[] { 90, 58, 69, 70, 82, 100, 13, 57, 47, 18 }));
		System.out.println(snq.solve_encrptedString("qi13nz11ps57ss87it10s", 126));

	}
}
