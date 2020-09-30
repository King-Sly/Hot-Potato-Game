

package hotpotato;

import java.util.Scanner;

/**
 * An implementation of a circularly linked list.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class CircularlyLinkedList<E> {
    //---------------- nested Node class ----------------

    /**
     * Singly linked node, which stores a reference to its element and
     * to the subsequent node in the list.
     */
    private static class Node<E> {

        /**
         * The element stored at this node
         */
        private E element;

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;

        //Constructor

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the CircularlyLinkedList
    /**
     * The designated cursor of the list
     */
    private Node<E> tail;           // we store tail (but not head)

    /**
     * Number of nodes in the list
     */
    private int size;

    //Constructor

    /**
     * Constructs an initially empty list.
     */
    public CircularlyLinkedList() {
        tail = null;
        size = 0;
    }

    // access methods

    /**
     * Returns the number of elements in the linked list.
     *
     * @return number of elements in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns (but does not remove) the first element of the list.
     *
     * @return element at the front of the list (or null if empty)
     */
    public E first() {
        if (isEmpty())
            return null;
        return tail.getNext().getElement();  // the head is *after* the tail
    }

    /**
     * Returns (but does not remove) the last element of the list.
     *
     * @return element at the back of the list (or null if empty)
     */
    public E last() {
        if (isEmpty())
            return null;
        return tail.getElement();
    }

    // update methods

    /**
     * Rotate the first element to the back of the list.
     */
    public void rotate() {
        if (tail != null)                // if empty, do nothing
            tail = tail.getNext();         // the old head becomes the new tail
    }

    /**
     * Adds an element to the front of the list.
     *
     * @param e the new element to add
     */
    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);             // link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param e the new element to add
     */
    public void addLast(E e) {
        addFirst(e);             // insert new element at front of list
        tail = tail.getNext();   // now new element becomes the tail
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {
        if (isEmpty())          // nothing to remove
            return null;
        Node<E> head = tail.getNext();
        if (head == tail)
            tail = null;   // must be the only node left
        else
            tail.setNext(head.getNext());  // removes "head" from the list
        size--;
        return head.getElement();
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
        if (tail == null)
            return "()";

        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = tail;
        do {
            walk = walk.getNext();
            sb.append(walk.getElement());
            if (walk != tail)
                sb.append(", ");
        } while (walk != tail);
        sb.append(")");
        return sb.toString();
    }




    /**
     * method to exchange any adjacent entry that is not in the tail regions
     *
     * @param e1 takes first element of the tail
     * @param e2 takes second element of the tail
     */
    public void exchange(E e1, E e2) {

        int num = 0;
        int errorCount = 0;
        //while loop to specifically check if e1 or e2 is in the linked list
        while (num < size) {
            tail = tail.getNext();
            if (!(tail.element.equals(e1) || tail.element.equals(e2))) {
                errorCount += 1;//if condition to determine this check
            }
            num += 1;
        }
        //this automatically checks if e1 and e2 are in the list
        if (errorCount == size || errorCount == size - 1) {
            System.out.println("Error not included");
        }
        else if (sideCheck(e1, e2)) {
            if (tail != null) { //checks if the tail element is empty

                Node<E> initialNode = tail; //assigns the tail node to a node
                E initialElement = initialNode.element;

                int counterList = 0;

                boolean tailChecker = initialElement.equals(e1) || initialElement.equals(e2); //checks if the elements to be swapped are the tail elements of the list

                if (size == 2) { //special case when size is 2
                    tail = tail.getNext();
                } else if (!tailChecker) { //true if elements are not in tail region

                    //loop to iterate over the tails of the items in the circular linked list
                    while (counterList < size) { //int counter

                        if (tail.element.equals(e2) || tail.element.equals(e1)) { //checks if the tails are equal to e2 or e1

                            Node<E> counterNode = tail.getNext();
                            tail.setNext(tail.getNext().getNext());//sets the tail to the next two node


                            int counterNumber = 1;
                            while (counterNumber <= size - 2) {
                                tail = tail.getNext();
                                counterNumber += 1;
                            }
                            counterNode.setNext(tail.getNext());//sets the counterNode in between nodes
                            tail.setNext(counterNode);//finishes the switch
                            counterList = size;
                        }

                        else {
                            tail = tail.getNext();
                        }
                        counterList += 1;

                    }
                    //swaps the items back to its initial order before the exchange was called
                    E elementToFind;
                    counterList = 0;
                    while (counterList < size) {
                        tail = tail.getNext();
                        elementToFind = tail.element;
                        if (initialElement.equals(elementToFind)) {
                            counterList = size;
                        }
                        counterList += 1;
                    }

                } else {
                    exchangeTail(e1, e2);
                }
            }
        }
        else if (e1.equals(e2)) {
            System.out.println("No exchange made!");
        }
        else {
            nonsideExchange(e1, e2);
        }
    }

    /**
     * method that simply switches element if they are in the tail
     *
     * @param e1 first element to be switched
     * @param e2 second element to be switched
     */
    public void exchangeTail(E e1, E e2) {
        Node<E> initialNode = tail.getNext();
        Node<E> counterNode = tail;
        int counter = 1;

        //checks if the next node has element e1 or e2
        if (initialNode.element.equals(e1) || initialNode.element.equals(e2)) {
            while (counter <= size - 1) {//changes each tail
                tail = tail.getNext();
                counter += 1;
            }
            tail.setNext(tail.getNext().getNext());//sets the link between two nodes

            tail = tail.getNext(); //shifts the tail
            counterNode.setNext(tail.getNext()); //sets the node
            tail.setNext(counterNode); //sets the counter Node and finishes the switch

        } else {//if first is false
            while (counter <= size - 1) {
                tail = tail.getNext();
                counter += 1;
            }
            tail.setNext(tail.getNext().getNext());
            counter = 1;
            while (counter < size - 1) {
                counter += 1;
                tail = tail.getNext();
            }
            counterNode.setNext(tail.getNext());//sets the counter node link
            tail.setNext(counterNode); //finishes setting the counter node link
            tail = tail.getNext().getNext();
        }
    }
    /**
     * this method takes two elements and checks if these two element are side by side
     * @param e1 first element to be exchanged
     * @param e2 second element to be exchanged
     * @return true if they are adjacent to each other
     */
    public boolean sideCheck(E e1, E e2) {
        boolean isCheck = false;
        if (tail.element.equals(e1) || tail.element.equals(e2)) { //checks if the two elements are side by side
            if (tail.getNext().element.equals(e1) || tail.getNext().element.equals(e2)) {
                isCheck = true;
            }
        }
        int counter = 0;
        //while loop to iterate and check if the two elements are side by side
        while (counter < size) {
            tail = tail.getNext();
            if (tail.element.equals(e1) || tail.element.equals(e2)) {
                if (tail.getNext().element.equals(e1) || tail.getNext().element.equals(e2)) {
                    isCheck = true;
                }
            }
            counter += 1;
        }
        return isCheck;
    }

    /**
     * this method exchanges players not placed side by side
     * @param e1 exchanges player 1 not close to player 2
     * @param e2 exchanges player 2 not close to player 1
     */
    public void nonsideExchange(E e1, E e2) {
            Node<E> originalTail = tail;
            E initialElement = originalTail.element;
            Node<E> firstNode = tail;
            Node<E> tailFirstNode = tail;
            Node<E> tailSecondNode = tail;
            Node<E> secondNode = tail;
            boolean tailChecker = initialElement.equals(e1) || initialElement.equals(e2); //checks if the elements are in the tail region
            int counter = 0; //introduces counter to go through the linked list
            if (!tailChecker) {//checks if tail checker is not true
                while (counter < size) {
                    tail = tail.getNext();//goes through each node
                    if (tail.getNext().element.equals(e1) || tail.getNext().element.equals(e2)) {
                        firstNode = tail.getNext();
                        tailFirstNode = tail;
                        tail.setNext(tail.getNext().getNext());
                        int secondCounter = 0;
                        while (secondCounter < size) {
                            tail = tail.getNext();
                            if (tail.getNext().element.equals(e1) || tail.getNext().element.equals(e2)) {
                                secondNode = tail.getNext();
                                tailSecondNode = tail;
                                tail.setNext(tail.getNext().getNext());
                            }
                            secondCounter += 1;
                        }
                    }
                    counter += 1;
                }
                int thirdCounter = 0;
                while (thirdCounter < size) {
                    tail = tail.getNext();
                    if (tailSecondNode == tail) {
                        firstNode.setNext(tail.getNext());//sets the node
                        tail.setNext(firstNode);
                    }
                    if (tailFirstNode == tail) {
                        secondNode.setNext(tail.getNext());
                        tail.setNext(secondNode);
                    }
                    thirdCounter += 1;
                }
                tail = tail.getNext();
                int fourthCounter = 0;
                while (fourthCounter < size) {
                    tail = tail.getNext();
                    if (originalTail == tail) {
                        fourthCounter = size;
                    }
                    fourthCounter += 1;
                }
            }
            else { //else to change players who are in the tail region
                int fifthCounter = 0;
                Node<E> Node2 = tail.getNext();
                Node<E> tailP = tail;
                while (fifthCounter < size - 1) {
                    tail = tail.getNext();
                    fifthCounter += 1;
                }
                Node<E> Node1 = tail.getNext();
                Node<E> tailN = tail;//temporary variable
                tail.setNext(tail.getNext().getNext());
                int sixthCounter = 0;
                while (sixthCounter < size) {
                    tail = tail.getNext();//goes through each node
                    if (tail.getNext().element.equals(e1) || tail.getNext().element.equals(e2)) {
                        Node2 = tail.getNext();//temporary variable
                        tailP = tail;
                        tail.setNext(tail.getNext().getNext());
                        Node1.setNext(tail.getNext());
                        tail.setNext(Node1);
                        int index = 0;//counter
                        while (index < size) {
                            if (tail == tailN) {
                                Node2.setNext(tail.getNext());
                                tail.setNext(Node2);//sets the node and completes the exchange
                                index = size;
                            }
                            tail = tail.getNext();
                            index += 1;
                        }
                        sixthCounter = size;
                    }
                    sixthCounter += 1;
                }
            }

        }
    }








