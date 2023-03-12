import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int K, x, y, cnt = 2;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		x = Integer.parseInt(input[0]);
		y = Integer.parseInt(input[1]);
    //샤워실 길이 N : 2^K
		int N = 1<<K;
		map = new int[N][N];
    //배수구의 위치 -1로 마킹
		map[y-1][x-1] = -1;
		
		divide(N, y-1, x-1);
		
    //문제의 좌표대로 출력하기위해 맨 끝 행부터 출력
		for (int i = N-1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]+" ");
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
	}
	
  //정사각형 길이 n과 특정 r,c좌표를 인자로 받는 메서드
	static void divide(int n, int r, int c) {
    //길이가 2(K가 1)인 정사각형에서 r,c 좌표 제외한 모든 칸들을 cnt값으로 채워줌(cnt는 타일번호를 구별짓기 위한 값) 
		if(n == 2) {
      //r,c가 위치하는 사분면의 왼쪽 위의 칸 좌표를 구해주기
			int baseR = r/2, baseC = c/2;
			if(baseR != 0) {
				baseR *= 2;
			}
			if(baseC != 0) {
				baseC *= 2;
			}
			//길이 2짜리 정사각형 탐색 후 값 채우기
			for (int i = baseR; i < baseR + 2; i++) {
				for (int j = baseC; j < baseC + 2; j++) {
					if(i == r && j == c) continue;
					map[i][j] = cnt;
				}
			}
      //cnt번 타일 채웠으니 번호++
			cnt++;
			return;
		}
    
    //길이가 4(K가 2)인 경우
    //1. r,c(배수구) 좌표를 인자로 넣고 재귀 => 배수구 포함한 정사각형에 값이 채워짐
		divide(2, r, c);
    //2. 내부 정사각형 4칸 중 한칸은 값이 채워졌음(4사분면 중 1개의 사분면은 값 채우기 완료)
    //값이 채워지지 않은 칸에 대해 타일번호 부여하면서 재귀(나머지 사분면 값 채우기)
		for (int i = 1; i < 3; i++) {
			for (int j = 1; j < 3; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					divide(2, i, j);
				}
			}
		}
		return;
	}

}
