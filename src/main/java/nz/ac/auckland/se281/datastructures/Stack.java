package nz.ac.auckland.se281.datastructures;

public class Stack<T> {
  private Node<T> top;
  private int size;

  public Stack() {
    top = null;
    size = 0;
  }

  public void push(T data) {
    Node<T> newNode = new Node<>(data);
    newNode.setNext(top);
    top = newNode;
    size++;
  }

  public T pop() {
    Node<T> removedNode = top;
    top = top.getNext();
    removedNode.setNext(null);
    size--;
    return removedNode.getData();
  }

  public T peek() {
    return top.getData();
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
