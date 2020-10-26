package ru.mail.polis.ads.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();

        int[] array = new int[n + 2];
        array[0] = 0;
        array[n + 1] = 0;

        for (int i = 1; i <= n; i++) {
            array[i] = in.nextInt();
        }

        int step = in.nextInt();

        int[] costs = new int[n + 2];
        costs[0] = array[0];

        for (int i = 1; i < n + 2; i++) {
            int max = Integer.MIN_VALUE;

            for (int j = Math.max(i - step, 0); j < i; j++) {
                if (costs[j] > max) {
                    max = costs[j];
                }
            }

            costs[i] = array[i] + max;
        }

        System.out.println(costs[n + 1]);
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
