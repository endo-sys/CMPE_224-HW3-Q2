import java.util.ArrayList;

public class Graph {
    int stations;
    int first_station;
    int totalline;


    ArrayList<Edge>[] Adj;
    public Graph(int stations){
        this.stations=stations;
        this.Adj = new ArrayList[stations+1];

        for (int i=0;i<stations+1;i++){
            Adj[i]= new ArrayList<>();
        }
    }
    public void addconnection(int a,int b,int weight,int bus){
        Adj[a].add(new Edge(a,b,weight,bus));
    }


}
