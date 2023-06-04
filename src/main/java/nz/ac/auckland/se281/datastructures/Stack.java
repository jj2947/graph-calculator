package nz.ac.auckland.se281.datastructures;

/**
 * A stack that contains data.
 */
public class Stack<T> {
  private Node<T> top;
  private int size;

  /**
   * A stack that contains data.
   */
  public Stack() {
    top = null;
    size = 0;
  }

  /**
   * Adds an element to the top of the stack.
   * 
   * @param data the element to be added.
   */
  public void push(T data) {
    // Create a new node with the data
    Node<T> newNode = new Node<>(data);
    newNode.setNext(top);  // Set the next node to the current top
    top = newNode; // Set the new node to be the top
    size++; 
  }

  /**
   * Removes the top element from the stack and returns it.
   * 
   * @return the top element of the stack.
   */
  public T pop() {
    // Set removed node to the current top
    Node<T> removedNode = top;
    // Set the new top to the next node
    top = top.getNext();
    // Remove the link to the next node
    removedNode.setNext(null);
    size--;
    // Return the data of the removed node
    return removedNode.getData();
  }

  public T peek() {
    return top.getData();
  }

  public int size() {
    return size;
  }

  /**
   * Checks if the stack is empty.
   * 
   * @return true if the stack is empty, false otherwise.
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    } else {
      return false;
    }
  }
}
