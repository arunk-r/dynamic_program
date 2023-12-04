package com.dynamic.program.data.structures.xor;



public class DoublyLinkedList {
    // Node structure of a memory efficient doubly linked list
    class Node {
        public int data;
        public Node npx; // XOR of next and previous node
    }

    /* returns XORed value of the node addresses */
    public Node XOR (Node a, Node b) {
        return null;//(Node) (a ^ b); // xor on pointers in java?
    }

    /* Insert a node at the beginning of the
       XORed linked list and makes the newly
       inserted node as head */
    public void insert(Node[] head_ref, int data)
    {

        // Allocate memory for new node
        Node new_node = new Node();
        new_node.data = data;

        /* Since new node is being inserted at the
           beginning, npx of new node will always be
           XOR of current head and NULL */
        new_node.npx = head_ref[0];

        /* If linked list is not empty, then npx of
           current head node will be XOR of new node
           and node next to current head */
        if (head_ref[0] != null) {
            // *(head_ref)->npx is XOR of NULL and next.
            // So if we do XOR of it with NULL, we get next
            head_ref[0].npx = XOR(new_node, head_ref[0].npx);
        }

        // Change head
        head_ref[0] = new_node;
    }

    // prints contents of doubly linked list in forward direction
    public void printList (Node head) {
        Node curr = head;
        Node prev = null;
        Node next;

        System.out.println("Following are the nodes of Linked List: ");

        while (curr != null) {
            // print current node
            System.out.print(curr.data + " ");

            // get address of next node: curr->npx is
            // next^prev, so curr->npx^prev will be
            // next^prev^prev which is next
            next = XOR(prev, curr.npx);

            // update prev and curr for next iteration
            prev = curr;
            curr = next;
        }
    }

    // Driver code
    public static void main (String[] args) {
        /* Create following Doubly Linked List
           head-->40<-->30<-->20<-->10 */
        Node[] head = new Node[1];
        DoublyLinkedList dl = new DoublyLinkedList();
        dl.insert(head, 10);
        dl.insert(head, 20);
        dl.insert(head, 30);
        dl.insert(head, 40);

        // print the created list
        dl.printList(head[0]);
    }
}
