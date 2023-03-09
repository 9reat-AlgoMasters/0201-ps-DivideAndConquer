import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long X;
    static long count;

    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());
        count = 0;
        dp = new long[N+1][2];
        dp[0][0] = 1; // 패티
        dp[0][1] = 1; // 길이

        for (int i = 1; i <= N; i++) {
            dp[i][0] = dp[i-1][0] * 2 + 1;
            dp[i][1] = dp[i-1][1] * 2 + 3;
        }

        solve(N, X);

    }
        
    // 버거의 레벨이 양끝의 번 갯수
    // 0 P 1 1
    // 1 BPPPB 3 5
    // 2 BBPPPBPBPPPBB 7 13
    // 7개 먹으면 13/2 보다 크니까 레벨1 버거만큼은 다 먹고 중간에 하나도 먹음
    // 4개 먹으면?? 13/2 보다 작으니까 레벨1 버거를 본다.(여기서는 버거레벨이 하나 낮아지기 때문에 번 갯수도 하나 줄어듬)
    // 3이 5/2 보다크니까 먹을수 있음  1 + 1 해서 2개됨
    // 6개면? 13/2 임 -1해서 레벨1 버거 봄 갯수가 딱 맞음으로 3개가됨
    // 11개면 13 - 번 갯수가 11이니까 다 먹기 가능

    static void solve(int n, long x) {
        if (x == 0) { // 다먹음
            System.out.println(count);
            return;
        }
        if (n == 0) { // 마지막 L0버거라면
            count++;
            System.out.println(count);
            return;
        }
        
        x--; // 패티는 레벨이 낮아질때마다 번이 하나 씩 줄어듬
        
        if (dp[n - 1][1] < x) {
            count += dp[n-1][0] + 1;
            x -= dp[n-1][1] + 1;
            solve(n - 1, x);
        } else if (dp[n - 1][1] > x) {
            solve(n - 1, x);
        } else if(dp[n-1][1] == x) {
            count += dp[n-1][0];
            x -= dp[n - 1][1];
            solve(n - 1, x);
        }
    }
}
