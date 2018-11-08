package skills_challenge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;

public class main {
	public static void main(String[] args) {
		
		
		// test get data on specific .gz file
		try {
			get_data file1 = new get_data("ftp://ftp.ncdc.noaa.gov/pub/data/noaa/isd-lite/1901/029070-99999-1901.gz");
			System.out.println(String.format("Average temp in file: %.2f", file1.get_avg_temp_f()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		// get all weather sites frequencies
		try {
			weather_site_freq weather_sites= new weather_site_freq("ftp://ftp.ncdc.noaa.gov/pub/data/noaa/isd-lite/");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// test single site every year on specific site and entire isd-lite database
		
		try {
			single_site_every_year site1 = new single_site_every_year("ftp://ftp.ncdc.noaa.gov/pub/data/noaa/isd-lite/","108660-99999");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		// Test get all sites all years (this will take a long time to run)
		get_all_sites_all_years all_sites = new get_all_sites_all_years();

	}
}
