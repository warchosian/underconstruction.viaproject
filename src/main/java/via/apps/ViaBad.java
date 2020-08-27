package via.apps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment.Variable;

//https://www.codota.com/code/java/classes/org.apache.tools.ant.taskdefs.ExecTask
//https://www.codota.com/code/java/methods/org.apache.tools.ant.taskdefs.ExecTask/setFailIfExecutionFails

class ViaBad {

	public static void main(String[] args) throws IOException {
		ViaBad via= new ViaBad();
		via.exec();
	}
		
	void exec() throws IOException {
		String appName="textpad";
		Properties prop = new Properties();
		File propFile = new File("conf_home/applications.properties");

		InputStream inputStream = new FileInputStream(propFile);

		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new FileNotFoundException("property file " + propFile.getCanonicalPath() + " not found in the classpath");
		}

		Date time = new Date(System.currentTimeMillis());

		// get the property value and print it out
		String executable = prop.getProperty("application." + appName + ".executable");
		System.out.println(executable);
		
		
		Project projet = new Project();

		ExecTask exec = new ExecTask();

		exec.setProject(projet);

		exec.setSpawn(true);
		Variable var1 = new Variable();
		var1.setKey("SAYS");
		var1.setValue("HELLO");
		exec.addEnv(var1);
		Commandline.Argument arg = exec.createArg();
		arg.setValue("CeFichier.txt");
		//String cmd = "\"C:\\NovoLife\\Local\\SoftInstallations\\Notepad++\\npp.7.8.6\\notepad++.exe\"";
		
		String cmd = executable;

		System.out.println("Execution de : " + cmd);

		exec.setExecutable(cmd);

		exec.setSpawn(false);
		exec.setResultProperty("result");
		exec.setOutputproperty("output");

		exec.execute();

		String result = projet.getProperty("result");
		String output = projet.getProperty("output");

		System.out.print("exec return code " + result + ": " + output);

		System.out.println("-- Fin d'execution");
		}

	}

