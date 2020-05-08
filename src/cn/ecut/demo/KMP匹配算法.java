package cn.ecut.demo;

import java.util.Scanner;

public class KMP匹配算法 {
	/**
	 * ע��  i�� ��  j whileѭ���� ��ʾʲô  ��j ��ʾƥ���� p �ַ��Ĵ��� ��
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
	            //�������ǰ�ַ�ƥ��ɹ�����S[i] == P[j]������i++��j++      
	            i++;  
	            j++;  
	        }  
	        else  
	        {  
	            //�����ʧ�䣨��S[i]! = P[j]������i = i - (j - 1)��j = 0      
	            i = i - j + 1;  
	            j = 0;  
	        }  
	    }  
	    //ƥ��ɹ�������ģʽ��p���ı���s�е�λ�ã����򷵻�-1  
	    if (j == pLen)  
	        return i - j;  
	    else  
	        return -1;  
	}  
 /**
 * �ַ�����ת:
	�������ַ���A��B������ܽ�A���м�ĳ��λ�÷ָ�Ϊ�����������ַ���������Ϊ�մ�����������ߵ��ַ����ƶ����ұ��ַ�����������µ��ַ������Ա�Ϊ�ַ���Bʱ����true��
	���磺���A=��youzan����B=��zanyou����A����you����zan���иλ��õ���zanyou����B��ͬ����true��
	��������:
	2����Ϊ�յ��ַ���(˵��:����һ���ַ�����Ӣ�ķֺ�";"�ָ�Ϊ2���ַ���)
	����:youzan;zanyou ��ΪA=��youzan����B=��zanyou��
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
