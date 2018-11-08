package skills_challenge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class get_data {
	// This class is used to get the temperature data and create averages from a .gz file on the ftp server 
	
	private double avg_temp_c = 0; //average temp for file in degrees C
	private double avg_temp_f = 0; //average temp for file in degrees F
	private int size; //number of observations in file
	private String location;
	private List<String> entire_file;
	private Dictionary<Integer, Integer> month_total_temp = new Hashtable<Integer, Integer>(); 
	private Dictionary<Integer, Integer> month_size = new Hashtable<Integer, Integer>(); 
	private Dictionary<Integer, Double> month_avg_temp = new Hashtable<Integer, Double>(); 
	
	public get_data(String filename) throws FileNotFoundException, IOException {
		URL url = new URL(filename);
        URLConnection urlConnection = (URLConnection)url.openConnection();
		location = filename.split("-")[0];
		GZIPInputStream gzip = new GZIPInputStream(urlConnection.getInputStream());
        Scanner sc = new Scanner(new InputStreamReader(gzip));
        size = 0;
        double total = 0;
        String current_line;
        int file_temp;
        int file_month;
        while(sc.hasNextLine()) {
        	current_line = sc.nextLine();
        	entire_file.add(current_line);
        	file_temp = Integer.parseInt(current_line.split("\\s+")[4]);
        	file_month = Integer.parseInt(current_line.split("\\s+")[1]);
        	if(file_temp != -9999) {
        		size +=1;
                total += file_temp/10.0;
                if(month_total_temp.get(file_month) == null) {
                	month_total_temp.put(file_month,0);
                	month_size.put(file_month,0);
                }
                month_total_temp.put(file_month, (month_total_temp.get(file_month)+file_temp));
                month_size.put(file_month, (month_size.get(file_month)+1));
        	}
        }
        sc.close();
        if(size>0) {
        	avg_temp_c = (double) (total/size);
        	avg_temp_f = avg_temp_c*(9.0/5.0) + 32;
        	for(int i = 1;i<13;i++) {
        		if(month_total_temp.get(i) != null) {
        			month_avg_temp.put(i, (double)32+(9.0/5.0)* (month_total_temp.get(i)/10.0)/month_size.get(i));
        		}
        	}
        }
	}
	//make internal variables accessable via methods
	public List<String> get_entire_file(){
		return entire_file;
	}
	public double get_avg_temp_c() {
        return avg_temp_c;
	}
	public double get_avg_temp_f() {
        return avg_temp_f;
	}
	public double get_size() {
        return size;
	}
	public String get_location() {
		return location;
	}
	public Dictionary<Integer, Double> get_monthly_average(){
		return month_avg_temp;
	}
}
