/**
 * Entry class to be used with the StudentLookup class.
 * @author tjrey
 *
 */
public class Entry implements Comparable<Entry> {
	private String name;
	private int count;
	
	public Entry(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void addAmount(int amount) {
		count += amount;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Entry e) {
		if (e == null)
			throw new NullPointerException();
		if (this.name.equalsIgnoreCase(e.name))
			return 0;
		else
			return this.count > e.count ? -1 : this.count == e.count ? this.name.compareTo(e.name) : 1;
	}
}
