package via.modules.warch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class Warch {

	public static void main(String[] args) throws IOException, InterruptedException {
		Warch bash = new Warch();
		bash.executeCommands();

	}
	
	public void executeCommands() throws IOException, InterruptedException {

	    File tempScript = createTempScript();

	    try {
	    	System.out.println("01. New ProcesBuilder");
	        ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Git\\bin\\bash.exe", tempScript.toString());
	        System.out.println("02. InheritIO");
	        pb.inheritIO();
	        System.out.println("03. Start");
	        Process process = pb.start();
	        System.out.println("04. WaitFor");
	        process.waitFor();
	        System.out.println("04. End WaitFor");
	    } finally {
	    	System.out.println("05. Delete");
	        tempScript.delete();
	        System.out.println("06. End Delete");
	    }
	}

	public File createTempScript() throws IOException {
		System.out.println("11. CreateTempFile");
	    File tempScript = File.createTempFile("script", null);

	    System.out.println("12. OutputStreamWrite");
	    Writer streamWriter = new OutputStreamWriter(new FileOutputStream(
	            tempScript));
	    System.out.println("13. PrintWriter");
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
	    printWriter.println("df");

	    System.out.println("14. End PrintWriter");
	    
	    System.out.println("15. Close");
	    printWriter.close();
	    System.out.println("16. End Close");

	    return tempScript;
	}

}
