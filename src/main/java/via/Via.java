package via;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.ExecTask;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment.Variable;

import via.modules.warch.Warch;

public class Via {
	static String result = "";
	InputStream inputStream;
	File propertyFile = null;
	private boolean simulate=false;

	public static void main(String[] args) throws ConfigurationException, IOException {
		Via via = new Via();
		try {

			via.execute(args);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void execute(String[] args) throws ConfigurationException, IOException, InterruptedException {
		boolean ok = true;

		String applicationName = null;
		boolean applicationNameFound = false;
		File applicationFile = null;
		PropertiesConfiguration propertiesConfiguration = null;
		String applicationPath = null;
		String executableName = null;
		Project project = null;
		ExecTask execTask = null;
		boolean spawn = true;

		String viaCommand = null;
		String sysCommand = null;
		String dirPath = null;
		String dirName = null;
		System.out.println("|| Projet via : 2020-08-26 14:55");
		System.out.println("|| ~ 2020-08-26 14:55 Evol: ajout de l'option -propfile");
		System.out.println("|| ~ 2020-08-25 22:15 Corr: absence de commande -> java.lang.NullPointerException");
		System.out.println("|| ~ 2020-08-25 21:55 Corr: option -available");

		System.out.println("- Options d'aide: -available, -about [app], -guide [app], -download [app], -install [app]");
		System.out.println("* Repertoire de travail: " + System.getProperty("user.dir"));
		// System.out.println("* working directory 2: " + new
		// File(".").getCanonicalPath());
		if (ok) {
			if (args.length == 0) {
				ok = false;
				System.out.println("# via doit être suivi d'une directive d'execution.");
			}

			if (ok) {
				propertyFile = new File("via.properties");
				if (!propertyFile.exists()) {
					System.err.println(
							"* Le fichier par defaut via.properties n'existe pas: " + propertyFile.getAbsolutePath());
				} else {
					System.out.println("* Fichier properties: " + propertyFile.getCanonicalPath());
					propertiesConfiguration = new PropertiesConfiguration(propertyFile);
				}

			}

			if (ok) {
				if (args.length == 1) {
					// ok = false;
					if (args[0].contentEquals("-warch")) {
						Warch bash = new Warch();
						try {
							bash.executeCommands();
							System.exit(1);
						} catch (IOException | InterruptedException e) {
							ok = false;
							e.printStackTrace();
						}
						System.exit(1);
					}
				}

			}

			if (ok) {

				// System.out.println("Application=" + applicationPath);

				project = new Project();
				execTask = new ExecTask();
				execTask.setProject(project);

			}
			if (ok == true) {
				execTask.setSpawn(true);
				execTask.setNewenvironment(true);
				sysCommand = "";
				viaCommand = "via";
				Commandline.Argument arg = execTask.createArg();
				boolean ignoreNext = false;
				for (int i = 0; i < args.length; i++) {
					System.out.println("args[" + i + "]=" + args[i]);
					if (args[i].contentEquals("-dir")) {
						ignoreNext = true;
						dirPath = args[i + 1];
						dirPath = dirPath.replace("/", "\\");
						System.out.println("* dir en verification :" + dirPath);

						File dirFile = new File(dirPath);
						if (!dirFile.isDirectory()) {
							ok = false;
							System.err.println("# dir n'est pas un repertoire : " + dirFile.getAbsolutePath());
						} else {
							System.out.println("* dir est un repertoire : " + dirFile.getCanonicalPath());
							sysCommand = dirFile.getCanonicalPath().substring(0, 2) + " && cd "
									+ dirFile.getCanonicalPath() + " && " + sysCommand;
							execTask.setDir(dirFile);
						}

					} else if (args[i].contentEquals("-available")) {
						Iterator<String> keys = propertiesConfiguration.getKeys();

						String var = "";
						String val = "";
						while (keys.hasNext()) {
							var = keys.next();
							if (var.startsWith("application.") && var.endsWith(".executable")) {
								val = propertiesConfiguration.getString(var);
								System.out.println(var + "=" + val);
							}
						}
						System.exit(1);
					} else if (args[i].contentEquals("-simulate")) {
						simulate = true;
					} else if (args[i].contentEquals("-spawn")) {
						ignoreNext = true;
						String spawnString = args[i + 1];
						if (spawnString.contentEquals("true")) {
							System.out.println("* Spawn :" + spawnString);
							spawn = true;
						} else if (spawnString.contentEquals("false")) {
							System.out.println("* Spawn :" + spawnString);
							spawn = false;
						} else {
							ok = false;
							System.out.println("* Spawn :" + spawnString);
							System.err.println("#  Spawn : valeur doit etre egale à [true] ou [false]");
						}
					} else if (args[i].contentEquals("-propfile")) {
						ignoreNext = true;
						String propfile = args[i + 1];
						String message = "";
						if (propertyFile == null) {
							message = "* Le fichier properties est celui indiqué par l'option -propfile";
						} else {
							message = "* Le fichier via.properties existant est remplacé par celui indiqué par l'option -propfile";
						}
						propertyFile = new File(propfile);
						if (!propertyFile.exists()) {
							ok = false;
							System.err.println("# Le fichier properties indiqué par l'option -propfile n'existe pas: "
									+ propertyFile.getAbsolutePath());
							System.exit(99);
						} else {
							System.out.println(message);
							System.out.println("* Fichier properties: " + propertyFile.getCanonicalPath());
							propertiesConfiguration = new PropertiesConfiguration(propertyFile);
						}

					} else if (args[i].contentEquals("-download")) {
						ignoreNext = true;
						String app = args[i + 1];
						String urlEntry = "application." + app + ".download";
						System.out.println("* Recherche de l'url de " + urlEntry);
						String url = propertiesConfiguration.getString(urlEntry);
						if (url != null) {
							System.out.println("* Url :" + url);
							this.executeWww(url);
						} else {
							System.err.println("# Aucune entree pour :" + urlEntry);
						}
						System.exit(1);
					} else if (args[i].contentEquals("-guide")) {
						ignoreNext = true;
						String app = args[i + 1];
						String urlEntry = "application." + app + ".guide";
						System.out.println("* Recherche de l'url de " + urlEntry);
						String url = propertiesConfiguration.getString(urlEntry);
						if (url != null) {
							System.out.println("* Url :" + url);
							this.executeWww(url);
						} else {
							System.err.println("# Aucune entree pour :" + urlEntry);
						}
						System.exit(1);
					} else if (args[i].contentEquals("-about")) {
						ignoreNext = true;
						String app = args[i + 1];
						String urlEntry = "application." + app + ".about";
						System.out.println("* Recherche de l'url de " + urlEntry);
						String url = propertiesConfiguration.getString(urlEntry);
						if (url != null) {
							System.out.println("* Url :" + url);
							this.executeWww(url);
						} else {
							System.err.println("# Aucune entree pour :" + urlEntry);
						}
						System.exit(1);

					} else if (args[i].contentEquals("-install")) {
						ignoreNext = true;
						String app = args[i + 1];
						String urlEntry = "application." + app + ".install";
						System.out.println("* Recherche de l'url de " + urlEntry);
						String url = propertiesConfiguration.getString(urlEntry);
						if (url != null) {
							System.out.println("* Url :" + url);
							this.executeWww(url);
						} else {
							System.err.println("# Aucune entree pour :" + urlEntry);
						}
						System.exit(1);

					} else {
						if (!ignoreNext) {
							ignoreNext = false;
							if (!applicationNameFound) {
								applicationNameFound = true;
								applicationName = args[i];
								System.out.println("* application: " + applicationName);
								executableName = "application." + applicationName + ".executable";
								System.out.println("* Recherche depuis l'entrée: " + executableName);

								applicationPath = propertiesConfiguration.getString(executableName);

								if (applicationPath == null) {
									ok = false;
									System.err.println("# Echec de la recherche de \" + executableName");
								} else {
									applicationFile = new File(applicationPath);
									if (!applicationFile.exists()) {
										try {
											System.err.println("# Le fichier d'execution n'existe pas :"
													+ applicationFile.getCanonicalPath());
											ok = false;
										} catch (java.io.IOException ioe) {
											System.err.println("# Le chemin du fichier d'execution est incorrect :"
													+ applicationPath);
											ok = false;
										}

									} else {
										System.out.println("* Correspondance trouvée et vérifiée : "
												+ applicationFile.getCanonicalPath());
										if (sysCommand == null) {
											sysCommand = applicationFile.getCanonicalPath();
										} else {
											sysCommand += " " + applicationFile.getCanonicalPath();
										}
									}
								}
							} else {
								sysCommand += " " + args[i];
								arg.setValue(args[i]);
							}

						} else {
							ignoreNext = false;
						}
					}
					viaCommand += " " + args[i];
				}

				if (dirPath == null) {

					dirName = "application." + applicationName + ".dir";
					dirPath = propertiesConfiguration.getString(dirName);
					if (dirPath != null) {
						dirPath = dirPath.replace("/", "\\");
						System.out.println("* dir en verification :" + dirPath);

						File dirFile = new File(dirPath);
						if (!dirFile.isDirectory()) {
							ok = false;
							System.err.println(
									"# dir (" + dirName + ") n'est pas un repertoire : " + dirFile.getAbsolutePath());
						} else {
							System.out.println(
									"* dir (" + dirName + ") est un repertoire : " + dirFile.getCanonicalPath());
							execTask.setDir(dirFile);
						}
					}

				}

				if (ok) {

					/*
					 * String pattern = ".*-dir\s([a-zA-Z:/\\\\_]*).*";
					 * 
					 * // Create a Pattern object Pattern r = Pattern.compile(pattern);
					 * 
					 * // Now create matcher object. Matcher m = r.matcher(command); if (m.find( ))
					 * { //System.out.println("Found value: " + m.group(0) ); if(dir!=null) {
					 * System.out.
					 * println("* ecrasement de dir dans via.properties par dir en ligne de commande: "
					 * + dir ); dir = m.group(1) ; dir = dir.replace("/", "\\");
					 * System.out.println("* dir en verification :" + dir);
					 * 
					 * File dirFile = new File(dir); if (!dirFile.isDirectory()) { ok = false;
					 * System.err.println("# dir n'est pas un repertoire : " +
					 * dirFile.getAbsolutePath()); } else {
					 * System.out.println("* dir est un repertoire : " +
					 * dirFile.getCanonicalPath()); execTask.setDir(dirFile); } } }
					 */
					if (applicationPath == null) {
						ok = false;
						System.err.println("# Aucune application à executer !!!");
						System.exit(1);
					} else {
						applicationPath = applicationPath.replace("/", "\\");
					}
					// System.out.println("* execution: " + applicationPath);
				}

			}

			if (ok) {
				if (!applicationFile.canExecute()) {
					System.err.println(
							"# Le fichier d'execution n'est pas executable :" + applicationFile.getCanonicalPath());
					ok = false;
				}
			}

			if (ok) {

				execTask.setExecutable(applicationPath);
				execTask.setResultProperty("result");
				execTask.setOutputproperty("output");
				execTask.setSpawn(false);
				try {
//					System.out.println("* Application:\n\t" + applicationPath);
//
//					System.out.println("* Commande:\n\t" + sysCommand);
//					System.out.println("* ViaCommande:\n\t" + viaCommand);
//
//					execTask.execute();
//
//					result = project.getProperty("result");
//					String output = project.getProperty("output");
//					System.out.println("\n* =========> Resultat du traitement");
//					System.out.print("* output:   [");
//					Thread.sleep(500);
//					System.err.print(output);
//					System.out.println("]");
//					Thread.sleep(500);
//					System.out.print("* exitcode: [");
//					Thread.sleep(500);
//					System.err.print(result);
//					System.out.println("]");
					
					if(simulate==true) {
						System.out.println("* Mode simulation, l'execution aurait été :\n" + sysCommand);
					} else {
						System.out.println("* Commande soumise à l'execution:\n" + sysCommand);
						executeMsdos(sysCommand);
					}
						

				} catch (Exception ioe) {
					ok = false;
					System.err.println(ioe.getMessage());
				}
			}
		}

		try

		{
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ok) {
//			System.out.println(
//					"* <========= Fin du traitement en succes avec errorlevel=[" + Integer.parseInt(result) + "]");
//			System.exit(Integer.parseInt(result));
		} else {
			System.err.println("# ========= Fin du traitement en echec avec errorlevel=[99]");
			System.exit(99);
		}

	}

	public void executeWww(String url) throws IOException, InterruptedException {
		String urlDownload = "https://www.textpad.com/download";
		String urlGuide = "https://webhome.cs.uvic.ca/~mserra/TextPadHOW/TextPadMain.html";

		File tempScript = File.createTempFile("cmd", ".bat");

		Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript));
		PrintWriter printWriter = new PrintWriter(streamWriter);

		printWriter.println("start " + url);
		printWriter.close();

		System.out.println(tempScript.toString());

		try {
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", tempScript.toString());
			Map<String, String> env = pb.environment();

			pb.inheritIO();
			Process process = pb.start();
			process.waitFor();
		} finally {

			tempScript.delete();
		}

	}

	public void executeMsdos(String command) throws IOException, InterruptedException {

		File tempScript = File.createTempFile("cmd", ".bat");

		Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript));
		PrintWriter printWriter = new PrintWriter(streamWriter);
		printWriter.println(command);
		// printWriter.println("set");

		printWriter.close();
		System.out.println(tempScript.toString());

		try {
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", tempScript.toString());
			Map<String, String> env = pb.environment();
			// set environment variable u
			// env.clear();
			// env.put("MyVar", "Herve");

			pb.inheritIO();
			System.out.println("* Lancement de l'execution");
			Process process = pb.start();
			process.waitFor();
		} finally {
			tempScript.delete();
		}
	}

}
