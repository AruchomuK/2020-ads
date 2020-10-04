package ru.mail.polis.ads.homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem6124 {

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack stack = new Stack();
        String[] split = new String[2];
        String command = null;

        do {
            command = reader.readLine();
            split = command.split(" ");

            switch (split[0]) {
                case "push": {
                    stack.push(Integer.parseInt(split[1]));
                    System.out.println("ok");
                    break;
                }

                case "pop": {
                    if (stack.size() != 0) {
                        System.out.println(stack.pop());
                    } else {
                        System.out.println("error");
                    }

                    break;
                }

                case "back": {
                    if (stack.size() != 0) {
                        System.out.println(stack.back());
                    } else {
                        System.out.println("error");
                    }

                    break;
                }

                case "size": {
                    System.out.println(stack.size());
                    break;
                }

                case "clear": {
                    stack.clear();
                    System.out.println("ok");
                    break;
                }
            }
        } while (!command.equals("exit"));

        System.out.println("bye");
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

    //STACK REALIZATION

    public static class Stack {
        ArrayList<Integer> stackList = new ArrayList<>();

        public void push(int element) {
            stackList.add(element);
        }

        public int pop() {
            int popElement = stackList.get(stackList.size() - 1);
            stackList.remove(stackList.size() - 1);
            return popElement;
        }

        public int back() {
            return stackList.get(stackList.size() - 1);
        }

        public int size() {
            return stackList.size();
        }

        public void clear()  {
            stackList = new ArrayList<>();
        }
    }

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}