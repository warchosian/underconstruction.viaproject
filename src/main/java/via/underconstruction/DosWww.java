package via.underconstruction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

public class DosWww {
	

	public static void main(String[] args) throws IOException, InterruptedException {
		DosWww msdos = new DosWww();
		
		msdos.executeWww();

	}
	
	
	
	public void executeWww() throws IOException, InterruptedException {
		String urlDownload = "https://www.textpad.com/download";
		String urlGuide = "https://webhome.cs.uvic.ca/~mserra/TextPadHOW/TextPadMain.html";
		
		File tempScript = createTempBatFile(urlDownload);
		System.out.println(tempScript.toString());

		try {
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", tempScript.toString());

			// ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "set");
			// ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "execution.txt");
			Map<String, String> env = pb.environment();
			// set environment variable u
			//env.clear();
			env.put("MyVar", "Herve");

			pb.inheritIO();
			Process process = pb.start();
			process.waitFor();
		} finally {
			tempScript.delete();
		}
		System.out.println("=== Fin de traitement");
	}

	public File createTempBatFile(String url) throws IOException {
		File tempBatFile = File.createTempFile("cmd", ".bat");

		Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempBatFile));
		PrintWriter printWriter = new PrintWriter(streamWriter);

		printWriter.println("start " + url);
		//printWriter.println("set");

		printWriter.close();

		return tempBatFile;
	}

}
