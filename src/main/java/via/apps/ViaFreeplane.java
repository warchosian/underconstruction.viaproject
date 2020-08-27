package via.apps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment.Variable;

/**
 * @author Crunchify.com
 * 
 */

public class ViaFreeplane {
	static String result = "";
	InputStream inputStream;

	public static void main(String[] args) throws ConfigurationException, IOException {
		boolean ok = true;
		File propertyFile = null;
		String applicationName = null;
		boolean applicationNameFound = false;
		File applicationFile = null;
		PropertiesConfiguration propertiesConfiguration = null;
		String applicationPath = null;
		String executableName = null;
		Project project = null;
		ExecTask execTask = null;
	
		String viaCommand = null;
		String sysCommand = null;
		File dirFile = null;
		String dirPath = null;
		
		System.out.println("* Repertoire de travail: " + System.getProperty("user.dir"));
		//System.out.println("* working directory 2: " + new File(".").getCanonicalPath());
		

				project = new Project();
				execTask = new ExecTask();
				execTask.setProject(project);
				
		
			
				execTask.setSpawn(true);
				execTask.setNewenvironment(true);
				
				Commandline.Argument arg = execTask.createArg();
				dirPath="G:/WarchoLife/WarchoPrivate/Amis/HerveMarchal/Quotidien";
				dirPath = dirPath.replace("/", "\\");
				dirFile= new File(dirPath);
				
				execTask.setDir(dirFile);
				

				applicationPath="G:/WarchoLife/WarchoPortable/PortableCommon/FreeplanePortable/FreeplanePortable-1.7.10/FreeplanePortable.exe";

				applicationPath = applicationPath.replace("/", "\\");
				// System.out.println("* execution: " + applicationPath);
				applicationFile = new File(applicationPath);
	

				execTask.setExecutable(applicationPath);
				execTask.setResultProperty("result");
				execTask.setOutputproperty("output");
				execTask.setSpawn(false);
				
					
					execTask.execute();

					result = project.getProperty("result");
					String output = project.getProperty("output");
					System.out.println("\n* =========> Resultat du traitement");
					System.out.print("* output:   [");
					//Thread.sleep(500);
					System.out.print(output);
					System.out.println("]");
					//Thread.sleep(500);
					System.out.print("* exitcode: [");
					//Thread.sleep(500);
					System.out.print(result);
					System.out.println("]");

				
			
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ok) {
			System.out.println(
					"* <========= Fin du traitement en succes avec errorlevel=[" + Integer.parseInt(result) + "]");
			System.exit(Integer.parseInt(result));
		} else {
			System.err.println("# ========= Fin du traitement en echec avec errorlevel=[99]");
			System.exit(99);
		}

	}

}
