import java.util.*;

public class Bakery {
    static int solve(ArrayList<Integer> cakes){
        // TO be completed by students
        SkipList p = new SkipList();
        int answer = 0;
        int n = cakes.size();
        for(int i=0;i<n;i++){
            if(p.upperBound(cakes.get(i))==Integer.MAX_VALUE){
                p.insert(cakes.get(i));
                answer++;
            }
            else{
                int r = p.upperBound(cakes.get(i));
                p.insert(cakes.get(i));
                p.delete(r);
            }
        }
        return answer;
    }
}
