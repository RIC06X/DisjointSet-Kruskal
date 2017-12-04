import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {
    int V;
    int E;
    Edge edge[];
    public Graph(int v, int e){
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; i++){
            edge[i] = new Edge();
        }
    }
    public int find (DisjointSet subset[], int i){
        if (subset[i].parent!=i){
            subset[i].parent= find(subset, subset[i].parent);
        }
        return subset[i].parent;
    }

    public void Union(DisjointSet subset[], int x, int y){
        int xRoot = find(subset,x);
        int yRoot = find(subset,y);
        if (subset[xRoot].rank<subset[yRoot].rank){
            subset[xRoot].parent=yRoot;
        }else if (subset[xRoot].rank>subset[yRoot].rank){
            subset[yRoot].parent=xRoot;
        }
        else {
            subset[xRoot].parent = yRoot;
            subset[xRoot].rank++;
        }
    }

    public void KruskalMST(){
        // The numbers of MST's Edge is the same as the numbers of vertice;
        float total_weight = 0;
        Edge result[] = new Edge[V];
        for (int i = 0; i< V; i++){
            result[i] = new Edge();
        }
        //Create subset for each vertice
        DisjointSet subsets[] = new DisjointSet[V];
        for (int i = 0; i < V; i++){
            subsets[i] = new DisjointSet();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
        //sort the edges in ascending order by weight;
        Arrays.sort(edge);
        Edge next_edge = new Edge();
        int counter = 0;
        for (int i = 0; i < V; i++){
            next_edge = edge[i];
            int x = find(subsets,next_edge.src);
            int y = find(subsets,next_edge.dest);
            if (x!=y){
                result[counter]=next_edge;
                total_weight=total_weight+next_edge.weight;
                Union(subsets,x,y);
                counter++;
            }
        }
        //Print the content of the result[]
        System.out.println(total_weight);
        for (int i = 0; i< counter; i++){
            System.out.println("("+result[i].src +","+ result[i].dest+") weight: " + result[i].weight);
        }
    }

    public static void main(String args[]){
        try{
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            Scanner scan = new Scanner(System.in);
            File file = new File(scan.nextLine());
            scan = new Scanner(file);
            int V = scan.nextInt();
            int E = scan.nextInt();
            Graph graph = new Graph(V, E);
            for (int i = 0; i < E; i++){
                graph.edge[i].src=scan.nextInt();
                graph.edge[i].dest=scan.nextInt();
                graph.edge[i].weight=scan.nextInt();
            }
            graph.KruskalMST();
        }catch (Exception e){
            System.out.println("No file found");
        }
    }

}
