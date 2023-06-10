package org.andreypodymov.yandex.sprint2;

public class BadTask {
    static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static Node<String> solution(Node<String> head, int idx) {
        if (idx == 0) {
            head = head.next;
            return head;
        }
        int current = 0;
        Node<String> startOfHead = head;
        while (current != idx - 1) {
            head = head.next;
            current++;
        }
        head.next = head.next.next;
        return startOfHead;
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        Node<String> newHead = solution(node0, 1);
        assert newHead == node0;
        assert newHead.next == node2;
        assert newHead.next.next == node3;
        assert newHead.next.next.next == null;
        // result is : node0 -> node2 -> node3
    }
}