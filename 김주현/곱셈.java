import java.io.*;
import java.util.StringTokenizer;

public class Q1629 {
    static int A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        sb.append(exp(A, B));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static long exp(long a, int exp) {
        if (exp == 1) {
            return a % C;
        }

        if (exp % 2 == 0) {
                return exp((a*a)%C,exp / 2);
        } else {
            return (a * exp(a, exp-1)) % C;
        }
    }
}