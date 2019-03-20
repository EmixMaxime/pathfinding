package graph;

import java.util.Set;

/**
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 */
public interface Graph<V, E> {

  /**
   * Returns a set of all edges touching the specified vertex. If no edges are touching the
   * specified vertex returns an empty set.
   *
   * @param vertex the vertex for which a set of touching edges is to be returned.C
   * @return a set of all edges touching the specified vertex.
   * @throws IllegalArgumentException if vertex is not found in the graph.
   * @throws NullPointerException if vertex is <code>null</code>.
   */
  Set<E> edgesOf(V vertex);

  /**
   * Returns a set of all edges outgoing from the specified vertex.
   *
   * <p>In the case of undirected graphs this method returns all edges touching the vertex, thus,
   * some of the returned edges may have their source and target vertices in the opposite order.
   *
   * @param vertex the vertex for which the list of outgoing edges to be returned.
   * @return a set of all edges outgoing from the specified vertex.
   * @throws IllegalArgumentException if vertex is not found in the graph.
   * @throws NullPointerException if vertex is <code>null</code>.
   */
  Set<E> outgoingEdgesOf(V vertex);

  /**
   * @param vertex vertex whose presence in this graph is to be tested.
   * @return <tt>true</tt> if this graph contains the specified vertex.
   */
  boolean containsVertex(V vertex);

  /**
   * Ensures that the specified vertex exists in this graph (otherwise throws exception).
   *
   * @param vertex
   * @return <code>true</code> if this assertion holds.
   * @throws NullPointerException if specified vertex is <code>null</code>.
   * @throws IllegalArgumentException if specified vertex does not exist in this graph.
   */
  boolean assertVertexExist(V vertex);

  /**
   * @param edge
   * @return source vertex
   */
  V getEdgeSource(E edge);

  /**
   * @param edge
   * @return target vertex
   */
  V getEdgeTarget(E edge);
}
