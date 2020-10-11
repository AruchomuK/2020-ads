package ru.mail.polis.ads.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem4037 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        Robot[] robots = new Robot[count];

        for (int i = 0; i < count; i++) {
            robots[i] = new Robot(in.nextInt(), in.nextInt());
        }

        mergeSort(robots, robots.length);

        for (int i = 0; i < robots.length; i++)
            System.out.println(robots[i].getMain() + " " + robots[i].getHelp());
    }

    private static class Robot {
        private int main;
        private int help;

        public Robot(int main, int help) {
            this.main = main;
            this.help = help;
        }

        public int getMain() {
            return main;
        }

        public int getHelp() {
            return help;
        }
    }

    private static void mergeSort(Robot[] array, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        Robot[] leftArray = new Robot[mid];
        Robot[] rightArray = new Robot[n - mid];

        for (int i = 0; i < mid; i++) {
            leftArray[i] = array[i];
        }

        for (int j = mid; j < n; j++) {
            rightArray[j - mid] = array[j];
        }

        mergeSort(leftArray, mid);
        mergeSort(rightArray, n - mid);
        merge(array, leftArray, rightArray, mid, n - mid);
    }

    private static void merge(Robot[] array, Robot[] leftArray, Robot[] rightArray, int left, int right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int mainArrayIndex = 0;

        while (leftIndex < left && rightIndex < right) {
            if (leftArray[leftIndex].getMain() <= rightArray[rightIndex].getMain()) {
                array[mainArrayIndex++] = leftArray[leftIndex++];
            } else {
                array[mainArrayIndex++] = rightArray[rightIndex++];
            }
        }

        while (leftIndex < left) {
            array[mainArrayIndex++] = leftArray[leftIndex++];
        }

        while (rightIndex < right) {
            array[mainArrayIndex++] = rightArray[rightIndex++];

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
