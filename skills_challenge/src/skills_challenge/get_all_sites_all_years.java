package skills_challenge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class get_all_sites_all_years {
	public get_all_sites_all_years() {
	try {
		//get list of year folders from ftp
		String ftp_server = "ftp://ftp.ncdc.noaa.gov/pub/data/noaa/isd-lite/";
		scrape_ftp_html ftp_scrape = new scrape_ftp_html(ftp_server);
		List<String> years = ftp_scrape.get_array();
		Dictionary avg_temp_data_by_year = new Hashtable();
		for (String year : years) {
			System.out.println(year);
			int total = 0;
			int count = 0;
			scrape_ftp_html ftp_scrape_year = new scrape_ftp_html(ftp_server+year+"/");
			List<String> sites = ftp_scrape_year.get_array();
			for (String site : sites) {
				get_data site_data = new get_data(ftp_server+year+"/"+site);
				total += site_data.get_avg_temp_f();
				count +=1;
			}
			if(count>0) {
				System.out.println((double)total/count);
				avg_temp_data_by_year.put(year+"_total", (double)total/count);
				
			}				
		}
		for (String year : years) {
			System.out.println(String.format("In %s the average temp was: %d", year,avg_temp_data_by_year.get(year)));
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
