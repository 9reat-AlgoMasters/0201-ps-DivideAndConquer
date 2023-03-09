import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int N;
    static int x;
    static int y;
    static int[][] map;
    static int[][] map2;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        N = K << 1;
        map = new int[N + 1][N + 1];
        map2 = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == x && j == y) {
                    map[i][j] = -1;
                }
            }
        }


        if (K == 1) {
            rotate();
            printAnswer(1);
        }

        int tmp = findQuadrant();

        if (K == 2) {
            int cnt = 2;
            for (int i = 1; i <= 4; i++) {
                if (i != tmp) {
                    if (i == 1) {
                        printadj(1, 1, cnt);
                        cnt++;
                    }
                    if (i == 2) {
                        printadj(1, N, cnt);
                        cnt++;
                    }
                    if (i == 3) {
                        printadj(N, 1, cnt);
                        cnt++;
                    }
                    if (i == 4) {
                        printadj(N, N, cnt);
                        cnt++;
                    }

                }

            }

            rotate();
            printAnswer(cnt);

        }


    }

    //4분면에서 -1위치 찾기
    static int findQuadrant() {
        int tmp = 0;
        if (x < 3 && y < 3) {
            tmp = 1;
            print(1,1);
        }
        if (x < 3 && y >= 3) {
            tmp = 2;
            print(1,3);
        }
        if (x >= 3 && y < 3) {
            tmp = 3;
            print(3,1);
        }
        if (x >= 3 && y >= 3) {
            tmp = 4;
            print(3,3);
        }
        return tmp;
    }

    static void print(int x, int y) {

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (map[x + i][y + j] == 0) {
                    map[x + i][y + j] = 1;
                }
            }
        }
    }

    static void printadj(int x, int y, int cnt) {
        map[x][y] = cnt;
        for (int j = 0; j < 4; j++) {
            int a = x + dx[j];
            int b = y + dy[j];
            if (check(a, b)) {
                map[a][b] = cnt;
            }
        }
    }

    static void printAnswer(int cnt) {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (map2[i][j] == 0) {
                    System.out.print(cnt + " ");
                } else {
                    System.out.print(map2[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static void rotate() {
        // 하수구 방향으로 바꾸기 위해 반시계 방향으로 90도 회전함
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                map2[i][j] = map[j][N + 1 - i];
            }
        }
    }


    static boolean check(int a, int b) {
        if (a <= 0 || a > N || b <= 0 || b > N) {
            return false;
        }
        return true;
    }
}
