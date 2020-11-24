package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private final double LOAD_FACTOR = 0.75;

    private static class Node<Key, Value> {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int capacity = 16;
    private int size = 0;
    private Node<Key, Value>[] table = new Node[capacity];

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int index = hash(key);
        Node<Key, Value> current = table[index];

        while ((current != null) && (!current.key.equals(key))) {
            current = current.next;
        }

        return (current == null) ? null : current.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int index = hash(key);
        Node<Key, Value> current = table[index];

        if (current == null) {
            table[index] = new Node(key, value, null);
            ++size;
        } else {
            while((current.next != null) && (!current.next.key.equals(key))) {
                current = current.next;
            }

            if (current.key.equals(key)) {
                current.value = value;
            } else {
                if (current.next == null) {
                    current.next = new Node(key, value,null);
                    ++size;
                } else {
                    current.next.value = value;
                }
            }
        }

        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int index = hash(key);
        Node<Key, Value> current = table[index];

        if (current == null) {
            return null;
        }

        if (current.key.equals(key)) {
            Value removingValue = current.value;
            table[index] = current.next;
            --size;

            return removingValue;
        }

        while((current.next != null) && (!current.next.key.equals(key))) {
            current = current.next;
        }

        if (current.next == null) {
            return null;
        }

        Value removingValue = (Value) current.next.value;
        current.next = current.next.next;
        --size;

        return removingValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize() {
        Node<Key, Value>[] newTable = new Node[capacity * 2];

        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                newTable[i] = table[i];
            }
        }

        table = newTable;
        capacity *= 2;
    }
}
