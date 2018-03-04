import java.lang.Comparable;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;
public class Term implements Comparable<Term>
{
	private String query;
	private double weight;

	public Term(String query, double weight)
	{
		if(query == null)
			throw new NullPointerException();
		if(weight <= 0)
			throw new IllegalArgumentException();
		this.query = query;
		this.weight = weight;
	}

	public static Comparator<Term> byReverseWeightOrder()
	{
		return new ReverseWeightOrderComparator();
	}

	public static Comparator<Term> byPrefixOrder(int r)
	{
		return new PrefixOrderComparator(r);
	}

	public int compareTo(Term that)
	{
		return this.query.compareTo(that.query);
	}

	@Override
	public String toString()
	{
		return weight + "\t" + query;
	}

	private static class ReverseWeightOrderComparator implements Comparator<Term>
	{
		public int compare(Term t1, Term t2)
		{
			return -(int)Math.ceil(t1.weight-t2.weight);
		}
	}

	private static class PrefixOrderComparator implements Comparator<Term>
	{
		private int r;
		public PrefixOrderComparator(int r)
		{
			this.r = r;
		}

		public int compare(Term t1, Term t2)
		{
			String sub1 = t1.query.substring(0,r-1);	
			String sub2 = t2.query.substring(0,r-1);
			return sub1.compareTo(sub2);
		}
	}

	public static void main(String[] args)
	{
		Term[] terms = new Term[5];
		Scanner input = new Scanner(System.in);
		for(double d = 0; d < 5; d++)
		{
			terms[(int)d] = new Term(input.nextLine(),d+1);
		}
		Arrays.sort(terms,Term.byPrefixOrder(2));
		System.out.println(Arrays.toString(terms));
		System.out.println(BinarySearchDeluxe.firstIndexOf(terms,terms[3],Term.byPrefixOrder(2)));
	}
}
