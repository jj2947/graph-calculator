package nz.ac.auckland.se281.datastructures;

public class LinkedList<T> {
  private Node<T> head;
  private int size;

  public LinkedList() {
    head = null;
    size = 0;
  }

  public void add(T data) {
    Node<T> newNode = new Node<>(data);

    if (head == null) {
      head = newNode;
    } else {
      Node<T> current = head;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(newNode);
    }
    size++;
  }

  public T get(int index) {

    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    return current.getData();
  }

  public void insert(int index, T data) {

    if (index == 0) {
      Node<T> newNode = new Node<>(data);
      newNode.setNext(head);
      head = newNode;
    } else {
      Node<T> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      Node<T> newNode = new Node<>(data);
      newNode.setNext(current.getNext());
      current.setNext(newNode);
    }
    size++;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }
}
