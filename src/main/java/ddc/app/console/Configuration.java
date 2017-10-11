package ddc.app.console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import ddc.config.XmlConfiguration;
import ddc.support.util.FormatUtils;


public class Configuration {
	
	public static void writeConfiguration(Path path, Object conf) throws IOException {
		try {			
//			XmlUtils.write(configuration.getFile(), configuration);
			System.out.println(FormatUtils.format("Configuration Writing - file", path));
			XmlConfiguration<Object> c = new XmlConfiguration<>();
			c.write(path, conf);
		} catch (IOException e) {
			System.err.println("writeConfiguration() exception:[" + e.getMessage() + "]");
			throw e;
		}		
	}
		
//	public static Configuration writeConfiguration(File file, Class<? extends Configuration> clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
//		Configuration c = clazz.newInstance();
//		writeConfiguration(file, c);
//		return c;
//	}
	/**
	 * Read the configuration file
	 * @param file
	 * @return the configuration file, null if fails
	 * @throws IOException 
	 */
	public static Object readConfiguration(Path path) throws Throwable {
		try {
			System.out.println(FormatUtils.format("readConfiguration() file",path));
			XmlConfiguration<Object> c = new XmlConfiguration<>();
			return c.read(path);
		} catch (Throwable e) {
			System.err.println("Configuration Reading - file:[" + path+ "] " + e.getMessage());
			throw e;
		}
	}
	
	public static void deleteConfiguration(File file) throws IOException {
		try {		
			System.out.println(FormatUtils.format("Configuration Deleting - file", file));
			file.delete();
		} catch (SecurityException e) {
			System.err.println("Configuration Deleting - file:[" + file.getCanonicalPath() + "] " + e.getMessage());
			throw e;
		}
	}
}
