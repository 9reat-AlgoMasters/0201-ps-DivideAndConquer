import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q1030 {
    static final int BLACK = 1;
    static final int WHITE = 0;
    
    static int s, N, K;
    static long r1, r2, c1, c2;
    static Set<Integer> blackAreas = new HashSet<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        r1 = Long.parseLong(st.nextToken());
        r2 = Long.parseLong(st.nextToken());
        c1 = Long.parseLong(st.nextToken());
        c2 = Long.parseLong(st.nextToken());
    
        initBlackAreas();
        
        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                sb.append(dfs(s, r1 + i, c1 + j));
            }
            sb.append("\n");
        }
    
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void initBlackAreas() {
        int start = (N-K) / 2;
    
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int x = start + i;
                int y = start + j;
                blackAreas.add(x * N + y);
            }
        }
    }
    
    private static int dfs(int s, long r, long c) {
        if (s == 0) return WHITE;
        
        if (s == 1) {
            if (blackAreas.contains((int)(r * N + c))) {
                return BLACK;
            }
            return WHITE;
        }
    
        long unit = (long) Math.pow(N, s - 1);
        int x = (int)(r / unit);
        int y = (int)(c / unit);
        if (blackAreas.contains(x * N + y)) {
            return BLACK;
        }
        return dfs(s - 1, r - unit * x, c - unit * y);
    }
}
