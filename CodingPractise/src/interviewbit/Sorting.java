package interviewbit;

public class Sorting {
	
	// merge sort
	
	void merge(int Arr[], int start, int mid, int end) {

		// create a temp array
		int temp[] = new int[end - start + 1];

		// crawlers for both intervals and for temp
		int i = start, j = mid+1, k = 0;

		// traverse both arrays and in each iteration add smaller of both elements in temp 
		while(i <= mid && j <= end) {
			if(Arr[i] <= Arr[j]) {
				temp[k] = Arr[i];
				k += 1; i += 1;
			}
			else {
				temp[k] = Arr[j];
				k += 1; j += 1;
			}
		}

		// add elements left in the first interval 
		while(i <= mid) {
			temp[k] = Arr[i];
			k += 1; i += 1;
		}

		// add elements left in the second interval 
		while(j <= end) {
			temp[k] = Arr[j];
			k += 1; j += 1;
		}

		// copy temp to original interval
		for(i = start; i <= end; i += 1) {
			Arr[i] = temp[i - start];
		}
	}
	void mergeSort(int Arr[], int start, int end) {

		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(Arr, start, mid);
			mergeSort(Arr, mid+1, end);
			merge(Arr, start, mid, end);
		}
	}
	
	// insertion sort
	
	void insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;
            
            /* Move elements of arr[0..i-1], that are
            greater than key, to one position ahead
            of their current position */
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }
	
	// quick sort
	
	int partition(int arr[], int low, int high) 
	{ 
		int pivot = arr[high]; 
		int i = (low-1); // index of smaller element 
		for (int j=low; j<high; j++) 
		{ 
			// If current element is smaller than or 
			// equal to pivot 
			if (arr[j] <= pivot) 
			{ 
				i++; 

				// swap arr[i] and arr[j] 
				int temp = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp; 
			} 
		} 

		// swap arr[i+1] and arr[high] (or pivot) 
		int temp = arr[i+1]; 
		arr[i+1] = arr[high]; 
		arr[high] = temp; 

		return i+1; 
	} 

	void quickSort(int arr[], int low, int high) 
	{ 
		if (low < high) 
		{ 
			/* pi is partitioning index, arr[pi] is 
			now at right place */
			int pi = partition(arr, low, high); 

			// Recursively sort elements before 
			// partition and after partition 
			quickSort(arr, low, pi-1); 
			quickSort(arr, pi+1, high); 
		} 
	}
	
	// selection sort
	
	void swap(int A[], int i, int j) {  
        int temp = A[i];  
        A[i] = A[j];  
        A[j] = temp;  
    }  
  
    int findMinIndex(int A[], int start) {  
        int min_index = start;  
  
        ++start;  
  
        while(start < A.length) {  
            if(A[start] < A[min_index])  
                min_index = start;  
  
            ++start;  
        }  
  
        return min_index;  
    }  
  
    void selectionSort(int A[]) {  
        for(int i = 0; i < A.length; ++i) {  
            int min_index = findMinIndex(A, i);  
  
            if(i != min_index)  
                swap(A, i, min_index);  
        }  
    } 
	
	// bubble sort
    
	void bubbleSort(int[] array) {
		int n = array.length;
		for (int i = 0; i < n - 1; i++) {
			Boolean swapped = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (array[j] > array[j + 1]) /* For descending order use < */
				{
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped)
				break;
		}
		System.out.println("Sorted list of numbers:");
		for (int i = 0; i < n; i++)
			System.out.println(array[i]);
	}
	
	
}
