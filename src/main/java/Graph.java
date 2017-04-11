import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by svolkovskyi on 11.04.17.
 */
public class Graph {
    private final Map<String, Vertex> adjacencyList = new HashMap<>();


    public Graph(List<String[]> lines) {
        for (String[] parts : lines) {
            Vertex vertex = new Vertex(parts);
            this.adjacencyList.put(vertex.name(), vertex);
            for (String sibling : vertex.siblings()) {
                adjacencyList.put(sibling, new Vertex(sibling));
            }
        }
    }

    public void performBellmanFord() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            for(Map.Entry<String, Vertex> entry : adjacencyList.entrySet()){
                Vertex u = entry.getValue();
                for(String sibling : u.siblings()){
                    Vertex v = adjacencyList.get(sibling);
                    int pathWeight = u.hard(sibling);
                    v.relax(u.name(), u.pathEstimate(), pathWeight);
                }
            }
        }
    }

    public double hard(String name){
        return adjacencyList.get(name).pathEstimate();
    }

    public void print(){
        System.out.println("\nAdjacency list: ");
        for (Vertex v : adjacencyList.values()) {
            System.out.println(v);
        }
    }
}
