import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q14600 {
    static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 하 우 상 좌
    // 각 사분면의 하수구를 뺀 ㄱ 자 모양 순서에서 인덱스 2, 3 번의 위치를 바꿈
    static final int[][] pieces = {{2, 3}, {1, 2}, {0, 1}, {3, 0}}; // (상,좌), (상, 우), (하, 우), (하, 좌)
    static final int[][] sameShapeFinder = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    static final int[][] tileDir = {{1, 1}, {1, 0}, {0, 0}, {0, 1}};

    static final int OUT = -1;

    static int N, size;
    static int[][] map;
    static int outX, outY;
    static int pieceNum = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        size = (int) Math.pow(2, N);
        map = new int[size][size];
        st = new StringTokenizer(br.readLine());
        int tempX = Integer.parseInt(st.nextToken());
        int tempY = Integer.parseInt(st.nextToken());
        outX = size - tempY;
        outY = tempX -1;
        map[outX][outY] = -1;

        color(size, 0, 0);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean color(int size, int x, int y) {
        if (size == 1) {
            return x == outX && y == outY;
        }

        boolean hasOut = false;
        int unit = size/2;
        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
                if (color(unit, x + unit * i, y + unit * j)) {
                    hasOut = true;

                    int outQuadrant = i * 2 + j;

                    if (outQuadrant == 2) {
                        outQuadrant = 3;
                    } else if (outQuadrant == 3) {
                        outQuadrant = 2;
                    }
                    colorPiece(x + (size - 1) * tileDir[outQuadrant][0],
                            y + (size - 1) * tileDir[outQuadrant][1],
                            outQuadrant,
                            unit);
                }
            }
        }
        return hasOut;
    }

    private static void colorPiece(int x, int y, int piece, int size) {
        if (size == 1) {
            map[x][y] = pieceNum;

            for (int i=0; i<2; i++) {
                int[] d = DIR[pieces[piece][i]];
                map[x + d[0]][y + d[1]] = pieceNum;
            }
            pieceNum++;
            return;
        }

        colorPiece(x, y, piece, size / 2);
        colorPiece(x + sameShapeFinder[piece][0], y + sameShapeFinder[piece][1], piece, size /2);
        for (int i=0; i<2; i++) {
            int[] d = DIR[pieces[piece][i]];
            int pieceChanger = i == 0 ? -1 : 1;
            int nextPiece = piece + pieceChanger;
            if (nextPiece == -1) {
                nextPiece = 3;
            } else if (nextPiece == 4) {
                nextPiece = 0;
            }
            colorPiece(x + (size * 2 - 1) * d[0], y + (size * 2 - 1) * d[1], nextPiece, size / 2);
        }
    }
}
