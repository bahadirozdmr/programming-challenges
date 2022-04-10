import javax.management.NotificationEmitter;
import java.util.*;

// For this challenge you will determine what nodes are farthest apart.
/*
have the function FarthestNodes(strArr) read strArr which will be an array of hyphenated letters representing paths between those two nodes.
For example: ["a-b","b-c","b-d"] means that there is a path from node a to b (and b to a),
b to c, and b to d. Your program should determine the longest path that exists in the graph and return the length of that path.
So for the example above, your program should return 2 because of the paths a-b-c and d-b-c.
The path a-b-c also means that there is a path c-b-a. No cycles will exist in the graph and every node will be connected to some other node in the graph.
*/
public class FarthestNode {

    public int findFarthestNode(String[] strArray) {
        var nodeMap = createGraph(strArray);
        int maxLength = 0;
        for (Node node : nodeMap.values()) {
            var currentLength = calculateLength(node);
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

    int calculateLength(Node node) {
        HashMap<String,Boolean> alreadyVisited=new HashMap<>();
        HashMap<String,Integer> cost=new HashMap<>();
        int length=0;
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(node);
        while (!unsettledNodes.isEmpty()) {
            var currentNode=unsettledNodes.stream().findFirst().get();
            alreadyVisited.put(currentNode.getName(),true);
            unsettledNodes.remove(currentNode);
            for(Node childNode:currentNode.getConnections()) {
                if(!alreadyVisited.containsKey(childNode.getName())) {
                    int newLength = cost.getOrDefault(currentNode.name,0)  +1;
                    cost.put(childNode.name,newLength);
                    length=Math.max(length,newLength);
                    unsettledNodes.add(childNode);
                }
            }
        }
        return length;
    }

    HashMap<String,Node> createGraph(String[] strArray){
        var nodeMap=new HashMap<String,Node>();
        for (String s : strArray) {
            var nodes = s.split("-");
            if (!nodeMap.containsKey(nodes[0])) {
                var node = new Node(nodes[0]);
                nodeMap.put(nodes[0], node);
            }
            if (!nodeMap.containsKey(nodes[1])) {
                var node = new Node(nodes[1]);
                nodeMap.put(nodes[1], node);
            }
            var nodeA = nodeMap.get(nodes[0]);
            var nodeB = nodeMap.get(nodes[1]);
            nodeA.addConnection(nodeB);
            nodeB.addConnection(nodeA);
        }
        return nodeMap;
    }

    static class Node {
        String name;
        Set<Node> connections;
        public Node(String name) {
            this.name=name;
            this.connections=new HashSet<>();
        }

        public String getName() {
            return name;
        }
        public void addConnection(Node node) {
            connections.add(node);
        }
        public Set<Node> getConnections() {
            return connections;
        }
    }
}


