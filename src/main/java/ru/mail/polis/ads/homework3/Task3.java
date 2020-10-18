package ru.mail.polis.ads.homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Queue;
import  java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(Integer.MAX_VALUE);
        maxHeap.offer(Integer.MIN_VALUE);
        int median = -1;
        int number = -1;

        while (true) {
            number = in.nextInt();

            if (median == -1 && maxHeap.size() != 0 && minHeap.size() != 0) {
                if (number < maxHeap.peek()) {
                    median = maxHeap.poll();
                    maxHeap.offer(number);
                } else if (number > minHeap.peek()) {
                    median = minHeap.poll();
                    minHeap.offer(number);
                } else {
                    median = number;
                }
            } else {
                if (number < median) {
                    minHeap.offer(median);
                    maxHeap.offer(number);
                } else {
                    minHeap.offer(number);
                    maxHeap.offer(median);
                }

                median = -1;
            }

            if (maxHeap.size() != 0 && minHeap.size() != 0) {
                out.println(median == -1 ? (maxHeap.peek() + (minHeap.peek() - maxHeap.peek()) / 2) : median);
            }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
