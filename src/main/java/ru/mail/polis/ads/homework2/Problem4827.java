package ru.mail.polis.ads.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.math.BigInteger;
import java.util.ArrayList;

public class Problem4827 {
    private static void solve(final Scanner in, final PrintWriter out) {
        int k = Integer.parseInt(in.nextLine());
        String[] heights = in.nextLine().split(" ");
        ArrayList<BigInteger> finalArray = new ArrayList<>(heights.length);

        for (String height : heights) {
            finalArray.add(new BigInteger(height));
        }

        String answer = String.valueOf(findOrderStatistic(finalArray, finalArray.size() - k));
        System.out.println(answer);
    }

    private static BigInteger findOrderStatistic(ArrayList<BigInteger> array, int k) {
        int left = 0;
        int right = array.size() - 1;

        while (true) {
            int mid = partition(array, left, right);

            if (mid == k) {
                return array.get(mid);
            } else if (k < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    private static int partition(ArrayList<BigInteger> array, int left, int right) {
        BigInteger mid = array.get((left + right) / 2);
        int l = left;
        int r = right;

        while (l <= r) {
            while (array.get(l).compareTo(mid) < 0) {
                l++;
            }

            while (array.get(r).compareTo(mid) > 0) {
                r--;
            }

            if (l >= r) {
                break;
            }

            BigInteger tmp = array.get(l);
            array.set(l, array.get(r));
            array.set(r, tmp);
        }

        return r;
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
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
