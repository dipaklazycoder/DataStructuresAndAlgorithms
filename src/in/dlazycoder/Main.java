package in.dlazycoder;

import in.dlazycoder.trees.BinaryTreeTraversal;
import in.dlazycoder.trees.Node;
import in.dlazycoder.trees.TraverseType;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        BinaryTreeTraversal binaryTreeTraversal = new BinaryTreeTraversal();
        Node root = binaryTreeTraversal.createRandomBinaryTree(null, 5, 3);
        System.out.println("Inorder Traversal:  ");
        int[]in= binaryTreeTraversal.traverseTree(root, TraverseType.INORDER);
        System.out.println("\nPreorder Traversal:  ");
        int[] pre = binaryTreeTraversal.traverseTree(root,TraverseType.PREORDER);
        System.out.println("\nPostorder Traversal:  ");
        binaryTreeTraversal.traverseTree(root, TraverseType.POSTORDER);

        root = binaryTreeTraversal.buildTreeUsingInorderPreorder(in,pre,0,in.length-1);
        System.out.println("\nNew Inorder Traversal:  ");
        binaryTreeTraversal.traverseTree(root, TraverseType.INORDER);

        System.out.println("\nLevelorder Traversal:  ");
        binaryTreeTraversal.traverseTree(root, TraverseType.LEVELORDER);
        Node searched = binaryTreeTraversal.searchNodeByKey(root, 3);
        int[] kthNodes = binaryTreeTraversal.findKthNodeForGivenKey(root, 19, 2);


    }
}
