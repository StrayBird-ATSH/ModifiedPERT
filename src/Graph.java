import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

class Graph {
    Vertex[] vertices;

    Graph(int vertexNumber) {
        vertices = new Vertex[vertexNumber];
        for (int i = 0; i < vertexNumber; i++)
            vertices[i] = new Vertex();
    }

    void PERT() {
        ArrayList<Vertex> sort = topologicalSort();
        initializeSingleSource();
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

    private void initializeSingleSource() {
        for (Vertex v : vertices) {
            v.d = v.weight;
            v.pi = null;
        }
    }

    private ArrayList<Vertex> topologicalSort() {
        DFS();
        ArrayList<Vertex> sortedV = new ArrayList<>(Arrays.asList(vertices));
        sortedV.sort(new Vertex.FinishTimeComparator());
        return sortedV;
    }

    private void DFS() {
        for (Vertex u : vertices) {
            u.color = Color.WHITE;
            u.pi = null;
        }
        int time = 0;
        for (Vertex u : vertices)
            if (u.color.equals(Color.WHITE))
                DFSVisit(u, time);
    }

    private void DFSVisit(@NotNull Vertex u, int time) {
        time++;
        u.d = time;
        u.color = Color.GREY;
        for (Vertex v : u.adj)
            if (v.color == Color.WHITE) {
                v.pi = u;
                DFSVisit(v, time);
            }
        u.color = Color.BLACK;
        time++;
        u.f = time;
    }
}
