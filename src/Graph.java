import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class Graph {
    ArrayList<Vertex> vertices;
    private int time = 0;

    Graph(int vertexNumber) {
        vertices = new ArrayList<>(vertexNumber);
        for (int i = 0; i < vertexNumber; i++)
            vertices.add(new Vertex());
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
        ArrayList<Vertex> sortedV = new ArrayList<>(vertices);
        sortedV.sort(new Vertex.FinishTimeComparator());
        return sortedV;
    }

    private void DFS() {
        for (Vertex u : vertices) {
            u.color = Color.WHITE;
            u.pi = null;
        }
        time = 0;
        for (Vertex u : vertices)
            if (u.color.equals(Color.WHITE))
                DFSVisit(u);
    }

    private void DFSVisit(@NotNull Vertex u) {
        time++;
        u.d = time;
        u.color = Color.GREY;
        for (Vertex v : u.adj)
            if (v.color == Color.WHITE) {
                v.pi = u;
                DFSVisit(v);
            }
        u.color = Color.BLACK;
        time++;
        u.f = time;
    }
}
