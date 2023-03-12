import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a ,b, c;
        String[] input = br.readLine().split(" ");
        a = Integer.parseInt(input[0]);
        b = Integer.parseInt(input[1]);
        c = Integer.parseInt(input[2]);

        System.out.println(solve(a,b,c));
    }
    static long solve(int a, int b, int c){
        if(b==1){
            return a % c;
        }
        long val = solve(a,b/2,c);
        if(b % 2 == 1){
            return (val * val % c * a ) % c;
        }else{
            return (val * val) % c;
        }
    }
}
