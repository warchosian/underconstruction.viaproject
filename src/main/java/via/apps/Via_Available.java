package via.apps;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.configuration.ConfigurationException;
import org.xml.sax.SAXException;

import via.Via;

public class Via_Available {
	Logger log = Logger.getLogger(this.getClass().getSimpleName());

	// java -cp gen.jar/Transcos_V01.00.04.jar composants.specifiques.cir.transcos.ExtractionTranscos fichier-xlsx=transcos/donnees/entree/TRANSCOS.xlsx dossier-sortie=transcos/donnees/sortie-csv
	
	// executer(File ifdXlsx,File ifdXsd,File ifdXslErr,File oddGen
	public static void main(String[] args) throws IOException, TransformerConfigurationException, ParserConfigurationException, TransformerException, SAXException, ConfigurationException  {
		Logger mainlog = Logger.getLogger(Via_Available.class.getName());
		//String[] argsTest = new String[]{"fichier-xlsx=transcos/donnees/entree/TRANSCOS.xlsx","dossier-sortie=transcos/donnees/sortie-csv"};
		//String[] argsTest = new String[]{"fichier-entree-car=donnees/entree/AAP01.20150122_F4B.car","mode-generation=sans-num-lig","fichier-sortie-xml=donnees/sortie/AAP01.20150122_F4B.car.sans-num-lig.xml"};
		//String[] argsTest = new String[]{"fichier-entree-xml=donnees/sortie/AAP01.20150122_F4B.car.sans-num-lig.xml","fichier-sortie-car=donnees/sortie/AAP01.20150122_F4B.reverse.car"};
		//String[] argsTest = new String[]{"fichier-entree-xml=donnees/sortie/AAP01.20150122_F4B.car.trie.xml","fichier-sortie-car=donnees/sortie/AAP01.20150122_F4B.car.trie"};
		
		String[] argsTest = new String[]{
				 "-available"};
				
		
		Via.main(argsTest );
		
		mainlog.info("***** FIN !!! *****");
	}

	
}

