import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int s, N, K, R1, R2, C1,C2,size;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		s = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		R1 = Integer.parseInt(input[3]);
		R2 = Integer.parseInt(input[4]);
		C1 = Integer.parseInt(input[5]);
		C2 = Integer.parseInt(input[6]);		
		
		size = (int) Math.pow(N,s);
		for (int i = R1; i <= R2; i++) {
			for (int j = C1; j <= C2; j++) {
        //범위 내 좌표 각각의 색 찾기
				solve(0, i, j);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void solve(int cnt, int r, int c) {
		int temp = size;
    //s번 사분면 쪼개서 가운데 정사각형에 들어가면 검은색 아니면 하얀색
		for (int i = 0; i < s; i++) {
			if(temp < N) {
				break;
			}
			int val = temp/N;
			if(r / val >= (N-K)/2 && r / val < (N+K)/2
				&& c / val >= (N-K)/2 && c / val < (N+K)/2) {
				sb.append(1);
				return;
			}else {
				r = r % val;
				c = c % val;
				temp = val;
			}
		}
		sb.append(0);
	}
}
