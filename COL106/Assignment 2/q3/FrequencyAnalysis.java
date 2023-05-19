import java.util.ArrayList;
import java.util.Dictionary;

import Includes.*;

public class FrequencyAnalysis {
    
    //sizes of hash-tables are updated
    static final int[] hashTableSizes = {173, 6733, 100003};
    COL106Dictionary<String, Integer> dict1 = new COL106Dictionary<String, Integer>(hashTableSizes[0]);
    COL106Dictionary<String, Integer> dict2 = new COL106Dictionary<String, Integer>(hashTableSizes[1]);
    COL106Dictionary<String, Integer> dict3 = new COL106Dictionary<String, Integer>(hashTableSizes[2]);

    void fillDictionaries(String inputString) throws NullKeyException, KeyAlreadyExistException, KeyNotFoundException {
        /*
         * To be filled in by the student
         */
        System.out.println("fill");
        String f="";
        inputString=inputString.toLowerCase();
        int n = inputString.length();
        for(int i=0;i<inputString.length();i++){
            int ascii=(int)inputString.charAt(i);
            if((ascii>=97 && ascii<=122 || ascii>=65 && ascii<=90) && i!=n-1){
                f+=inputString.charAt(i);
            }
            else if(ascii==32 || i==n-1){
                if(f.length()!=0){
                if(dict3.if_present(f)){
                    Integer value = dict3.get(f);
                    value=value+1;

                    dict1.update(f, value);
                    dict2.update(f, value);
                    dict3.update(f, value);
                }
                else{
                dict1.insert(f, 1);
                dict2.insert(f, 1);
                dict3.insert(f, 1);
                }
                f="";
                }
            }
        }
    }
    
    long[] profile() {
        /*
         * To be filled in by the student
         */
        return new long[4];
    }

    int[] noOfCollisions() {
        /*
         * To be filled in by the student
         */
        System.out.println("collisions");
        int[] ans = new int[3];
        
        int ans1=0;
        for(int i=0;i<dict1.hashTable.length;i++){
            if(dict1.hashTable[i]!=null){
            int temp_size=dict1.hashTable[i].size();
            ans1=ans1+temp_size-1;
            }
        }

        int ans2=0;
        for(int i=0;i<dict2.hashTable.length;i++){
            if(dict2.hashTable[i]!=null){
            int temp_size1=dict2.hashTable[i].size();
            ans2=ans2+temp_size1-1;
            }
        }

        int ans3=0;
        for(int i=0;i<dict3.hashTable.length;i++){
            if(dict3.hashTable[i]!=null){
            int temp_size2=dict3.hashTable[i].size();
            ans3=ans3+temp_size2-1;
            }
        }
        ans[0]=ans1;
        ans[1]=ans2;
        ans[2]=ans3;
        return ans;
    }

    String[] hashTableHexaDecimalSignature() {
        /*
         * To be filled in by the student
         */
        String[] ans = new String[3];
        ans[0]=dict1.BinaryToHexa();
        ans[1]=dict2.BinaryToHexa();
        ans[2]=dict3.BinaryToHexa();
        return ans;
    }
    
    String[] distinctWords() {
        /*
         * To be filled in by the student
         */
        System.out.println("distinct words");
        String[] ans = dict3.keys(String.class);
        return ans;
    }

    Integer[] distinctWordsFrequencies() {
        /*
         * To be filled in by the student
         */
        System.out.println("frequency");
        Integer[] ans = dict3.values(Integer.class);
        return ans;
    }
}
