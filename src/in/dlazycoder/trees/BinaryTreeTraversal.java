package in.dlazycoder.trees;

import java.util.*;

public class BinaryTreeTraversal {
    List<Integer> result = new ArrayList<>();
    Queue<Node> queue = new LinkedList<>();
    Random random = new Random();
    public static int pIndex=0;
    Node root;
    public Node createRandomBinaryTree(Node root, int data, int height) {

        if (height-- > 0) {
            Node node = new Node();
            node.data = data;
            root = node;
            root.left = createRandomBinaryTree(root.left, random.nextInt(25), height);
            root.right = createRandomBinaryTree(root.right, random.nextInt(25), height);
        }
        return root;
    }

    public int[] traverseTree(Node root, TraverseType traverseType) {
        if(root == null)
            return null;
        clear();
        int a[]=null;
        switch (traverseType) {
            case INORDER:a = inorderTraversal(root);
                break;
            case PREORDER:a = preorderTraversal(root);
                break;
            case POSTORDER:a = postorderTraversal(root);
            break;
            case LEVELORDER:
                queue.clear();
                queue.add(root);
                a = levelorderTraversal(root);
        }
        clear();
        return a;
    }

    public Node searchNodeByKey(Node root, int key) {
        if(root == null)
            return null;
        if(root.data  == key)
            return root;
       Node temp = searchNodeByKey(root.left, key);
       if(temp!=null)
           return temp;
       temp = searchNodeByKey(root.right, key);
       if(temp!=null)
           return temp;
        return temp;
    }

    public int[] findKthNodeForGivenKey(Node root, int key, int k){
        Node start = searchNodeByKey(root, key);
        if(start == null)
            return null;
        HashMap<Node,Node> parentMap = new HashMap<>();
        populateMap(root, null, parentMap);
        HashSet<Node> seen = new HashSet<>();
        queue.add(start);
        int current = 0;
        while (!queue.isEmpty()) {

            if (current == k) {
                extractQueue(queue);
            }
            // process current layer
            int layerSize = queue.size();
            while (layerSize-- > 0) {

                Node currentNode = queue.remove();

                //add left child
                if (currentNode.left != null && !seen.contains(currentNode.left)) {
                    seen.add(currentNode.left);
                    queue.add(currentNode.left);
                }
                //add right child
                if (currentNode.right != null && !seen.contains(currentNode.right)) {
                    seen.add(currentNode.right);
                    queue.add(currentNode.right);
                }

                //add parent
                Node parentNode = parentMap.get(currentNode);
                if (parentNode != null && !seen.contains(parentNode)) {
                    seen.add(parentNode);
                    queue.add(parentNode);
                }
            }
            current++;
        }
        return null;
    }

    private int[] extractQueue(Queue<Node> queue) {
       return queue.stream().mapToInt(x->x.data).toArray();
    }

    private void populateMap(Node root, Node parent, HashMap<Node, Node> parentMap) {
        if(root == null)
            return;
        parentMap.put(root, parent);
        populateMap(root.left, root,parentMap);
        populateMap(root.right, root,parentMap);
    }

    private int[] levelorderTraversal(Node root) {
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.data);
            result.add(node.data);
            if(node.left!=null)
                queue.add(node.left);
            if(node.right!=null)
                queue.add(node.right);
        }
        return convertToArray(result);
    }

    private int[] inorderTraversal(Node root) {
        if(root==null) {
            return null;
        }
        inorderTraversal(root.left);
        System.out.print(root.data);
        result.add(root.data);
        inorderTraversal(root.right);
        return convertToArray(result);
    }
    private int[] preorderTraversal(Node root) {

        if(root==null) {
            return null;
        }
        System.out.print(root.data);
        result.add(root.data);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return convertToArray(result);
    }
    private int[] postorderTraversal(Node root) {

        if(root==null) {
            return null;
        }
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        System.out.print(root.data);
        result.add(root.data);
        return convertToArray(result);
    }

    public Node buildTreeUsingInorderPreorder(int[] inorder, int[]preorder, int start, int end) {
        if(start>end)
            return null;


        Node node = buildNode(preorder[pIndex]);
        pIndex++;
        if(start == end)
            return node;

        int nextNodePosition = searchNode(inorder,node.data, start, end);
        node.left = buildTreeUsingInorderPreorder(inorder, preorder, start, nextNodePosition-1);
        node.right = buildTreeUsingInorderPreorder(inorder, preorder, nextNodePosition+1, end);
        return node;

    }

    private int searchNode(int[] inorder, int data, int start, int end) {
        while (start<end) {
            if(inorder[start]==data)
                return start;
            start++;
        }
        return -1;
    }

    private Node buildNode(int i) {
        Node node = new Node();
        node.data = i;
        return node;
    }

    private int[] convertToArray(List<Integer> result) {
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void clear(){
        result.clear();
    }

}



