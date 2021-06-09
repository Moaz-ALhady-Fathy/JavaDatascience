

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;



public class pyramidDAO 
{
    
    private Pyramid createPyramid(String[] ListofStrings)
    {
        String pharaoh = ListofStrings[0] ;
        String modern_name = ListofStrings[2];
        String site = ListofStrings[4];
        String height = ListofStrings[7];
        
        return new Pyramid(pharaoh, modern_name, site, height);
        
    }

    public List<Pyramid> readPyramidFromCSV(String filename) 
    {
        File f=new File(filename);
        List<String> Lines=new ArrayList<String>();
        
        List<Pyramid> ALL_pyramids = new ArrayList<Pyramid>();
        
        try 
        {
            Lines = Files.readAllLines(f.toPath());
        } 
        catch (IOException ex) 
        {
            System.out.println("there is an issue reading pharoess infooo ");
        }
        
        for(String line : Lines) 
        { 
            String[] fields = line.split(",");
            for(int i=0 ;i<fields.length;i++)
            {
                fields[i]=fields[i].trim();
            }
            ALL_pyramids.add(createPyramid(fields));
        }
        
        return ALL_pyramids; 
    }
    
}
