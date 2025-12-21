package day06;
import java.util.Scanner;

public class pre {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        StringBuilder c = new StringBuilder();
        for(char b : a.toCharArray()){
        	if(Character.isUpperCase(b)) {
        		c.append(Character.toLowerCase(b));
        	}else if(Character.isLowerCase(b)) {
        		c.append(Character.toUpperCase(b));
        	}else {
        		c.append(b);
        	}
        }
        System.out.println(c);
    }
	}

class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        StringBuilder sb = new StringBuilder(my_string);
        int a = my_string.length();
        sb.replace(s,a,overwrite_string);
        return my_string;
    }
}