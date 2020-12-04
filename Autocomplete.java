import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

// A data type that provides autocomplete functionality for a given set of
// string and weights, using Term and BinarySearchDeluxe.
public class Autocomplete {
    private Term[] terms;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null)
            throw new NullPointerException();

        int n = terms.length;
        this.terms = new Term[n];
        for (int i = 0; i < n; i++) {
            this.terms[i] = terms[i];
        }
        Arrays.sort(this.terms);
    }

    // All terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null)
            throw new NullPointerException();

        Term[] matches;
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
        if (firstIndex == -1)
            matches = new Term[0];
        else {
            int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
            matches = new Term[1 + lastIndex - firstIndex];

            for (int i = 0; i < matches.length; i++) {
                matches[i] = terms[i + firstIndex];
            }
            Arrays.sort(matches, Term.byReverseWeightOrder());
        }
        return matches;
    }

    // The number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null)
            throw new NullPointerException();
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
        return 1 + firstIndex - lastIndex;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println(results[i]);
            }
        }
    }
}
