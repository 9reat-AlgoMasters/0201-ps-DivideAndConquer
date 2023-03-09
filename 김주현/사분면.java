/*
 * 풀이 순서
 * 1. 해당 숫자를 방향에 맞춰 2진수로 바꾼다.
 * 2. 바꾼 2진수에 옮겨야 하는 숫자를 더한다.
 * 3. 결과 2진수를 다시 변환한다.
 * 3-1. 원래 이진수의 같은 자리와 비교하면 바뀌었으면 짝을 바꾼다.
 * */


/*
 * 가로 방향
 * 2,3 -> 0 // 1, 4 -> 1
 *
 * 짝
 * 1 - 2 // 3 - 4
 *
 * 세로 방향
 * 1, 2 -> 0 // 3, 4 -> 1
 *
 * 짝
 * 1 - 4 // 2 - 3
 * */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Q1891 {
    static final int IMPOSSIBLE = -1;
    static final int ROW = 0;
    static final int COL = 1;

    static int N;
    static int[] targetNum;
    static long max;
    static Set<Integer>[] oneBitNumbers = new HashSet[2];
    static Map<Integer, Integer>[] convertTable = new HashMap[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        targetNum = new int[N];
        char[] input = st.nextToken().toCharArray();
        for (int i = 0; i < N; i++) {
            targetNum[i] = input[i] - '0';
        }
        max = (long) Math.pow(2, N);

        st = new StringTokenizer(br.readLine());
        long dx = Long.parseLong(st.nextToken());
        long dy = (-1) * Long.parseLong(st.nextToken());

        initOneBitNumbers();
        initConvertTable();

        int[] movedTarget = move(targetNum, dx, ROW);
        if (movedTarget[0] != IMPOSSIBLE) {
            movedTarget = move(movedTarget, dy, COL);
        }

        sb.append(arrToNumber(movedTarget));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static String arrToNumber(int[] movedTarget) {
        if (movedTarget[0] == IMPOSSIBLE) {
            return "-1";
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            sb.append(movedTarget[i]);
        }
        return sb.toString();
    }


    private static void initOneBitNumbers() {
        oneBitNumbers[ROW] = new HashSet<>();
        oneBitNumbers[ROW].add(1);
        oneBitNumbers[ROW].add(4);

        oneBitNumbers[COL] = new HashSet<>();
        oneBitNumbers[COL].add(3);
        oneBitNumbers[COL].add(4);
    }

    private static void initConvertTable() {
        convertTable[ROW] = new HashMap<>();
        convertTable[ROW].put(1, 2);
        convertTable[ROW].put(2, 1);
        convertTable[ROW].put(3, 4);
        convertTable[ROW].put(4, 3);

        convertTable[COL] = new HashMap<>();
        convertTable[COL].put(1, 4);
        convertTable[COL].put(4, 1);
        convertTable[COL].put(2, 3);
        convertTable[COL].put(3, 2);
    }

    public static int[] move(int[] target, long move, int direction) {
        long binary = encode(target, direction);
        long movedBinary = binary + move;
        if (isNotInRange(movedBinary)) {
            return new int[] {IMPOSSIBLE};
        }
        return decode(target, binary, movedBinary, direction);
    }

    private static long encode(int[] target, int direction) {
        long binary = 0;
        long powNum = 1;
        for (int i = N-1; i>=0; i--) {
            if (isOneBit(target[i], direction)) {
                binary += powNum;
            }
            powNum <<= 1;
        }

        return binary;
    }

    private static int[] decode(int[] target, long binary, long movedBinary, int direction) {
        int[] movedTarget = new int[N];
        for (int i = N-1; i>=0; i--) {
            int quadrantNum = target[i];
            if (binary % 2 != movedBinary % 2) {
                quadrantNum = convertTable[direction].get(quadrantNum);
            }
            movedTarget[i] = quadrantNum;
            binary >>= 1;
            movedBinary >>= 1;
        }
        return movedTarget;
    }

    private static boolean isOneBit(int quadrantNum, int direction) {
        return oneBitNumbers[direction].contains(quadrantNum);
    }

    private static boolean isNotInRange(long binary) {
        return binary < 0 || binary >= max;
    }
}
