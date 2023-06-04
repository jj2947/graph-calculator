package nz.ac.auckland.se281.datastructures;

/** A queue that contains data. */
public class Queue<T> {

  private int length;
  private Node<T> front;
  private Node<T> rear;

  /** A queue that contains data. */
  public Queue() {
    length = 0;
    front = null;
    rear = null;
  }

  /**
   * Adds an element to the end of the queue.
   *
   * @param data the element to be added.
   */
  public void enqueue(T data) {
    // Create a new node with the data
    Node<T> node = new Node<>(data);
    // If the queue is empty, set the front to the new node
    if (isEmpty()) {
      front = node;
    } else { // Otherwise, set the next node of the rear to the new node
      rear.setNext(node);
    }
    // Set the rear to the new node and increment the length
    rear = node;
    length++;
  }

  /**
   * Removes the first element from the queue and returns it.
   *
   * @return the first element of the queue.
   */
  public T dequeue() {
    // Set the dequeued node to the front
    Node<T> dequeued = front;
    // Set the new front to the next node
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

  /**
   * Checks if the queue is empty.
   *
   * @return true if the queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    if (size() == 0) {
      return true;
    } else {
      return false;
    }
  }
}
