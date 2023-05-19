import java.util.ArrayList;
import java.util.Collections;

public class SkipList {

        public SkipListNode head;
        public SkipListNode tail;
        public int height;
        public Randomizer randomizer;
        private final int NEG_INF = Integer.MIN_VALUE;
        private final int POS_INF = Integer.MAX_VALUE;

        SkipList(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            this.head = new SkipListNode(NEG_INF,1);
            this.tail = new SkipListNode(POS_INF,1);
            this.head.next.add(0,this.tail);
            this.tail.next.add(0,null);
            this.height = 1;
            this.randomizer = new Randomizer();
        }

        public boolean delete(int num){
            // TO be completed by students
            SkipListNode[] arr = new SkipListNode[this.height];
            SkipListNode n = this.head;
            for(int i = this.height ; i>=1; i--){
                while(n.next.get(i-1)!=null &&n.next.get(i-1).value<num){
                    n=n.next.get(i-1);
                }
                arr[i-1]=n;
            }
            n=n.next.get(0);
            if(n.value!=num) return false;
            else{
                for(int i = 0; i < this.height; i++) {
                    if(arr[i].next.get(i) == n) {
                        arr[i].next.set(i, n.next.get(i));
                    }
                }
                while(this.height > 1 && this.head.next.get(this.height - 1) == this.tail) {
                    this.head.next.remove(this.height - 1);
                    this.tail.next.remove(this.height - 1);
                    this.height--;
                }
                return true;
            }
        }

        public boolean search(int num){
            // TO be completed by students
            SkipListNode n = this.head;
            for(int i = this.height ; i>=1; i--){
                while(n.next.get(i-1).value<=num){
                    n=n.next.get(i-1);
                }
            }
            if(n.value==num){
                return true;
            }
            else{
                return false;
            }
        }

        public Integer upperBound(int num){ 
            // TO be completed by students            
            SkipListNode n = this.head;
            for(int i = this.height ; i>=1; i--){
                while(n.next.get(i-1).value<=num){
                    n=n.next.get(i-1);
                }
            } 
            if(n.next.get(0)==this.tail) return Integer.MAX_VALUE; 
            else return n.next.get(0).value;
        }

        public void insert(int num){
            // TO be completed by students
            SkipListNode[] arr = new SkipListNode[this.height];
            SkipListNode n = this.head;
            for(int i = this.height ; i>=1; i--){
                while(n.next.get(i-1)!=null &&n.next.get(i-1).value<num){
                    n=n.next.get(i-1);
                }
                arr[i-1]=n;
            }
            n=n.next.get(0);
            int new_height = 1;
            while(new_height< this.height+1){
                if(randomizer.binaryRandomGen()==true){
                    new_height++;
                }
                else{
                    break;
                }
            }
            if(new_height>this.height){
                // this.height++;
                this.head.next.add(this.height,this.tail);
                this.tail.next.add(this.height,null);
                SkipListNode[] arr2=new SkipListNode[this.height+1];
                for(int x=0;x<this.height;x++){
                    arr2[x]=arr[x];
                }
                this.height++;
                head.height++;
                tail.height++;
                arr2[this.height-1]=this.head;
                arr=new SkipListNode[this.height+1];
                arr=arr2;
            }
            SkipListNode p = new SkipListNode(num, new_height);
            for(int i=0;i<new_height;i++){
                
                p.next.add(arr[i].next.get(i));
                arr[i].next.set(i, p);
            }
        }

        public void print(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            for(int i = this.height ; i>=1; --i){
                SkipListNode it = this.head;
                while(it!=null){
                    if(it.height >= i){
                        System.out.print(it.value + "\t");
                    }
                    else{
                        System.out.print("\t");
                    }
                    it = it.next.get(0);
                }
                System.out.println("null");
            }
        }
}