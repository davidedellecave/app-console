package ddc.app.console;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import ddc.support.util.LogConsole;

public abstract class PropsConfigApp {
	private static final LogConsole logger = new LogConsole(PropsConfigApp.class);

	public void run(String[] args) throws Throwable {
		try {
			doMain(args);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public abstract void run(Properties props) throws Throwable;

	public void doMain(String[] args) throws Throwable {
		String propsFile = "config.properties";
		if (args.length == 0) {
			logger.warn("Config param not found");
			logger.warn("Default config is used:[" + propsFile + "]");
		} else {
			propsFile = args[0];
		}
		Path configPath = Paths.get(propsFile);
		logger.info("Config file:[" + propsFile + "]");
		if (!Files.exists(configPath)) {
			logger.warn("Config file not found:[" + configPath + "]");
		}
		Properties props = new Properties();
		props.load(new FileInputStream(configPath.toFile()));
		logger.info("Starting...");
		run(props);
		logger.info("Terminated...");
	}

	public Path getPropAsRequiredFile(Properties props, String key) {
		String sPath = props.getProperty(key);
		if (sPath == null) {
			throw new RuntimeException("Property not found - key:[" + key + "]");
		}
		Path path = Paths.get(sPath);
		if (!Files.exists(path)) {
			throw new RuntimeException("Source file not found - path:[" + path + "]");
		}
		return path;
	}

	public Path getPropAsFile(Properties props, String key) {
		String sPath = props.getProperty(key);
		if (sPath == null) {
			throw new RuntimeException("Property not found - key:[" + key + "]");
		}
		Path path = Paths.get(sPath);
		return path;
	}
	
	public Boolean getPropAsBoolean(Properties props, String key) {
		String s = props.getProperty(key);
		return Boolean.valueOf(s);
	}
	
	public String getPropAsString(Properties props, String key) {
		return props.getProperty(key);
	}

	public long getPropAsLong(Properties props, String key) {
		String s = props.getProperty(key);
		return Long.valueOf(s);
	}

}
