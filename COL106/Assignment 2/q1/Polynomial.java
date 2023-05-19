public class Polynomial extends LinkedList{
    
    public Polynomial add(Polynomial p){
        Polynomial sum = new Polynomial();
        //Polynomial new_sum = new Polynomial();
        Node p1 = this.head;
        Node p2 = p.head;
        int d1 = this.len();
        int d2 = p.len();
        if(d1>d2){
            Node temp1 = p1;
            Node temp2 = p2;
            while(d1!=d2){
                sum.insert(temp1.data);
                temp1=temp1.next;
                d1--;
            }
            while(temp2!=null){
                sum.insert(temp1.data+temp2.data);
                temp1=temp1.next;
                temp2=temp2.next;
            }
        }
        else if(d1<d2){
            Node tempa = p1;
            Node tempb = p2;
            while(d2!=d1){
                sum.insert(tempb.data);
                tempb=tempb.next;
                d2--;
            }
            while(tempb!=null){
                sum.insert(tempb.data+tempa.data);
                tempa=tempa.next;
                tempb=tempb.next;
            }
        }
        else{
            Node x = p1;
            Node y = p2;
            while(x!=null){
                sum.insert(x.data+y.data);
                x=x.next;
                y=y.next;
            }
        }
        
        if(sum.head.data==0){
            // Node a =sum.head;
            while(sum.head.data==0 && sum.head.next!=null){
                //System.out.println("kjfbdk");
                //System.out.println(sum.head.data);
                sum.head=sum.head.next;
            } //System.out.println(l);
            // if(sum.len()==0){
            //     System.out.println("dshd");
            //     sum.insert(0);
            // }
        }
        return sum;
    }

    public Polynomial mult(Polynomial p){
        //to be implemented by the student
        Node p1 = this.head;
        Node p2 = p.head;
        int d1 = this.len();
        int d2 = p.len();
        int n = d1+d2-1;
        Polynomial ans = new Polynomial();
        for(int i=0;i<n;i++){
            ans.insert(0);
        }
        Node temp = ans.head;
        Node pow = ans.head;
        Node x = p1;
        Node y = p2;
        while(d2!=0){
            while(d1!=0){
                temp.data=temp.data+(x.data*y.data);
                x=x.next;
                d1--;
                temp=temp.next;
            }
            x=p1;
            pow=pow.next;
            y=y.next;
            d2--;
            temp=pow;
            d1=this.len();
        }
        if(ans.head.data==0){
            // Node a =sum.head;
            while(ans.head.data==0 && ans.head.next!=null){
                //System.out.println("kjfbdk");
                //System.out.println(sum.head.data);
                ans.head=ans.head.next;
            } //System.out.println(l);
            // if(sum.len()==0){
            //     System.out.println("dshd");
            //     sum.insert(0);
            // }
        }
        // if(ans.head.data==0){
        //     Polynomial new_sum = new Polynomial();
            
        //     while(ans.head.data==0){
        //         ans.head=ans.head.next;
        //     }
        //     if(ans.head==null){
        //         new_sum.insert(0);
        //         return new_sum;
        //     }
        // }
        return ans; 
    }
    // public static void main(String[] args) {
    //     Polynomial l1 = new Polynomial();
    //     l1.insert(1);
    //     l1.insert(-1);
    //     l1.insert(3);
    //     Polynomial l2 = new Polynomial();
    //     l2.insert(0);
    //     //l2.insert(1);
    //     //l2.insert(-3);
    //     Polynomial sum = l1.add(l2);
    //     System.out.println(sum.len());
    //     Polynomial multiply=l1.mult(l2);
    //     sum.display();
    //     System.out.println(" ");
    //     multiply.display();
    // }
}
