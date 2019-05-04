package cn.ecut.demo;

public class Demo1 {
	public static void main(String[] args) {
		int [] arr = {};
		int length = arr.length;
		int i = 0;
		boolean  flag ;
		while(i<arr.length && i>0) {
			if(arr[i]==0) {
				flag = false;
				break;
			}
			if(arr[i]>0) {
				i = i+arr[i];
			}
			if(arr[i]<0) {
				i = i+arr[i];
			}
		}
		if(i>=arr.length && i<0)
			flag = true;
	}
}
