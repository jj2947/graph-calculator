package nz.ac.auckland.se281.datastructures;

/** A linked list that contains data. */
public class LinkedList<T> {
  private Node<T> head;
  private int size;

  public LinkedList() {
    head = null;
    size = 0;
  }

  /**
   * Adds an element to the end of the linked list
   *
   * @param data the element to be added
   */
  public void add(T data) {
    // Create a new node with the data
    Node<T> newNode = new Node<>(data);

    // If the list is empty, set the head to the new node
    if (head == null) {
      head = newNode;
    } else { // Otherwise, iterate through the list until the last node is reached
      Node<T> current = head;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      // Set the next node of the last node to the new node
      current.setNext(newNode);
    }
    size++;
  }

  /**
   * Gets the element at the specified index and returns it
   *
   * @param index the index of the element to be returned
   * @return the element at the specified index
   */
  public T get(int index) {

    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    return current.getData();
  }

  /**
   * Inserts an element at the specified index
   *
   * @param index the index to insert the element at
   * @param data the element to be inserted
   */
  public void insert(int index, T data) {

    // If the index is 0, set the head to the new node
    if (index == 0) {
      Node<T> newNode = new Node<>(data);
      newNode.setNext(head);
      head = newNode;
    } else { // Otherwise, iterate through the list until the node before the index is reached
      Node<T> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext(); // Set the current node to the next node
      }
      // Create a new node with the data and set the next node to the current node's next node
      Node<T> newNode = new Node<>(data);
      newNode.setNext(current.getNext());
      // Set the next node of the current node to the new node
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
