package labExercise1;

public class Country {
	private String country;
	private String code;
	
	public Country(String country, String code) {
		this.code= code;
		this.country = country;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return  this.country;
	}

	
}
