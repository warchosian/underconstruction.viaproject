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

public class ListApps  {

	public static void main(String[] args) throws ConfigurationException {
		File file = new File("via.properties");
			PropertiesConfiguration config = new PropertiesConfiguration(file);

	
		Iterator<String> keys = config.getKeys();
		
		String var="";
		String val="";
		while(keys.hasNext()) {
			var=keys.next();
			if(var.startsWith("application.") && var.endsWith(".executable")) {
				val=config.getString(var);
			    System.out.println(var+ "=" + val);
			}
		}
		
		
		
	}
}