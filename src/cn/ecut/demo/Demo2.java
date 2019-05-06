package cn.ecut.demo;

public class Demo2{
    public static void main(String [] args){
        int [] arr = {6,1,1,1,4};
        int h = arr.length/2;
        int [] nArr = new int[arr.length];
        int curr = 0;
        int t = 0;
        int value = arr[curr];//
        int n = 0;//出现次数
        while(curr<arr.length) {
        	value = arr[curr];
        	if(arr[t]==value) {
        		n++;
        	}
        	t++;
        	if(n>h) {
        		System.out.print(value);
        		break;
        	}
        	if(t==arr.length) {
        		curr++;
        		t = curr;
        		n=0;
        	}
        }
        
        
    }
}