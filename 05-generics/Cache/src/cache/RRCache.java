package cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class RRCache<K, V> implements Cache<K, V> {

	private static final int DEFAULT_CAPACITY = 10000;

	private Map<K, V> cache;
	private final long capacity;
	private long successfulHits;
	private long totalHits;

	public RRCache(long capacity) {
		cache = new HashMap<>();
		this.capacity = capacity;
		successfulHits = 0;
		totalHits = 0;
	}

	@Override
	public V get(K key) {
		if (cache.get(key) != null) {
			successfulHits++;
		}
		totalHits++;

		return cache.get(key);
	}

	@Override
	public void set(K key, V value) {
		if (key != null && value != null) {
			if (isFull()) {
				evictCache();
			}
			cache.put(key, value);
		}
	}

	@Override
	public boolean remove(K key) {
		if (cache.containsKey(key)) {
			cache.remove(key);
			return true;
		}
		return false;
	}

	@Override
	public long size() {
		return cache.size();
	}

	@Override
	public void clear() {
		cache.clear();
		totalHits = 0;
		successfulHits = 0;
	}

	@Override
	public double getHitRate() {
		if (totalHits == 0) {
			return 0;
		}

		return (double) successfulHits / totalHits;
	}

	@Override
	public int getUsesCount(K key) {
		throw new UnsupportedOperationException();
	}

	private void evictCache() {
		Iterator<Entry<K, V>> iterator = cache.entrySet().iterator();
		if (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
	}

	private boolean isFull() {
		return cache.size() == capacity;
	}

}
