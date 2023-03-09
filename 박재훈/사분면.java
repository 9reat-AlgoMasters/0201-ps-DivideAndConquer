import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int d;
	static String num;
	static long x, y;
	static long sr, sc, er, ec;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		d = Integer.parseInt(input[0]);
		num = input[1];
		input = br.readLine().split(" ");
		x = Long.parseLong(input[0]);
		y = Long.parseLong(input[1]);
		
    //입력받은 조각을 2^d x 2^d 배열의 좌표로 나타내는 메서드(sr, sc에 좌표 저장)
		calcPoint();
    
    //도착해야할 조각 좌표
		er = sr + x;
		ec = sc - y; 
		
    //범위 내에 존재하면 좌표를 통해 조각 번호를 구하는 메서드 실행, 범위 밖이면 -1 출력
		if(checkRange(1L<<d, er, ec)) {
			calcNum();
			System.out.println(sb);
		}else {
			System.out.println(-1);
		}
	}
	
	static void calcPoint() {
    //최초 2^d x 2^d 크기 배열
		long size = 1L<<d;
		for (int i = 0; i < d; i++) {
      //조각 번호를 한 자리씩 가져와서
			char ch = num.charAt(i);
			int q = ch - '0';
			switch (q) {
      //사분면 번호에 따라 좌표값 재조정
			case 1:
				sr += size/2;
				break;
			case 3:
				sc += size/2;
				break;
			case 4:
				sr += size/2;
				sc += size/2;
				break;
			}
      //더 작은 사분면으로 쪼개기 위해 size를 반으로
			size /= 2;
		}
	}
  
	static void calcNum() {
		long size = 1L<<d;
		for (int i = 0; i < d; i++) {
			long v = size/2;
      //구한 도착 좌표를 어느 사분면에 위치하는지 찾기, 해당 사분면의 원점으로 좌표 조정
			if(er < v && ec < v) {
				sb.append(2);
			}else if(er >= v && ec < v) {
				sb.append(1);
				er -= v;
			}else if(er < v && ec >= v) {
				sb.append(3);
				ec -= v;
			}else {
				sb.append(4);
				er -= v;
				ec -= v;
			}	
		  //다음 사분면 찾기 위해 size 반으로 
			size = v;			
		}
	}
	
	static boolean checkRange(long size, long r, long c) {
		return r>=0 && r < size && c>=0 && c <size;
	}
}
