package ru.mail.polis.ads.homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String template = in.next();
        String word = in.next();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '?' || word.charAt(i) == '*') {
                String temp = template;
                template = word;
                word = temp;
            }
        }
        
        System.out.println(check(template, word) ? "YES" : "NO");
    }

    private static boolean check(String template, String word) {
        int m = template.length();
        int n = word.length();

        boolean[][] checkMatrix = new boolean[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            checkMatrix[i][0] = true;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (template.charAt(i - 1) == word.charAt(j - 1) || template.charAt(i - 1) == '?') {
                    checkMatrix[i][j] = checkMatrix[i - 1][j - 1];
                } else if (template.charAt(i - 1) == '*') {
                    checkMatrix[i][j] = checkMatrix[i - 1][j] || checkMatrix[i][j - 1] || checkMatrix[i - 1][j - 1];
                } else {
                    checkMatrix[i][j] = false;
                }
            }
        }

        return checkMatrix[m - 1][n - 1];
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
