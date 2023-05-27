package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {
  Set<T> verticies;
  Set<Edge<T>> edges;

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.verticies = verticies;
    this.edges = edges;
  }

  public Set<T> getRoots() {
    Set<T> roots = new HashSet<T>();

    for (T vertex : verticies) {
      int count = 0;
      for (Edge<T> edge : edges) {
        if (edge.getDestination().equals(vertex)) {
          count++;
          break;
        }
      }
      if (count == 0) {
        roots.add(vertex);
      }
    }

    if (isEquivalence()) {
      for (T vertex : verticies) {
        for (T vertex2 : verticies) {
          if (!getEquivalenceClass(vertex).contains(vertex2)) {
            roots.add(getEquivalenceClass(vertex).iterator().next());
            break;
          }
        }
      }
    }
    return roots;
  }

  public boolean isReflexive() {
    boolean vertexReflexive = false;

    for (T vertex : verticies) {
      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(vertex) && edge.getDestination().equals(vertex)) {
          vertexReflexive = true;
          break;
        }
      }
      if (!vertexReflexive) {
        return false;
      }
    }
    return true;
  }

  public boolean isSymmetric() {
    if (edges.size() == 0) {
      return true;
    }

    boolean edgeSymmetric = false;

    for (Edge<T> edge1 : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge1.getSource().equals(edge2.getDestination())
            && edge1.getDestination().equals(edge2.getSource())) {
          edgeSymmetric = true;
          break;
        }
      }
    }

    if (!edgeSymmetric) {
      return false;
    } else {
      return true;
    }
  }

  public boolean isTransitive() {
    if (edges.size() == 0) {
      return true;
    }

    for (Edge<T> edge1 : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge1.getDestination().equals(edge2.getSource())) {
          boolean edge3Exists = false;

          for (Edge<T> edge3 : edges) {
            if (edge1.getSource().equals(edge3.getSource())
                && edge2.getDestination().equals(edge3.getDestination())) {
              edge3Exists = true;
              break;
            }
          }
          if (!edge3Exists) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean isAntiSymmetric() {
    if (edges.size() == 0) {
      return true;
    }

    for (Edge<T> edge1 : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge1.getSource().equals(edge2.getDestination())
            && edge1.getDestination().equals(edge2.getSource())) {
          if (!edge1.getSource().equals(edge2.getSource())) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean isEquivalence() {
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    } else {
      return false;
    }
  }

  public Set<T> getEquivalenceClass(T vertex) {
    if (!isEquivalence()) {
      return new HashSet<T>();
    }

    Set<T> equivalenceClass = new HashSet<T>();

    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(vertex)) {
        equivalenceClass.add(edge.getDestination());
      }
    }

    return equivalenceClass;
  }

  public List<T> iterativeBreadthFirstSearch() {
    Queue<T> queue = new Queue<>();
    List<T> visited = new ArrayList<>();
    List<T> toEnqueue = new ArrayList<>();

    for (T root : getRoots()) {
      toEnqueue.add(root);
    }

    selectionSort(toEnqueue);

    for (int i = toEnqueue.size() - 1; i >= 0; i--) {
      queue.enqueue(toEnqueue.get(i));
    }

    while (!queue.isEmpty()) {
      toEnqueue.clear();
      for (Edge<T> edge : edges) {

        if (edge.getSource().equals(queue.peek())) {
          toEnqueue.add(edge.getDestination());
        }
      }

      selectionSort(toEnqueue);

      for (int i = toEnqueue.size() - 1; i >= 0; i--) {
        queue.enqueue(toEnqueue.get(i));
      }

      if (!queue.isEmpty()) {
        visited.add(queue.dequeue());
      }
      
      while (!queue.isEmpty() && visited.contains(queue.peek())) {
        queue.dequeue();
      }
    }

    return visited;
  }

  public List<T> iterativeDepthFirstSearch() {
    Stack<T> stack = new Stack<>();
    List<T> visited = new ArrayList<>();
    List<T> toPush = new ArrayList<>();

    for (T root : getRoots()) {
      toPush.add(root);
    }

    selectionSort(toPush);

    for (T element : toPush) {
      stack.push(element);
    }

    while (!stack.isEmpty()) {
      toPush.clear();

      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(stack.peek())) {
          toPush.add(edge.getDestination());
        }
      }

      if (!stack.isEmpty()) {
        visited.add(stack.pop());
      }
      
      selectionSort(toPush);

      for (T element : toPush) {
        stack.push(element);
      }

      while (!stack.isEmpty() && visited.contains(stack.peek())) {
        stack.pop();
      }
    }

    return visited;
  }

  public List<T> recursiveBreadthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveDepthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }

  public void selectionSort(List<T> list) {

    if (list.size() == 0) {
      return;
    }

    for (int i = 0; i < list.size() - 1; i++) {
      int greatest = i;
      for (int j = i + 1; j < list.size(); j++) {
        if (list.get(j).compareTo(list.get(greatest)) > 0) {
          greatest = j;
        }
      }
      swap(i, greatest, list);
    }
  }

  private void swap(int sourceIndex, int destIndex, List<T> inputArray) {
    T temp = inputArray.get(destIndex);
    inputArray.set(destIndex, inputArray.get(sourceIndex));
    inputArray.set(sourceIndex, temp);
  }
}
