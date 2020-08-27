package via.underconstruction;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment.Variable;

//https://www.codota.com/code/java/classes/org.apache.tools.ant.taskdefs.ExecTask
//https://www.codota.com/code/java/methods/org.apache.tools.ant.taskdefs.ExecTask/setFailIfExecutionFails

class ExecBash {
	
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
		
		System.out.println(app);
		String command = "cmd /c start cmd.exe /K " + appFd.getAbsolutePath() + " -c ls -l";
		System.out.println(command);
		
		if(appFd.exists()) {
			System.out.println("exists " + appFd.getAbsolutePath());
		}
		
		if(appFd.canExecute()) {
			System.out.println("canExecute " + appFd.getAbsolutePath());
		}
		
		Process process  = Runtime.getRuntime().exec(command);
		
		
		
	
		System.out.println("-- Execution OK");
		
		
	}

}