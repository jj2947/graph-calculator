package nz.ac.auckland.se281.datastructures;

public class Stack<T> {
  private Node<T> tail;
  private int size;

  public Stack() {
    tail = null;
    size = 0;
  }

  public void push(T data) {
    Node<T> newNode = new Node<>(data);
    newNode.setNext(tail);
    tail = newNode;
    size++;
  }

  public T pop() {
    Node<T> poppedNode = tail;
    tail = tail.getNext();
    poppedNode.setNext(null);
    size--;
    return poppedNode.getData();
  }

  public T peek() {
    return tail.getData();
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    if (size == 0) {
      return true;
    } else {
      return false;
    }
  }
}
