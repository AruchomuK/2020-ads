package ru.mail.polis.ads.homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task1 {
    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        double c = Double.parseDouble(in.readLine());

        System.out.println(binarySearch(c));
    }

    private static double binarySearch(double c) {

        double l = 0;
        double r = c;
        double result = 0;

        while (Math.abs(c - result) > 0.0000001) {
            double mid = (l + r) / 2;

            result = Math.pow(mid, 2) + Math.sqrt(mid);

            if (result <= c) {
                l = mid;
            } else {
                r = mid;
            }
        }

        return l;
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
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
