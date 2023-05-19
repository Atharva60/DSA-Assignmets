package com.gradescope.assignment1;

import com.gradescope.assignment1.AbstractFreqOfLetters;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FreqOfLetters extends AbstractFreqOfLetters {
    public Integer[] count_letters(String fname) throws FileNotFoundException, IOException {
        /*
         * To be filled in by the student
         * Input: File name
         * Return: Array of 26 length containing freq of characters present in the file
         * Note: Ignore ' ' and EOF characters from input file
         */
        BufferedReader br = new BufferedReader(new FileReader(fname));
        String s;
        Integer[] result = new Integer[26];
        for(int i=0;i<26;i++){
            result[i]=0;
        }
        while ((s = br.readLine()) != null){
            for(int i=0;i<s.length();i++){
                if((int)s.charAt(i)>=97 && (int)s.charAt(i)<=122){
                    result[(int)s.charAt(i)-97]++;
                }
            }
        }
       
        return result;
    }
}
