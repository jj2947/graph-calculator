package nz.ac.auckland.se281.datastructures;

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
    // TODO: Task 1.
    throw new UnsupportedOperationException();
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
    boolean edgeSymmetric = false;

    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(edge.getDestination())
          && edge.getDestination().equals(edge.getSource())) {
        edgeSymmetric = true;
        break;
      }
    }
    if (!edgeSymmetric) {
      return false;
    }
    return true;
  }

  public boolean isTransitive() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isAntiSymmetric() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isEquivalence() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public Set<T> getEquivalenceClass(T vertex) {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeBreadthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeDepthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveBreadthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveDepthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }
}
