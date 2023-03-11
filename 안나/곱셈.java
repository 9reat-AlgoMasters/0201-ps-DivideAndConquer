import java.io.*;
import java.util.*;

public class Main {
	static int A;
	static int B;
	static int C;
	static long result;
	static int temp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// ( A * A ) % C = ( A % C ) * ( Ａ % Ｃ ） % Ｃ
		temp = A % C;
		result = find(B);
		System.out.println(result);
	}
	static long find(int b) {
		if(b==1) {
			return temp;
		}

		long answer = find(b/2); 
		if(b %2 ==0) {
			return answer * answer % C;
		}else {
			return (find(b/2+1) * answer) % C;
		}
		
		
	}

}