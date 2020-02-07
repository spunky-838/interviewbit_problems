package interviewbit;

import java.util.HashSet;

public class LinkedLists {

	static class ListNode {
		public int val;
		public ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}

	}

	public ListNode getIntersectionNode(ListNode a, ListNode b) {
		ListNode d = a;
		int al = 0;
		while (d != null) {
			al++;
			d = d.next;
		}
		int bl = 0;
		ListNode c = b;
		while (c != null) {
			bl++;
			c = c.next;
		}
		int diff = Math.abs(al - bl);
		ListNode e = a;
		if (al > bl) {
			c = b;
		} else {
			e = b;
			c = a;
		}
		while (diff > 0) {
			e = e.next;
			diff--;
		}
		while (e != null && c != null) {
			if (e == c) {
				return e;
			}
			e = e.next;
			c = c.next;
		}
		return null;
	}

	// 5 4 3 2 1
	public ListNode reverseList(ListNode A) {
		ListNode prev = null;
		ListNode current = A;
		ListNode next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		A = prev;
		return A;

	}
	
	public ListNode reverseBetween(ListNode A, int B, int C) {
		if(B==C) {return A;}
		if(B>C) {return A;}
		ListNode prev = null;
		ListNode current = A;
		ListNode n=A;
		ListNode next = null;
		int cnt=1;
		while (current != null && current.next!=null) {
			if(B<=cnt && cnt<C) {
				next = current.next;
				ListNode temp=next.next;
				current.next=temp;
				next.next=prev==null?n:prev.next;
				if(prev!=null) {
					prev.next=next;
				}else {
					n=next;
				}
			}else if(cnt==C){
				break;
			}else {
				prev=current;
				current=current.next;
			}
			cnt++;
		}
		return n;
    }

	public ListNode deleteDuplicates(ListNode A) {
		if (A == null) {
			return null;
		}
		ListNode b = A;
		while (A.next != null) {
			if (A.val == A.next.val) {
				ListNode n = A.next.next;
				A.next = n;
			} else {
				A = A.next;
			}
		}
		return b;
	}

	//
	public static ListNode swapPairs(ListNode A) {
		if (A == null || A.next == null)
			return A;
		ListNode b = A.next, prev = null;
		while (A != null && A.next != null) {
			ListNode temp = A.next;
			A.next = A.next.next;
			temp.next = A;
			if (prev != null)
				prev.next = temp;
			prev = A;
			A = A.next;

		}
		return b;
	}

	public ListNode detectCycle(ListNode a) {
		HashSet<ListNode> s = new HashSet<ListNode>();
		if (a == null || a.next == null) {
			return null;
		}
		s.add(a);
		while (a.next != null) {
			if (s.contains(a.next)) {
				return a.next;
			}
			a = a.next;
		}
		return null;

	}

	// Floyd cycle
	public ListNode detectCycle_floydCycle(ListNode a) {
		if (a == null || a.next == null) {
			return null;
		}
		ListNode b = a;
		ListNode c = a.next;
		while (b != null && c != null) {
			if (b == c) {
				b = a;
				c = c.next;
				while (b != null) {
					if (b == c) {
						return b;
					}
					b = b.next;
					c = c.next;
				}
			}
			b = b.next;
			c = c.next != null ? c.next.next : null;
		}
		return null;

	}
	
	public ListNode insertionSortList(ListNode A) {
		ListNode b=A;
		while(A.next!=null) {
			if(A.val>A.next.val) {
				ListNode c=b;
				while(c!=A.next) {
					if(c.val>=A.next.val) {
						if(c==b) {b=A.next;}
						ListNode temp =c;
						c=A.next;
						A.next=A.next.next;
						c.next=temp;
						break;
					}
				}
				A=A.next;
			}
			
		}
		return b;
    }

	public static void main(String[] args) {
		ListNode b = new ListNode(2);
		b.next = new ListNode(3);
		b.next.next = new ListNode(4);
		b.next.next.next = new ListNode(5);
		b.next.next.next.next = new ListNode(6);
		swapPairs(b);

	}
}
