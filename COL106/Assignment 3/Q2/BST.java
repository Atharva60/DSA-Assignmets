import java.util.*;
public class BST {

    public BSTNode helpinsert(BSTNode node, int num){
        if(node==null){
            node= new BSTNode(num);
            return node;
        }
        if(node.value>num){
            node.left = helpinsert(node.left, num);
        }
        else if(node.value<num){
            node.right = helpinsert(node.right, num);
        }
        node.height=helpheight(node);
        return node;
    }

    public int helpheight(BSTNode node){
        if(node==null){
            return 0;
        }
        else{
            int lh=helpheight(node.left);
            int rh=helpheight(node.right);
            int ans = 1+Math.max(lh, rh);
            return ans;
        }
    }

    public BSTNode helpdelete(BSTNode node, int num){
        if(node==null){
            return null;
        }
        if(this.search(num)==false){
            return null;
        }
        if(node.value<num){
            node.right=helpdelete(node.right, num);
        }
        else if (node.value>num){
            node.left=helpdelete(node.left, num);
        }
        else{
            if(node.right==null){
                return node.left;
            }
            else if(node.left==null){
                return node.right;
            }
            else{
                BSTNode predecessor = node.left;
                while(predecessor.right!=null){
                    predecessor=predecessor.right;
                }
                int inorder_predecessor = predecessor.value;
                node.value=inorder_predecessor;
                node.left=helpdelete(node.left, inorder_predecessor);
            }
        }
        node.height=helpheight(node);
        return node;
    }

    public void helpinorder(BSTNode node, ArrayList<Integer> arr){
        if(node==null){
            return ;
        }
        helpinorder(node.left, arr);
        arr.add(node.value);
        helpinorder(node.right, arr);
    }

    public void helppreorder(BSTNode node, ArrayList<Integer> arr){
        if(node==null){
            return;
        }
        arr.add(node.value);
        helppreorder(node.left, arr);
        helppreorder(node.right, arr);
    }

    public void helppostorder(BSTNode node, ArrayList<Integer> arr){
        if(node==null){
            return ;
        }
        helppostorder(node.left, arr);
        helppostorder(node.right, arr);
        arr.add(node.value);
    }

    public BSTNode root;

    public BST() {
        root = null;
    }
    public void insert(int num) {
        // TO be completed by students
        this.root=helpinsert(this.root, num);
    }

    public boolean delete(int num) {
        // TO be completed by students
        BSTNode temp = this.root;
        temp = helpdelete(temp, num);
        if(temp==null){
            return false;
        }
        return true;
    }

    public boolean search(int num) {
        // TO be completed by students
        BSTNode temp = this.root;
        if(temp==null){
            return false;
        }
        while(temp!=null){
            if(temp.value==num){
                return true;
            }
            else{
                if(temp.value> num){
                    temp = temp.left;
                }
                else{
                    temp = temp.right;
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> inorder() {
        // TO be completed by students
		ArrayList<Integer> al = new ArrayList<>();
        helpinorder(this.root, al);
		return al;
    }
    public ArrayList<Integer> preorder() {
        // TO be completed by students
		ArrayList<Integer> al = new ArrayList<>();
        helppreorder(this.root, al);
		return al;
    }
    public ArrayList<Integer> postorder() {
        // TO be completed by students
		ArrayList<Integer> al = new ArrayList<>();
        helppostorder(this.root, al);
		return al;
    }
}