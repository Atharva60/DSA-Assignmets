import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class PowerLine {
    String cityA;
    String cityB;

    public PowerLine(String cityA, String cityB) {
        this.cityA = cityA;
        this.cityB = cityB;
    }
}

class sheher{
    String naam;
    ArrayList<PowerLine> linesincluded;
    public sheher(String naam){
        this.naam = naam;
        this.linesincluded = new ArrayList<PowerLine>();
    }
}

// Students can define new classes here

public class PowerGrid {
    int numCities;
    int numLines;
    String[] cityNames;
    PowerLine[] powerLines;
    HashMap<String, sheher> cityGraph;
    // Students can define private variables here

    public PowerGrid(String filename) throws Exception {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        numCities = Integer.parseInt(br.readLine());
        numLines = Integer.parseInt(br.readLine());
        cityNames = new String[numCities];
        for (int i = 0; i < numCities; i++) {
            cityNames[i] = br.readLine();
        }
        powerLines = new PowerLine[numLines];
        for (int i = 0; i < numLines; i++) {
            String[] line = br.readLine().split(" ");
            powerLines[i] = new PowerLine(line[0], line[1]);
        }
        cityGraph = new HashMap<String, sheher>();
        for (int i = 0; i < numCities; i++) {
            cityGraph.put(cityNames[i], new sheher(cityNames[i]));
        }
        for (int i = 0; i < numLines; i++) {
            sheher cA = cityGraph.get(powerLines[i].cityA);
            sheher cB = cityGraph.get(powerLines[i].cityB);
            cA.linesincluded.add(powerLines[i]);
            cB.linesincluded.add(powerLines[i]);
        }
        // TO be completed by students
    }


    private void dfs(int curr, boolean[] visited, int[] parent, int[] discoveryTime, int[] lowTime, int time, PowerLine[] powerLines, ArrayList<PowerLine> criticalLines) {
        visited[curr] = true;
        discoveryTime[curr] = lowTime[curr] = ++time;
    
        for (int i = 0; i < powerLines.length; i++) {
            PowerLine line = powerLines[i];
            int neighbor = -1;
    
            if (line.cityA.equals(cityNames[curr]))
                neighbor = Arrays.asList(cityNames).indexOf(line.cityB);
            else if (line.cityB.equals(cityNames[curr]))
                neighbor = Arrays.asList(cityNames).indexOf(line.cityA);
    
            if (neighbor == -1) {
                continue;
            }
    
            if (!visited[neighbor]) {
                parent[neighbor] = curr;
                dfs(neighbor, visited, parent, discoveryTime, lowTime, time, powerLines, criticalLines);
    
                // check if the subtree rooted at neighbor has a connection to ancestors of curr
                lowTime[curr] = Math.min(lowTime[curr], lowTime[neighbor]);
                if (lowTime[neighbor] > discoveryTime[curr]) {
                    // line between curr and neighbor is a critical line
                    criticalLines.add(line);
                }
            } else if (neighbor != parent[curr]) {
                // neighbor is already visited and is not the parent of curr
                lowTime[curr] = Math.min(lowTime[curr], discoveryTime[neighbor]);
            }
        }
    }
    

    public ArrayList<PowerLine> criticalLines() {
        /*
         * Implement an efficient algorithm to compute the critical transmission lines
         * in the power grid.
         
         * Expected running time: O(m + n), where n is the number of cities and m is the
         * number of transmission lines.
         */
        int n = this.numCities;
        ArrayList<PowerLine> ans = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        int[] discoveryTime = new int[n];
        int[] lowTime = new int[n];
        for(int i=0;i<n;i++){
            parent[i]=-1;
        }
        for(int i=0;i<n;i++){
            visited[i]=false;
        }
        int time = 0;
        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                dfs(i, visited, parent, discoveryTime, lowTime, time, powerLines, ans);
            }
        }
        return ans;
    }

    public void preprocessImportantLines() {
        /*
         * Implement an efficient algorithm to preprocess the power grid and build
         * required data structures which you will use for the numImportantLines()
         * method. This function is called once before calling the numImportantLines()
         * method. You might want to define new classes and data structures for this
         * method.
         
         * Expected running time: O(n * logn), where n is the number of cities.
         */
        return;
    }

    public int numImportantLines(String cityA, String cityB) {
        /*
         * Implement an efficient algorithm to compute the number of important
         * transmission lines between two cities. Calls to numImportantLines will be
         * made only after calling the preprocessImportantLines() method once.
         
         * Expected running time: O(logn), where n is the number of cities.
         */
        return 0;
    }
    // public static void main(String[] args) throws Exception {
    //     PowerGrid pg = new PowerGrid("text.txt");
    //     System.out.println(pg.criticalLines().get(0).cityB);
    // }
}