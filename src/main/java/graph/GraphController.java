package graph;

import java.util.ArrayList;
import java.util.List;

public final class GraphController
{
    public AbstractGraph g;
    public TraversalStrategyInterface traversalStrategy;
    public final List<Vertex> vertices;

    public GraphController()
    {
        vertices = createVertexList();
    }

    private static List<Vertex> createVertexList()
    {
        List<Vertex> vertices = new ArrayList<>();
        return  vertices;
    }
}
