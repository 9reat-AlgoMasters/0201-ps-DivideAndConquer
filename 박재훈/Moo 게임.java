import java.util.Scanner;

public class Main {
  //S(k) moo 수열의 길이를 저장하기 위한 배열
	static int[] s;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
    //계산해보니 S(27)부터 길이가 10^9 이상이 됨. 풀이에 필요한 수열 개수는 0~27까지 28개.
		s = new int[28];
    //초기 수열 S(0) = moo, 길이3
		s[0] = 3;
		int len = s[0];
		int idx = 0;
    //i번 수열 길이의 합은 이전 수열의 2배 + m 1개 + o i+2개
    //입력받은 N보다 큰 길이를 가지는 최초의 k를 구함(idx변수에 저장)
		for (int i = 1; len < N; i++) {
			s[i] = 2*s[i-1] + i+3;
			len = s[i];
			idx = i;
		}
		moo(N, idx);
	}
  
  //S(k)수열에서 n번째 글자를 구하는 메서드
	static void moo(int n, int idx) {
    //n이 1일때 => 각 수열의 첫번째 글자가 뭐냐는 의미 => 항상 m임
		if(n == 1) {
			System.out.println('m');
			return;
		}
    //n이 S(0)길이보다 작음 => N이 1일 때는 위에서 처리해줬으므로 2,3일때 처리 => S(0)은 moo니까 항상 o
		if(idx == 0) {
			System.out.println('o');
			return;
		}
    //S(k) = S(k-1) + "moo...o"(o가 k+2개) + S(k-1)
    //n이 S(k-1)의 길이보다 작다면 뒤의 "moo..o"+ S(k-1)는 의미가 없으며 S(k-1)수열에서의 n번째 글자를 구하면 됨
		if(n <= s[idx-1]) {
			moo(n, idx-1);
      //n이 "moo...o" 영역 어딘가에 속함
		}else if(n <= s[idx-1] + idx+3) {
			if(n - s[idx-1] == 1) {
				System.out.println('m');
			}else {
				System.out.println('o');
			}
			return;
      //n이 뒷쪽 S(k-1) 영역 어딘가에 속함
		}else {
      //앞의 S(k-1) + "moo...o"길이만큼 빼준 n을 인자로 재귀
			moo(n-(s[idx-1] + idx+3), idx-1);
		}
	}
}
