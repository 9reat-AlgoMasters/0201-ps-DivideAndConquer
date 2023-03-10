import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A;
    static int B;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(recur(B));


    }

    static long recur(int n) {
        if(n == 1){
            return A % C;
        }

        long t = recur(n / 2);

        if (n % 2 == 0) {
            return t * t % C;
        }
        if (n % 2 == 1) {
            return t * t % C * A % C;
        }
        return -1;
    }
}
