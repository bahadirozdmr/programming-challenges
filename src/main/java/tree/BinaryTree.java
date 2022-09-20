package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    Node root;

    public void printAllLevel(){
        Queue<Node> queue=new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node tempNode=queue.poll();
            System.out.println(tempNode.data);
            if(tempNode.left!=null) {
                queue.add(tempNode.left);
            }
            if(tempNode.right!=null)
                queue.add(tempNode.right);
        }
    }

    public void createBinaryTree(){
        BinaryTree tree_level = new BinaryTree();
        tree_level.root = new Node(1);
        tree_level.root.left = new Node(2);
        tree_level.root.right = new Node(3);
        tree_level.root.left.left = new Node(4);
        tree_level.root.left.right = new Node(5);

        System.out.println("Level order traversal of binary tree is - ");
                tree_level.printAllLevel();
    }

}

