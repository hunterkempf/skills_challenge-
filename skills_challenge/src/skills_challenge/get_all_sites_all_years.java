package skills_challenge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class get_all_sites_all_years {
	public get_all_sites_all_years() {
	try {
		File file = new File("weather_station_progress_all_sites.csv");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Weather Station,Started,Finished\r\n");
		
		weather_site_freq weather_sites= new weather_site_freq("ftp://ftp.ncdc.noaa.gov/pub/data/noaa/isd-lite/");
		Map sitelist = weather_sites.get_site_list();
		Iterator iterator = sitelist.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry me = (Map.Entry) iterator.next();
			String key = String.format("%s", me.getKey());
			bw.write(String.format("%s,Y,", key));
			single_site_every_year site1 = new single_site_every_year("ftp://ftp.ncdc.noaa.gov/pub/data/noaa/isd-lite/",key);
			bw.write("Y\r\n");
		}
		bw.close();
		fw.close();
		
		System.out.println("Finished All Sites All Years, check output csv files for more information");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
