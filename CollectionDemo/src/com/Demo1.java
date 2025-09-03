package com;

import java.util.*;

public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {1,2,3,4,5,6,7,90};
		//ArrayList al= new ArrayList();
		//LinkedList al= new LinkedList();
		//Stack al= new Stack();
		//Vector al= new Vector();
		//HashSet al= new HashSet(); // no dup + random order
		//LinkedHashSet al= new LinkedHashSet();
		//TreeSet al= new TreeSet();
		//HashMap map = new HashMap<>(); // no dup + random order + key value pair
		//TreeMap map= new TreeMap<>();   // no dup + sorted order + key value pair
		//LinkedHashMap map= new LinkedHashMap<>(); // no dup + user entered order + k v pair
		Hashtable map = new Hashtable(); // legacy class
		map.put(11, 100);
		map.put(15, 500);
		map.put(12, 200);
		map.put(13, 300);
		map.put(14, 400);
		map.put(11, 1000);
//		al.add(33);
//		al.add(400);
//		al.add(200);
//		al.add(323);
		//al.add("Heko");
		//al.add(499);
		Set s = map.entrySet();
		Iterator i= s.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	

	}

}
