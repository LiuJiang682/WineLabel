package devproblem;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class KeyValueSwapSortMapUtils {

	public static final <V, K> TreeMap<V, K> keyValueSwapSort(Map<K, V> map) {
		if (null == map) {
			throw new IllegalArgumentException("map cannot be null!");
		}
		TreeMap<V, K> sortedMap = new TreeMap<>();
		for(Entry<K, V> elm : map.entrySet()) {
			sortedMap.put(elm.getValue(), elm.getKey());
		}
		return sortedMap;
	}
	
}
