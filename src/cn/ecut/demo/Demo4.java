package cn.ecut.demo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Demo4 {

	public static void main(String[] args) {
		ArrayList arr= new ArrayList();
		for(int i=0;i<17;i++) {
			arr.add("a");
		}
		int n = 7;
		n = n>>1;
		System.out.println(n);
		HashMap map = new HashMap();
		map.put("", 1);
		ConcurrentHashMap cch = new ConcurrentHashMap();
		cch.put("", 1);
	}
}
