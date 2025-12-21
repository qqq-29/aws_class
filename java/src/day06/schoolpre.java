package day06;

import java.util.Scanner;

public class schoolpre {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String my = scan.next();
		String over = scan.next();
		int sss = scan.nextInt();
		MySolution solution = new MySolution();
		String aaa = solution.solution(my, over, sss);
		System.out.println(aaa);
	}

}

class MySolution {
    public String solution(String my_string, String overwrite_string, int s) {
        StringBuilder sb = new StringBuilder(my_string);
        int a = overwrite_string.length();
        sb.replace(s,a+s,overwrite_string);
        return sb.toString();
    }
}