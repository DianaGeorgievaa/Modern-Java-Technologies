package cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCache<K, V> implements Cache<K, V> {

	private Map<K, V> cache;
	private Map<K, Integer> cacheUsses;

	private long capacity;
	private long successfulHits;
	private long totalHits;

	public LFUCache(long capacity) {
		cache = new HashMap<>();
		cacheUsses = new HashMap<>();
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
		cacheUsses.put(key, cacheUsses.get(key) + 1);

		return cache.get(key);
	}

	@Override
	public void set(K key, V value) {
		if (key != null && value != null) {
			if (isFull()) {
				cache.remove(getLeastFrequantlyUsedKey());
			}

			if (cacheUsses.containsKey(key)) {
				cacheUsses.put(key, cacheUsses.get(key) + 1);
			} else {
				cacheUsses.put(key, 1);
			}

			cache.put(key, value);
		}
	}

	@Override
	public boolean remove(K key) {
		return cache.remove(key) != null && cacheUsses.remove(key) != null;
	}

	@Override
	public long size() {
		return cache.size();
	}

	@Override
	public void clear() {
		cache.clear();
		cacheUsses.clear();
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
		if (cacheUsses.containsKey(key)) {
			return cacheUsses.get(key);
		}
		return 0;
	}

	private boolean isFull() {
		return cache.size() == capacity;
	}

	private K getLeastFrequantlyUsedKey() {
		int minFrequancy = Integer.MAX_VALUE;
		K currentKey = null;

		for (Map.Entry<K, Integer> entry : cacheUsses.entrySet()) {
			if (entry.getValue() <= minFrequancy) {
				minFrequancy = entry.getValue();
				currentKey = entry.getKey();
			}
		}
		return currentKey;
	}
}
