package com.example.lab8javafx.graphtheory;

public class Edge
{
    String edgeIdentifier ;
    Node nodeA , nodeB ;

    public Edge (Node nodeA, Node nodeB)
    {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        edgeIdentifier = getIdentifierFromNodes(nodeA, nodeB);
    }

    /**
     * @param nodeA the first node of the edge
     * @param nodeB the last node of the edge
     * @return the name of an edge containing nodes A and B. The method is designed such that Edge(A,B) equals Edge(B,A)
     */
    public static String getIdentifierFromNodes(Node nodeA, Node nodeB)
    {
        String identifierA = nodeA.getNodeIdentifier();
        String identifierB = nodeB.getNodeIdentifier();

        if (identifierA.compareTo(identifierB) > 0)
        {
            String temp = identifierA;
            identifierA = identifierB;
            identifierB = temp;
        }

        return identifierA + "-" + identifierB;
    }

    public String getEdgeIdentifier() {
        return edgeIdentifier;
    }

    public Node getNodeA() {
        return nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }
}
