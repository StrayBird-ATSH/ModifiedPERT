import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


    private void relax(Vertex u, Vertex v, Vertex w) {

    }

    private void initializeSingleSource(@NotNull Graph G, Vertex s) {
        for (Vertex v : G.vertices) {
            v.d = Integer.MAX_VALUE;
            v.pi = null;
        }
        s.d = 0;
    }

    ArrayList<Vertex> topologicalSort(Graph G) {
        DFS(G);
        ArrayList<Vertex> sortedV = new ArrayList<>(G.vertices);
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
                DFSVisit(G, u, time);
    }

    private void DFSVisit(Graph G, @NotNull Vertex u, int time) {
        time++;
        u.d = time;
        u.color = Color.GREY;
        for (Vertex v : u.adj)
            if (v.color.equals(Color.WHITE)) {
                v.pi = u;
                DFSVisit(G, u, time);
            }
        u.color = Color.BLACK;
        time++;
        u.f = time;
    }
}
