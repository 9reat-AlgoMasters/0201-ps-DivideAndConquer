import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int K = 28; // 10억까지
    static int N;
    static int[] dp;
    static int pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[K];
        dp[0] = 3;

        for (int i = 1; i < K; i++) {
            dp[i] = dp[i-1]*2 + i + 3;
            if (dp[i] >= N) {
                pos = i;
                break;
            }
        }

        moo(pos, N);

    }

    static void moo(int pos, int size) {
        if (pos ==0 ||size <= 3) {
            if (size == 1) {
                System.out.println('m');
            } else {
                System.out.println('o');
            }
            return;
        }


        if (dp[pos - 1] == size) {
            size -= dp[pos - 1];
            moo(pos - 1, size);
        } else if (dp[pos - 1] < size) {
            if (dp[pos - 1] + pos + 3 < size) {
                size -= dp[pos - 1] + pos + 3;

            } else {
                size -= dp[pos - 1];

            }

            moo(pos - 1, size);
        } else if (dp[pos - 1] > size) {
            moo(pos - 1, size);
        }
    }
}
