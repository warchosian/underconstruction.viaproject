package via.underconstruction;

import java.io.IOException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment.Variable;

//https://www.codota.com/code/java/classes/org.apache.tools.ant.taskdefs.ExecTask
//https://www.codota.com/code/java/methods/org.apache.tools.ant.taskdefs.ExecTask/setFailIfExecutionFails

class PandocHelpSansOptionO {

	public static void main(String[] args) throws IOException {
		Project projet = new Project();

		ExecTask exec = new ExecTask();

		exec.setProject(projet);

		Commandline.Argument arg = exec.createArg();

		// pandoc" , "text.md" ,"-o", "text8.html"};
//		arg.setValue("/c");
//		arg.setValue("C:\\NovoLife\\Local\\SoftInstallations\\Notepad++\\npp.7.8.6\\notepad++.exe");
		arg.setValue("-h");
//		arg.setValue("G:\\WarchoLife\\WarchoPortable\\CommonDevs\\Pandoc\\pandoc.exe");
//		arg.setValue("text.md");
//		arg.setValue("-o");
//		arg.setValue("text66.html");

		//String cmd = "C:\\\\NovoLife\\\\Local\\\\SoftInstallations\\\\Notepad++\\\\npp.7.8.6\\\\notepad++.exe";
		String cmd = "G:\\WarchoLife\\WarchoPortable\\CommonDevs\\Pandoc\\pandoc.exe";

		System.out.println("Execution de : " + cmd);
		// exec.setExecutable("\"C:\\NovoLife\\Local\\SoftInstallations\\Notepad++\\npp.7.8.6\\notepad++.exe\"");
		// Execution de :
		// "C:\NovoLife\Local\SoftInstallations\Notepad++\npp.7.8.6\notepad++.exe"
		// exec.setExecutable("\"C:\\NovoLife\\Local\\SoftInstallations\\TextPad\\textpad.8.4.1\\TextPad.exe\"");
		exec.setExecutable(cmd);

		exec.setResultProperty("result");
		exec.setOutputproperty("output");
		// IMPORTANT pour recupérer le retour
		// https://www.codota.com/code/java/methods/org.apache.tools.ant.taskdefs.ExecTask/setTaskType

		//exec.setFailIfExecutionFails(true);
		try {
			exec.setSpawn( false );
			//exec.setSpawn( false );
			exec.execute();
		} catch (Exception ioe) {
			System.err.println("#Interception erreur : " + ioe.getMessage());
		}

		String result = projet.getProperty("result");
        String output = projet.getProperty("output");
        
        System.out.println("exec output " + output);
//		  
		System.out.println("exec return code " + result);
//		  System.out.print("exec return code " + result + ": " + output);

		System.out.println("-- Execution OK");

	}

}