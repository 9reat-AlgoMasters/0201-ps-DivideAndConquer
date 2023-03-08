import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        System.out.println(divide(0,0,N));
    }

    static int divide(int r, int c, int size){
        int[] temp = new int[4];
        //size(한 변 길이)가 1인 정사각형 행렬은 자기 자신(r, c 위치의 배열 값)을 반환
        if(size == 1){
            return map[r][c];
        }
      //size 2 이상부터는 사분면으로 나눠서 반환값을 받아오기
        temp[0] = divide(r, c, size/2);
        temp[1] = divide(r+size/2, c, size/2);
        temp[2] = divide(r, c+size/2, size/2);
        temp[3] = divide(r+size/2, c+size/2, size/2);
      //4개의 수 중 2번째로 큰 값 반환
        Arrays.sort(temp);
        return temp[2];
    }
}
