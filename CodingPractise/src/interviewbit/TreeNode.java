package interviewbit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int x) {
		val = x;
		left = null;
		right = null;
	}
	
	long max =Integer.MIN_VALUE;
	public int maxPathSum(TreeNode A) {
		maxSum(A);
		return (int) max;
    }
	
	long maxSum(TreeNode A) {
		if(A==null) {
			return Integer.MIN_VALUE;
		}
		
		if(A.right==null && A.left==null) {
			max =Math.max(max, A.val);
			return A.val;
		}
		
		long a = A.val;
		long al = maxSum(A.left);
		long ar = maxSum(A.right);
		long m = Math.max(Math.max(a+al+ar, a+al), Math.max(a+ar, a));
		max=Math.max(max, m);
		return a+al>a+ar?a+al:a+ar;
	}

	public class TreeNode_s {
		String val;
		TreeNode_s left;
		TreeNode_s right;

		public TreeNode_s(String x) {
			val = x;
			left = null;
			right = null;
		}
	}

// balanced binary tree

	public int isBalanced(TreeNode A) {
		if (A == null) {
			return 1;
		}
		if (A.right == null && A.left != null && (A.left.left != null || A.left.right != null)) {
			return 0;
		}
		if (A.left == null && A.right != null && (A.right.left != null || A.right.right != null)) {
			return 0;
		}
		return isBalanced(A.left) == 1 && isBalanced(A.right) == 1 ? 1 : 0;
	}

	public int isBalanced_2(TreeNode A) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(A);
		while (!q.isEmpty()) {
			A = q.poll();
			if (Math.abs(height(A.left) - height(A.right)) > 1) {
				return 0;
			}
			if (A.left != null) {
				q.add(A.left);
			}
			if (A.right != null) {
				q.add(A.right);
			}
		}
		return 1;
	}

	public ArrayList<Integer> solve(String A, ArrayList<String> B) {
		TreeMap<Integer, ArrayList<Integer>> m = new TreeMap<Integer, ArrayList<Integer>>(Collections.reverseOrder());
		ArrayList<Integer> res = new ArrayList<Integer>();
		HashSet<String> hs = new HashSet<String>(java.util.Arrays.asList((A.split("_"))));
		for (int i = 0; i < B.size(); i++) {
			int count = 0;
			for (String s : B.get(i).split("_")) {
				if (hs.contains(s)) {
					count++;
				}
			}
			if (m.containsKey(count)) {
				m.get(count).add(i);
			} else {
				ArrayList<Integer> arr = new ArrayList<Integer>(java.util.Arrays.asList(i));
				m.put(count, arr);
			}

		}
		for (Map.Entry<Integer, ArrayList<Integer>> e : m.entrySet()) {
			res.addAll(e.getValue());
		}
		return res;
	}

	public int height(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			return Math.max(height(node.left), height(node.right)) + 1;
		}
	}

	public int isSameTree(TreeNode A, TreeNode B) {
		if (A == null && B == null) {
			return 1;
		}
		if (A == null || B == null) {
			return 0;
		}
		if (A.val != B.val) {
			return 0;
		}

		return isSameTree(A.left, B.left) & isSameTree(A.right, B.right);
	}

	public int isSym(TreeNode A, TreeNode B) {
		if (A == null && B == null) {
			return 1;
		}
		if (A == null || B == null) {
			return 0;
		}
		if (A.val != B.val) {
			return 0;
		}

		return isSym(A.left, B.right) & isSym(A.right, B.left);
	}

	public int isSymmetric(TreeNode A) {
		return isSym(A.right, A.left);
	}

	public TreeNode buildTree(ArrayList<Integer> A) {
		if (A.isEmpty()) {
			return null;
		}
		if (A.size() == 1) {
			return new TreeNode(A.get(0));
		}
		int ind = findMax(0, A.size() - 1, A);
		TreeNode a = new TreeNode(A.get(ind));
		a.right = ind == A.size() - 1 ? null : buildTree(new ArrayList<Integer>(A.subList(ind + 1, A.size())));
		a.left = ind == 0 ? null : buildTree(new ArrayList<Integer>(A.subList(0, ind)));
		return a;
	}

	public int findMax(int st, int end, ArrayList<Integer> A) {
		int max = st;
		for (int i = st; i <= end; i++) {
			if (A.get(i) > A.get(max)) {
				max = i;
			}
		}
		return max;
	}

	ArrayList<TreeNode> a = new ArrayList<TreeNode>();

	public int kthsmallest(TreeNode A, int B) {
		if (A != null) {
			kthsmallest(A.left, B);
			if (a.size() < B) {
				a.add(A);
				kthsmallest(A.right, B);
			}

		}
		return a.size() >= B ? a.get(B - 1).val : 0;
	}

	ArrayList<Integer> b = new ArrayList<Integer>();

	public ArrayList<Integer> inorderTraversal(TreeNode A) {
		if (A != null) {
			inorderTraversal(A.left);
			b.add(A.val);
			inorderTraversal(A.right);
		}
		return b;
	}

	public TreeNode invertTree(TreeNode A) {
		if (A == null) {
			return null;
		}
		TreeNode r = A.right;
		A.right = invertTree(A.left);
		A.left = invertTree(r);
		return A;
	}

	public int maxDepth(TreeNode A) {
		if (A == null) {
			return 0;
		}
		return Math.max(maxDepth(A.left), maxDepth(A.right)) + 1;
	}

	ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();
		a.add(A.val);
		s.add(A);
		res.add(a);
		process(s, false);
		return res;

	}

	public void process(Stack<TreeNode> A, boolean lr) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		if (A.isEmpty()) {
			return;
		}
		while (!A.isEmpty()) {
			TreeNode i = A.pop();
			if (lr) {
				if (i.left != null) {
					a.add(i.left.val);
					s.add(i.left);
				}
				if (i.right != null) {
					a.add(i.right.val);
					s.add(i.right);
				}
			} else {
				if (i.right != null) {
					a.add(i.right.val);
					s.add(i.right);
				}
				if (i.left != null) {
					a.add(i.left.val);
					s.add(i.left);
				}
			}
		}
		if (!a.isEmpty()) {
			res.add(a);
		}
		process(s, !lr);
		return;
	}

	public ArrayList<String> prefix_1(ArrayList<String> A) {
		ArrayList<String> B = new ArrayList<String>(A);
		Collections.sort(A, Collections.reverseOrder());
		ArrayList<String> res = new ArrayList<String>();
		HashMap<String, String> m = new HashMap<String, String>();
		for (int i = 0; i < A.size(); i++) {
			if (i == 0) {
				String s = commonPrefix(A.get(i), A.get(i + 1));
				m.put(A.get(i), s);
			} else if (i == A.size() - 1) {
				String s = commonPrefix(A.get(i), A.get(i - 1));
				m.put(A.get(i), s);
			} else {
				String s = commonPrefix(A.get(i), A.get(i + 1));
				String q = commonPrefix(A.get(i), A.get(i - 1));
				m.put(A.get(i), s.length() > q.length() ? s : q);
			}
		}
		for (int i = 0; i < B.size(); i++) {
			res.add(m.get(B.get(i)));
		}
		return res;
	}

	HashMap<String, String> m = new HashMap<String, String>();

	public ArrayList<String> prefix(ArrayList<String> A) {
		Collections.sort(A);

		TreeNode_s prefix_res = new TreeNode_s(A.get(0));
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 1; i < A.size(); i++) {
			insert_node(prefix_res, A.get(i));
		}
		process_prefix(prefix_res);
		for (int i = 0; i < A.size(); i++) {
			res.add(m.get(A.get(i)));
		}
		return res;
	}

	public void process_prefix(TreeNode_s A) {
		if (A != null) {
			process_prefix(A.left);
			m.put(A.val, return_short(A));
			process_prefix(A.right);
		}
		return;
	}

	public String return_short(TreeNode_s t) {
		String res = "";
		if (t.left != null) {
			String p = commonPrefix(t.val, t.left.val);
			res = p.length() > res.length() ? p : res;
		}
		if (t.right != null) {
			String p = commonPrefix(t.val, t.right.val);
			res = p.length() > res.length() ? p : res;
		}
		return res.length() == 0 ? t.val.charAt(0) + "" : res;
	}

	public String commonPrefix(String str1, String str2) {
		StringBuilder result = new StringBuilder();
		int n1 = str1.length(), n2 = str2.length();
		for (int i = 0, j = 0; i <= n1 - 1 && j <= n2 - 1; i++, j++) {
			if (str1.charAt(i) != str2.charAt(j)) {
				break;
			}
			result.append(str1.charAt(i));
		}
		String r = result.toString();

		return r.length() == str1.length() ? r : r + str1.charAt(r.length());
	}

	public void insert_node(TreeNode_s n, String s) {
		if (n.val.compareTo(s) <= 0) {
			if (n.right == null) {
				n.right = new TreeNode_s(s);
				return;
			} else {
				insert_node(n.right, s);
			}
		} else {
			if (n.left == null) {
				n.left = new TreeNode_s(s);
				return;
			} else {
				insert_node(n.left, s);
			}
		}
		return;
	}

	public TreeNode flatten(TreeNode a) {
		if(a==null) {return null;}
		TreeNode r = flatten(a.right);
		TreeNode l=flatten(a.left);
		if(l!=null) {
			TreeNode link = l;
			while(link.right!=null) {
				link=link.right;
			}
			link.right=r;
			a.right=link;
			a.left=null;
		}
		return a;
	}
	
	public ArrayList<Integer> order(ArrayList<Integer> A, ArrayList<Integer> B) {
		Integer[] res = new Integer[A.size()];
		TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
		for(int i=0;i<A.size();i++) {
			m.put(A.get(i), B.get(i));
		}
		for(Map.Entry<Integer, Integer> e :m.entrySet()) {
			int count =e.getValue();
			int i=0;
			while(count !=0 && i<res.length) {
				if(res[i]==null) {
					count--;
				}
				i++;
			}
			while(res[i]!=null) {
				i++;
			}
			res[i]=e.getKey();
		}
		 ArrayList<Integer> r = new ArrayList<Integer>();
		 r.addAll(java.util.Arrays.asList(res));
		
		return r;
    }
	
	public static void main(String args[]) {
		ArrayList<String> B = new ArrayList<String>();
		B.add("bearcat");
		B.add("bert");
		TreeNode A = new TreeNode(1);
		A.left =new TreeNode(2);
		A.right =new TreeNode(3);
		A.right.left =new TreeNode(4);
		A.right.left.right =new TreeNode(5);
		System.out.println(A.maxPathSum(A));
	}
}
