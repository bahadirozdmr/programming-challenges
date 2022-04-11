import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// For this challenge you will determine whether a given set of vertices form a Hamiltonian path on the graph.
/*
have the function HamiltonianPath(strArr) take strArr which will be an array of length three.
The first part of the array will be a list of vertices in a graph in the form (A,B,C,...), the second part of
the array will be the edges connecting the vertices in the form (A-B,C-D,...) where each edge is bidirectional.
The last part of the array will be a set of vertices in the form (X,Y,Z,...) and your program will have to determine whether or not
 the set of vertices given forms a Hamiltonian path on the graph which means that every vertex in the graph is visited only once in the order given.


For example: if strArr is ["(A,B,C,D)","(A-B,A-D,B-D,A-C)","(C,A,D,B)"]
 then the vertices (C,A,D,B) in this order do in fact form a Hamiltonian path on the graph so your program should return the string yes
 . If for example the last part of the array was (D,A,B,C) then this doesn't form a Hamiltonian path because once you get to B you can't get to C in the graph without passing through the visited vertices again. Here your program should return the vertex where the path had to terminate, in this case your program should return B.
The graph will have at least 2 vertices and all the vertices in the graph will be connected.
        */
public class HamiltonianPath {

    public String findPath(String[] strArrays) {
        //getNodes
        var nodeMap=getNodes(strArrays[0]);
        //make connections
        makeConnections(strArrays[1], nodeMap);
        //find path
        var paths=findPath(strArrays[2]);

        for(var i=0;i< paths.length-1;i++) {
            var node=nodeMap.get(paths[i]);
            node.isVisited=true;
            if(!isExists(node,paths[i+1],nodeMap)) {
                return node.getName();
            }
        }
        return "yes";
    }

    private boolean isExists(Node node,String nextPath,HashMap<String,Node> hashMap) {
        for(String connectionName:node.getConnections()) {
            var con=hashMap.get(connectionName);
            if(!con.isVisited()) {
                if(connectionName.equals(nextPath)){
                    return true;
                }
            }
        }
        return false;
    }

    private HashMap<String,Node> getNodes(String nodeStr){
        var nodeMap=new HashMap<String,Node>();
        nodeStr=nodeStr.replace("(","").replace(")","");
        for(String name:nodeStr.split(",")) {
            var node= new Node(name);
            nodeMap.put(name,node);
        }
        return nodeMap;
    }

    private void makeConnections(String conStr,HashMap<String,Node> nodeMap) {
        conStr=conStr.replace("(","").replace(")","");
        for (String con:conStr.split(",")) {
            var conNodes=con.split("-");
            nodeMap.get(conNodes[0]).addConnection(conNodes[1]);
            nodeMap.get(conNodes[1]).addConnection(conNodes[0]);
        }
    }

    private String[] findPath(String pathStr) {
        return pathStr.replace("(","").replace(")","").split(",");
    }

    public static class Node{
        String name;
        public boolean isVisited;
        public List<String> connections;
        public Node(String name) {
            this.name=name;
            this.isVisited=false;
            this.connections=new ArrayList<>();
        }

        public void setVisited(boolean visited) {
            isVisited = visited;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void addConnection(String connection) {
            this.connections.add(connection);
        }

        public List<String> getConnections() {
            return connections;
        }

        public String getName() {
            return name;
        }
    }
}
