import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q5904 {
    static int N;
    static List<Integer> lengths = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int k = findMaxK(N);

        sb.append(dfs(N, k));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static char dfs(int n, int k) {
        if (k==0) {
            if (n == 1) {
                return 'm';
            }
            return 'o';
        }

        if (n <= lengths.get(k - 1)) {
            return dfs(n, k - 1);
        } else if (n - lengths.get(k - 1) <= k + 3) {
            if (n - lengths.get(k - 1) == 1) {
                return 'm';
            } else {
                return 'o';
            }
        } else {
            return dfs(n - lengths.get(k - 1) - (k + 3), k-1);
        }

    }

    private static int findMaxK(int max) {
        int length = 3;
        int k = 0;
        lengths.add(length);

        while (length < max) {
            k++;
            length += length + (k+3);
            lengths.add(length);
        }
        return k;
    }
}
