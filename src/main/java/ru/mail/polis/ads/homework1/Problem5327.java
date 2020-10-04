package ru.mail.polis.ads.homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.EmptyStackException;
import java.util.Stack;

public final class Problem5327 {

    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException{
        String bracketsLine = in.readLine();
        Stack<Character> stack = new Stack<>();

        try {
            for (char character : bracketsLine.toCharArray()) {
                if (character == '(') {
                    stack.push(character);
                }

                if (character == ')') {
                    if (stack.peek() != '(') {
                        out.write("NO");
                        return;
                    }

                    stack.pop();
                }
            }

            if (stack.size() == 0) {
                out.write("YES");
            } else {
                out.write("NO");
            }
        } catch (EmptyStackException e) {
            out.write("NO");
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
