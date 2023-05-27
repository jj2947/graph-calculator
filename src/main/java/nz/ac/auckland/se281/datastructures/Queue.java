package nz.ac.auckland.se281.datastructures;

public class Queue<T> {

  private int length;
  private Node<T> head, tail;

  public Queue() {
    length = 0;
    head = null;
    tail = null;
  }

  public void enqueue(T data) {
    Node<T> node = new Node<>(data);
    if (isEmpty()) {
      head = node;
    } else {
      tail.setNext(node);
    }
    tail = node;
    length++;
  }

  public T dequeue() {
    Node <T> dequeued = head;
    head = head.getNext();
    length--;
    return dequeued.getData();
  }

  public T peek() {
    return head.getData();
  }

  public int size() {
    return length;
  }

  public boolean isEmpty() {
    if (size() == 0) {
      return true;
    } else {
      return false;
    }
  }
}
