public class PointQuadtree {

    enum Quad {
        NW,
        NE,
        SW,
        SE
    }

    public PointQuadtreeNode root;

    public PointQuadtree() {
        this.root = null;
    }
    ///This is the insert function:
    public PointQuadtreeNode helpinsert(PointQuadtreeNode node, CellTower a){
        if(node==null){
            node = new PointQuadtreeNode(a);
            return node;
        }
        if(cellTowerAt(a.x,a.y)==true){
            return null;
        }
        else{
            int x1 = node.celltower.x;
            int y1 = node.celltower.y;
            int x2 = a.x;
            int y2 = a.y;
            if(x2>x1){
                if(y2>y1){
                    node.quadrants[1]=helpinsert(node.quadrants[1],a);
                }
                else if(y1<y2){
                    node.quadrants[3]=helpinsert(node.quadrants[3],a);
                }
                else{
                    node.quadrants[3]=helpinsert(node.quadrants[3],a);
                }
            }
            else if(x2<x1){
                if(y2>y1){
                    node.quadrants[0]=helpinsert(node.quadrants[0],a);
                }
                else if(y1<y2){
                    node.quadrants[2]=helpinsert(node.quadrants[2],a);
                }
                else{
                    node.quadrants[0]=helpinsert(node.quadrants[0],a);
                }
            }
            else{
                if(y2>y1){
                    node.quadrants[1]=helpinsert(node.quadrants[1],a);
                }
                else if(y1<y2){
                    node.quadrants[2]=helpinsert(node.quadrants[2],a);
                }
                else{
                    return null;
                }
            }
        }
        return node;
    }

    public boolean insert(CellTower a) {
        // TO be completed by students
        PointQuadtreeNode temp = this.root; 
        temp = helpinsert(temp, a);
        if(temp==null){
            return false;
        }
        else{
            this.root=temp;
            return true;
        }
    }

    /// This is the search function:

    public boolean helpsearch(PointQuadtreeNode node, int p, int q){
        if(node==null){
            return false;
        }
        else if(node.celltower.x==p && node.celltower.y==q){
            return true;
        }
        boolean ans=false;
            int x1 = node.celltower.x;
            int y1 = node.celltower.y;
            int x2 = p;
            int y2 = q;
            if(x2>x1){
                if(y2>y1){
                    ans=helpsearch(node.quadrants[1],p,q);
                }
                else if(y1<y2){
                    ans=helpsearch(node.quadrants[3],p,q);
                }
                else{
                    ans=helpsearch(node.quadrants[3],p,q);
                }
            }
            else if(x2<x1){
                if(y2>y1){
                    ans=helpsearch(node.quadrants[0],p,q);
                }
                else if(y1<y2){
                    ans=helpsearch(node.quadrants[2],p,q);
                }
                else{
                    ans=helpsearch(node.quadrants[0],p,q);
                }
            }
            else{
                if(y2>y1){
                    ans=helpsearch(node.quadrants[1],p,q);
                }
                else if(y1<y2){
                    ans=helpsearch(node.quadrants[2],p,q);
                }
            }
        
        return ans;
    }

    public boolean cellTowerAt(int x, int y) {
        // TO be completed by students
        return helpsearch(this.root, x,y);
    }

    public PointQuadtreeNode helpchoose(PointQuadtreeNode node,int x, int y, int r){
        if(node==null){
            return null;
        }
        PointQuadtreeNode max = null;
        if(node.celltower.distance(x, y)<=r ){
            if(node.celltower.cost<=max.celltower.cost ){
                max=node;
            }
        }
        
        return node;
    }

    public CellTower chooseCellTower(int x, int y, int r) {
        // TO be completed by students
        
        return null;
    }
    public static void main(String[] args) {
        PointQuadtree obj = new PointQuadtree();
        CellTower c1 = new CellTower(0,0,5);
        CellTower c2 = new CellTower(-2,0,4);
        CellTower c3 = new CellTower(2,3,10);
        CellTower c4 = new CellTower(-4,6,9);
        
        System.out.println(obj.insert(c1));
        System.out.println(obj.insert(c2));
        System.out.println(obj.insert(c3));
        System.out.println(obj.cellTowerAt(0,0));
    }
}
