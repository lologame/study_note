package cn.lo.collection;

public class Sort {
	
	public static void mergeSort(int[] arr){
		int[] tmpArr = new int[arr.length];
		mergeSort(arr,tmpArr,0,arr.length-1);
	}
	
	private static void mergeSort(int[] arr, int[] tmpArr,int startIdx , int endIdx){
		if(startIdx == endIdx){
			return;
		}
		
		int center = (startIdx + endIdx) / 2;
		
		mergeSort(arr,tmpArr,startIdx,center);
		mergeSort(arr,tmpArr,center+1,endIdx);
		
		merge(arr,tmpArr,startIdx,center,endIdx);
	}
	
	private static void merge(int[] arr,int[] tmpArr,int startIdx,int center, int endIdx){
		int leftStart = startIdx;
		int leftEnd   = center;
		int rightStart = center + 1;
		int rightEnd = endIdx;
		
		int leftPos = leftStart;
		int rightPos = rightStart;
		
		int tmpPos = startIdx;
		
		while((leftPos <= leftEnd) && (rightPos <= rightEnd)){
			if(arr[leftPos] < arr[rightPos]){
				tmpArr[tmpPos++] = arr[leftPos];
				leftPos++;
			}else{
				tmpArr[tmpPos++] = arr[rightPos];
				rightPos++;
			}
			
		}
		
		while(leftPos <= leftEnd){
			tmpArr[tmpPos++] = arr[leftPos++];
		}
		
		while(rightPos <= rightEnd){
			tmpArr[tmpPos++] = arr[rightPos++];
		}
		
		System.arraycopy(tmpArr, startIdx, arr, startIdx, endIdx-startIdx+1);
		
	}
	
	public static void quikSort(int[] arr){
		quikSort(arr, 0, arr.length-1);
	}
	
	private static int median3(int[] arr , int left , int right){
		
		int center = (left + right)/2;
		
		if(arr[left] > arr[center]){
			swap(arr,left,center);
		}
		
		if(arr[left] > arr[right]){
			swap(arr,left,right);
		}
		
		if(arr[center] > arr[right]){
			swap(arr,center,right);
		}
		
		swap(arr,center,right);
		
		return arr[right];
	}
	
	private static void quikSort(int[] arr , int left , int right){
		
		if(left >= right){
			return;
		}
		
		int pivot = median3(arr, left, right);
		
		int pivotIdx = 0;
		
		for(int i = left,j = right-1;;){
			while(arr[i] < pivot){i++;}
			while(arr[j] > pivot){j--;};
			if(i < j){
				swap(arr,i,j);
			}else{
				swap(arr,i,right);
				pivotIdx = i;
				break;
			}
		}
		
		quikSort(arr,left,pivotIdx-1);
		quikSort(arr,pivotIdx+1,right);
	
	}
	
	private static void swap(int[] arr , int i , int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,5,7,6,2,4,8,4,12,13,5};
		mergeSort(arr);
		
		for(int i = 0;i  <arr.length; i++){
			System.out.print(arr[i]+" ");
		}
		
		
		System.out.println();
		
		quikSort(arr);
		
		for(int i = 0;i  <arr.length; i++){
			System.out.print(arr[i]+" ");
		}
	}

}
