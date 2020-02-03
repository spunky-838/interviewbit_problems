package interviewbit;

public class LinkedLists {

	class ListNode {
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
	
	public ListNode deleteDuplicates(ListNode A) {
		if(A==null) {return null;}
		ListNode b=A;
		while(A.next!=null) {
			if(A.val==A.next.val) {
				ListNode n = A.next.next;
				A.next=n;
			}else {
				A=A.next;
			}
		}
		return b;
    }

	public static void main(String[] args) {

	}
}
