package cn.ecut.demo;

public class Demo3{
    public static void main(String [] args){
        int [] arr = {6,5,4,4,1,2};
        for(int i = 0;i<arr.length;i++) {
        	int t = i;
        	for(int j = i;j<(arr.length-1);j++) {
        		if(arr[j]>arr[j+1]) {
        			t = j+1;
        		}
        	}
        	int temp = arr[i];
        	arr[i]= arr[t];
        	arr[t] = temp;
        }
        System.out.println(arr);
    }
}