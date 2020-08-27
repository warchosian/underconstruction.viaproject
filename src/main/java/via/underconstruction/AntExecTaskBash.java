package via.underconstruction;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment.Variable;

//https://www.codota.com/code/java/classes/org.apache.tools.ant.taskdefs.ExecTask
//https://www.codota.com/code/java/methods/org.apache.tools.ant.taskdefs.ExecTask/setFailIfExecutionFails

class AntExecTaskBash {
	
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
		arg.setValue("-c");
		arg.setValue("ls");
		
		String app = "G:\\WarchoLife\\WarchoPortable\\PortableCommon\\DockerGitShells\\Git\\bin\\bash.exe";
	
		File appFd = new File(app);
		if(appFd.exists()) {
			System.out.println("exists " + appFd.getAbsolutePath());
		}
		
		if(appFd.canExecute()) {
			System.out.println("canExecute " + appFd.getAbsolutePath());
		}
		exec.setExecutable(app);

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