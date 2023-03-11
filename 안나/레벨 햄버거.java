import java.io.*;
import java.util.StringTokenizer;


public class Main {
	static long N;
	static long X;
	static long total;
	static StringBuilder sb;
	static long result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		X = Long.parseLong(st.nextToken());
		sb = new StringBuilder();
		/*
		 * 패티의 개수
		 * 레벨 0 => 1개
		 * 레벨 1 => 1+1+1 3개
		 * 레벨 2 => 3+1+3 7개
		 * 레벨 3 => 7+1+7 15개
		 * 
		 * 전체 햄버거 장수
		 * 레벨 0 => 1개
		 * 레벨 1 => 빵+1+패+1+빵 5개
		 * 레벨 2 => 빵+5+패+5+빵 13개
		 * 레벨 3 => 빵+13+패+13+빵 29개
		 * 
		 * 전체 햄버거 장수를 
		 * 
		 */
		total = 1; //전체 햄버거 장수
		for (int i = 0; i < N; i++) {
			total = total*2 + 3;
		}
		long p = (total+1)/2 ; //총 패티의 개수
		long left = 1; //그 레벨에 더해지는 첫번째 번
		long right = total; //그 레벨에 더해지는 마지막 번 
		long mid = (left+right)/2; //mid는 무조건 그 레벨의 가운데 패티가 된다.
		result =0;
		p = (p-1)/2;
		
		while (true) {
//			System.out.println(X+" "+p+" "+total+" "+left+" "+right+" "+mid +" "+ result);
			
			//mid보다 N이 더 크면 mid 까지의 패티의 개수 이상으로 패티를 먹었다.
			if(mid == X) {
				result+=p+1;
				break;
			}
			if(mid < X) { //오른쪽에 있으면 mid 기준 왼쪽의 패티의 개수를 누적합 해줌
				if (right == X) {
					result+=p*2+1;
					break;
				}
				result+=p+1; //1을 더하는 이유는 중간에 끼는 패티의 개수 (mid가 가르키는 패티를 말함)
			
				
				//이전 레벨에 대해 먹은 패티의 개수를 구하기 위해서 변수 세팅
				total = (total-3)/2;
				left = mid+1;
				right = right-1;
				mid = (left+right)/2;
				
			}else { //왼쪽
				//N보다 mid가 더 크면 오른쪽 부분의 패티는 먹지 않았다는 것이 확실하기 때문에 더해주지 않는다.
				if(left==X) {
					break;
				}
				total = (total-3)/2;
				left = left+1;
				right = mid-1;
				mid = (left+right)/2;
			}
			p = (p-1)/2; //전 레벨의 패티의 개수 중간 날려주고 절반 날려주고
		}
		System.out.println(result);
	}
	
}