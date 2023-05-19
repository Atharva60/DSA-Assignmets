import java.io.*;
import java.util.*;

public class SnakesLadder extends AbstractSnakeLadders {
	
	int N, M;
	int snakes[];
	int ladders[];
	int adjlist[][];
	int beenthere[];
	int moves[];
	
	public SnakesLadder(String name)throws Exception{
		File file = new File(name);
		BufferedReader br = new BufferedReader(new FileReader(file));
		N = Integer.parseInt(br.readLine());
        
        M = Integer.parseInt(br.readLine());

	    snakes = new int[N];
		ladders = new int[N];
	    for (int i = 0; i < N; i++){
			snakes[i] = -1;
			ladders[i] = -1;
		}

		for(int i=0;i<M;i++){
            String e = br.readLine();
            StringTokenizer st = new StringTokenizer(e);
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());

			if(source<destination){
				ladders[source] = destination;
			}
			else{
				snakes[source] = destination;
			}
        }
		beenthere = new int[N];
		for(int i=0;i<N;i++){
			beenthere[i]=0;
		}
		adjlist = new int[N][6];
		for(int i=0;i<N;i++){
			for(int j=0;j<6;j++){
				adjlist[i][j]=-1;
			}
		}
		for(int i=0;i<N;i++){
			if(beenthere[i]==0){
				for(int j=0;j<6 && (i+j+1)<N;j++){
					if(ladders[i+j+1]!=-1){
						int f=i+j+1;
						while(ladders[f]!=-1){
							beenthere[f]=1;
							f=ladders[f];
						}
						adjlist[i][j]=f;
					}
					else if(snakes[i+j+1]!=-1){
						int f=i+j+1;
						while(snakes[f]!=-1){
							beenthere[f]=1;
							f=snakes[f];
						}
						adjlist[i][j]=f;
					}
					else{
						adjlist[i][j]=i+j+1;
					}
				}
			}
		}
		for(int i=N-1;i>N-7;i--){
			adjlist[i][N-1-i]=100;
		}
		moves = new int[N];
		for(int i=0;i<N;i++){
			moves[i]=2147483647;
		}
		int start = 0;
		moves[start]=0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		while(q.isEmpty()!=true){
			int a = q.remove();
			if(a==N){
				break;
			}
			for(int i=a+1;i<a+7;i++){
				if(i<N){
					int p = i;
					if(ladders[p]!=-1){
						p=ladders[p];
					}
					else if(snakes[p]!=-1){
						p=snakes[p];
					}
					if(moves[p]-moves[a]>1){
						moves[p]=moves[a]+1;
						q.add(p);
					}
				}
			}
		}
	}
    
	public int OptimalMoves()
	{
		/* Complete this function and return the minimum number of moves required to win the game. */
		return moves[N-1];
	}

	public int Query(int x, int y)
	{
		/* Complete this function and 
			return +1 if adding a snake/ladder from x to y improves the optimal solution, 
			else return -1. */

			int p = moves[N-1];
			int n = 0;
			if(x < y) {
				int s = x;
				int e = y;
				if(ladders[s] == -1) {
					ladders[s] = e;
				} else {
					e = ladders[s];
					ladders[s] = y;
				}
				n = moves[s] + moves[N-1-e] + 1;
			} 
			else { 
				int s = x;
				int e = y;
				if(snakes[s] == -1) {
					snakes[s] = e;
				} else {
					e = snakes[s];
					snakes[s] = y;
				}
				n = moves[s] - moves[N-1-e];
			}
			if(n < p) {
				return 1;
			} 
			else {
				return -1;
			}
	}

	public int[] FindBestNewSnake()
	{
		int result[] = {-1, -1};
		/* Complete this function and 
			return (x, y) i.e the position of snake if adding it increases the optimal solution by largest value,
			if no such snake exists, return (-1, -1) */

		return result;
	}
	// public static void main(String[] args) throws Exception {
	// 	SnakesLadder obj = new SnakesLadder("input.txt");
	// 	for(int i=0;i<100;i++){
	// 		for(int j=0;j<6;j++){
	// 			System.out.print(obj.adjlist[i][j]);
	// 			System.out.print(" ");
	// 		}
	// 		// System.out.println(" ");
	// 	}
	// }
}