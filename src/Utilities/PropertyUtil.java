package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	public Properties ReadProperty(String FileName) throws IOException
	{
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream(FileName);
			prop.load(input);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		
	}

}
