package via.underconstruction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

public class Bash {

	public static void main(String[] args) throws IOException, InterruptedException {
		Bash bash = new Bash();
		bash.executeCommands();

	}
	
	public void executeCommands() throws IOException, InterruptedException {

	    File tempScript = createTempScript();

	    try {
	        ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", tempScript.toString());
	        Map<String, String> env = pb.environment();
	        //set environment variable u
	        env.clear();
	        env.put("MyVar", "Herve");
	      
	     
			pb.inheritIO();
	        Process process = pb.start();
	        process.waitFor();
	    } finally {
	        tempScript.delete();
	    }
	}

	public File createTempScript() throws IOException {
	    File tempScript = File.createTempFile("script", ".sh");

	    Writer streamWriter = new OutputStreamWriter(new FileOutputStream(
	            tempScript));
	    PrintWriter printWriter = new PrintWriter(streamWriter);

	    printWriter.println("#!/bin/bash");
	    printWriter.println("pwd");
	    printWriter.println("cd ..");
	    printWriter.println("pwd");
	    printWriter.println("cd ..");
	    printWriter.println("pwd");
	    printWriter.println("cd ..");
	    printWriter.println("pwd");
	    printWriter.println("ls -l");
	    
	    printWriter.println("ls");
	    printWriter.println("env");

	    printWriter.close();

	    return tempScript;
	}

}
