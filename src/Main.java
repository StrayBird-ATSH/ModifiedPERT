import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\zhang\\IdeaProjects\\ModifiedPERT\\input.txt");
        Scanner input;
        try {
            input = new Scanner(file);
            int m = input.nextInt();
            int n = input.nextInt();
            input.nextLine();
            Graph graph = new Graph(m);
            for (int i = 0; i < n; i++) {
                String edge = input.nextLine();
                int startEdge = Integer.parseInt(edge.split(" ")[0]) - 1;
                int finishEdge = Integer.parseInt(edge.split(" ")[1]) - 1;
                graph.vertices[startEdge].adj.add(graph.vertices[finishEdge]);
            }
            String weight = input.nextLine();
            for (int i = 0; i < m; i++)
                graph.vertices[i].weight = Integer.parseInt(weight.split(" ")[i]);
            long time = System.currentTimeMillis();
            graph.PERT();
            System.out.println("The time is " + (System.currentTimeMillis() - time));
            int distance = 0;
            for (Vertex vertex : graph.vertices)
                if (vertex.d > distance)
                    distance = vertex.d;
            System.out.println(distance);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
