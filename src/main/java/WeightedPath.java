import java.util.*;

public class WeightedPath {
// For this challenge you will have to determine the shortest weighted path from one node to an end node.
/*
have the function WeightedPath(strArr) take strArr which will be an array of strings which models a non-looping weighted Graph. The structure of the array will be as follows: The first element in the array will be the number of nodes N (points) in the array as a string. The next N elements will be the nodes which can be anything (A, B, C .. Brick Street, Main Street .. etc.). Then after the Nth element, the rest of the elements in the array will be the connections between all of the nodes along with their weights (integers) separated by the pipe symbol (|). They will look like this: (A|B|3, B|C|12 .. Brick Street|Main Street|14 .. etc.). Although, there may exist no connections at all.
An example of strArr may be: ["4","A","B","C","D","A|B|1","B|D|9","B|C|3","C|D|4"]. Your program should return the shortest
path when the weights are added up from node to node from the first Node to the last Node in the array separated by dashes.
So in the example above the output should be A-B-C-D.
****
Here is another example with strArr being ["7","A","B","C","D","E","F","G","A|B|1","A|E|9","B|C|2","C|D|1","D|F|2","E|D|6","F|G|2"].
The output for this array should be A-B-C-D-F-G.
There will only ever be one shortest path for the array. If no path between the first and last node exists, return -1.
 The array will at minimum have two nodes. Also, the connection A-B for example, means that A can get to B and B can get to A. A path may not go through any Node more than once.
*/


    List<Node> findMinimumPath(String[] strArray) {
        var nodeList = new HashMap<String, Node>();
        var nodeLength = Integer.parseInt(strArray[0]);

        for (var i = 1; i <= nodeLength; i++) {
            var nodeKey = strArray[i];
            var node = new Node(nodeKey);
            nodeList.put(nodeKey, node);
        }
        for (var i = nodeLength + 1; i < strArray.length; i++) {
            var collection = getCollection(strArray[i]);
            if (nodeList.containsKey(collection[0])) {
                var node = nodeList.get(collection[0]);
                var connectionNode = nodeList.get(collection[1]);
                node.addConnections(connectionNode, Integer.parseInt(collection[2]));
            }
        }
        var source = nodeList.get(strArray[1]);
        var destination = nodeList.get(strArray[nodeLength]);
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            var currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (var connection : currentNode.getConnections().entrySet()) {
                var node = connection.getKey();
                var distance = connection.getValue();
                if (!settledNodes.contains(node)) {
                    calculateMinimumDistance(node, distance, currentNode);
                    unsettledNodes.add(node);
                }
            }
            settledNodes.add(currentNode);
        }
        for (Node node : settledNodes) {
            if (node.getName().equals(destination.getName())) {
                return node.getShortestPath();
            }
        }

        return null;
    }

    void calculateMinimumDistance(Node connectionNode,Integer distance,Node sourceNode) {
        //currentNode -> a
        //connectionNode ->b
        Integer sourceDistance=sourceNode.getDistance();
        if(sourceDistance+distance<connectionNode.getDistance()) {
            connectionNode.setDistance(sourceDistance+distance);
            LinkedList<Node> shortestPath=new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            connectionNode.setShortestPath(shortestPath);
        }
    }

    Node getLowestDistanceNode(Set<Node> unsettedNodes) {
        Node lowestDistanceNode=null;
        var lowestDistance=Integer.MAX_VALUE;
        for(Node node:unsettedNodes) {
            int nodeDistance=node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    String[] getCollection(String collectionBulk) {
        return collectionBulk.split("\\|");
    }


}
class Node {
    private final String name;
    private Integer distance =Integer.MAX_VALUE;
    private final HashMap<Node,Integer> connections;
    private List<Node> shortestPath = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void addConnections(Node destination, int distance) {
        connections.put(destination,distance);
    }
    public HashMap<Node,Integer> getConnections()
    {
        return this.connections;
    }
    public void setDistance(int distance) {
        this.distance=distance;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public Node(String name) {
        this.name=name;
        this.connections=new HashMap<>();
    }
}



