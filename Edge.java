public class Edge implements Comparable<Edge> {
    int src;
    int dest;
    float weight;

    public Edge(){
        src = 0;
        dest = 0;
        weight = 0;
    }
    @Override
    public int compareTo(Edge o) {
        if (this.weight>o.weight)
            return 1;
        else if (this.weight<o.weight)
            return -1;
        else
            return 0;
    }
}
