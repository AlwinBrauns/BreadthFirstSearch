package com.company;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        ArrayList<GraphNode> graph = new ArrayList<>();
        initGraph(graph);
        //Breadth-first Search \/
        final int from = 0;
        ArrayList<GraphNode> reachableNodes = BST(graph,graph.get(from));
        reachableNodes.forEach(
                graphNode ->
                        System.out.println(
                                "From node " + from + " node " + graphNode.getId() + " is reachable")
        );
    }

    private static ArrayList<GraphNode> BST(ArrayList<GraphNode> graph, GraphNode startingFrom) {
        Queue<GraphNode> queue = new LinkedList<>();
        ArrayList<Mark<GraphNode>> marked = new ArrayList<>();
        //Mark every graphNode as not discovered yet (false)
        for(int i = 0; i < graph.size(); i++){
            marked.add(new Mark<>(graph.get(i),false));
        }
        //Start with Node 0 and mark it as discovered
        queue.add(startingFrom);
        marked.get(startingFrom.getId()).setMarked(true);
        //Discover as long queue not empty
        while (!queue.isEmpty()){
            GraphNode dequedNode = queue.remove();
            for (GraphNode neighbour:dequedNode.getConnected()) {
                if(!marked.get(neighbour.getId()).isMarked()){
                    queue.add(neighbour);
                    marked.get(neighbour.getId()).setMarked(true);
                }
            }
        }
        ArrayList<GraphNode> markedGraphNodes = new ArrayList<>();
        for (Mark<GraphNode> markedGraphNode:marked) {
            markedGraphNodes.add(markedGraphNode.getElement());
        }
        return markedGraphNodes;
    }


    private static void initGraph(ArrayList<GraphNode> graph) {
        //Creating an example Graph with 8 Nodes (0,1,2,3,4,5,6,7)
        for(int i = 0; i < 8; i++)
        {
            graph.add(new GraphNode(i));
        }
        graph.get(0).setConnected(new GraphNode[]{
                graph.get(1),
                graph.get(6),
                graph.get(7)
        });
        graph.get(1).setConnected(new GraphNode[]{
                graph.get(2),
                graph.get(3),
                graph.get(4),
                graph.get(7)
        });
        graph.get(2).setConnected(new GraphNode[]{
                graph.get(1),
                graph.get(5),
        });
        graph.get(3).setConnected(new GraphNode[]{
                graph.get(0),
                graph.get(1),
                graph.get(5),
        });
        graph.get(4).setConnected(new GraphNode[]{
                graph.get(1),
                graph.get(7),
        });
        graph.get(5).setConnected(new GraphNode[]{
                graph.get(2),
                graph.get(3),
        });
        graph.get(6).setConnected(new GraphNode[]{
                graph.get(0),
        });
        graph.get(7).setConnected(new GraphNode[]{
                graph.get(0),
                graph.get(1),
                graph.get(4),
        });
    }
}
