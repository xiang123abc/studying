package cn.ecut.demo;

import java.util.Scanner;
 
public class Demo1{
 
    public static void main(String args[]){
 
        Scanner sc=new Scanner(System.in);
 
        while(sc.hasNext()){
 
            String s=sc.next();
 
            String str=s.substring(1,s.length()-1);
 
            String ss[]=str.split(",");
 
            int a[]=new int[ss.length];
 
            for(int i=0;i<ss.length;i++)
 
            a[i]=Integer.parseInt(ss[i]);
 
            boolean o=false;
 
            int count=0;
 
            int k=0;
 
            while(count<=a.length){
 
                count++;
 
                k+=a[k];
 
                if(k<0||k>=a.length){
 
                    o=true;
 
                    break;
 
                }
 
            }
 
            System.out.println(o);
 
        }
 
    }
 
}