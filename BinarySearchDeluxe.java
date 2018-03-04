import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.BinarySearch;
public class BinarySearchDeluxe
{
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator)
	{
		if(a == null || key == null || comparator == null)
			throw new NullPointerException("One of the passed in arguments is null.");

		return Arrays.binarySearch(a, key, comparator);
	}

	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator)
	{
		if(a == null || key == null || comparator == null)
			throw new NullPointerException("One of the passed in arguments is null.");
		comparator = new ReverseComparator(comparator);

		return Arrays.binarySearch(a, key, comparator);	
	}

	private static class ReverseComparator <T> implements Comparator<T>
	{
		private Comparator<T> comparator;
		public ReverseComparator(Comparator<T> comparator)
		{
			this.comparator = comparator;
		}

		public int compare(T t1, T t2)
		{
			return -(comparator.compare(t1,t2));
		}
	}

	private static <Key> int indexOf(Key[] a, Key key, Comparator<Key> comparator, boolean getLast) 
	{
		int lo = 0;
		int hi = a.length - 1;
		int mid;
		int returnVal = -1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			mid = lo + (hi - lo) / 2;
			if      (comparator.compare(key,a[mid]) < 0)
			       	hi = mid - 1;
			else if (comparator.compare(key,a[mid]) > 0)
			       	lo = mid + 1;
			else return mid;
		}
		return returnVal;
	}
}
