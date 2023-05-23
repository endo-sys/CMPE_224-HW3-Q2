
public class Edge implements Comparable<Edge> {

    int endpoint;
    int bus;
    int startpoint;
    int weight;
    public Edge(int startpoint,int endpoint,int weight,int bus){
        this.endpoint=endpoint;
        this.weight= weight;
        this.startpoint=startpoint;
        this.bus=bus;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.weight>o.weight){
            return 1;
        }
        else {
            return -1;
        }
    }
    public  String toString(){
        return this.startpoint + " "+ this.endpoint + " "+ this.weight +" ";
    }
}
