import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int d;
    static long xPos;
    static long yPos;
    static long size;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        String nums = st.nextToken();
        sb = new StringBuilder();

        size = 1L << d;

        findPosition(nums, size); // 입력된  값의 행,열위치 찾기

        st = new StringTokenizer(br.readLine());
        long r = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        xPos -= c;
        yPos += r;


        if (!check(xPos, yPos)) {
            System.out.println(-1);
        } else {
            findMoveQuadrant(xPos, yPos, size);
        }

        System.out.println(sb.toString());
    }
    
    // 4분면 위치를 찾아간다.
    static void findMoveQuadrant(long x, long y, long size) {
        if (size == 1) {
            return;
        }

        long temp = size/2;

        if (x < temp && y < temp) {
            sb.append(2);
            findMoveQuadrant(x, y, temp);
        }
        if (x < temp && y >= temp) {
            sb.append(1);
            y -= temp;
            findMoveQuadrant(x, y, temp);
        }
        if (x >= temp && y < temp) {
            sb.append(3);
            x -= temp;
            findMoveQuadrant(x, y, temp);
        }
        if (x >= temp && y >= temp) {
            sb.append(4);
            x -= temp;
            y -= temp;
            findMoveQuadrant(x, y, temp);
        }

    }
    
    // 계속 나누어진 4분면에서의 행과 열의 값을 찾기
    static void findPosition(String nums, long size) {
        for (int i = 0; i < d; i++) {
            size /= 2;
            int position = nums.charAt(i) - '0';
            if (position == 1) {
                yPos += size;
            }

            if (position == 3) {
                xPos += size;
            }

            if (position == 4) {
                xPos += size;
                yPos += size;
            }

        }
    }

    static boolean check(long x, long y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return false;
        }
        return true;
    }

}
