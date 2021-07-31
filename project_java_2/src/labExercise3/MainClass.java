package labExercise3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import labExercise1.City;
import labExercise1.CountriesCSVDAO;
import labExercise1.Country;

public class MainClass {

	public static void main(String[] args) {
		Map<Country,List<City>> countriesList = CountriesCSVDAO.mapCountriesAndCities("D:\\Course\\Workspace\\Java\\Day 3 Java\\cities_cleaned.csv", "D:\\Course\\Workspace\\Java\\Day 3 Java\\countries_cleaned.csv");
		
		//Remove countries with 0 cities:
		List<Country> emptyCountries = new ArrayList<Country>();
		
		System.out.println("No. of countries before removing empty countries="+countriesList.size());
		
		countriesList.forEach((country,cities)->{ if (cities.size()==0) {
			emptyCountries.add(country);
			}});
		emptyCountries.forEach(Country -> countriesList.remove(Country));
		
		System.out.println("No. of countries after removing empty countries="+countriesList.size());
		
		
		//get highest population city in each country:
		Map<Country,City> highestPopulation = new HashMap<Country,City>();
		countriesList.forEach((country,cities)->{
			highestPopulation.put(country, cities.stream()
										   .reduce((c1,c2)-> c1.getPopulation()>= c2.getPopulation()? c1 : c2)
										   .get());
		});
		
		System.out.println("Highest population city per country: \n"+highestPopulation);
		
		//get highest population capital:
		List<City> cities = CountriesCSVDAO.readCitiesFromCSV("D:\\Course\\Workspace\\Java\\Day 3 Java\\cities_cleaned.csv");
		
		
		City highestPopulationCapital = cities.stream()
											  .filter(city-> city.getCapital().equals("primary"))
											  .max((c1,c2)-> c1.getPopulation()-c2.getPopulation())
											  .get();
		System.out.println("highest population capital:"+highestPopulationCapital);
	}

}
