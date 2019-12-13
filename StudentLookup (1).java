
/**
 * Your implementation of the LookupInterface.  The only public methods
 * in this class should be the ones that implement the interface.  You
 * should write as many other private methods as needed.  Of course, you
 * should also have a public constructor.
 * 
 * @author TaJah Reynolds
 */
  
 
public class StudentLookup implements LookupInterface {
	private LinkedSortedList<Entry> list;

	public StudentLookup() {
		list = new LinkedSortedList<Entry>();
	}

	@Override
	public void addString(int amount, String s) {
		Entry newEntry = new Entry(s, amount);
		if (list.contains(newEntry))
		{
			int position = list.getPosition(newEntry);
			Entry oldEntry = new Entry(list.getEntry(position).getName(), list.getEntry(position).getCount());
			newEntry.addAmount(list.getEntry(position).getCount());
			list.remove(oldEntry);
		}
		list.add(newEntry);
	}

	@Override
	public int lookupCount(String s) {
		Entry entry = new Entry(s, 0);
		if (list.contains(entry))
			return list.getEntry(list.getPosition(entry)).getCount();
		return 0;
	}

	@Override
	public String lookupPopularity(int n) {
		return list.getEntry(n).getName();
	}

	@Override
	public int numEntries() {
		return list.getLength();
	}

}
