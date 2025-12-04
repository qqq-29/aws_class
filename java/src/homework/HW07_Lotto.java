package homework;

public class HW07_Lotto {

	public static void main(String[] args) {
		/* 1~45사이의 랜덤한 수 6개를 생성하여 배열에 저장하고 출력하세요.
		 * 중복 X
		 * */
		
		int [] lotto = new int[6];
		int min = 1;
		int max = 45;
		for(int i = 0; i < 6; i++) {
			lotto[i] = (int)(Math.random()*(max-min+1)+min);
			for(int j = 0; j<i; j++) {
				if(lotto[j]==lotto[i]) {
					i--;
					break;
				}
			}
		}
		for (int t = 0; t<lotto.length; t++) {
		System.out.println("lotto : " + lotto[t]);
		//테스트 할 때는 1~8로 테스트하여 중복되지 않는지 확인 후 이상 없으면 1~45로 변경
		}
	}

}