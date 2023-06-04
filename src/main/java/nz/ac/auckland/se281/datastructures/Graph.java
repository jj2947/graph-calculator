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

  /**
   * A graph that is composed of a set of verticies and edges.
   *
   * @param verticies the set of verticies in the graph.
   * @param edges the set of edges in the graph.
   */
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

  /**
   * Finds the roots of the graph and returns them in ascending numerical order.
   *
   * @return the roots of the graph.
   */
  public Set<T> getRoots() {

    // Initialize the roots set and the unordered roots array list
    Set<T> roots = new LinkedHashSet<T>();
    List<T> unorderedRoots = new ArrayList<T>();

    // Loop through all the vertices and the variable count is the in degree of each vertex
    for (T vertex : verticies) {
      int count = 0;
      for (Edge<T> edge : edges) {
        // If the destination of an edge is equal to the vertex, the vertex has an in degree greater
        // than zero, so it is not a root
        if (edge.getDestination().equals(vertex)) {
          count++;
          break;
        }
      }
      // If the in degree of a vertex is 0 (count is 0), add it to the roots array list
      if (count == 0) {
        unorderedRoots.add(vertex);
      }
    }

    // If the graph is an equivalence relation, find the minimum value of each different equivalence
    // class which will be a root of the graph
    if (isEquivalence()) {
      for (T vertex : verticies) {
        for (T vertex2 : verticies) {
          if (!getEquivalenceClass(vertex).contains(vertex2)) {
            unorderedRoots.add(adjacencyMap.get(vertex).get(0));
            break;
          }
        }
      }
    }

    // Sort the unordered roots array list
    sort(unorderedRoots);

    // Add the sorted roots to the roots set
    for (T root : unorderedRoots) {
      roots.add(root);
    }

    return roots;
  }

  /**
   * Finds whether the graph is reflexive.
   *
   * @return true if the graph is reflexive, false otherwise.
   */
  public boolean isReflexive() {

    // Initialize the vertexReflexive boolean variable
    boolean vertexReflexive;

    // Loop through all vertices and edges in the graph
    for (T vertex : verticies) {
      vertexReflexive = false;
      for (Edge<T> edge : edges) {
        // If the vertex has a self loop, it is reflexive so set vertexReflexive to true and break
        // the loop
        if (edge.getSource().equals(vertex) && edge.getDestination().equals(vertex)) {
          vertexReflexive = true;
          break;
        }
      }
      // If a vertex does not have a self loop, the graph is not reflexive so return false
      if (!vertexReflexive) {
        return false;
      }
    }
    // If all vertices have a self loop, the graph is reflexive so return true
    return true;
  }

  /**
   * Finds whether the graph is symmetric.
   *
   * @return true if the graph is symmetric, false otherwise.
   */
  public boolean isSymmetric() {

    // If there are no edges in the graph, it is symmetric so return true
    if (edges.size() == 0) {
      return true;
    }

    boolean edgeSymmetric;

    // Loop through all edges in the graph
    for (Edge<T> edge1 : edges) {
      edgeSymmetric = false;
      for (Edge<T> edge2 : edges) {
        // If there is an edge from vertex A to vertex B and an edge from vertex B to vertex A, then
        // the symmetry is satisfied for the edge
        if (edge1.getSource().equals(edge2.getDestination())
            && edge1.getDestination().equals(edge2.getSource())) {
          edgeSymmetric = true;
          break;
        }
      }
      // If an edge does not satisfy the symmetry property, the graph is not symmetric so return
      if (!edgeSymmetric) {
        return false;
      }
    }
    // If all edges satisfy the symmetry property, the graph is symmetric so return true
    return true;
  }

  /**
   * Finds whether the graph is transitive.
   *
   * @return true if the graph is transitive, false otherwise.
   */
  public boolean isTransitive() {
    // If there are no edges in the graph, it is transitive so return true
    if (edges.size() == 0) {
      return true;
    }

    boolean edge3Exists;

    // If there is an edge from vertex A to vertex B and an edge from vertex B to vertex C, then
    // there should be an edge from vertex A to vertex C for the transitivity property to be
    // satisfied
    for (Edge<T> edge1 : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge1.getDestination().equals(edge2.getSource())) {
          edge3Exists = false;

          // If there is an edge from vertex A to vertex C, set edge3Exists to true and break the
          // loop
          for (Edge<T> edge3 : edges) {
            if (edge1.getSource().equals(edge3.getSource())
                && edge2.getDestination().equals(edge3.getDestination())) {
              edge3Exists = true;
              break;
            }
          }
          // If there is no edge from vertex A to vertex C, the graph is not transitive so return
          // false
          if (!edge3Exists) {
            return false;
          }
        }
      }
    }
    // If all edges satisfy the transitivity property, the graph is transitive so return true
    return true;
  }

  /**
   * Finds whether the graph is anti symmetric.
   *
   * @return true if the graph is anti symmetric, false otherwise.
   */
  public boolean isAntiSymmetric() {
    // If there are no edges in the graph, it is anti symmetric so return true
    if (edges.size() == 0) {
      return true;
    }

    // For all edges, if there is an edge from vertex A to vertex B and an edge from vertex B to
    // vertex A, and A=B the antisymmetric property is satisfied
    for (Edge<T> edge1 : edges) {
      for (Edge<T> edge2 : edges) {
        if (edge1.getSource().equals(edge2.getDestination())
            && edge1.getDestination().equals(edge2.getSource())) {
          // If A != B, the antisymmetric property is not satisfied so return false
          if (!edge1.getSource().equals(edge2.getSource())) {
            return false;
          }
        }
      }
    }
    // If all edges satisfy the antisymmetric property, the graph is antisymmetric so return true
    return true;
  }

  /**
   * Finds whether the graph is an equivalence relation.
   *
   * @return true if the graph is an equivalence relation, false otherwise.
   */
  public boolean isEquivalence() {
    // If the graph is reflexive, symmetric and transitive, it is an equivalence relation so return
    // true
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Finds and returns the equivalence class of a vertex.
   *
   * @param vertex the vertex to find the equivalence class of.
   * @return the equivalence class of the vertex.
   */
  public Set<T> getEquivalenceClass(T vertex) {

    Set<T> equivalenceClass = new LinkedHashSet<T>();

    // If the graph is not an equivalence relation, return the empty set
    if (!isEquivalence()) {
      return equivalenceClass;
    }

    // Add the destination values from the adjacency map to the equivalence class
    for (int i = 0; i < adjacencyMap.get(vertex).size(); i++) {
      equivalenceClass.add(adjacencyMap.get(vertex).get(i));
    }
    return equivalenceClass;
  }

  /**
   * Iteratively finds the visited vertices for the breadth first search algorithm and returns the
   * visited list.
   *
   * @return the visited vertices for the breadth first search algorithm.
   */
  public List<T> iterativeBreadthFirstSearch() {
    Queue<T> queue = new Queue<>();
    List<T> visited = new ArrayList<>();

    // Add all the roots to the queue after all successors of the root before have been visited
    for (T root : getRoots()) {
      queue.enqueue(root);
      // Loop through all the vertices in the graph while the queue is not empty
      while (!queue.isEmpty()) {
        // Run the bfs algorithm which will will modify the queue and visited list
        bfsAlgorithm(visited, queue);
      }
    }
    return visited;
  }

  /**
   * Iteratively finds the visited vertices for the depth first search algorithm and returns the
   * visited list.
   *
   * @return the visited vertices for the depth first search algorithm.
   */
  public List<T> iterativeDepthFirstSearch() {
    Stack<T> stack = new Stack<>();
    List<T> visited = new ArrayList<>();
    List<T> toPush = new ArrayList<>();

    // Add all the roots to the toPush arraylist
    for (T root : getRoots()) {
      toPush.add(root);
    }

    // Push all the roots to the stack in descending numerical order
    for (int i = toPush.size() - 1; i >= 0; i--) {
      stack.push(toPush.get(i));
    }

    // Loop through all the vertices in the graph while the stack is not empty
    while (!stack.isEmpty()) {

      // Run the dfs algorithm which will modify the stack and visited list
      dfsAlgorithm(visited, stack);
    }
    return visited;
  }

  /**
   * Recursively finds the visited vertices for the breadth first search algorithm and returns the
   * visited list.
   *
   * @return the visited vertices for the breadth first search algorithm.
   */
  public List<T> recursiveBreadthFirstSearch() {
    List<T> visited = new ArrayList<>();
    Queue<T> queue = new Queue<>();

    // Add all the roots to the queue after all successors of the root before have been visited
    for (T root : getRoots()) {
      queue.enqueue(root);
      // Run the recursive bfs algorithm on the queue which will modify the queue and visited list
      recursiveBfs(visited, queue);
    }
    return visited;
  }

  /**
   * Recursively finds the visited vertices for the depth first search algorithm and returns the
   * visited list.
   *
   * @return the visited vertices for the depth first search algorithm.
   */
  public List<T> recursiveDepthFirstSearch() {
    List<T> visited = new ArrayList<>();
    Stack<T> stack = new Stack<>();
    List<T> toPush = new ArrayList<>();

    // Add all the roots to the toPush arraylist
    for (T root : getRoots()) {
      toPush.add(root);
    }

    // Push all the roots to the stack in descending numerical order
    for (int i = toPush.size() - 1; i >= 0; i--) {
      stack.push(toPush.get(i));
    }

    // Call the recursive dfs method which will modify the stack and visited list
    recursiveDfs(visited, stack);

    return visited;
  }

  /**
   * Recursively modifies the visited vertices and queue for the breadth first search algorithm.
   *
   * @param visited the list of visited vertices.
   * @param queue the queue of vertices to visit.
   */
  private void recursiveBfs(List<T> visited, Queue<T> queue) {
    if (queue.isEmpty()) {
      return;
    }

    // Run the bfs algorithm which will modify the queue and visited list
    bfsAlgorithm(visited, queue);

    // Recursively call this method
    recursiveBfs(visited, queue);
  }

  /**
   * Recursively modifies the visited vertices and stack for the depth first search algorithm.
   *
   * @param visited the list of visited vertices.
   * @param stack the stack of vertices to visit.
   */
  private void recursiveDfs(List<T> visited, Stack<T> stack) {
    if (stack.isEmpty()) {
      return;
    }

    // Run the dfs algorithm which will modify the stack and visited list
    dfsAlgorithm(visited, stack);

    // Recursively call this method
    recursiveDfs(visited, stack);
  }

  /**
   * Sorts a list of elements into ascending numerical order.
   *
   * @param list the list to sort.
   */
  public void sort(List<T> list) {

    // If the list is empty, return
    if (list.size() == 0) {
      return;
    }

    // Loop through the list and set the least element to the current i element
    for (int i = 0; i < list.size() - 1; i++) {
      int least = i;
      // Loop through all the elements after the current i element
      for (int j = i + 1; j < list.size(); j++) {
        // If the element at j is less than the element at least, set least to j
        if (compareNumerically(list.get(j), list.get(least)) < 0) {
          least = j;
        }
      }
      // Swap the element at i with the element at least
      swap(i, least, list);
    }
  }

  /**
   * Compares two elements numerically and returns the comparison as an integer.
   *
   * @param a the first element to compare.
   * @param b the second element to compare.
   * @return the comparison of the two elements.
   */
  private int compareNumerically(T a, T b) {
    // Convert the elements to integers to compare them numerically
    int valueA = Integer.parseInt(a.toString());
    int valueB = Integer.parseInt(b.toString());
    // Return the comparison of the two elements, if a is less than b, return -1, if a is greater
    // than b, return 1, if a is equal to b, return 0
    return Integer.compare(valueA, valueB);
  }

  /**
   * Swaps two elements in a list.
   *
   * @param i the index of the first element to swap.
   * @param j the index of the second element to swap.
   * @param list the list to swap the elements in.
   */
  private void swap(int i, int j, List<T> list) {
    T temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }

  /**
   * Runs the depth first search algorithm and modifies the stack and visited list.
   *
   * @param visited the list of visited vertices.
   * @param stack the stack of vertices to visit.
   */
  private void dfsAlgorithm(List<T> visited, Stack<T> stack) {

    for (T vertex : adjacencyMap.keySet()) {
      // If the vertex is equal to the top of the stack, pop the vertex and add it to the
      // visited list
      if (vertex.equals(stack.peek())) {
        visited.add(stack.pop());
        // Loop through all the successors of the vertex in descding order and push them to the
        // stack
        for (int i = adjacencyMap.get(vertex).size() - 1; i >= 0; i--) {
          stack.push(adjacencyMap.get(vertex).get(i));
        }
        break;
      }
    }

    // If the vertex at the top of the stack has already been added to the visited list, pop it
    while (!stack.isEmpty() && visited.contains(stack.peek())) {
      stack.pop();
    }
  }

  /**
   * Runs the breadth first search algorithm and modifies the queue and visited list.
   *
   * @param visited the list of visited vertices.
   * @param queue the queue of vertices to visit.
   */
  private void bfsAlgorithm(List<T> visited, Queue<T> queue) {

    // Loop through all the vertices in the graph
    for (T vertex : adjacencyMap.keySet()) {
      // If the vertex is at the front of the queue, dequeue the vertex and add it to the visited
      // list
      if (vertex.equals(queue.peek())) {
        visited.add(queue.dequeue());
        // Loop through all the successors of the vertex and enqueue them
        for (int i = 0; i < adjacencyMap.get(vertex).size(); i++) {
          queue.enqueue(adjacencyMap.get(vertex).get(i));
        }
        break;
      }
    }

    // If the vertex at the front of the queue has already been added to the visited list, dequeue
    // it
    while (!queue.isEmpty() && visited.contains(queue.peek())) {
      queue.dequeue();
    }
  }
}
