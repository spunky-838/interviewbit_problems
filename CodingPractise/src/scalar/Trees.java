package scalar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class Trees {

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
			left = null;
			right = null;
		}
	}

	public int kthSmallest(TreeNode root, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();

		while (root != null || !s.isEmpty()) {
			while (root != null) {
				s.push(root);
				root = root.left;
			}
			root = s.pop();
			res.add(root.val);
			root = root.right;
		}
		return res.get(k);
	}

	public int maxSubarraySumCircular(int[] A) {
		int l = A.length;
		int max = Integer.MIN_VALUE;
		int ind = -1;
		int sum = 0;
		int i = 0;
		int size = 0;
		while (i < l && size < l + 1) {
			max = Math.max(sum, max);
			if (sum + A[i % l] < 0) {
				sum = 0;
				ind = (i + 1) % l;
				size = 0;
			} else {
				if (ind == -1) {
					ind = 0;
				}
				sum += A[i % l];
				size++;
				max = Math.max(sum, max);
			}
			i++;
		}
		return max;

	}

	public ArrayList<Integer> inorderTraversal(TreeNode A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();

		while (A != null || !s.isEmpty()) {
			while (A != null) {
				s.push(A);
				A = A.left;
			}
			A = s.pop();
			res.add(A.val);
			A = A.right;
		}
		return res;
	}

	public String stringShift(String s, int[][] shift) {

		String res = s;
		for (int i = 0; i < shift.length; i++) {
			int r = shift[i][0];
			int n = shift[i][1];
			if (r == 1) {
				res = res.substring(0, res.length() - n) + res.substring(res.length() - n);
			} else if (r == 0) {
				res = res.substring(n) + res.substring(0, n);
			}
		}
		return res;

	}

	public boolean checkValidString(String s) {

		Stack<Character> st1 = new Stack<Character>();
		Stack<Character> st2 = new Stack<Character>();

		int i = 0;
		while (i < s.length()) {
			if (s.charAt(i) == '(' || s.charAt(i) == '*') {
				st1.push(s.charAt(i));
			} else if (s.charAt(i) == ')') {
				boolean c = false;
				while (!st1.isEmpty() && !c) {
					if (st1.peek() == '(') {
						st1.pop();
						c = true;
					} else {
						st2.push(st1.pop());
					}
				}
				while (!st2.isEmpty()) {
					st1.push(st2.pop());
				}
				if (!c && st1.isEmpty()) {
					return false;
				}
			}
			i++;
		}

		while (!st1.isEmpty()) {
			if (st1.peek() == '(') {
				if (st2.isEmpty()) {
					return false;
				} else {
					st1.pop();
					st2.pop();
				}
			} else if (st1.peek() == '*') {
				st2.push(st1.pop());
			}
		}
		return true;
	}

	public ArrayList<Integer> preorderTraversal(TreeNode A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();

		if (A == null) {
			return res;
		}
		s.push(A);
		while (s.size() > 0) {
			A = s.pop();
			res.add(A.val);
			if (A.right != null)
				s.push(A.right);
			if (A.left != null)
				s.push(A.left);
		}
		return res;
	}

	public ArrayList<Integer> postorderTraversal(TreeNode A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (A == null) {
			return res;
		}
		Stack<TreeNode> s = new Stack<TreeNode>();
		s.push(A);
		if (A.left != null) {
			A = A.left;
		} else {
			A = A.right;
		}
		while (!s.empty() || A != null) {
			if (A != null) {
				s.push(A);
				A = A.left;
			} else {
				TreeNode each = s.peek().right;
				if (each == null) {
					each = s.pop();
					res.add(each.val);
					while (!s.empty() && each == s.peek().right) {
						each = s.pop();
						res.add(each.val);
					}
				} else {
					A = each;
				}
			}
		}
		return res;
	}

	public TreeNode buildTree(ArrayList<Integer> A, ArrayList<Integer> B) {
		if (A == null || A.size() == 0 || A.size() != B.size()) {
			return null;
		}
		TreeNode root = new TreeNode(B.get(B.size() - 1));
		if (A.size() == 1) {
			return root;
		}
		int rootIndex = A.indexOf(B.get(B.size() - 1));
		if (rootIndex == -1) {
			return null;
		}

		ArrayList<Integer> lTreeInorder = new ArrayList<Integer>(A.subList(0, rootIndex));
		ArrayList<Integer> rTreeInorder = new ArrayList<Integer>(A.subList(rootIndex + 1, A.size()));

		ArrayList<Integer> lTreePostorder = new ArrayList<Integer>(B.subList(0, rootIndex));
		ArrayList<Integer> rTreePostorder = new ArrayList<Integer>(B.subList(rootIndex, B.size() - 1));
		root.left = buildTree(lTreeInorder, lTreePostorder);
		root.right = buildTree(rTreeInorder, rTreePostorder);
		return root;

	}

	public TreeNode buildTree_inpre(ArrayList<Integer> A, ArrayList<Integer> B) {
		if (B == null || B.size() == 0 || B.size() != A.size()) {
			return null;
		}
		TreeNode root = new TreeNode(A.get(0));
		if (B.size() == 1) {
			return root;
		}
		int rootIndex = B.indexOf(A.get(0));
		if (rootIndex == -1) {
			return null;
		}

		ArrayList<Integer> lTreeInorder = new ArrayList<Integer>(B.subList(0, rootIndex));
		ArrayList<Integer> rTreeInorder = new ArrayList<Integer>(B.subList(rootIndex + 1, B.size()));

		ArrayList<Integer> lTreePreorder = new ArrayList<Integer>(A.subList(1, rootIndex + 1));
		ArrayList<Integer> rTreePreorder = new ArrayList<Integer>(A.subList(rootIndex + 1, A.size()));
		root.left = buildTree(lTreePreorder, lTreeInorder);
		root.right = buildTree(rTreePreorder, rTreeInorder);
		return root;

	}

	public int solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
		TreeNode t = buildTree(B, C);
		ArrayList<Integer> preOrder = preorderTraversal(t);
		if (A.size() != preOrder.size()) {
			return 0;
		}
		for (int i = 0; i < A.size(); i++) {
			if ((int) A.get(i) != (int) preOrder.get(i)) {
				return 0;
			}
		}
		return 1;
	}

	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		if (A == null) {
			return res;
		}
		q.add(A);

		while (!q.isEmpty()) {
			ArrayList<Integer> e = new ArrayList<Integer>();
			int i = q.size();
			while (i > 0) {
				TreeNode t = q.poll();
				e.add(t.val);
				if (t.left != null)
					q.add(t.left);
				if (t.right != null)
					q.add(t.right);
				i--;
			}
			res.add(e);
		}
		return res;
	}

	public ArrayList<Integer> solve_rightView(TreeNode A) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		if (A == null) {
			return res;
		}
		q.add(A);

		while (!q.isEmpty()) {
			int e = q.peek().val;
			int i = q.size();
			while (i > 0) {
				TreeNode t = q.poll();
				e = t.val;
				if (t.left != null)
					q.add(t.left);
				if (t.right != null)
					q.add(t.right);
				i--;
			}
			res.add(e);
		}
		return res;

	}

	public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		TreeMap<Integer, ArrayList<Integer>> tm = new TreeMap<Integer, ArrayList<Integer>>();

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		Queue<Integer> qNum = new LinkedList<Integer>();
		if (A == null) {
			return res;
		}
		q.add(A);
		qNum.add(0);
		while (!q.isEmpty()) {
			int i = q.size();
			while (i > 0) {
				TreeNode t = q.poll();
				Integer n = qNum.poll();
				ArrayList<Integer> arr = tm.get(n) != null ? tm.get(n) : new ArrayList<Integer>();
				arr.add(t.val);
				tm.put(n, arr);
				if (t.left != null) {
					q.add(t.left);
					qNum.add(n - 1);
				}
				if (t.right != null) {
					q.add(t.right);
					qNum.add(n + 1);
				}
				i--;
			}
		}

		for (Map.Entry<Integer, ArrayList<Integer>> entry : tm.entrySet()) {
			res.add(entry.getValue());
		}

		return res;

	}

	// Definition for binary tree with next pointer.
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}

	TreeLinkNode getNext(TreeLinkNode node) {
		TreeLinkNode temp = node.next;
		while (null != temp) {
			if (null != temp.left) {
				return temp.left;
			}
			if (null != temp.right) {
				return temp.right;
			}
			temp = temp.next;
		}
		return null;
	}

	public void connect(TreeLinkNode root) {
		if (null == root) {
			return;
		}
		root.next = null;
		while (null != root) {
			TreeLinkNode node = root;
			while (null != node) {
				if (node.left != null) {
					if (node.right != null) {
						node.left.next = node.right;
					} else {
						node.left.next = getNext(node);
					}
				}
				if (node.right != null) {
					node.right.next = getNext(node);
				}
				node = node.next;
			}
			if (root.left != null) {
				root = root.left;
			} else if (root.right != null) {
				root = root.right;
			} else {
				root = getNext(root);
			}
		}
	}

	public ArrayList<Integer> solve(TreeNode A) {
		if (A == null) {
			return null;
		}
		ArrayList<Integer> lView = new ArrayList<Integer>();
		ArrayList<Integer> rView = new ArrayList<Integer>();
		ArrayList<Integer> leafnodes = new ArrayList<Integer>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(A);
		while (!q.isEmpty()) {
			int i = q.size();
			ArrayList<Integer> eachlevel = new ArrayList<Integer>();
			while (i > 0) {
				TreeNode e = q.poll();
				if (e.left != null) {
					q.add(e.left);
				}
				if (e.right != null) {
					q.add(e.right);
				}
				eachlevel.add(e.val);
				i--;
			}
			if (eachlevel.size() > 0) {
				lView.add(eachlevel.get(0));
				rView.add(eachlevel.get(eachlevel.size() - 1));
			}
		}
		inordertravesal(A, leafnodes);
		ArrayList<Integer> result = new ArrayList(lView);
		for (int i = 0; i < leafnodes.size(); i++) {
			if (result.indexOf(leafnodes.get(i)) == -1) {
				result.add(leafnodes.get(i));
			}
		}
		for (int i = rView.size() - 1; i >= 0; i--) {
			if (result.indexOf(rView.get(i)) == -1) {
				result.add(rView.get(i));
			}
		}
		return result;
	}

	public void inordertravesal(TreeNode A, ArrayList<Integer> leafnodes) {
		if (A == null) {
			return;
		}
		if (A.left == null && A.right == null) {
			leafnodes.add(A.val);
		}
		inordertravesal(A.left, leafnodes);
		inordertravesal(A.right, leafnodes);
		return;
	}

	public int LBSlength(final String A) {
		int res[] = new int[A.length()];
		int max = 0;
		for (int i = 0; i < A.length(); i++) {
			char a = A.charAt(i);
			if (a == '[' || a == '{' || a == '(') {
				res[i] = 0;
			} else if (a == ']') {
				if (i > 0) {
					if (A.charAt(i - 1) == '[') {
						res[i] = i > 1 ? res[i - 2] + 2 : 2;
					} else if (i - res[i - 1] - 1 >= 0 && A.charAt(i - res[i - 1] - 1) == '[') {
						res[i] = res[i - 1] + 2;
						res[i] = i - res[i - 1] - 2 >= 0 ? res[i - res[i - 1] - 2] + res[i] : res[i];
					} else {
						res[i] = 0;
					}
					max = Math.max(max, res[i]);
				} else {
					res[i] = 0;
				}
			} else if (a == '}') {
				if (i > 0) {
					if (A.charAt(i - 1) == '{') {
						res[i] = i > 1 ? res[i - 2] + 2 : 2;
					} else if (i - res[i - 1] - 1 >= 0 && A.charAt(i - res[i - 1] - 1) == '{') {
						res[i] = res[i - 1] + 2;
						res[i] = i - res[i - 1] - 2 >= 0 ? res[i - res[i - 1] - 2] + res[i] : res[i];
					} else {
						res[i] = 0;
					}
					max = Math.max(max, res[i]);
				} else {
					res[i] = 0;
				}
			} else if (a == ')') {
				if (i > 0) {
					if (A.charAt(i - 1) == '(') {
						res[i] = i > 1 ? res[i - 2] + 2 : 2;
					} else if (i - res[i - 1] - 1 >= 0 && A.charAt(i - res[i - 1] - 1) == '(') {
						res[i] = res[i - 1] + 2;
						res[i] = i - res[i - 1] - 2 >= 0 ? res[i - res[i - 1] - 2] + res[i] : res[i];
					} else {
						res[i] = 0;
					}
					max = Math.max(max, res[i]);
				} else {
					res[i] = 0;
				}
			}
		}
		return max;
	}

	public boolean isCousins(TreeNode root, int x, int y) {
		if (root == null) {
			return false;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		int d1 = -1;
		TreeNode p1 = null;
		TreeNode p2 = null;
		int d2 = -1;
		int depth = 0;
		while (!q.isEmpty()) {
			int i = q.size();
			while (i > 0) {
				TreeNode e = q.poll();
				if (e.left != null) {
					q.add(e.left);
					if (e.left.val == x) {
						d1 = depth;
						p1 = e;
					}
					if (e.left.val == y) {
						d2 = depth;
						p2 = e;
					}
				}
				if (e.right != null) {
					q.add(e.right);
					if (e.right.val == x) {
						d1 = depth;
						p1 = e;
					}
					if (e.right.val == y) {
						d2 = depth;
						p2 = e;
					}
				}
				depth++;
				i--;
			}
		}

		if (p1 != null && p2 != null && p1 != p2 && d1 == d2) {
			return true;
		}
		return false;

	}

	public int max = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		findmaxPathWith(root);
		return max;
	}

	public int findmaxPathWith(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int a = findmaxPathWith(root.right);
		int b = findmaxPathWith(root.left);
		int sum1 = Math.max(Math.max(a, b) + root.val, root.val);
		int sum2 = Math.max(sum1, a + b + root.val);
		max = Math.max(max, sum2);
		return sum1;
	}

	public int findComplement(int num) {

		int d = (int) (Math.log10(num) / Math.log10(2));
		int n = (int) (Math.pow(2, d + 1) - 1);
		return num ^ n;
	}

	public static int maxDifference(TreeNode root, int diff) {
		if (root == null) {
			return Integer.MAX_VALUE;
		}

		int left = maxDifference(root.left, diff);
		int right = maxDifference(root.right, diff);

		int d = root.val - Math.min(left, right);

		diff = Math.max(diff, d);

		return Math.min(Math.min(left, right), root.val);
	}

	public static int maxDifference(TreeNode root) {
		int diff = Integer.MIN_VALUE;
		maxDifference(root, diff);

		return diff;
	}

	public static int diff = Integer.MIN_VALUE;

	public int solve_maxdiff(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {

		int max = A.get(0);
		int min = A.get(0);
		maxDifference(A, B, 1, max, min);
		return diff;
	}

	public void maxDifference(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B, int ind, int max, int min) {
		for (int i = 0; i < B.size(); i++) {
			ArrayList<Integer> each = B.get(i);
			if ((int) each.get(0) == ind) {
				int mx = Math.max(max, A.get(each.get(1) - 1));
				int mn = Math.min(min, A.get(each.get(1) - 1));
				diff = Math.max(diff, mx - mn);
				maxDifference(A, B, each.get(1), mx, mn);
			}
		}
	}
	
	

	public static void main(String[] args) {
		ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3 }));
		ArrayList<Integer> B = new ArrayList<Integer>(Arrays.asList(new Integer[] { 2, 1, 3 }));
		Trees t = new Trees();
		System.out.println(t.maxSubarraySumCircular(new int[] { 5, -3, 5 }));
	}
}
