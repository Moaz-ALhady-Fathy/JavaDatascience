package Day5.XChart;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class App 
{
    public static void main( String[] args )
    {
    	List<TitanicPassenger> passengerList =readPassengersFromJSON("titanic_csv.json");
//    	graphAge(passengerList);
    	graphPassengerClass(passengerList);
    	
    }
    
    public static void graphAge(List<TitanicPassenger> passengerList) {
    	//get a list of passenger ages and a list of passenger names:
    	List<Float> Ages = passengerList.stream ().map (TitanicPassenger::getAge).limit(20).collect (Collectors.toList ());
    	List<String> Names = passengerList.stream ().map (TitanicPassenger::getName).limit(20).collect (Collectors.toList ());
    	
    	//Create and configure an empty chart:
    	CategoryChart chart = new CategoryChartBuilder()
    								.width(1024)
    								.height(768)
    								.title("Age Histogram")
    								.xAxisTitle("Names")
    								.yAxisTitle("Ages")
    								.build();
    	//Customize the chart:
    	chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
    	chart.getStyler().setHasAnnotations(true);
    	chart.getStyler().setStacked(false);
    	
    	//Add the data to the chart:
    	chart.addSeries("Passengers Ages", Names, Ages);
    	
    	//Show the Chart:
    	new SwingWrapper(chart).displayChart();
    
    
    }
    
    public static void graphPassengerClass(List<TitanicPassenger> passengerList) {
    	//Map each class to the number of passengers it contains:
    	Map<String, Long> result = passengerList.stream()
    								.collect(Collectors.groupingBy(TitanicPassenger::getPclass, Collectors.counting ()));
		
    	// Create and configure an empty pie Chart
		PieChart chart = new PieChartBuilder()
								.width(800)
								.height(600)
								.title ("Number of passengers per class")
								.build ();
		// Customize Chart:
		//There are 3 passenger classes in the data, we give each class a color:
		Color[] sliceColors = new Color[]{new Color (100, 44, 150), new Color (130, 80, 90), new Color (12, 170, 110)};
		chart.getStyler ().setSeriesColors (sliceColors);
		
		// Add each passenger class to the chart:
		chart.addSeries ("First Class", result.get ("1"));
		chart.addSeries ("Second Class", result.get ("2"));
		chart.addSeries ("Third Class", result.get ("3"));
		
		// Show the graph
		new SwingWrapper(chart).displayChart();
    }
    
    public static List<TitanicPassenger> readPassengersFromJSON(String filePath){
    	//Create a list to hold the objects mapped from JSON file
    	List<TitanicPassenger> passengerList = new ArrayList<TitanicPassenger>();
    	
    	//Create object mapper:
    	ObjectMapper objectMapper = new ObjectMapper();
    	//Configure the mapper so that any unknown properties (or properties that don't have a setter in the object class) will be ignored to avoid UnrecognizedPropertyException.
    	objectMapper.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	
    	//Read the JSON file as an input stream and map its contents to objects:
    	try (InputStream input = new FileInputStream (filePath)) {
			passengerList = objectMapper.readValue(input, new TypeReference<List<TitanicPassenger>>(){});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		return passengerList;
    	
    }
}
