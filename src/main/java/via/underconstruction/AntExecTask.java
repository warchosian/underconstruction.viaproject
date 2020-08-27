package via.underconstruction;

import java.io.IOException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment.Variable;

//https://www.codota.com/code/java/classes/org.apache.tools.ant.taskdefs.ExecTask
//https://www.codota.com/code/java/methods/org.apache.tools.ant.taskdefs.ExecTask/setFailIfExecutionFails

class AntExecTask {
	
	public static void main(String[] args) throws IOException {
		Project projet = new Project();

		ExecTask exec = new ExecTask();
		
		exec.setProject(projet);

		exec.setSpawn( true );
		Variable var1 =new Variable();
		var1.setKey("SAYS");
		var1.setValue("HELLO");
		exec.addEnv(var1);
		Commandline.Argument arg = exec.createArg();
		arg.setValue("CeFichier.txt");
		String cmd0 = "\"C:\\NovoLife\\Local\\SoftInstallations\\Notepad++\\npp.7.8.6\\notepad++.exe\"";
		String cmd = "C:\\NovoLife\\Local\\SoftInstallations\\Notepad++\\npp.7.8.6\\notepad++.exe";
		//String cmd = "C:\\NovoLife\\Local\\SoftInstallations\\TextPad\\textpad.8.4.1\\TextPad.exe";
		System.out.println("Execution de : " + cmd);
		//exec.setExecutable("\"C:\\NovoLife\\Local\\SoftInstallations\\Notepad++\\npp.7.8.6\\notepad++.exe\"");
		// Execution de : "C:\NovoLife\Local\SoftInstallations\Notepad++\npp.7.8.6\notepad++.exe"
		//exec.setExecutable("\"C:\\NovoLife\\Local\\SoftInstallations\\TextPad\\textpad.8.4.1\\TextPad.exe\"");
		exec.setExecutable(cmd0);
		//exec.setExecutable("D:\\Softs\\Notepad++\\notepad++.exe");
//		String resultProperty = "";
//		exec.setResultProperty(resultProperty);
		
		
		  exec.setResultProperty("result");
		  exec.setOutputproperty("output");
		// IMPORTANT pour recupérer le retour  
		 // https://www.codota.com/code/java/methods/org.apache.tools.ant.taskdefs.ExecTask/setTaskType
		exec.setSpawn(false);
		exec.execute();
		
		String result = projet.getProperty("result");
		  String output = projet.getProperty("output");
		  
		  System.out.print("exec return code " + result + ": " + output);
		
		
	
	
		System.out.println("-- Execution OK");
		
		
	}

}