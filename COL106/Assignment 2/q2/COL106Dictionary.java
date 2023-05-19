import java.util.LinkedList;

import Includes.DictionaryEntry;
import Includes.HashTableEntry;
import Includes.KeyAlreadyExistException;
import Includes.KeyNotFoundException;
import Includes.NullKeyException;

import java.lang.reflect.Array;

public class COL106Dictionary<K, V> {

    private LinkedList<DictionaryEntry<K, V>> dict;
    /*
     * dict is a Linked-List, where every node of linked-list is of type DictionaryEntry.
     * DictionaryEntry is a key-value pair, where the type of key and value is K and V respectively.
     */ 
    public LinkedList<HashTableEntry<K, V>>[] hashTable;
    /*
     * hashTable is an array of Linked-Lists which is initialized by the COL106Dictionary constructor.
     * Each index of hashTable stores a linked-list whose nodes are of type HashTableEntry
     * HashTableEntry is a key-address pair, where the type of key is K and the corresponding address is the address of the DictionaryEntry in the linked-list corresponding to the key of HashTableEntry
     */ 
    
    @SuppressWarnings("unchecked")
    COL106Dictionary(int hashTableSize) {
        dict = new LinkedList<DictionaryEntry<K, V>>();
        // This statement initiailizes a linked-list where each node is of type DictionaryEntry with key and value of type K and V respectively.
        hashTable = (LinkedList<HashTableEntry<K, V>>[]) Array.newInstance(LinkedList.class, hashTableSize);
        // This statement initiailizes the hashTable with an array of size hashTableSize where at each index the element is an instance of LinkedList class and
        // this array is type-casted to an array of LinkedList where the LinkedList contains nodes of type HashTableEntry with key of type K. 
    }

    public void insert(K key, V value) throws KeyAlreadyExistException, NullKeyException {
        /*
         * To be filled in by the student
         * Input: A key of type K and it corresponding value of type V
         * Working: Inserts the argumented key-value pair in the Dictionary in O(1)
         */
       
        if(key==null) throw new NullKeyException();
        int index = hash(key);
        if(hashTable[index]==null){
            DictionaryEntry<K,V> dic = new DictionaryEntry<K,V>(key, value);
            HashTableEntry<K, V> ht = new HashTableEntry<K,V>(key, dic);
            LinkedList<HashTableEntry<K,V>> ll=new LinkedList<HashTableEntry<K,V>>();
            ll.add(ht);
            dict.add(dic);
            hashTable[index]=ll;
            return;
        }
        for(HashTableEntry<K, V> ele:hashTable[index]){
            if(ele.key.equals(key)){
                throw new KeyAlreadyExistException(); 
            }
        }
        DictionaryEntry<K,V> dic = new DictionaryEntry<K,V>(key, value);
        HashTableEntry<K, V> ht = new HashTableEntry<K,V>(key, dic);
        hashTable[index].add(ht);
        dict.add(dic);
    }

    public V delete(K key) throws NullKeyException, KeyNotFoundException{
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the associated value of type V with the argumented key
         * Working: Deletes the key-value pair from the Dictionary in O(1)
         */
        
        if(key==null) throw new NullKeyException();
        int index = hash(key);
       
        V value=null;
        if(hashTable[index]==null) throw new KeyNotFoundException();
        
        for(HashTableEntry<K, V> ele:hashTable[index]){
            if(ele.key.equals(key)){
                value = ele.dictEntry.value;
                dict.remove(ele.dictEntry);
                hashTable[index].remove(ele);
                return value;
            }
        }
        throw new KeyNotFoundException();
        
    }

    public V update(K key, V value) throws NullKeyException, KeyNotFoundException{
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the previously associated value of type V with the argumented key
         * Working: Updates the value associated with argumented key with the argumented value in O(1)
         */
        
        if(key==null) throw new NullKeyException();
        int index = hash(key);
        V old_value=null;
        if(hashTable[index]==null) throw new KeyNotFoundException();
        for(HashTableEntry<K, V> ele:hashTable[index]){
            if(ele.key.equals(key)){
                old_value = ele.dictEntry.value;
                ele.dictEntry.value=value;
            }
        }
        if(old_value==null) throw new KeyNotFoundException();
        
        return old_value;
    }

    public V get(K key) throws NullKeyException, KeyNotFoundException {
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the associated value of type V with the argumented key in O(1)
         */
        //System.out.println("get");
        if(key==null) throw new NullKeyException();
        int index = hash(key);
        V value=null;
        if(hashTable[index]==null) throw new KeyNotFoundException();
        for(HashTableEntry<K, V> ele:hashTable[index]){
            if(ele.key.equals(key)){
                value = ele.dictEntry.value;
            }
        }
        if(value==null) throw new KeyNotFoundException();
        return value;
    }

    public int size() {
        /*
         * To be filled in by the student
         * Return: Returns the size of the Dictionary in O(1)
         */
        //System.out.println("size");
        return dict.size();
    }

    @SuppressWarnings("unchecked")
    public K[] keys(Class<K> cls) {
        /*
         * To be filled in by the student
         * Return: Returns array of keys stored in dictionary.
         */
        //System.out.println("keys");
        int capacity = 0;
        K[] ans = (K[]) Array.newInstance(cls, dict.size());
        for(DictionaryEntry<K, V> ele:dict){
            ans[capacity]= ele.key;
            capacity++;
        }
        return ans; //(K[]) Array.newInstance(cls, dict.size());
    }

    @SuppressWarnings("unchecked")
    public V[] values(Class<V> cls) {
        /*
         * To be filled in by the student
         * Return: Returns array of keys stored in dictionary.
         */
        //System.out.println("values");
        int capacity = 0;
        V[] ans = (V[]) Array.newInstance(cls, dict.size());
        for(DictionaryEntry<K, V> ele:dict){
            ans[capacity]= ele.value;
            capacity++;
        }
        return ans;
        //return (V[]) Array.newInstance(cls, dict.size());
    }

    public int hash(K key) {
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the hash of the argumented key using the Polynomial Rolling
         * Hash Function.
         */
        //System.out.println("hash");
        String s = key.toString();
        int hash_value = 0;
        int base = 1;
        int prime = 131;
        int c = this.hashTable.length;
        for(int i=0;i<s.length();i++){
            int ascii = (int)s.charAt(i);
            hash_value = hash_value + (ascii+1)*base;
            base=base*prime;
            hash_value = hash_value % c;
            base = base% c;
        }
        return hash_value % c;
    }
    
    
    public boolean if_present(K key){
        if(key==null ) return false;
        int index = hash(key);
        if(hashTable[index]==null) return false;
        for(HashTableEntry<K, V> ele:hashTable[index]){
            if(ele.key.equals(key)){
               return true;
            }
        }
       return false;
    }
    public String BinaryToHexa(){
        char[] sixteen={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        String a ="";
        String final_a = "";
        for(int i=0;i<hashTable.length;i++){
            if(hashTable[i]==null){
                a+="0";
            }
            else{
                a+="1";
            }
        } 
        if(a.length()%4==1){
            String temp_a ="000";
            final_a=temp_a + a;
        }
        else if(a.length()%4==2){
            String temp_a ="00";
            final_a=temp_a + a;
        }
        else if(a.length()%4==3){
            String temp_a ="0";
            final_a=temp_a + a;
        }
        System.out.println(final_a.length());
        String hex="";
        int iteration = 0;
        while(iteration<(final_a.length()/4)){
            
            int count = 0;
            String part = "";
            while(count<4){
                
                part+=final_a.charAt(count+iteration*4);
                count++;
            }
            int val = 8*(part.charAt(0) - '0') + 4*(part.charAt(1)-'0') + 2*(part.charAt(2)-'0') + 1*(part.charAt(3)-'0');
            hex+=sixteen[val];
            iteration++;
        }
        return hex;
    }
}
