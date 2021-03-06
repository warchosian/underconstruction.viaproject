package via.underconstruction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

/**
 * @author Crunchify.com
 * 
 */

public class ListProperties3  {
	String result = "";
	InputStream inputStream;

	public String getPropValues() throws IOException {

		try {
			
			Properties prop = new Properties();
			String propFileName = "via.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			Date time = new Date(System.currentTimeMillis());
			
		
			//prop.load(new FileInputStream("prop.properties"));
			Set<Map.Entry<Object, Object>> set = prop.entrySet();
			List<Object> list = new ArrayList<>();
			for (Map.Entry<Object, Object> entry : prop.entrySet())
			{
			   list.add(entry.getKey());
			}
			System.out.println(list);
			// get the property value and print it out
			String user = prop.getProperty("user");
			String company1 = prop.getProperty("company1");
			String company2 = prop.getProperty("company2");
			String company3 = prop.getProperty("company3");

			result = "Company List = " + company1 + ", " + company2 + ", " + company3;
			System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}

	public String getPropVarsValues0() throws FileNotFoundException  {
		String propFileName = "src/main/resources/config.properties";
		

		CompositeConfiguration config = new CompositeConfiguration();

		config.addConfiguration(new SystemConfiguration());
		try {
			config.addConfiguration(new PropertiesConfiguration(propFileName));
		} catch (ConfigurationException e) {
			System.err.println(e);
		}

		String val1 = config.getString("company1");
		String val2 = config.getString("company2");
		System.out.println("company1=" + val1 + ", company2=" + val2);

		
		
		return result;

	}
	

	
	public static void main(String[] args) throws ConfigurationException {
		File file = new File("via.properties");
	
	

		PropertiesConfiguration config = new PropertiesConfiguration(file);

		
		String val1 = config.getString("company1");
		String val2 = config.getString("company2");
		String val3 = config.getString("company3");
		System.out.println("company1=" + val1 + ", company2=" + val2 + ", company3=" + val3);
		
		String x1 = config.getString("application.textpad.home");
		String x2= config.getString("application.textpad.executable");
		
		
	
		System.out.println("application1=" + x1 + ", application2=" + x2 );

		Iterator<String> keys = config.getKeys();
		
		String var="";
		String val="";
		while(keys.hasNext()) {
			var=keys.next();
			val=config.getString(var);
		    System.out.println(var+ "=" + val);
		}
		
		
		
	}
}