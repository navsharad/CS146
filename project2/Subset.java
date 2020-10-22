import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Takes a command-line integer k; reads in a sequence of strings from
// standard input; and prints out exactly k of them, uniformly at random.
public class Subset {
    public static void main(String[] args) {
        ResizingArrayRandomQueue<String> q = new ResizingArrayRandomQueue<String>();

        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }


    }
}
