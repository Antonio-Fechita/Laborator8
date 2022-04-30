package com.example.lab8javafx.graphtheory;


import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * https://jgrapht.org/javadoc-1.3.1/org/jgrapht/alg/clique/BronKerboschCliqueFinder.html
 */

public class CliqueFinder
{
    public static void findCliques(ArrayList<Node> nodes)
    {
        SimpleGraph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        ArrayList<Edge> edges = new ArrayList<>();
        HashSet<String> edgeNames = new HashSet<>();

        for (Node node : nodes)
        {
            graph.addVertex( node.getNodeIdentifier() );

            ArrayList<Node> neighbors = node.getNeighbours();

            for (Node currentNeighbor: neighbors)
            {
                String possibleEdgeIdentifier = Edge.getIdentifierFromNodes(node, currentNeighbor);

                if ( !edgeNames.contains( possibleEdgeIdentifier ))
                {
                    Edge newEdge = new Edge(node, currentNeighbor);
                    edges.add(newEdge);
                    edgeNames.add( newEdge.getEdgeIdentifier() );
                }
            }
        }

        for (Edge edge : edges)
            graph.addEdge( edge.getNodeA().getNodeIdentifier(), edge.getNodeB().getNodeIdentifier() );

        BronKerboschCliqueFinder bronKerboschCliqueFinder = new BronKerboschCliqueFinder(graph);

        Iterator<Set<String>> cliques = bronKerboschCliqueFinder.iterator();//get the cliques

        System.out.println("Cirques found: ");

        while (cliques.hasNext())
        {
            Set<String> currentClique = cliques.next();

            if (currentClique.size() < 3)
                continue;

            System.out.print("[ ");

            for (String cityName: currentClique)
            {
                System.out.print(cityName + " ");
            }

            System.out.print("]\n");
        }


    }
}