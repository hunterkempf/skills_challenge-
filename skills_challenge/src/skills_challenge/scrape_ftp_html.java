package skills_challenge;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;



public class scrape_ftp_html {
	//get ftp structure from page
	private HashMap<String,Integer> file_map = new HashMap<String,Integer>();
	private List<String> files = new ArrayList<String>();
	public scrape_ftp_html(String website) throws SocketException, IOException {
		URL url = new URL(website);
        URLConnection urlConnection = (URLConnection)url.openConnection();
        Scanner sc = new Scanner(new InputStreamReader(urlConnection.getInputStream()));
        String current_line;
        while(sc.hasNextLine()) {
        	current_line = sc.nextLine();
        	String filename =current_line.split("\\s+")[8];
        	// add files but not pdfs or txt files
        	if(filename.split("\\.").length <2) {
            	files.add(filename);
            	System.out.println(filename);
            }
        	else {
            	if(filename.split("\\.")[1].equals("gz")) {
            		files.add(filename);
            		System.out.println(filename);
            		// Add weather station name to file_map or increment counter
            		String key = String.format("%s-%s", filename.split("-")[0],filename.split("-")[1]);
            		if(file_map.containsKey(key)) {
            			int value = file_map.get(key)+1;
            			file_map.put(key, value);
            		}else {
            			file_map.put(key, 1);
            		}
            		
            		
            	}
            }
        }
        sc.close();
	}
	public List<String> get_array(){
		return files;
	}
	public HashMap<String,Integer> get_HashMap(){
		return file_map;
	}
}
