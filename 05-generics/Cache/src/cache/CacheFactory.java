package cache;

import enums.EvictionPolicy;

public interface CacheFactory {
	/**
	 * Constructs a new Cache<K, V> with the specified maximum capacity and eviction
	 * policy
	 * 
	 * @throws IllegalArgumentException if the given capacity is less than or equal
	 *                                  to zero. Note that IllegalArgumentException
	 *                                  is a `RuntimeException` from the JDK
	 */
	static <K, V> Cache<K, V> getInstance(long capacity, EvictionPolicy policy) {
		if (capacity <= 0) {
			throw new IllegalArgumentException();
		}

		return constructCache(capacity, policy);
	}

	/**
	 * Constructs a new Cache<K, V> with maximum capacity of 10_000 items and the
	 * specified eviction policy
	 */
	static <K, V> Cache<K, V> getInstance(EvictionPolicy policy) {
		return constructCache(10000, policy);
	}

	private static <K, V> Cache<K, V> constructCache(long capacity, EvictionPolicy policy) {
		if (policy == EvictionPolicy.LEAST_FREQUENTLY_USED) {
			return new LFUCache<>(capacity);
		}
		else if(policy == EvictionPolicy.RANDOM_REPLACEMENT) {
			return new RRCache<>(capacity);
		}
		return null;
	}
}