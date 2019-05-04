package cn.ecut.demo;

import java.util.Scanner;

public class KMP匹配算法 {
	/**
	 * 注意  i， 和  j while循环后 表示什么  （j 表示匹配上 p 字符的次数 ）
	 * @param s
	 * @param p
	 * @return
	 */
	int ViolentMatch(char[] s, char[] p)  
	{  
	    int sLen =s.length;  
	    int pLen =p.length; //strlen(p);  
	  
	    int i = 0;  
	    int j = 0;  
	    while (i < sLen && j < pLen)  
	    {  
	        if (s[i] == p[j])  
	        {  
	            //①如果当前字符匹配成功（即S[i] == P[j]），则i++，j++      
	            i++;  
	            j++;  
	        }  
	        else  
	        {  
	            //②如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0      
	            i = i - j + 1;  
	            j = 0;  
	        }  
	    }  
	    //匹配成功，返回模式串p在文本串s中的位置，否则返回-1  
	    if (j == pLen)  
	        return i - j;  
	    else  
	        return -1;  
	}  
 /**
 * 字符串旋转:
	给定两字符串A和B，如果能将A从中间某个位置分割为左右两部分字符串（都不为空串），并将左边的字符串移动到右边字符串后面组成新的字符串可以变为字符串B时返回true。
	例如：如果A=‘youzan’，B=‘zanyou’，A按‘you’‘zan’切割换位后得到‘zanyou’和B相同返回true。
	输入描述:
	2个不为空的字符串(说明:输入一个字符串以英文分号";"分割为2个字符串)
	例如:youzan;zanyou 即为A=‘youzan’，B=‘zanyou’
 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		String []  str2 = str.split(";");
		String a = str2[0];
		String b = str2[1];
		int i = 0; //a
		int j = 0; //b
		while (i<a.length() && j<b.length()) {
			if(a.substring(i,i+1).equals(b.substring(j,j+1))) {
				i++;
				j++;
			}else {
				i = i-j +1;
				j = 0;
			}
		}
		if(j!=0 && a.substring(0,j).equals(b.substring(j,b.length()))) {
			System.out.println(true);
		}else
			System.out.println(false);
	}
}
