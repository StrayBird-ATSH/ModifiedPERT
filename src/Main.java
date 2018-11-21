import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void PERT(Graph G) {
        ArrayList<Vertex> sort = topologicalSort(G);
        initializeSingleSource(G);
        for (Vertex u : sort)
            for (Vertex v : u.adj)
                relax(u, v);
    }

    private void relax(@NotNull Vertex u, @NotNull Vertex v) {
        if (v.d < u.d + v.weight) {
            v.d = u.d + v.weight;
            v.pi = u;
        }
    }

    private void initializeSingleSource(@NotNull Graph G) {
        for (Vertex v : G.vertices) {
            v.d = v.weight;
            v.pi = null;
        }
    }

    private ArrayList<Vertex> topologicalSort(Graph G) {
        DFS(G);
        ArrayList<Vertex> sortedV = (ArrayList<Vertex>) Arrays.asList(G.vertices);
        sortedV.sort(new Vertex.FinishTimeComparator());
        return sortedV;
    }

    private void DFS(@NotNull Graph G) {
        for (Vertex u : G.vertices) {
            u.color = Color.WHITE;
            u.pi = null;
        }
        int time = 0;
        for (Vertex u : G.vertices)
            if (u.color.equals(Color.WHITE))
                DFSVisit(u, time);
    }

    private void DFSVisit(@NotNull Vertex u, int time) {
        time++;
        u.d = time;
        u.color = Color.GREY;
        for (Vertex v : u.adj)
            if (v.color.equals(Color.WHITE)) {
                v.pi = u;
                DFSVisit(u, time);
            }
        u.color = Color.BLACK;
        time++;
        u.f = time;
    }
}
