import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q17829 {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sb.append(dfs(N, 0, 0));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int dfs(int size, int r, int c) {
        if (size == 1) {
            return map[r][c];
        }

        int[] arr = new int[4];
        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
                int unit = size/2;
                arr[2 * i + j] = dfs(unit, r + unit * i, c + unit * j);
            }
        }
        Arrays.sort(arr);
        return arr[2];
    }
}
