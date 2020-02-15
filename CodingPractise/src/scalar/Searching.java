package scalar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Searching {

	public int solve_peakEleent(ArrayList<Integer> A) {
		int low = 0;
		int high = A.size() - 1;
		while (low < high) {
			int mid = (high + low) / 2;
			if (A.get(mid) < A.get(mid + 1)) {
				low = mid + 1;
				continue;
			}
			if (mid - 1 >= 0 && A.get(mid) < A.get(mid - 1)) {
				high = mid - 1;
				continue;
			}
			return A.get(mid);
		}
		return A.get(low);
	}

	public int solve_single(ArrayList<Integer> A) {
		int low = 0;
		int high = A.size() - 1;
		while (low < high) {
			int mid = (high + low) / 2;
			if (A.get(mid).compareTo(A.get(mid + 1)) != 0 && A.get(mid).compareTo(A.get(mid - 1)) != 0) {
				return A.get(mid);
			}
			if (mid % 2 == 0) {
				if (A.get(mid).compareTo(A.get(mid + 1)) == 0) {
					low = mid + 2;
					continue;
				} else {
					high = mid;
					continue;
				}
			} else {
				if (A.get(mid).compareTo(A.get(mid - 1)) == 0) {
					low = mid + 1;
					continue;
				} else {
					high = mid - 1;
					continue;
				}
			}
		}
		return A.get(low);
	}

	public int solve(ArrayList<Integer> A, int B) {
		if (B == 0) {
			return 0;
		}
		Collections.sort(A);
		int s = A.size();
		int sum = 0;
		int i = s - 1;
		for (; i >= 0; i--) {
			sum += A.get(i);
			if (sum > B) {
				return s - i;
			}
		}
		return s - i;
	}

	public int search_rotated(final List<Integer> A, int B) {

		int low = 0;
		int high = A.size() - 1;
		while (low < high) {
			int mid = (high + low) / 2;
			if (A.get(mid) == B) {
				return mid;
			}
			if (A.get(mid) < A.get(high)) {
				if (B > A.get(mid)) {
					low = mid + 1;
					continue;
				} else {
					high = mid - 1;
					continue;
				}
			}
			if (A.get(mid) > A.get(low)) {
				if (B > A.get(low)) {
					high = mid - 1;
					continue;
				} else {
					low = mid + 1;
					continue;
				}
			}
		}
		return A.get(low) == B ? low : -1;
	}

	public int searchMatrix(ArrayList<ArrayList<Integer>> A, int B) {
		int col = A.get(0).size();
		int row = A.size();
		int i = 0, j = col - 1;
		while (i < row && j >= 0 && A.get(i).get(col - 1) != B) {
			if (A.get(i).get(j) > B) {
				j--;
			} else {
				i++;
			}
		}
		if (i < row && j >= 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public int solve_staircase(int A) {
		long low = 1;
		long high = A;
		long a = 2 * A;
		while (low <= high) {
			long mid = (low + high) / 2;
			if ((mid * (mid + 1)) == 2 * A)
				return (int) mid;
			if (mid > 0 && (mid * (mid + 1)) > a && (mid * (mid - 1)) <= a)
				return (int) (mid - 1);

			if ((mid * (mid + 1)) > a)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return 0;
//		int res = (int) ((Math.pow(8*A+1, 0.5)-1)/2);
//		return res;
	}

	public int solve_specialInteger(ArrayList<Integer> A, int B) {
		int min = A.size();
		int i = 0;
		int j = 0;
		int sum = 0;
		while (sum <= B && j < A.size()) {
			sum += A.get(j);
			while (sum > B) {
				min = Math.min(min, j - i + 1);
				sum -= A.get(i);
				i++;
			}
			j++;
		}
		return min-1;
	}

	public static void main(String[] args) {

		Searching s = new Searching();
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);a.add(2);a.add(3);a.add(4);a.add(5);
		System.out.println(s.solve_specialInteger(a,10));
	}
}
