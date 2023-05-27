package nz.ac.auckland.se281.datastructures;

public class Queue<T> {

  private int length;
  private Node<T> front, rear;

  public Queue() {
    length = 0;
    front = null;
    rear = null;
  }

  public void enqueue(T data) {
    Node<T> node = new Node<>(data);
    if (isEmpty()) {
      front = node;
    } else {
      rear.setNext(node);
    }
    rear = node;
    length++;
  }

  public T dequeue() {
    Node <T> dequeued = front;
    front = front.getNext();
    length--;
    return dequeued.getData();
  }

  public T peek() {
    return front.getData();
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
