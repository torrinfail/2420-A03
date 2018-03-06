import java.util.Arrays;
import java.util.Comparator;
//import edu.princeton.cs.algs4.BinarySearch;
/**
 * @author
 * Aidan Hubert
 */
public class BinarySearchDeluxe
{
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator)
	{
		if(a == null || key == null || comparator == null)
			throw new NullPointerException("One of the passed in arguments is null.");

		return getFirst(a, key, comparator);
	}

	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator)
	{
		if(a == null || key == null || comparator == null)
			throw new NullPointerException("One of the passed in arguments is null.");
		
		return getLast(a, key, comparator);	
	}


	private static <Key> int getLast(Key[] a, Key key, Comparator<Key> comparator)
	{
		int low = -1, high = a.length;
		while (low+1 != high)
		{
			int mid = (low+high)>>>1;
			if (comparator.compare(a[mid],key) > 0) high=mid;
			else low=mid;
		}
		int p = low;
		if (  p >= a.length ||(p > -1 && comparator.compare(a[p],key) != 0))
			p=-1;//no key found
		return p;
	}

	private static <Key> int getFirst(Key[] a, Key key, Comparator<Key> comparator)
	{
		int low = -1, high = a.length;
		while (low+1 != high)
		{
			int mid = (low+high)>>>1;
			if (comparator.compare(a[mid],key) < 0) low=mid;
			else high=mid;
		}
		int p = high;
		if ( p >= a.length || comparator.compare(a[p],key) != 0 )
			p=-1;//no key found
		return p;
	}
}
