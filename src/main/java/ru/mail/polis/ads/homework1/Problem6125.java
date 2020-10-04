package ru.mail.polis.ads.homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem6125 {

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Queue queue = new Queue();
        String[] split = new String[2];
        String command = null;

        do {
            command = reader.readLine();
            split = command.split(" ");

            switch (split[0]) {
                case "push": {
                    queue.push(Integer.parseInt(split[1]));
                    System.out.println("ok");
                    break;
                }

                case "pop": {
                    System.out.println(queue.pop());
                    break;
                }

                case "front": {
                    System.out.println(queue.front());
                    break;
                }

                case "size": {
                    System.out.println(queue.size());
                    break;
                }

                case "clear": {
                    queue.clear();
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

    //QUEUE REALIZATION

    public static class Queue {
        LinkedList<Integer> queueList = new LinkedList<>();

        public void push(int element) {
            queueList.add(element);
        }

        public int pop() {
            int popElement = queueList.getFirst();
            queueList.removeFirst();
            return popElement;
        }

        public int front() {
            return queueList.getFirst();
        }

        public int size() {
            return queueList.size();
        }

        public void clear()  {
            queueList = new LinkedList<>();
        }
    }

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}