package labExercise1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountriesCSVDAO {
	
	public static Map<Country,List<City>> mapCountriesAndCities (String cityFileName, String countryFileName){
		
		Map<Country,List<City>> countriesAndCities = new HashMap<Country,List<City>>();
		
		//read countries from CSV:
		List<Country> countries = (List<Country>) readFromCSV(countryFileName, Country.class);
		//read cities from CSV:
		List<City> cities = (List<City>) readFromCSV(cityFileName, City.class);
		
		//Map each country to its cities
		for (Country country: countries) {
			countriesAndCities.put(country, cities.stream()
											.filter(city -> country.getCode().equals(city.getCode()))
											.collect(Collectors.toList()));
		}
		
		return countriesAndCities;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List readFromCSV(String fileName, Class c) {
		List objects = new ArrayList();
		File CSVFile = new File(fileName);
		List<String> lines = new ArrayList<String>();
		
		try {
			lines = Files.readAllLines(CSVFile.toPath()); //Read the content of the file as lines
		} catch (IOException e) {
			System.out.println("while reading the file "+ fileName + ", an error occured: \n" + e);
		}
		
		//Extract fields from each line and create City objects or Country objects:
		for (int l=1; l<lines.size(); l++) {
			String[] fields = lines.get(l).split(";",-1);
			
			for (int i=0; i<fields.length; i++) {
				fields[i] = fields[i].trim();
			}
			
			if (c.getName().equals(City.class.getName())) {
				City city = createCity(fields);
				objects.add(city);
			} else if (c.getName().equals(Country.class.getName())){
				Country country = createCountry(fields);
				objects.add(country);
			}
			
		}
		
		return objects;
	}
	
	public static List<Country> readCountriesFromCSV(String fileName){
		List<Country> countries = new ArrayList<Country>();
		File countriesCSV = new File(fileName);
		List<String> lines = new ArrayList<String>();
		
		try {
			lines = Files.readAllLines(countriesCSV.toPath()); //Read the content of the file as lines
		} catch (IOException e) {
			System.out.println("while reading the file "+ fileName + ", an error occured: \n" + e);
		}
		
		//Extract fields from each line and create City objects:
		for (int l=1; l<lines.size(); l++) {
			String[] fields = lines.get(l).split(";",-1);
			for (int i=0; i<fields.length; i++) {
				fields[i] = fields[i].trim();
			}
			Country country = createCountry(fields);
			countries.add(country);
		}
		
		return countries;
	}
	


	public static List<City> readCitiesFromCSV(String fileName) {
		List<City> cities = new ArrayList<City>();
		File citiesCSV = new File(fileName);
		List<String> lines = new ArrayList<String>();
		
		try {
			lines = Files.readAllLines(citiesCSV.toPath()); //Read the content of the file as lines
		} catch (IOException e) {
			System.out.println("while reading the file "+ fileName + ", an error occured: \n" + e);
		}
		
		//Extract fields from each line and create City objects:
		for (int l=0; l<lines.size(); l++) {
			String[] fields = lines.get(l).split(";",-1);
			for (int i=0; i<fields.length; i++) {
				fields[i] = fields[i].trim();
			}
			
			
			City city = createCity(fields);
			cities.add(city);
		}
		
		return cities;
	}
	

	private static Country createCountry(String[] fields) {
		String country = fields[0].replace("\"", "");
		String code = fields[1].replace("\"", "");
		
		return new Country(country, code);
	}


	private static City createCity(String[] fields) {
		
		int cityId;
		try {
			cityId = Integer.parseInt(fields[0].replace("\"", ""));
		} catch (NumberFormatException e1) {
			cityId = 0;
		}
		String city = fields[1].replace("\"", "");
		String countryId = fields[2].replace("\"", "");
		int population;
		try {
			population = Integer.parseInt(fields[4].replace("\"", ""));
		} catch (NumberFormatException e1) {
			population =0;
		}
		String capital;
		try {
			capital = fields[3].replace("\"", "");
		} catch (Exception e) {
			capital = null;
		}
		
		return new City(cityId, city, countryId, capital, population);
	}
}
