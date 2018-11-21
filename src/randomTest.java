import java.io.File;
import java.io.FileWriter;

public class randomTest {
    public static void main(String[] args) throws Exception {
        File file = new File("input.txt");
        FileWriter fileWriter = new FileWriter(file);
        int n = 100000;
        int m = 20000;
        fileWriter.write(n + " " + m + "\n");
        for (int i = 1; i <= m; i++) {
            int x = (int) (Math.random() * n) + 1;
            int y = (int) (Math.random() * n) + 1;
            if (x == y) x++;
            if (x > y) {
                fileWriter.write(x + " " + y + "\n");
            } else {
                fileWriter.write(y + " " + x + "\n");
            }
        }
        for (int i = 1; i <= n; i++) {
            int value = (int) (Math.random() * 1000);
            fileWriter.write(value + " ");
        }
        fileWriter.close();
    }
}
