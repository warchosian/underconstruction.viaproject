package via.underconstruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchArgs {
	public static void main(String[] args) {
		String line = "xx -dir C:/toto/titi\\tutu 999 aaa";
		String pattern = ".*-dir\\s([a-zA-Z:/\\\\_]*).*";

	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);

	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	         //System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	     
	      }else {
	         System.out.println("NO MATCH");
	      }
	}
}
