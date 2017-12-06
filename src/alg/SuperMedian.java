package alg;

import java.util.ArrayList;
import java.util.Arrays;

public class SuperMedian {
	
	public int median(int[] list) {
		return select(list, (int) Math.floor(list.length/2.0));
	}
	public int select(int[] list, int selection) {
		int n = list.length;
		if (n <= 5) {
			return adhocSelection(list, selection);
		}
		
		int medSize = (int) ((n % 5 == 0) ? Math.floor(n/5.0) : Math.floor(n/5.0) + 1);
		int[] medians = new int[medSize];
		
		for (int i = 0; i < medSize; ++i) {
			int end =  (5*i+4 < n) ? 5*i+4 : n-1;
			int[] temp = Arrays.copyOfRange(list, 5*i, end);
			medians[i] = adhocSelection(temp, (int) Math.floor(temp.length/2.0));
		}
		
		int pivot = select(medians, (int) Math.floor(medSize/2.0));
		Tuple pq = partition(list, pivot);
		int p = pq.left(), q = pq.right();
		
		if (selection < p) {
			return select(Arrays.copyOfRange(list, 0, p-1), selection);
		}
		else if (selection > q) {
			return select(Arrays.copyOfRange(list, q+1, n-1), selection-q-1);
		}
		else {
			return pivot;
		}
	}
	
	public int adhocSelection(int[] list, int selection) {
		int[] sortedList = adhocSort(list);
		return sortedList[selection];
	}
	
	public int[] adhocSort(int[] list) {
		int n = list.length;
		
		if (n == 1) {
			return list;
		}
		else if (n == 2) {
			return adhocSort2(list);
		}
		else if (n == 3) {
			return adhocSort3(list);
		}
		else if (n == 4) {
			return adhocSort4(list);
		}
		else {
			return adhocSort5(list);
		}
	}
	private int[] adhocSort2(int[] list) {
		if (list[0] > list[1]) {
			return swap(list, 0, 1);
		}
		return list;
	}
	private int[] adhocSort3(int[] list) {
		if (list[0] > list[1]) {
			list = swap(list, 0, 1);
		}
		int[] temp = { list[0], list[1] };
		return insert(temp, list[2]);
	}
	private int[] adhocSort4(int[] list) {
		if (list[0] > list[1]) {
			list = swap(list, 0, 1);
		}
		if (list[2] > list[3]) {
			list = swap(list, 2, 3);
		}
		if (list[0] > list[2]) {
			int[] temp = { list[2], list[3] , list[0], list[1] };
		}
		int[] temp = { list[2], list[3] };
		temp = insert(temp, list[1]);
		int[] ret = { list[0], temp[0], temp[1], temp[2] };
		
		return ret;
	}
	private int[] adhocSort5(int[] list) {
		if (list[0] > list[1]) {
			list = swap(list, 0, 1);
		}
		if (list[2] > list[3]) {
			list = swap(list, 2, 3);
		}
		if (list[0] > list[2]) {
			int[] temp = { list[2], list[3], list[0], list[1], list[4] };
			list = temp;
		}
		
		int[] temp = { list[0], list[2], list[3] };
		int[] xList = insert(temp, list[4]);
		int[] temp2 = { xList[1], xList[2], xList[3] };
		int[] ret = { xList[0], temp2[0], temp2[1], temp2[2], list[1] };
		
		return ret;
	}
	private int[] insert(int[] list, int x) {
		if (x < list[1]) {
			if (x < list[0]) {
				if (list.length == 2) {
					int[] temp = { x, list[0], list[1] };
					return temp;
				}
				else {
					int[] temp = { x, list[0], list[1], list[2] };
					return temp;
				}
			}
			else {
				if (list.length == 2) {
					int[] temp = { list[0], x, list[1] };
					return temp;
				}
				else {
					int[] temp = { list[0], x, list[1], list[2] };
					return temp;
				}
			}
		}
		else {
			if (list.length == 2 || x > list[2]) {
				if (list.length == 2) {
					int[] temp = { list[0], list[1], x };
					return temp;
				}
				else {
					int[] temp = { list[0], list[1], list[2], x };
					return temp;
				}
			}
			else {
				int[] temp = { list[0], list[1], x, list[2] };
				return temp;
			}
		}
	}
	
	private int[] swap(int[] list, int a, int b) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
		
		return list;
	}
	private Tuple partition(int[] list, int pivot) {
		int p = 0, q = -1;
		int med = list[pivot];
		ArrayList<Integer> leftSide = new ArrayList<Integer>();
		ArrayList<Integer> rightSide = new ArrayList<Integer>();
		
		for (int i : list) {
			if (i < med) {
				leftSide.add(i);
				p++; q++;
			}
			else if (i == med) {
				q++;
			}
			else {
				rightSide.add(i);
			}
		}
		
		for (int i = 0; i < leftSide.size(); ++i) {
			list[i] = leftSide.get(i);
		}
		for (int i = 0, j = leftSide.size(); i < q-p; ++i, ++j) {
			list[j] = med;
		}
		for (int i = 0, j = leftSide.size()+(q-p); i < rightSide.size(); ++i, ++j) {
			list[j] = rightSide.get(i);
		}
		
		return new Tuple(p, q);
	}

	public static void main(String[] args) {
		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		
		SuperMedian s = new SuperMedian();
		int med = s.median(arr);
		
		System.out.println(med);
	}

}















