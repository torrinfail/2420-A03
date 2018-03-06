import java.util.Arrays;
import java.util.Comparator;
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
		int returnVal = low;
		if (  returnVal >= a.length ||(returnVal > -1 && comparator.compare(a[returnVal],key) != 0))
			returnVal = -1;
		return returnVal;
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
		int returnVal = high;
		if ( returnVal >= a.length ||(returnVal > -1 && comparator.compare(a[returnVal],key) != 0))
			returnVal = -1;
		return returnVal;
	}
}
