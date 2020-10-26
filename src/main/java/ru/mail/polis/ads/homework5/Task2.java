package ru.mail.polis.ads.homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        long w = in.nextInt();
        long h = in.nextInt();
        long n = in.nextInt();

        long l = (w > h) ? w : h;
        long r = (w > h) ? w : h;
        r *= n;

        while (l < r) {
            long mid = (r + l) / 2;
            long count = (mid / w) * (mid / h);

            if (n <= count) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        System.out.println(l);
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
