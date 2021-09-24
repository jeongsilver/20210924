package test;

import java.util.Scanner;

public class ScanUtil {
	static Scanner sc = new Scanner(System.in);
	
	public static int readInt(String msg) {
		System.out.print(msg);
		int num=sc.nextInt();
		sc.nextLine();
		return num;
	}
	
	public static String readStr(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
	
}
