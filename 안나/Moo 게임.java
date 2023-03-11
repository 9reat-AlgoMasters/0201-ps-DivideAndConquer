import java.io.*;


public class Main {
	static int N;
	static long totlen;
	static int level;
	static int count; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		if(N==1) {
			System.out.println("m");
			return;
		}
		totlen=3;
		level = 0;
		while (true) {
			totlen = totlen*2 + (level+4);
			if (totlen > N-1) {
				level+=1;
				break;
			}
			level+=1;
		}
		recusion(level);
		if(count+1 == N) {
			System.out.println("m");
		}else {
			System.out.println("o");
		}
		
	}
	static void recusion(int cnt) {
		if(cnt == 0) {
			count+=3;
//			System.out.printf("%d 단계 에서 3 추가 ---> total : %d \n", cnt, count);
			if(count>N) {
				count-=3;
			}
			return;
		}
		recusion(cnt-1);
		int temp = count;
		count += cnt+3;
//		System.out.printf("%d 단계 에서 중간 %d 추가 ---> total : %d \n",cnt, cnt+3, count);
		if(count>N) {
			
			count = temp;
//			System.out.printf("%d 단계 중간에 목표가 있음!! ---> total : %d \n", cnt, count);
			return;
		}
//		System.out.printf("%d 단계에서 뒤쪽 재귀 실행 ---> total : %d \n",cnt, count);
		recusion(cnt-1);
	}
	
}