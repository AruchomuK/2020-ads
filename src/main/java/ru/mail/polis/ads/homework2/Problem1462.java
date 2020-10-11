package ru.mail.polis.ads.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Problem1462 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        ArrayList<Integer> array = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            array.add(in.nextInt());
        }

        Comparator<Integer> comparator = (o1, o2) -> {
            if (o1 % 10 > o2 % 10) {
                return 1;
            } else if (o1 % 10 < o2 % 10) {
                return -1;
            } else {
                return o1 > o2 ? 1 : -1;
            }
        };

        array.sort(comparator);

        for (int i = 0; i < n; i++) {
            out.print(array.get(i) + " ");
        }
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
