package com.gradescope.assignment1;

import com.gradescope.assignment1.AbstractParenthesisMatching;
import com.gradescope.assignment1.DemoStack;

public class ParenthesisMatching extends AbstractParenthesisMatching {
    public Boolean match_parenthesis(String s){
        /*
         * To be filled in by the student
         * Input: String made up of characters ‘(’, ‘{’, ‘[’, ‘)’, ‘}’, and ‘]’ 
         * Return: Whether input string is a matching parenthesis
         */
        DemoStack bin = new DemoStack();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)== '(' || s.charAt(i)== '{' || s.charAt(i)== '['){
                bin.push(s.charAt(i));
            }
            else{
                if(bin.is_empty()==true){
                    return false;
                }
                else if(s.charAt(i)==')'){
                    if(bin.top()=='('){
                        bin.pop();
                    }
                    else{
                        return false;
                    }
                }
                else if(s.charAt(i)==']'){
                    if(bin.top()=='['){
                        bin.pop();
                    }
                    else{
                        return false;
                    }
                }
                else if(s.charAt(i)=='}'){
                    if(bin.top()=='{'){
                        bin.pop();
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        if(bin.is_empty()==true){
            return true;
        }
        return false;
    }
}
