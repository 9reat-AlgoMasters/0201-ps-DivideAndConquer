import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int [][]map;
	static int copy[][];
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int n = N;
		while (n !=1 ) {
			copy = new int [n/2][n/2];
			pooling(0, 0, n);
			map = new int[n/2][n/2];
			for (int i = 0; i < copy.length; i++) {
				map[i] = Arrays.copyOf(copy[i], copy[i].length);
			}
//			print(map);
			n = n/2;
		}
		System.out.println(map[0][0]);
		
		
		
		
	}

	static void pooling(int r, int c ,int size ) {
		if(size == 2) {
			int max1 = (-1) *Integer.MAX_VALUE;
			int max2 = (-1) *Integer.MAX_VALUE;;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if(max1 <= map[r+i][c+j]) {
						max2 = max1;
						max1= map[r+i][c+j];
					}else if(max1 > map[r+i][c+j]) {
						if(max2 < map[r+i][c+j]) {
							max2 = map[r+i][c+j];
						}
					}
				}
			}
			copy[r/2][c/2] = max2;
			return;
			
		}
		
		for (int i = 0; i < size; i+=size/2) {
			for (int j = 0; j < size; j+=size/2) {
//				System.out.println(i+" "+ j+" "+ size/2);
				pooling(r+i, c+j, size/2);
			}
		}
		
	}

	static void print(int[][] copymap) {
		for (int i = 0; i < copymap.length; i++) {
			for (int j = 0; j < copymap.length; j++) {
				System.out.print(copymap[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("============");
	}

}