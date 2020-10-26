package ru.mail.polis.ads.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] array = new int[m][n];
        StringBuilder answer = new StringBuilder("");

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                array[i][j] = in.nextInt();
            }
        }

        for (int i = 1; i < m; i++) {
            array[i][0] = array[i][0] + array[i - 1][0];
        }

        for (int j = 1; j < n; j++) {
            array[0][j] = array[0][j] + array[0][j - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                array[i][j] = array[i][j] + Math.max(array[i - 1][j], array[i][j - 1]);
            }
        }

        int k = m - 1;
        int t = n - 1;

        while (k > 0 || t > 0) {
            if (k > 0 && t > 0) {
                if (array[k - 1][t] > array[k][t - 1]) {
                    answer.append("F");
                    k--;
                } else {
                    answer.append("R");
                    t--;
                }
            } else if (k == 0) {
                answer.append("R");
                t--;
            } else if (t == 0) {
                answer.append("F");
                k--;
            }
        }

        System.out.println(answer.reverse());
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
