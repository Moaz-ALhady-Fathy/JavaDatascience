package labExercise1;

public class City {
	private int cityId;
	private String city;
	private String code;
	private String capital;
	private int population;
	
	public City(int cityId, String city, String code, String capital, int population) {
		this.cityId = cityId;
		this.city = city;
		this.code= code;
		this.capital = capital;
		this.population = population;
	}
	
	public int getCityId() {
		return cityId;
	}
	
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCapital() {
		return capital;
	}
	
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", city=" + city + ", capital=" + capital + ", population=" + population
				+ "]";
	}
	
}
