package cn.ecut.demo;

import java.util.Arrays;
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
public class ���������㷨 {

	public static void main(String[] args) {
		int [] arr = {4,2,5,1,6};
		HeapSort(arr);
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
     * �鲢����
     *
     * @param array
     * @return
     */
    public static int[] MergeSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(MergeSort(left), MergeSort(right));
    }
    /**
     * �鲢���򡪡�����������õ������ϳ�һ����������
     *
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
