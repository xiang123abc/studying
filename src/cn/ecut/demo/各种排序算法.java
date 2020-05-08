package cn.ecut.demo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** https://mp.weixin.qq.com/s/t0dsJeN397wO41pwBWPeTg
 * ����ķ����У���������ֱ�Ӳ�������ϣ�����򣩣���������ð�����򡢿������򣩣�ѡ������ֱ��ѡ�����򡢶����򣩣�
	�鲢���򣬷������������򡢻�������
 * @author xiang
 * ð�����򣬲������� ƽ��ʱ�临�Ӷȶ�Ϊ  O(n2) �� ���ʱ�临�Ӷȶ�Ϊ   O(n)
 * ѡ�������ʱ�临�ӶȺ����ʱ�临�Ӷȶ�Ϊ  O(n2)
 * �鲢���򣬿�������Ͷ����� �� ƽ��ʱ�临�ӶȺ����ʱ�临�Ӷȶ�Ϊ  O( nlogn)
 * Ͱ���򣬼�������      �� ƽ��ʱ�临�ӶȺ����ʱ�临�Ӷȶ�Ϊ  O(n+k)
 *
 */
 public class 各种排序算法 {

	public static void main(String[] args) {
		int [] arr = {4,8,5,1,2,6,7,3};
		MergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	

	/**
	 * 快速排序
	 * @param arr
	 * @param low
	 * @param high
	 */
	private static void quickSort2(int[] arr, int low, int high) {
		if(low<high) {
			int index = getIndex2(arr,low,high);
			quickSort2(arr,0,index-1);
			quickSort2(arr,index+1,high);
		}
	}
	private static int getIndex2(int[] arr, int low, int high) {
		int tmp = arr[low];
		while(low<high) {
			while(low < high && arr[high]>=tmp) {
				high--;
			}
			arr[low] = arr[high];
			while(low < high && arr[low]<=tmp) {
				low++;
			}
			arr[high] = arr[low];
		}
		arr[low] = tmp;
		return low;
	}
	private static void quickSort(int[] arr, int low, int high) {

		if (low < high) {
			// 找寻基准数据的正确索引
			int index = getIndex(arr, low, high);

			// 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
			quickSort(arr, 0, index - 1);
			quickSort(arr, index + 1, high);
			System.out.println(0);
		}

	}
	private static int getIndex(int[] arr, int low, int high) {
		// 基准数据
		int tmp = arr[low];
		while (low < high) {
			// 当队尾的元素大于等于基准数据时,向前挪动high指针
			while (low < high && arr[high] >= tmp) {
				high--;
			}
			// 如果队尾元素小于tmp了,需要将其赋值给low
			arr[low] = arr[high];
			// 当队首元素小于等于tmp时,向前挪动low指针
			while (low < high && arr[low] <= tmp) {
				low++;
			}
			// 当队首元素大于tmp时,需要将其赋值给high
			arr[high] = arr[low];

		}
		// 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
		// 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
		arr[low] = tmp;
		return low; // 返回tmp的正确位置
	}
	 
    /**
     * �������򷽷�
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int[] QuickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
            QuickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            QuickSort(array, smallIndex + 1, end);
        return array;
    }

	/**
     * 归并,二分？
     *
     * @param array
     * @return
     */
    public static int[] MergeSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        //Arrays.copyOfRange(array, 0, mid);
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge2(MergeSort(left), MergeSort(right));
    }
    
    public static int[] merge2(int[] left, int[] right) {//4 8, 1 5
    	int [] res = new int[left.length+right.length];
    	for(int index = 0,i = 0,j=0;index<res.length;index++) {
    		if(i>=left.length) {   // 左边的数组取完了，直接取右边的
    			res[index] = right[j++];
    		}else if(j>=right.length){// 右边的数组取完了，直接取左边的
    			res[index] = left[i++];
    		} else if(left[i]>right[j]) {//左边的 大于 右边的，取右边的，右边的j++（移一位）
    			res[index] = right[j++];
    		}else {						//右边的 大于左边的，取左边边的，右边的i++（移一位）
    			res[index] = left[i++];
    		}
    	}
    	// 4 8 ,1 5 ---->得到新数组  1 4 5 8 
		return res;
    }
    /**
     * �鲢���򡪡�����������õ������ϳ�һ����������
     *  归并
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }
	/**
     * ð������
     *��������T(n) = O(n)   ��������T(n) = O(n2)   ƽ�������T(n) = O(n2)
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
        return array;
    }
    /**
     * ѡ������              ѡ��֮�� ��С����������
     * ��������T(n) = O(n2)  ��������T(n) = O(n2)  ƽ�������T(n) = O(n2)
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) //�ҵ���С����
                    minIndex = j; //����С������������
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
    /**
     * ��������        ����֮ǰ�Ѿ�����õ� λ��
     * ��������T(n) = O(n)   ������T(n) = O(n2)   ƽ�������T(n) = O(n2)
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }
   
    /**
     * ���������㷨����partition  
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }
    /**
     * ��������������Ԫ��
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
  //����ȫ�ֱ��������ڼ�¼����array�ĳ��ȣ�
    static int len;
    /**
     * �������㷨
     *
     * @param array
     * @return
     */
    public static int[] HeapSort(int[] array) {
        len = array.length;
        if (len < 1) return array;
        //1.����һ������
        buildMaxHeap(array);
        //2.ѭ��������λ�����ֵ����ĩλ������Ȼ�������µ�������
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0);
        }
        return array;
    }
    /**
     * ��������
     *
     * @param array
     */
    public static void buildMaxHeap(int[] array) {
        //�����һ����Ҷ�ӽڵ㿪ʼ���Ϲ�������
        for (int i = (len - 1) / 2; i >= 0; i--) {
            adjustHeap(array, i);
        }
    }
    /**
     * ����ʹ֮��Ϊ����
     *
     * @param array
     * @param i
     */
    public static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        //������������������������ڸ��ڵ㣬�����ָ��ָ��������
        if (i * 2 < len && array[i * 2] > array[maxIndex])
            maxIndex = i * 2;
        //������������������������ڸ��ڵ㣬�����ָ��ָ��������
        if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex])
            maxIndex = i * 2 + 1;
        //������ڵ㲻�����ֵ���򽫸��ڵ������ֵ���������ҵݹ�����븸�ڵ㽻����λ�á�
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }
	
}
