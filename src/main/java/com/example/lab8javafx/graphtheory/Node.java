package com.example.lab8javafx.graphtheory;

import java.util.ArrayList;

public interface Node
{
    String getNodeIdentifier();
    ArrayList<Node> getNeighbours();
}
