import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/**
 * This class gives auto complete functionality to a search field or any other text entry field.
 * @author Aidan Hubert
 */
public class Autocomplete
{
	private Term[] terms;

	/**
	 * Creates a new autocomplete object with the given array of terms. 
	 * Does not mutate the given array, by copying the array.
	 */
	public Autocomplete(Term[] terms)
	{
		Term[] copyArray = Arrays.copyOf(terms,terms.length);
		Arrays.sort(copyArray);
		this.terms = copyArray;
	}
	
	/**
	 * Returns all the terms that match the given prefix.
	 */
	public Term[] allMatches(String prefix)
	{
		Term prefixTerm = new Term(prefix, 1);

		int first = BinarySearchDeluxe.firstIndexOf(terms,prefixTerm,Term.byPrefixOrder(prefix.length()));
		int last = BinarySearchDeluxe.lastIndexOf(terms,prefixTerm,Term.byPrefixOrder(prefix.length()));
		if(first == -1 || last == -1)
			return new Term[0];
		Term[] matches = Arrays.copyOfRange(terms,first,last+1);
		Arrays.sort(matches,Term.byReverseWeightOrder());
		return matches;
	}
	
	/**
	 * Returns the total number of terms that match the given prefix.
	 */
	public int numberOfMatches(String prefix)
	{
		Term prefixTerm = new Term(prefix, 1);
		int first = BinarySearchDeluxe.firstIndexOf(terms,prefixTerm,Term.byPrefixOrder(prefix.length()));
		int last = BinarySearchDeluxe.lastIndexOf(terms,prefixTerm,Term.byPrefixOrder(prefix.length()));

		if(first == -1 || last == -1)
			return 0;

		return (last + 1) - (first) ;
	}

	/////////TEST/////////
	public static void main(String[] args) {

		// read in the terms from a file
		String filename = args[0];
		In in = new In(filename);
		int N = in.readInt();
		Term[] terms = new Term[N];
		for (int i = 0; i < N; i++) {
			double weight = in.readDouble();       // read the next weight
			in.readChar();                         // scan past the tab
			String query = in.readLine();          // read the next query
			terms[i] = new Term(query, weight);    // construct the term
		}

		// read in queries from standard input and print out the top k matching terms
		int k = Integer.parseInt(args[1]);
		Autocomplete autocomplete = new Autocomplete(terms);
		while (StdIn.hasNextLine()) {
			String prefix = StdIn.readLine();
			StdOut.println(autocomplete.numberOfMatches(prefix));
			Term[] results = autocomplete.allMatches(prefix);
			for (int i = 0; i < Math.min(k, results.length); i++)
				StdOut.println(results[i]);
		}
	}
}
