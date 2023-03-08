import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long X = sc.nextLong();
		
		System.out.println(burger(N,X));
	}
  
  //레벨 n인 버거에서 x만큼 먹을 때 먹는 패티 수를 구하는 메서드
	static long burger(int n, long x) {
    //0장 먹으면 당연히 0
		if(x == 0) return 0;
    //레벨 0 버거는 패티1장으로 이루어짐, 1리턴
		if(n == 0) return 1;
    
    //가운데 패티의 인덱스는 2^(n+1)-1이라는 규칙을 가짐
    //ex) 1레벨BP'P'PB 2^2-1 = 3
    //    2레벨BBPPPB'P'BPPPBB 2^3-1 = 7
		long mid = ((long)1<<(n+1)) - 1;
		
    //n레벨 햄버거는 번 + n-1햄버거 + 패티 + n-1햄버거 +번 으로 이루어짐
    //가운데 패티 인덱스 기준으로 더 적게 먹으면
		if(x < mid) {
      //맨 앞 번 하나 먹었다치고 n-1 버거에서 x-1장 먹었을 때의 결과를 구하는 것과 같음 
			return burger(n-1, x-1);
      //정확히 가운데 패티까지 먹었으면
		}else if(x == mid){
      //가운데 패티 1장 + n-1버거를 모두 먹었을 때의 결과 리턴
      //n-1 버거 크기 = n버거의mid-2 
			return 1 + burger(n-1, x-2);
      //뒷쪽 n-1버거까지 먹는 경우
		}else {
      //전체 다먹는 경우
			if(x == 2*mid-1) {
				return 1 + 2 * burger(n-1, mid - 2);
        //앞쪽 n-1버거 전체 + 가운데 패티 1장 + 뒷쪽 일부
			}else {
				return 1 + burger(n-1, mid - 2) + burger(n-1, x-mid);
			}
		}
	}
}
