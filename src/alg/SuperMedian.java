package alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SuperMedian {
	
	public int median(int[] list) {
		return select(list, list.length/2);
	}
	public int select(int[] list, int selection) {
		int n = list.length;
		if (n <= 5) {
			return adhocSelection(list, selection);
		}
		
		int medSize = ((n % 5 == 0) ? n/5 : (n/5 + 1));
		int[] medians = new int[medSize];
		
		for (int i = 0; i < medSize; ++i) {
			int end =  (5*i+4 < n) ? 5*i+4 : n-1;
			int[] temp = Arrays.copyOfRange(list, 5*i, end+1);
			medians[i] = adhocSelection(temp, temp.length/2);
		}
		
		int pivot = select(medians, medSize/2);
		Tuple pq = partition(list, pivot);
		int p = pq.left(), q = pq.right();
		
		if (selection < p) {
			return select(Arrays.copyOfRange(list, 0, p), selection);
		}
		else if (selection > q) {
			return select(Arrays.copyOfRange(list, q+1, n), selection-q-1);
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
			list = temp;
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
		ArrayList<Integer> leftSide = new ArrayList<Integer>();
		ArrayList<Integer> rightSide = new ArrayList<Integer>();
		
		for (int i : list) {
			if (i < pivot) {
				leftSide.add(i);
				p++; q++;
			}
			else if (i == pivot) {
				q++;
			}
			else {
				rightSide.add(i);
			}
		}
		
		for (int i = 0; i < leftSide.size(); ++i) {
			list[i] = leftSide.get(i);
		}
		for (int i = 0, j = leftSide.size(); i <= q-p; ++i, ++j) {
			list[j] = pivot;
		}
		for (int i = 0, j = leftSide.size()+(q-p+1); i < rightSide.size(); ++i, ++j) {
			list[j] = rightSide.get(i);
		}
		
		return new Tuple(p, q);
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long end = 0;
		int n = 1000000;
		int[] arr = new int[n];
		
		for(int i=0; i < arr.length; ++i) {
			arr[i] = (new Random()).nextInt(100);
		}

		int[] oldarr = Arrays.copyOf(arr, arr.length);
		Arrays.sort(oldarr);
		end = System.currentTimeMillis();
		System.out.println("Sort time: " + (end - start));

		start = System.currentTimeMillis();
		SuperMedian s = new SuperMedian();
		int med = s.median(arr);
		end = System.currentTimeMillis();
		System.out.println("Super-Median time: " + (end - start));
		
		System.out.println("Super Median median:" + med);
		System.out.println("Actual median:" + oldarr[arr.length/2]);
	}

}















