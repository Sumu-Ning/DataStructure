package pingsan.datastructure;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

public class MonotonicQueue<E> {
	private Comparator<? super E> comparator = null;
	private Deque<Map.Entry<E, Integer>> deque = new LinkedList<Map.Entry<E, Integer>>();

	public MonotonicQueue() {

	}

	public MonotonicQueue(Comparator<? super E> comparator) {
		this.comparator = comparator;
	}

	@SuppressWarnings("unchecked")
	public boolean offer(E element) {
		int count = 0;

		while (deque.size() > 0
				&& (comparator == null && ((Comparable<E>) element)
						.compareTo(deque.peekLast().getKey()) > 0)
				|| comparator != null
				&& comparator.compare(element, deque.peekLast().getKey()) > 0) {
			count += deque.pollLast().getValue() + 1;
		}

		deque.offer(new AbstractMap.SimpleEntry<E, Integer>(element, count));

		return true;
	}

	public E getMax() {
		return deque.peekFirst().getKey();
	}

	public E poll() {
		if (deque.peekFirst().getValue() > 0) {
			deque.peekFirst().setValue(deque.peekFirst().getValue() - 1);
		} else {
			deque.pollFirst();
		}

		return null;
	}
}