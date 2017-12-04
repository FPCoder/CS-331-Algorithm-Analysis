package alg;

import java.util.List;

public class SuperMedian {
	
	public int median(int[] list) {
		return select(list, Math.floor(list.length/2));
	}
	public int select(int[] list, int selection) {
		int n = list.length;
		if (n <= 5) {
			return adhocMedian(list);
		}
		
		int medSize = (int) Math.floor(n/5.0);
		int[] medians = new int[medSize];
		
		for (int i = 0; i < medSize; ++i) {
			int[] temp = { list[5*i], list[5*i+1], list[5*i+2], list[5*i+3], list[5*i+4] };
			medians[i] = adhocMedian(temp);
		}
		
		int pivot = select(medians, (int) Math.floor(medSize/2.0));
		//TODO partition list
		
	}
	
	public int adhocMedian(int[] list) {
		int[] sortedList = adhocSort(list);
		return sortedList[(int) Math.floor(list.length/2.0)];
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
				int[] temp = { x, list[0], list[1] };
				return temp;
			}
			else {
				int[] temp = { list[0], x, list[1] };
				return temp;
			}
		}
		else {
			if (list.length == 2 || x > list[2]) {
				int[] temp = { list[0], list[1], x };
				return temp;
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

	public static void main(String[] args) {
		// intODO Auto-generated method stub

	}

}
