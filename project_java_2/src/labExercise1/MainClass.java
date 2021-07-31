package labExercise1;

import java.util.List;
import java.util.Map;

public class MainClass {

	public static void main(String[] args) {
		Map<Country,List<City>> countriesList = CountriesCSVDAO.mapCountriesAndCities("D:\\Course\\Workspace\\Java\\Day 3 Java\\cities_cleaned.csv", "D:\\Course\\Workspace\\Java\\Day 3 Java\\countries_cleaned.csv");
		
		countriesList.forEach((country,cities) -> {
			cities.sort((c1,c2)-> Integer.compare(c1.getPopulation(), c2.getPopulation()));
			System.out.println(country.getCountry()+" Cities: "+cities);
		});
		
		
		
	}

}
