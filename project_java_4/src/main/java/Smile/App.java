package Smile;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.csv.CSVFormat;

import smile.io.Read;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.data.measure.NominalScale;
import smile.data.vector.IntVector;
import smile.data.vector.StringVector;
import smile.classification.RandomForest;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        DataFrame trainData = readCSV("src/main/resources/data/titanic_train.csv");
        System.out.println("Training Data information: \n" + trainData.structure());
        System.out.println("Statistical Summary: \n"+ trainData.summary());
        System.out.println("Press Enter to continue");
        System.in.read();
        
        System.out.println("======================Encoding Non Numeric Data======================");
        trainData = trainData.merge(IntVector.of("SexEncoded", encodeColumn(trainData, "Sex")));
        trainData = trainData.merge(IntVector.of("PClassEncoded", encodeColumn(trainData, "Pclass")));
        System.out.println("Training Data information (After encoding): \n" + trainData.structure());
        System.out.println("Press Enter to continue");
        System.in.read();
        
        System.out.println("======================Dropping the non-encoded columns and the Name column======================");
        trainData = trainData.drop ("Pclass", "Sex");
        System.out.println (trainData.structure ());
        System.out.println("Press Enter to continue");
        System.in.read();
        
        System.out.println("======================Omitting null Rows======================");
        trainData = trainData.omitNullRows();
        System.out.println("Statistical Summary: \n"+ trainData.summary ());
        System.out.println("Press Enter to continue");
        System.in.read();
        
        System.out.println("======================Training Random Forest model======================");
        RandomForest model = RandomForest.fit(Formula.lhs("Survived"), trainData);
        System.out.println("Model Metrics: \n" + model.metrics ());
        System.out.println("Press Enter to continue");
        System.in.read();
        
        System.out.println("======================preparing the testing data======================");
        DataFrame testData = loadTestData("src/main/resources/data/titanic_test.csv");
        int[] result = model.predict(testData);
        for (int i:result) {
        	System.out.print(i);
        }
    }
    
    
    
    
    
    public static DataFrame readCSV(String filePath) {
    	CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader ().withDelimiter(',');
    	DataFrame df = null;
    	
    	try {
			df = Read.csv(filePath, format);
			df = df.select("Pclass", "Age", "Sex", "Survived");
			System.out.println(df.summary());
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
  
    	return df;
    }
    
    public static int[] encodeColumn(DataFrame df, String columnName) {
    	String[] categories = df.stringVector(columnName).distinct().toArray(new String[0]);
    	int[] encoded = df.stringVector(columnName).factorize(new NominalScale(categories)).toIntArray();
    	return encoded;
    }
    
    
    public static DataFrame loadTestData(String filePath) {
    	CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader ().withDelimiter(',');
    	DataFrame df = null;
    	
    	try {
			df = Read.csv(filePath, format);
			df = df.select("Pclass", "Age", "Sex");
			System.out.println(df.structure());
			df = df.merge(IntVector.of("SexEncoded", encodeColumn(df, "Sex")));
			//Pclass is an int column, we'll change it to string column in order to perform the same encoding we did in the traing data
			df = df.merge(StringVector.of("pClassString", df.intVector("Pclass").toStringArray()));
			df = df.merge(IntVector.of("PClassEncoded", encodeColumn(df, "pClassString")));
	        df = df.drop ("Sex", "Pclass", "pClassString");
	        df = df.omitNullRows();
			System.out.println(df.structure());
			System.out.println(df.summary());
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
    	
    	return df;
    }
}
