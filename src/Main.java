
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
//2 3 4 6 3 4 5
//2 3 4 6 3
//2 3 4 6 3
//
public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int totalstation = keyboard.nextInt();
        int totalbus = keyboard.nextInt();
        Graph[] graphs = new Graph[totalbus];
        int count = 0;
        while (totalbus > 0) {
            int station = keyboard.nextInt();
            int storestation = station;
            graphs[count] = new Graph(totalstation);
            graphs[count].totalline=storestation;
            int[] patharray = new int[station];
            int count1 = 0;
            int tempstation;
            while (station > 0) {
                tempstation = keyboard.nextInt();
                patharray[count1] = tempstation;
                station--;
                count1++;
            }
            int count3 = 0;
            int count4 = count3 + 1;
            graphs[count].first_station= patharray[count3];

            while (count4 < patharray.length) {

                graphs[count].addconnection(patharray[count3], patharray[count4], 1,count);
                count3 = count4;
                count4 = count3 + 1;
            }

            graphs[count].addconnection(patharray[patharray.length-1],patharray[0],1,count);
            count++;
            totalbus--;
        }

        int []totalcost= minumumspanningcost(graphs,totalstation);
        for(int i =2;i<totalcost.length;i++){
            if(totalcost[i]==0){
                System.out.print(-1+" ");
            }
            else {
                System.out.print(totalcost[i]+" ");
            }
        }



    }
    public static int [] minumumspanningcost(Graph[]graphs,int totalstation){
        ArrayList<Edge>[] minumumcost = new ArrayList[totalstation+1];
        ArrayList<Edge>[]minumumcos2 = new ArrayList[totalstation+1];


        for (int i=0;i<totalstation+1;i++){
            minumumcost[i]= new ArrayList<>();
            minumumcos2[i]= new ArrayList<>();
        }
        PriorityQueue<Edge> temp = new PriorityQueue<>();
        PriorityQueue<Edge>newtemp = new PriorityQueue<>();


for (int i =0;i<graphs.length;i++){
    newtemp.add(new Edge(0,graphs[i].first_station,0,i));
            temp.add(new Edge(0,graphs[i].first_station,0,i));

int totalline1 = graphs[i].totalline;
            while (totalline1>=0){
                int starpoint = temp.peek().startpoint;
                int endpoint=temp.peek().endpoint;
                int cost = temp.peek().weight;
                int cost4 = newtemp.peek().weight;

                minumumcost[starpoint].add(temp.peek());
                minumumcos2[starpoint].add(newtemp.peek());

                totalline1--;
               temp.remove();
               newtemp.remove();


                for (Edge a: graphs[i].Adj[endpoint]){

                    int endpoint1 = a.endpoint;
                    int cost2 = a.weight;
                    int cost3 = cost2+cost4;

                    temp.add(new Edge(endpoint,endpoint1,cost2,i));
                    newtemp.add(new Edge(endpoint,endpoint1,cost3,i));

                }


            }
            while(!temp.isEmpty()){
                temp.remove();
            }
    while(!newtemp.isEmpty()){
        newtemp.remove();
    }

}






int minumumcostarray[] = new int[totalstation+1];
boolean [] visited = new boolean[totalstation+1];
PriorityQueue<Edge> tempQueue = new PriorityQueue<>();
for (int i =0;i<totalstation+1;i++){
    for (Edge a: minumumcos2[i]){
        if(a.endpoint==1){
            tempQueue.add(a);
        }
    }
}


while (!tempQueue.isEmpty()){
    int startpoint =tempQueue.peek().startpoint;
    int endpoint = tempQueue.peek().endpoint;
    int cost = tempQueue.peek().weight;
    int bus = tempQueue.peek().bus;
    tempQueue.remove();
    if(visited[endpoint]){
        continue;
    }
    visited[endpoint]= true;
    minumumcostarray[endpoint]=cost;

    for (Edge a: minumumcos2[endpoint]){
        int endpoint1 = a.endpoint;
        int cost2 = a.weight;
        int tempbus = a.bus;
        int totalcost = cost+cost2;
        if((endpoint==1)&&!visited[endpoint1]){
            tempQueue.add(a);
        } else if (bus==tempbus&&!visited[endpoint1]) {
            for (Edge c:minumumcost[endpoint]){
                int total = minumumcostarray[endpoint];
                total+=c.weight;

                tempQueue.add(new Edge(endpoint,c.endpoint,total,c.bus));
            }
        } else if (tempbus!=bus&&!visited[endpoint1]) {


                tempQueue.add(new Edge(endpoint,endpoint1,cost2+cost,tempbus));

            }
        }

        }
return minumumcostarray;
}



    }
