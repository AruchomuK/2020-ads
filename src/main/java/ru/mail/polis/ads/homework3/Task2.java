package ru.mail.polis.ads.homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap();

        for (int i = 0; i < n; i++) {
            int command = in.nextInt();

            switch(command) {
                case 0: {
                    int insertElement = in.nextInt();
                    heap.insert(insertElement);
                    break;
                }

                case 1: {
                    int extractElement = heap.exctract();
                    System.out.println(extractElement);
                    break;
                }
            }
        }

    }

    private static class Heap {
        private List<Integer> heap = new ArrayList<>();
        private int size = 0;

        public Heap() {
            heap.add(0);
        }

        public void insert(int element) {
            size++;
            if (size <= heap.size() - 1) {
                heap.set(size, element);
            } else {
                heap.add(element);
            }

            siftUp(size);
        }

        public int exctract() {
            int returnElement = heap.get(1);
            swap(heap,1, size);
            size--;
            siftDown(1);
            return returnElement;
        }

        public void siftUp(int k) {
            while (k > 1 && heap.get(k) > heap.get(k / 2)) {
                swap(heap, k, k / 2);
                k = k / 2;
            }
        }

        public void siftDown(int k) {
            while (2 * k <= size) {
                int j = 2 * k;

                if (j < size && heap.get(j) < heap.get(j + 1)) {
                    j++;
                }

                if (heap.get(k) >= heap.get(j)) {
                    break;
                }

                swap(heap, k, j);
                k = j;
            }
        }

        public void swap(List<Integer> heap, int index1, int index2) {
            int tmp = heap.get(index1);
            heap.set(index1, heap.get(index2));
            heap.set(index2, tmp);
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
