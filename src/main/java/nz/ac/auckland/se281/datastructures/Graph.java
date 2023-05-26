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
