package cn.ecut.demo;

import java.util.*;

public class Demo4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int  n = scan.nextInt();
		int[] fav = new int[n];
		for(int i=0;i<n;i++) {
			fav[i] = scan.nextInt();
		}
		Map<Integer,List<Integer>> map = new HashMap();
		for(int i=0;i<n;i++) {
			int key = fav[i];
			int value = i+1;
			if(!map.containsKey(key)) {
				List<Integer> list = new LinkedList();
				list.add(value);
				map.put(key,list);
			}else {
				List list = map.get(key);
				list.add(value);
				
			}
			int m = scan.nextInt();
			List list = new LinkedList();
			for(int i=0;i<m;i++){
				int lo=scan.nextInt();
	            int hi=scan.nextInt();
	            int des=scan.nextInt();
	            List<Integer> list2=map.get(des);
	            int count=0;
			}
		}
	}
}
