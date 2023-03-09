import java.io.*;
import java.util.StringTokenizer;

public class Q16974 {
    static int N;
    static long X;
    static long[] total;
    static long[] patties;
    static long count = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());
        
        makeDp();
        dfs(N, X);
    
        sb.append(count);
    
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void dfs(int n, long x) {
        if (x == 1) return;
        
        if (n == 1) {
            count += x-1;
            return;
        }
        
        // checkPoint 1
        if (x < total[n - 1]) {
            dfs(n - 1, x - 1);
            return;
        }
        
        count += patties[n-1];
        // checkPoint 2
        if (x >= total[n - 1] + 2) {
            count += 1;
        }
        
        if (x <= total[n - 1] + 2) {
            return;
        }
        
        // checkPoint 3
        if (x < 2 * total[n - 1] + 1) {
            dfs(n - 1, x - (total[n - 1] + 2));
        } else {
            count += patties[n-1];
        }
    }
    
    private static void makeDp() {
        total = new long[N+1];
        patties = new long[N + 1];
        total[0] = 1;
        patties[0] = 1;
        for (int i = 1; i <= N; i++) {
            total[i] = 2*total[i-1] + 3;
            patties[i] = 2*patties[i-1] + 1;
        }
    }
}
