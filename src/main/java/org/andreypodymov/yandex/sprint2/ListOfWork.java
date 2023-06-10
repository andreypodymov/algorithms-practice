package org.andreypodymov.yandex.sprint2;

public class ListOfWork {
    static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void solution(Node<String> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }
}