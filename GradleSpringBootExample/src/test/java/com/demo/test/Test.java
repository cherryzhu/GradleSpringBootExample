package com.demo.test;

public class Test {
	public static void main(String[] args) {
		int[] a = new int[10];
		for(int i = 0 ; i < a.length;i++){
			a[i] = i;
		}
		int[] b = a;
		
		b[5] = 23;
		for(int i : a){
			System.out.print(i+",");
		}
	}
}
