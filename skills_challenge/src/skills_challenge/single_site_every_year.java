package skills_challenge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
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

public class single_site_every_year {
	// get average temp every year for a single site
	public single_site_every_year(String ftp_server, String weather_site) throws SocketException, IOException {
		scrape_ftp_html ftp_scrape = new scrape_ftp_html(ftp_server);
		List<String> years = ftp_scrape.get_array();
		Dictionary<String, Dictionary<Integer, Double>> avg_temp_data_by_year = new Hashtable<String, Dictionary<Integer, Double>>();
		HashMap<String, Integer> site_count = new HashMap<String, Integer>();
		for (String year : years) {
			scrape_ftp_html ftp_scrape_year = new scrape_ftp_html(ftp_server + year + "/");
			List<String> sites = ftp_scrape_year.get_array();
			HashMap<String, Integer> site_count_file = ftp_scrape_year.get_HashMap();
			site_count_file.forEach((k, v) -> site_count.merge(k, v, (v1, v2) -> v1 + v2));
			for (String site : sites) {
				if (site.equals(weather_site + "-" + year + ".gz")) {
					get_data site_data = new get_data(ftp_server + year + "/" + site);
					avg_temp_data_by_year.put(year, (Dictionary<Integer, Double>) site_data.get_monthly_average());
					break;
				}
			}
		}
		// weather file output
		File file = new File("Temps_by_year.csv");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Weather site,Year,Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec\r\n");
		
		Dictionary<Integer, Double> monthly_data = new Hashtable<Integer, Double>();
		for (String year : years) {
			monthly_data = avg_temp_data_by_year.get(year);
			if (monthly_data != null) {
				bw.write(String.format("%s,%s,", weather_site, year));
				for (int month = 1; month < 13; month++) {
					bw.write(String.format("%.2f,", monthly_data.get(month)));
				}
				bw.write("\r\n");
				System.out.println(String.format("added %s", year));
			}
		}
		bw.close();
		fw.close();
		
	}
}
