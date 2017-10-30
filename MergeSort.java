package combinatorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MergeSort<T extends Comparable<T>> {
	
	private List<T> items    = new ArrayList<>();
	
	@SafeVarargs
	public MergeSort(T ... items) {
		this(Arrays.asList(items));
	}
	
	public MergeSort(List<T> items) {
		this.items = new ArrayList<T>(items);
	}
	
	private List<Integer> calculateSortedIndexes(List<T> subList) {
		int n = subList.size();
		ArrayList<Integer> sortedIndexes = new ArrayList<Integer>(Collections.nCopies(n, 0));
		if(n == 1) {
			return sortedIndexes;
		}
		int m = Math.floorDiv(n,2);
		List<Integer> sortedIndexesSubList1  = calculateSortedIndexes(subList.subList(0, m));
		List<Integer> sortedIndexesSubList2  = calculateSortedIndexes(subList.subList(m,n));
		int k = 0, l = 0;
		while(k < m && l < n-m) {
			T alfa = subList.get(sortedIndexesSubList1.get(k));
			T beta = subList.get(m + sortedIndexesSubList2.get(l));
			if(alfa.compareTo(beta) <= 0) {
				sortedIndexes.set(k+l, sortedIndexesSubList1.get(k));
				k++;
			}
			else {
				sortedIndexes.set(k+l, m + sortedIndexesSubList2.get(l)); 
				l++;
			}
		}
		while(k <m ) {
			sortedIndexes.set(k+l,  sortedIndexesSubList1.get(k)); k++;
		}
		while(l < n-m ) {
			sortedIndexes.set(k+l,  m+sortedIndexesSubList2.get(l)); l++;
		}
		return sortedIndexes;
	}
	

	public List<T> sort() {
		List<T> sortedItems   =  new ArrayList<T>();
		List<Integer> indexes =  calculateSortedIndexes(items);
		for(Integer idx : indexes) {
			sortedItems.add(items.get(idx));
		}
		return sortedItems;
	}

	
	public static void main(String args[]) {
		MergeSort<Integer> ms = new MergeSort<Integer>(32,12,17,24,11,22,54);
		System.out.println(ms.sort());
		MergeSort<String> ms2 = new MergeSort<>("Abate","Giocoliere","Cane","Gatto","Delfino","Sambuco","Balestra");
		System.out.println(ms2.sort());
	}



}
