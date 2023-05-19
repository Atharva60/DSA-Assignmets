public class LinkedList{ 
    
    public Node head;
    
    public LinkedList(){
        head = null;
    }

    public void insert(int c){
        //to be completed by the student
        Node tail = new Node(c);
        if(head==null){
            head=tail;
            return;
        }
        else{
            Node temp = head;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=tail;
        }
    }

    public int len(){
        //to be completed by the student
        Node temp = head;
        int length = 0;
        while(temp!=null){
            length++;
            temp=temp.next;
        }
        return length;
    }
    public void display(){
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
    }
}


