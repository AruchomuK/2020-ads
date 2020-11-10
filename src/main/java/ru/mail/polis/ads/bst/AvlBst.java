    package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;


        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    // get
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }

        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }

        return x.value;
    }

    //put
    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            ++size;
            return new Node(key, value, 1);
        }

        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        fixHeight(x);
        x = balance(x);

        return x;
    }

    //remove
    @Override
    public Value remove(@NotNull Key key) {
        Value removeNodesValue = get(root, key);

        if (removeNodesValue == null) {
            return null;
        }

        root = remove(root, key);
        --size;

        return removeNodesValue;
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        }

        if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        }

        if (key.compareTo(x.key) == 0) {
            x = innerRemove(x);
        }

        return x;
    }

    private Node innerRemove(Node x) {
        if (x.right == null) {
            return x.left;
        }

        if (x.left == null) {
            return x.right;
        }

        Node t = x;

        x = min(t.right);
        x.right = removeMin(t.right);
        x.left = t.left;

        return x;
    }

    private Node removeMin(Node x) {
        if (x.left == null) {
            return x.right;
        }

        x.left = removeMin(x.left);

        return x;
    }

    //min
    @Override
    public Key min() {
        Node minNode = min(root);
        return minNode == null ? null : minNode.key;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        return x.left == null ? x : min(x.left);
    }

    @Override
    public Value minValue() {
        Node minNode = min(root);
        return minNode == null ? null : minNode.value;
    }

    //max
    @Override
    public Key max() {
        Node maxNode = max(root);
        return maxNode == null ? null : maxNode.key;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        return x.right == null ? x : max(x.right);
    }

    @Override
    public Value maxValue() {
        Node maxNode = max(root);
        return maxNode == null ? null : maxNode.value;
    }

    //auxiliary methods
    @Override
    public Key floor(@NotNull Key key) {
        Node floorNode = floor(root, key);

        return floorNode == null ? null : floorNode.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) == 0) {
            return x;
        }

        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        }

        Node floorNode = floor(x.right, key);

        return floorNode == null ? x : floorNode;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node ceilNode = ceil(root, key);

        return ceilNode == null ? null : ceilNode.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) == 0) {
            return x;
        }

        if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        }

        Node ceilNode = ceil(x.left, key);

        return ceilNode == null ? x : ceilNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    public void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;

        fixHeight(y);
        fixHeight(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;

        fixHeight(x);
        fixHeight(y);

        return y;
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }

            return rotateRight(x);
        }

        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }

            return rotateLeft(x);
        }

        return x;
    }
}
