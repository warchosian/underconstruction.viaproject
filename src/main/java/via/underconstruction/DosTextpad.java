package via.underconstruction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

public class DosTextpad {

	public static void main(String[] args) throws IOException, InterruptedException {
		DosTextpad bash = new DosTextpad();
		bash.executeCommands();

	}
	
	public void executeCommands() throws IOException, InterruptedException {

	    File tempScript = createTempScript();
	    System.out.println(tempScript.toString());

	    try {
	        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", tempScript.toString());
	    	//ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "set");
	    	//ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "execution.txt");
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
	    File tempScript = File.createTempFile("cmd", ".bat");

	    Writer streamWriter = new OutputStreamWriter(new FileOutputStream(
	            tempScript));
	    PrintWriter printWriter = new PrintWriter(streamWriter);

	    printWriter.println("C:\\NovoLife\\Local\\SoftInstallations\\TextPad\\textpad.8.4.1\\TextPad.exe");
	   

	    printWriter.close();

	    return tempScript;
	}

}
