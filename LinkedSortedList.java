
public class LinkedSortedList<T extends Comparable<? super T>> implements SortedListInterface<T> {
	private Node firstNode; // Reference to first node of chain
	private int numberOfEntries;
	
	public LinkedSortedList()
	{
		firstNode = null;
		numberOfEntries = 0;
	}
	
	@Override
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		Node nodeBefore = getNodeBefore(newEntry);
		if (isEmpty() || (nodeBefore == null))
		{
			// Add at beginning
			newNode.setNextNode(firstNode);
			firstNode = newNode;
		}
		else
		{
			// Add after nodeBefore
			Node nodeAfter = nodeBefore.getNextNode();
			newNode.setNextNode(nodeAfter);
			nodeBefore.setNextNode(newNode);
		}
		numberOfEntries++;
	}
	
	private Node getNodeBefore(T anEntry)
	{
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while ( (currentNode!= null) && (anEntry.compareTo(currentNode.getData()) > 0))
		{
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		} // end while
		return nodeBefore;
	} // end getNodeBefore

	@Override
	public boolean remove(T anEntry) {
		Node nodeBefore = getNodeBefore(anEntry);
		if (nodeBefore == null)
		{
			if (firstNode.data.compareTo(anEntry) == 0)
			{
				firstNode = firstNode.next;
				numberOfEntries--;
				return true;
			}
			return false;
		}
		nodeBefore.next = nodeBefore.next.next;
		numberOfEntries--;
		return true;
	}

	@Override
	public int getPosition(T anEntry) {
		int position = 0;
		Node currentNode = firstNode;
		while(currentNode.data.compareTo(anEntry) != 0 && position < numberOfEntries)
		{
			currentNode = currentNode.next;
			position++;
		}
		if (!(currentNode.data.compareTo(anEntry) == 0))
			return -1;
		
		return position;
	}

	@Override
	public T getEntry(int givenPosition) {
		if (givenPosition > numberOfEntries || givenPosition < 0)
			throw new IndexOutOfBoundsException();
		Node currentNode = firstNode;
		for (int i = 0; i < givenPosition; i++) {
			currentNode = currentNode.next;
		}
		return currentNode.data;
	}

	@Override
	public boolean contains(T anEntry) {
		Node currentNode = firstNode;
		while (currentNode != null)
		{
			if (currentNode.data.compareTo(anEntry) == 0)
				return true;
			currentNode = currentNode.next;
		}
		return false;
	}
	
	@Override
	public T remove(int givenPosition) {
		if (givenPosition > numberOfEntries || givenPosition < 0)
			throw new IndexOutOfBoundsException();
		Node currentNode = firstNode;
		for (int i = 0; i < givenPosition; i++) {
			currentNode = currentNode.next;
		}
		Node nodeBefore = getNodeBefore(currentNode.data);
		nodeBefore.next = currentNode.next;
		return currentNode.data;
	}

	@Override
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}

	@Override
	public int getLength() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	@Override
	public T[] toArray() {
		// The cast is safe because the array only contains null entries
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[numberOfEntries];
		Node currentNode = firstNode;
		for (int i = 0; i < numberOfEntries; i++)
		{
			array[i] = currentNode.data;
			currentNode = currentNode.next;
		}
		return array;
	}
	
	private class Node
	{
		private T data;
		private Node next;
		
		public Node()
		{
			this(null, null);
		}
		
		public Node(T data) {
			this(data, null);
		}
		
		public Node(T data, Node next)
		{
			this.data = data;
			this.next = next;
		}
		
		public T getData()
		{
			return data;
		}
		
		public void setData(T data)
		{
			this.data = data;
		}
		
		public Node getNextNode()
		{
			return next;
		}
		
		public void setNextNode(Node next)
		{
			this.next = next;
		}
	}

}
