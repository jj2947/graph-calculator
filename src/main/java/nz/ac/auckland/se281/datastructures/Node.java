package nz.ac.auckland.se281.datastructures;

public class Node<T> {
  private T data;
  private Node<T> next;

  /**
   * A node that contains data.
   *
   * @param data
   */
  public Node(T data) {
    this.data = data;
    this.next = null;
  }

  public T getData() {
    return data;
  }

  public Node<T> getNext() {
    return next;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }
}
