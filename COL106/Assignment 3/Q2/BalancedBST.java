public class BalancedBST extends BST {

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
        int dif = diff(node);
        if(dif>1){
            if(num>node.left.value){
                node.left = leftrotation(node.left);
                return rightrotation(node);
            }
            else{
                return rightrotation(node);
            }
        }
        else if(dif<-1){
            if(num>node.right.value){
                return leftrotation(node);
            }
            else{
                node.right = rightrotation(node.right);
                return leftrotation(node);
            }
        }
        return node;
    }

    public int diff(BSTNode node){
        if(node==null){
            return 0;
        }
        return helpheight(node.left) - helpheight(node.right);
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

    public BSTNode rightrotation(BSTNode node){
        BSTNode temp = node.left;
        node.left = temp.right;
        temp.right = node;
        node.height = helpheight(node);
        temp.height = helpheight(temp);
        return temp;
    }

    public BSTNode leftrotation(BSTNode node){
        BSTNode temp = node.right;
        node.right = temp.left;
        temp.left = node;
        node.height = helpheight(node);
        temp.height = helpheight(temp);
        return temp;
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
        int dif = diff(node);
        if(dif>1){
            if(diff(node.left) < 0){
                node.left = leftrotation(node.left);
                return rightrotation(node);
            }
            else{
                return rightrotation(node);
            }
        }
        else if(dif<-1){
            if(diff(node.right)<=0){
                return leftrotation(node);
            }
            else{
                node.right = rightrotation(node.right);
                return leftrotation(node);
            }
        }
        return node;
    }

    public void insert(int key){
        // TO be completed by students
        this.root=helpinsert(this.root, key);
    }

    public boolean delete(int key){
        // TO be completed by students
        BSTNode temp = this.root;
        temp = helpdelete(temp, key);
        if(temp==null){
            return false;
        }
        return true;
    }
}
