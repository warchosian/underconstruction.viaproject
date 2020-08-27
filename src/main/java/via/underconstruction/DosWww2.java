package via.underconstruction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DosWww2 {
	

	public static void main(String[] args) throws IOException, InterruptedException {
		DosWww2 bash = new DosWww2();
		
		bash.executeWww();

	}
	
	
	
	public void executeWww() throws IOException, InterruptedException {
		String urlDownload = "https://www.textpad.com/download";
		String urlGuide = "https://webhome.cs.uvic.ca/~mserra/TextPadHOW/TextPadMain.html";
		
		String urls[] = {urlDownload,urlGuide};
		
		File tempScript = createTempScript(urls);
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

	public File createTempScript(String urls[]) throws IOException {
		File tempScript = File.createTempFile("cmd", ".bat");

		Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript));
		PrintWriter printWriter = new PrintWriter(streamWriter);
		String urlsString = Arrays.asList( urls ).stream().collect( Collectors.joining( " " ) );
		System.out.println(urlsString);
		printWriter.println("start " + urlsString);
		//printWriter.println("set");

		printWriter.close();

		return tempScript;
	}

}
