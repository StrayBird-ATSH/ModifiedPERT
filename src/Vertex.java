import java.util.ArrayList;
import java.util.Comparator;

class Vertex {
    Color color;
    Vertex pi;
    int d;
    int f;
    ArrayList<Vertex> adj;
    int weight;

    Vertex() {
        adj = new ArrayList<>();
    }

    public static class FinishTimeComparator implements Comparator<Vertex> {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            return o2.f - o1.f;
        }
    }
}
