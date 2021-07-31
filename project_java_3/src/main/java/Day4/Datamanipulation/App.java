package Day4.Datamanipulation;

import java.io.IOException;
import java.util.List;

import joinery.DataFrame;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

public class App {
  public static void main(String[] args) {
	  useJoinery();
	  useTablesaw();
  }
  
  public static void useJoinery() {
	//Using Joinery to create and manipulate dataframes:
	    try {
	    	DataFrame<Object> df = DataFrame.readCsv("src\\main\\resources\\data\\titanic.csv");
	    	DataFrame<Object> df2 = DataFrame.readCsv("src\\main\\resources\\data\\titanic.csv");
	    	System.out.println("#Summary of Titanic data:\n"+df.describe());
	    	System.out.println("=============================================================================================");
	    	//merging and manipulating columns:
	    	df2 = df2.retain("pclass", "survived", "name");
	    	System.out.println("#View of original data in df2:\n"+df2.head(3));
	    	List<Object> col = df.col("home.dest");
	    	df2.add("home.dest", col);
	    	System.out.println("=============================================================================================");
	    	//Merging data frames together:
	    	df2 = df.retain("ticket", "survived", "name");
	    	DataFrame<Object> dfMerged = df.merge(df2);
	    	System.out.println("#Merged data:\n"+dfMerged.head(10));

		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    
  }
	public static void useTablesaw() {
	    try {
			Table titanic = Table.read().csv("src\\main\\resources\\data\\titanic.csv");
			System.out.println(titanic.structure());
			System.out.println("#Summary of Titanic data:"+titanic.summary());
			System.out.println("=============================================================================================");
			//Create a new column from the "home.dest" column and add it to the table:
			StringColumn col = StringColumn.create("from.to",(List<String>) titanic.column("home.dest").asList());
			titanic.insertColumn(3, col);
			System.out.println("#View of modified data in df2:\n"+titanic.first(5));
			
	    } catch (IOException e) {
			e.printStackTrace();
		}
	}
}

