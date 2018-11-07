package skills_challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class test_llist {

	public test_llist() {
		HashMap<String, Integer> site_count = new HashMap<String, Integer>();
		site_count.put("test1", 5);
		site_count.put("test2", 4);
		site_count.put("test3", 3);
		site_count.put("test4", 2);

		Map test = sortByValue(site_count);
		Iterator iterator = test.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry me = (Map.Entry) iterator.next();
			System.out.println(String.format("%s,%d\r\n", me.getKey(), me.getValue()));
		}
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort(Entry.comparingByValue(Collections.reverseOrder()));
		//list.sort(Entry.comparingByValue());

		Map<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

}
