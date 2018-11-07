package skills_challenge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class weather_site_freq {
	// get average temp every year for a single site
	public weather_site_freq(String ftp_server) throws SocketException, IOException {
		scrape_ftp_html ftp_scrape = new scrape_ftp_html(ftp_server);
		List<String> years = ftp_scrape.get_array();
		Dictionary<String, Dictionary<Integer, Double>> avg_temp_data_by_year = new Hashtable<String, Dictionary<Integer, Double>>();
		HashMap<String, Integer> site_count = new HashMap<String, Integer>();
		for (String year : years) {
			scrape_ftp_html ftp_scrape_year = new scrape_ftp_html(ftp_server + year + "/");
			List<String> sites = ftp_scrape_year.get_array();
			HashMap<String, Integer> site_count_file = ftp_scrape_year.get_HashMap();
			site_count_file.forEach((k, v) -> site_count.merge(k, v, (v1, v2) -> v1 + v2));
		}

		// Counts of each weather station
		File file2 = new File("weather_station_counts.csv");
		if (!file2.exists()) {
			file2.createNewFile();
		}
		FileWriter fw2 = new FileWriter(file2);
		BufferedWriter bw2 = new BufferedWriter(fw2);
		bw2.write("Weather Station,Count\r\n");

		Map sorted_site_count = sortByValue(site_count);
		Iterator iterator = sorted_site_count.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry me = (Map.Entry) iterator.next();
			bw2.write(String.format("%s,%d\r\n", me.getKey(), me.getValue()));
		}
		bw2.close();

		System.out.println("finished weather site frequency, check output csv file for more information");
		
	}
	// allows hashmap to be sorted
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


