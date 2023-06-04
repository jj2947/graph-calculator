package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {
  private Set<T> verticies;
  private Set<Edge<T>> edges;
  private Map<T, LinkedList<T>> adjacencyMap;

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.verticies = verticies;
    this.edges = edges;

    // Initialize the adjacency map
    adjacencyMap = new HashMap<T, LinkedList<T>>();

    // Initialize the adjacency map with empty sets for each vertex
    for (T vertex : verticies) {
      adjacencyMap.put(vertex, new LinkedList<T>());
    }

    // Add edges to the adjacency map
    for (Edge<T> edge : edges) {
      T source = edge.getSource();
      T destination = edge.getDestination();

      // Add the destination to the adjacency map of the source
      LinkedList<T> destinations = adjacencyMap.get(source);

      // If destinations is empty or the destination is greater than the last element in the list,
      // add it to destinations
      if (destinations.isEmpty()
          || compareNumerically(destination, destinations.get(destinations.size() - 1)) > 0) {
        destinations.add(destination);
        // Otherwise, find the correct index to insert the destination so destinations is ordered
        // from smallest to largest
      } else {
        int index = 0;
        while (index < destinations.size()
            && compareNumerically(destination, destinations.get(index)) > 0) {
          index++;
        }
        destinations.insert(index, destination);
      }
    }
  }

  public Map<T, LinkedList<T>> getAdjacencyMap() {
    return adjacencyMap;
  }

  public Set<T> getRoots() {
    Set<T> roots = new LinkedHashSet<T>();
    List<T> unorderedRoots = new ArrayList<T>();

    for (T vertex : verticies) {
      int count = 0;
      for (Edge<T> edge : edges) {
        if (edge.getDestination().equals(vertex)) {
          count++;
          break;
        }
      }
      if (count == 0) {
        unorderedRoots.add(vertex);
      }
    }

    if (isEquivalence()) {
      for (T vertex : verticies) {
        for (T vertex2 : verticies) {
          if (!getEquivalenceClass(vertex).contains(vertex2)) {
            unorderedRoots.add(getEquivalenceClass(vertex).iterator().next());
            break;
          }
        }
      }
    }

    sort(unorderedRoots);

    for (T root : unorderedRoots) {
      roots.add(root);
    }

    return roots;
  }

  public boolean isReflexive() {
    boolean vertexReflexive;

    for (T vertex : verticies) {
      vertexReflexive = false;
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

    boolean edgeSymmetric;

    for (Edge<T> edge1 : edges) {
      edgeSymmetric = false;
      for (Edge<T> edge2 : edges) {
        if (edge1.getSource().equals(edge2.getDestination())
            && edge1.getDestination().equals(edge2.getSource())) {
          edgeSymmetric = true;
          break;
        }
      }
      if (!edgeSymmetric) {
        return false;
      }
    }

    return true;
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

    Set<T> equivalenceClass = new LinkedHashSet<T>();

    if (!isEquivalence()) {
      return equivalenceClass;
    }

    for (int i = 0; i < adjacencyMap.get(vertex).size(); i++) {
      equivalenceClass.add(adjacencyMap.get(vertex).get(i));
    }

    return equivalenceClass;
  }

  public List<T> iterativeBreadthFirstSearch() {
    Queue<T> queue = new Queue<>();
    List<T> visited = new ArrayList<>();

    for (T root : getRoots()) {
      queue.enqueue(root);

      while (!queue.isEmpty()) {
        for (T vertex : adjacencyMap.keySet()) {
          bfsAlgorithm(vertex, visited, queue);

          if (queue.isEmpty()) {
            break;
          }
        }
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

    for (int i = toPush.size() - 1; i >= 0; i--) {
      stack.push(toPush.get(i));
    }

    while (!stack.isEmpty()) {
      for (T vertex : adjacencyMap.keySet()) {
        dfsAlgorithm(vertex, visited, stack);

        if (stack.isEmpty()) {
          break;
        }
      }
    }

    return visited;
  }

  public List<T> recursiveBreadthFirstSearch() {
    List<T> visited = new ArrayList<>();
    Queue<T> queue = new Queue<>();

    for (T root : getRoots()) {
      queue.enqueue(root);
      recursiveBfs(visited, queue);
    }
    return visited;
  }

  public List<T> recursiveDepthFirstSearch() {
    List<T> visited = new ArrayList<>();
    Stack<T> stack = new Stack<>();
    List<T> toPush = new ArrayList<>();

    for (T root : getRoots()) {
      toPush.add(root);
    }

    for (int i = toPush.size() - 1; i >= 0; i--) {
      stack.push(toPush.get(i));
    }

    recursiveDfs(visited, stack);

    return visited;
  }

  private void recursiveBfs(List<T> visited, Queue<T> queue) {
    if (queue.isEmpty()) {
      return;
    }

    for (T vertex : adjacencyMap.keySet()) {
      bfsAlgorithm(vertex, visited, queue);

      if (queue.isEmpty()) {
        break;
      }
    }

    recursiveBfs(visited, queue);
  }

  private void recursiveDfs(List<T> visited, Stack<T> stack) {
    if (stack.isEmpty()) {
      return;
    }

    for (T vertex : adjacencyMap.keySet()) {
      dfsAlgorithm(vertex, visited, stack);

      if (stack.isEmpty()) {
        break;
      }
    }

    recursiveDfs(visited, stack);
  }

  public void sort(List<T> list) {

    if (list.size() == 0) {
      return;
    }

    for (int i = 0; i < list.size() - 1; i++) {
      int least = i;
      for (int j = i + 1; j < list.size(); j++) {
        if (compareNumerically(list.get(j), list.get(least)) < 0) {
          least = j;
        }
      }
      swap(i, least, list);
    }
  }

  private int compareNumerically(T a, T b) {
    // T is an integer
    int valueA = Integer.parseInt(a.toString());
    int valueB = Integer.parseInt(b.toString());
    return Integer.compare(valueA, valueB);
  }

  private void swap(int i, int j, List<T> list) {
    T temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }

  private void dfsAlgorithm(T vertex, List<T> visited, Stack<T> stack) {

    if (vertex.equals(stack.peek())) {

      visited.add(stack.pop());

      for (int i = adjacencyMap.get(vertex).size() - 1; i >= 0; i--) {
        stack.push(adjacencyMap.get(vertex).get(i));
      }
    }

    while (!stack.isEmpty() && visited.contains(stack.peek())) {
      stack.pop();
    }
  }

  private void bfsAlgorithm(T vertex, List<T> visited, Queue<T> queue) {

    if (vertex.equals(queue.peek())) {
      visited.add(queue.dequeue());
      for (int i = 0; i < adjacencyMap.get(vertex).size(); i++) {
        queue.enqueue(adjacencyMap.get(vertex).get(i));
      }
    }

    while (!queue.isEmpty() && visited.contains(queue.peek())) {
      queue.dequeue();
    }
  }
}
