package day06;

public class 연습장 {

	public static void main(String[] args) {
		
		String str1 = "Hello world";
		String str2 = "world";
		String str3 = "\n\t\t\t안녕하세요\n\n\n";
		String str4 = "사과,배,바나나,오렌지";
		
		System.out.println("str1에 str2가 몇번지부터 시작됩니까?" + str1.indexOf(str2));
		System.out.println("str1에 str2가 있습니까?" + str1.contains(str2));
		System.out.println("str1이 str2와 같습니까?" + str1.equals(str2));
		System.out.println("str1 길이" + str1.length());
		System.out.println("str1의 2번지부터 부분문자열은?" + str1.indexOf(2));
		System.out.println("str3.trim() : " + str3.trim());
		String fr [] = str4.split(",");
		for(String f : fr) {
			System.out.println(f);
		}
	}
		


}


