import java.util.ArrayList;

public class Bus {
    int busid;
    public ArrayList<Station> path;
public Bus(int busid){
    this.busid=busid;
    this.path = new ArrayList<Station>();
}
public void addstation(int station){
this.path.add(new Station(station));
}
}

