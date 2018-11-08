# skills_challenge-
Skills Challenge for Hypergraph interview

This project looks at weather data found in ftp://ftp.ncdc.noaa.gov/pub/data/noaa/ and uses java to get and clean the data and R to visualize it.
The java files are in the src folder and the R visualizations can be seen in skills_challenge.nb.html and the R code skills_challenge.Rmd

The included solution is by no means the fastest or optimal solution that could have been achieved if more time was spent on it. Also the visualizations were a small portion of time spent because the main focus was on java and the backend coding.

Description of included files:

main.java : 
      its purpose is to show functionality of included java 
  
get_data:
      its purpose is to go to the website link given to it in the constructor and parse through the GZip file and return file average temp(in degrees F or C as a double), monthly temp averages(in a Dictionary with month number as key and average as value), or the entire document (in a list of strings)

scrape_ftp_html: 
    its purpose is to scrape the ftp server and get a list of folders or a list of .gz files that the ftp server url provided has and return those as a list of strings it also will count the weather station ID in the .gz files and return a HashMap with the weather station ID and a count of the times it appears in that ftp folder.
    
single_site_every_year:
    its purpose is to take in an ftp server url and weather station ID and output a csv file with monthly average temperatures for every year that the weather station has data on the ftp for. using scrape_ftp_html and get_data
    
weather_site_freq:
    its purpose is to take in a url for the ftp server and output a csv with weather sites and the corresponding times they appear in the ftp to allow for selection of a single site in single_site_every_year that has a sufficient number of years of data with out trying to manually look through the ftp folders. It can also be used to get a list of all weather station IDs in the ftp that is used in the get_all_sites_all_years class.
    
get_all_sites_all_years:
    its purpose is to take in a url for the ftp server and output csv files for every weather station by calling single_site_every_year for each weather station in the list of weather stations from weather_site_freq. Which admittedly takes a long time and is not optimized

