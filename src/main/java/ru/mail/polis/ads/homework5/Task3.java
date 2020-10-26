package ru.mail.polis.ads.homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] array = new long[n];

        for (int i = 0; i < n; i++) {
            array[i] = Long.parseLong(in.next());
        }

        System.out.println(getMaxLength(array));
    }

    private static int getMaxLength(long[] array) {
        int[] temp = new int[array.length];
        temp[0] = 1;

        for (int i = 1; i < array.length; i++) {
            int count = 0;

            for (int j = i - 1; j >= 0 ; j--) {
                if (array[j] == 0) {
                    continue;
                }

                if (array[i] % array[j] == 0 && temp[j] > count) {
                    count = temp[j];
                }
            }

            temp[i] = count + 1;
        }

        return Arrays.stream(temp).max().getAsInt();
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
