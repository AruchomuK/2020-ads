package ru.mail.polis.ads.homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int coordinatesQuantity = in.nextInt();
        int cowsQuantity = in.nextInt();
        int[] coordinates = new int[coordinatesQuantity];

        for (int i = 0; i < coordinatesQuantity; i++) {
            coordinates[i] = in.nextInt();
        }

        int left = 0;
        int right = coordinates[coordinatesQuantity - 1] - coordinates[0] + 1;

        while (right - left > 1) {
            int mid = (right + left) / 2;

            if (isPossible(coordinates, mid, cowsQuantity)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        System.out.println(left);
    }

    private static boolean isPossible(int[] coordinates, int distance, int cowsQuantity) {
        int count = 1;
        int lastCow = coordinates[0];

        for (int coord : coordinates) {
            if (coord - lastCow >= distance) {
                count++;
                lastCow = coord;
            }
        }

        return count >= cowsQuantity;
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
