import javax.xml.crypto.NodeSetData;

import java.util.*;
public class BinaryTreeYT{

    static class Node{
        int data;
        Node left;
        Node right;

        public Node(){

        }
        public Node( int data){
            this.data=data;
            this.left=null;
            this.right=null;

        }
    }

    static class BinaryTree{

        static int idx=-1;
        public static Node buildTree(int nodes[]){

            idx++;
            if(nodes[idx]==-1){

                return null;
            }

                Node newNode=new Node(nodes[idx]);
                newNode.left=buildTree(nodes);
                newNode.right=buildTree(nodes);

                return newNode;
        }
    }

    public static void preeorder(Node root){

        if(root==null) return ;
        System.out.print(root.data+" ");

        preeorder(root.left);
        preeorder(root.right);

    }
    public static void inorder(Node root){

        if(root==null)return;

        inorder(root.left);
        System.out.print(root.data+" "); 
        inorder(root.right);
    }

    public static void postorder(Node root){
        if(root==null)return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data+" ");

    }

    public static void levelOrder(Node root){
        if(root==null)return ;

        Queue<Node> q=new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()){


            Node currNode=q.remove();

            if(currNode==null){

                System.out.println();

                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }

            }else{
                System.out.print(currNode.data+" ");

                if(currNode.left != null){

                    q.add(currNode.left);
                }
                if(currNode.right != null){

                    q.add(currNode.right);
                }

            }
        }

    }

    public static int countNodes(Node root){

        if(root==null)return 0;
        int leftNode=countNodes(root.left);
        int rightNode=countNodes(root.right);

        return leftNode + rightNode + 1;
    }
    public static int sumOfNodes(Node root){

        if(root==null)return 0;

        int leftSum=sumOfNodes(root.left);
        int rightSum=sumOfNodes(root.right);

        return leftSum + rightSum + root.data;
    }
    public static int hightOfTree(Node root){

        if(root==null) return 0;

        int leftHight=hightOfTree(root.left);
        int rightHight=hightOfTree(root.right);

        return Math.max(leftHight, rightHight)+1;
    }

    public static int diameter(Node root){

        if(root==null)return 0;
        int diam1=diameter(root.left);
        int diam2=diameter(root.right);
        
        int diam3=hightOfTree(root.left)+hightOfTree(root.right) +1;

        return Math.max(Math.max(diam1,diam2),diam3);
    }

    public static class TreeInfo{
        int ht;
        int diam;

        public TreeInfo(int ht,int diam){
            this.ht=ht;
            this.diam=diam;
        }

    }
    public static TreeInfo diameter2(Node root){

        if(root==null){
          return  new TreeInfo(0, 0);
        }
        TreeInfo left=diameter2(root.left);
        TreeInfo right=diameter2(root.right);

        int myHight=Math.max(left.ht,right.ht)+1;

        int diam1=left.diam;
        int diam2=right.diam;
        int diam3=left.ht+right.ht+1;

        int mydiam=Math.max(Math.max(diam1,diam2),diam3);

        TreeInfo myInfo=new TreeInfo(myHight,mydiam);
        return myInfo;

    }

    public static void main (String[] args){
         int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};

        BinaryTree tree= new BinaryTree();

        Node root=tree.buildTree(nodes);
        TreeInfo count=diameter2(root);
        System.out.print(count.diam);
    }
}