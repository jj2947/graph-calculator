package nz.ac.auckland.se281.datastructures;

import java.util.LinkedList;

public class Queue<T> {

  private LinkedList<T> queue;

  public Queue() {
    queue = new LinkedList<>();
  }

  public void enqueue(T data) {
    queue.addLast(data);
  }

  public T dequeue() {
    T data = queue.get(0);
    queue.removeFirst();
    return data;
  }

  public T peek() {
    return queue.getFirst();
  }

  public boolean isEmpty() {
    if (queue.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return queue.toString();
  }
}
