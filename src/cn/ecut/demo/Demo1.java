package cn.ecut.demo;

import java.util.*;
public class Demo1{
    public static void main(String args []){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String str = scan.nextLine();
            String[]  strs = str.split(",");
            String[] temparr = strs[0].split(" ");
            int [] arr = new int [temparr.length];
            int N = Integer.parseInt(strs[1]);
            for(int i=0;i<temparr.length;i++){
                arr[i]= Integer.parseInt(temparr[i]);
            }
            if(arr.length<3){
                System.out.print("False");
                return;
            }
             for(int i=0;i<arr.length-2;i++){
            	 for(int j=0;j<arr.length-1;j++){
            		 for(int k=0;k<arr.length;k++) {
            			 if(arr[i]+arr[k]+arr[j]==N) {
            				 System.out.print("True");
            				 return ;
            			 }
            		 }
            	 }
             }
        }
    }
}