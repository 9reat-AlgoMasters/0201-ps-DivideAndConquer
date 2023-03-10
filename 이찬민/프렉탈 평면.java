import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int s;
    static int n;
    static int k;
    static int r1, c1, r2, c2;
    static int[][] map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        map = new int[50][50];

        divideAndConquer(0, 0, (int) Math.pow(n, s), 0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void divideAndConquer(int x, int y, int size, int num, int time) {
        if (x > r2 || x + size <= r1 || y > c2 || y + size <= c1) {
            return;
        }

        if (time == s) {
            map[x - r1][y - c1] = num;
            return;
        }

        int nSize = size / n;
        int bStart = (n - k) / 2;
        int bEnd = bStart + k - 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int nextNum = num;
                if (num == 0 && checkBlack(bStart, bEnd, i, j)) {
                    nextNum = 1;
                }
                divideAndConquer(x + nSize * i, y + nSize * j, nSize, nextNum, time + 1);
            }
        }
    }

    static boolean checkBlack(int bStart, int bEnd, int x, int y) {
        if (x < bStart || x > bEnd || y < bStart || y > bEnd) {
            return false;
        }
        return true;
    }
}
