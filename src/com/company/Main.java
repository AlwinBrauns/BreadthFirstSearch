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
        ArrayList<GraphNode> reachableNodesOfBST = BST(graph,graph.get(from));
        reachableNodesOfBST.forEach(
                graphNode ->
                        System.out.println(
                                "(BST) From node " + from + " to node " + graphNode.getId() + " is reachable")
        );
        if(reachableNodesOfBST.size() == graph.size())
        {
            System.out.println("(BST) Every node is reachable");
        }

        //Depth-first Search \/
        ArrayList<GraphNode> reachableNodesOfDFS = DFS(graph, graph.get(from));
        reachableNodesOfDFS.forEach(
                graphNode ->
                        System.out.println(
                                "(DFS) From node " + from + " to node " + graphNode.getId() + " is reachable")
        );
    }
    private static ArrayList<GraphNode> DFS(ArrayList<GraphNode> graph, GraphNode startingFrom)
    {
        ArrayList<Mark<GraphNode>> marked = new ArrayList<>();
        ArrayList<GraphNode> markedGraphNodes = new ArrayList<>();
        initMarkedGraphNodes(graph, marked);
        DFS_RECURSIVE(startingFrom, marked);
        getMarkedElements(marked,markedGraphNodes);
        return markedGraphNodes;
    }

    private static void DFS_RECURSIVE(GraphNode current, ArrayList<Mark<GraphNode>> marked) {
        marked.get(current.getId()).setMarked(true);
        System.out.println("(DFS) Found: " + current.getId());
        for (GraphNode neighbour:current.getConnected()) {
            if(!marked.get(neighbour.getId()).isMarked()){
                DFS_RECURSIVE(neighbour,marked);
            }
        }
    }

    private static void initMarkedGraphNodes(ArrayList<GraphNode> graph, ArrayList<Mark<GraphNode>> marked) {
        //Mark every graphNode as not discovered yet (false)
        for (GraphNode graphNode : graph) {
            marked.add(new Mark<>(graphNode, false));
        }
    }

    private static ArrayList<GraphNode> BST(ArrayList<GraphNode> graph, GraphNode startingFrom) {
        Queue<GraphNode> queue = new LinkedList<>();
        ArrayList<Mark<GraphNode>> marked = new ArrayList<>();
        ArrayList<GraphNode> markedGraphNodes = new ArrayList<>();
        initMarkedGraphNodes(graph, marked);
        //Start with Node 0 and mark it as discovered
        queue.add(startingFrom);
        marked.get(startingFrom.getId()).setMarked(true);
        System.out.println("(BST) Starting point: " + startingFrom.getId());
        //Discover as long queue not empty
        while (!queue.isEmpty()){
            GraphNode dequedNode = queue.remove();
            for (GraphNode neighbour:dequedNode.getConnected()) {
                if(!marked.get(neighbour.getId()).isMarked()){
                    queue.add(neighbour);
                    marked.get(neighbour.getId()).setMarked(true);
                    System.out.println("(BST) Found: " + neighbour.getId());
                }
            }
        }
        getMarkedElements(marked, markedGraphNodes);
        return markedGraphNodes;
    }

    private static void getMarkedElements(ArrayList<Mark<GraphNode>> marked, ArrayList<GraphNode> markedGraphNodes) {
        for (Mark<GraphNode> markedGraphNode: marked) {
            markedGraphNodes.add(markedGraphNode.getElement());
        }
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
