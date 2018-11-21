import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File("");
        Scanner input;
        try {
            input = new Scanner(file);
            int m = input.nextInt();
            int n = input.nextInt();
            input.nextLine();
            Graph graph = new Graph(m);
            for (int i = 0; i < n; i++) {
                String edge = input.nextLine();
                graph.vertices[Integer.parseInt(edge.split(" ")[0]) + 1].adj.
                        add(graph.vertices[Integer.parseInt(edge.split(" ")[1]) + 1]);
            }
            String weight = input.nextLine();
            for (int i = 1; i <= m; i++)
                graph.vertices[i].weight = Integer.parseInt(weight.split(" ")[i - 1]);
            graph.PERT();
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
