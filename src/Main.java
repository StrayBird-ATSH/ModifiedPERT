public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    void DFS(Graph G) {
        for (Vertex u : G.vertices) {
            u.color = Color.WHITE;
            u.pi = null;
        }
        int time = 0;
        for (Vertex u : G.vertices)
            if (u.color.equals(Color.WHITE))
                DFSVisit(G, u, time);
    }

    void DFSVisit(Graph G, Vertex u, int time) {
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
