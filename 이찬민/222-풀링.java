import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(recur(0, 0, N));

    }

    static int recur(int x, int y, int n) {
        if (n == 2) {
            return getSecond(x, y);
        }

        List<Integer> t = new ArrayList<>();
        int temp = n >> 1;
        t.add(recur(x , y, temp));
        t.add(recur(x, y + temp, temp));
        t.add(recur(x + temp, y, temp));
        t.add(recur(x + temp, y + temp, temp));

        t.sort(Comparator.reverseOrder());
        return t.get(1);
    }

    static int getSecond(int x, int y) {
        List<Integer> t = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                t.add(map[x + i][y + j]);
            }
        }
        t.sort(Comparator.reverseOrder());
        return t.get(1);
    }
}
