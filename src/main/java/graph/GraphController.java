package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class GraphController
{
    private static final Logger LOGGER = Logger.getLogger("graph.GraphController.class");

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
