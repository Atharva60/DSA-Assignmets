package com.gradescope.assignment1;

import java.util.EmptyStackException;

import com.gradescope.assignment1.AbstractDemoStack;

public class DemoStack extends AbstractDemoStack {
    Character[] base;
    int index=0;

    public DemoStack(){
        super();
        base = new Character[10];
       
    }
    public void push(Character i){
        /*
         * To be filled in by the student
         * Input: Character to be inserted onto top of stack
         */
        if(index==base.length){
            Character[] newarr=new Character[2*index];
            for(int j=0;j<index;j++){
                newarr[j]=base[j];
            }
            base=newarr;
        }
        base[index]=i;
        index++;
    }

    public Character pop() throws EmptyStackException{
        /*
         * To be filled in by the student
         * Return: Character present at the top of the stack
         */
        if(is_empty()) throw new EmptyStackException();
            else {char a =base[index-1];
            // base[index-1]=null;
            index--;
           
    
        if(index==(base.length)/4 && base.length >40){
            Character[] newarr=new Character[base.length/2];
            for(int j=0;j<index;j++){
                newarr[j]=base[j];
            }
            base=newarr;
        }
        return a;}
    }
    
    public Boolean is_empty(){
        /*
         * To be filled in by the student
         * Return: Stack is empty or not
         */
        if(index<=0){
            return true;
        }else{
            return false;
        }
    }
    
    public Integer size(){
        /*
         * To be filled in by the student
         * Return: Number of elements which are present in stack
         */
        return index;
    }
    
    public Character top() throws EmptyStackException{
        /*
         * To be filled in by the student
         * Return: Character present at the top of the stack
         */
        if(is_empty()) throw new EmptyStackException();
        else
       return base[index-1];
    }

    public Character[] return_base_array(){
       /*
        * To be filled in the by the student
        * Return: Return reference to the base array storing the elements of stack
        */
        return base;
    } 

// public static void main(String[] args){
//     DemoStack s = new DemoStack();
//     int n=1;
//     for(int i=0;i<=n;i++)
//     {
//     s.push('a');}
    
//     int m=0;
//     for(int j=0;j<=m;j++)
//     {
//     System.out.println(s.pop());}
//     // System.out.println(s.size());
   
// }
 }