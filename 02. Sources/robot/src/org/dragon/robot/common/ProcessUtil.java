package org.dragon.robot.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ProcessUtil {
	// Logger for this class
	private static final Logger LOG = Logger.getLogger(ProcessUtil.class);

	private static final String CONFIG_FILE_NAME = "process.conf";
	private static final String DEFAULT_LANG = "DEFAULT_LANG";
	private static final String MIDDLE_VALUE = "MIDDLE_VALUE";
	private static final String VERY_VALUE = "VERY_VALUE";
	private static final String MORE_VALUE = "MORE_VALUE";
	private static final String POSSIBLE_VALUE = "POSSIBLE_VALUE";
	private static final String LESS_VALUE = "LESS_VALUE";
	private static final String SAMPLE_DATA = "SAMPLE_DATA";
	private static final String STANDARD_MODE_PROCESS_TIME = "STANDARD_MODE_PROCESS_TIME";
	private static final String CURRENT_WORKING_MODE = "CURRENT_WORKING_MODE";
	private static final String SHOW_DUST_VALUE = "SHOW_DUST_VALUE";
	
	public static int getCurrentWorkingMode(){
		int result = 0;
		try {
			result = Integer.parseInt(getProperty(CURRENT_WORKING_MODE));
		} catch (Exception e) {
		}

		return result;
	}
	
	public static void setCurrentWorkingMode(String value){
		setProperty(CURRENT_WORKING_MODE, value);
	}
	
	public static int getShowDustValue(){
		int result = 0;
		try {
			result = Integer.parseInt(getProperty(SHOW_DUST_VALUE));
		} catch (Exception e) {
		}

		return result;
	}
	
	public static void setSowDustVaue(String value){
		setProperty(SHOW_DUST_VALUE, value);
	}

	public static int getStandardProcessTime() {
		int result = 500;
		try {
			result = Integer.parseInt(getProperty(STANDARD_MODE_PROCESS_TIME));
		} catch (Exception e) {
		}

		return result;
	}

	public static void setStandardProcessTime(String value) {
		setProperty(STANDARD_MODE_PROCESS_TIME, value);
	}

	public static void main(String[] args) {
		System.out.println(getLanguage());
	}

	public static String getLanguage() {
		return getProperty(DEFAULT_LANG);
	}

	public static void setLanguage(String value) {
		setProperty(DEFAULT_LANG, value);
	}

	public static double getMiddleValue() {
		String propValue = getProperty(MIDDLE_VALUE);

		return getDoubleValue(propValue);

	}

	public static String getSampleData() {
		return getProperty(SAMPLE_DATA);
	}

	public static void setSampleData(String value) {
		setProperty(SAMPLE_DATA, value);
	}

	public static double getVeryValue() {
		String propValue = getProperty(VERY_VALUE);

		return getDoubleValue(propValue);
	}

	public static void setVeryValue(String value) {
		setProperty(VERY_VALUE, value);
	}

	public static double getMoreValue() {
		String propValue = getProperty(MORE_VALUE);

		return getDoubleValue(propValue);
	}

	public static void setMoreValue(String value) {
		setProperty(MORE_VALUE, value);
	}

	public static double getPossibleValue() {
		String propValue = getProperty(POSSIBLE_VALUE);

		return getDoubleValue(propValue);
	}

	public static void setPossibleValue(String value) {
		setProperty(POSSIBLE_VALUE, value);
	}

	public static double getLessValue() {
		String propValue = getProperty(LESS_VALUE);

		return getDoubleValue(propValue);
	}

	public static void setLessValue(String value) {
		setProperty(LESS_VALUE, value);
	}

	private static double getDoubleValue(String valStr) {
		double result = 0.25;
		try {
			result = Double.parseDouble(valStr);
		} catch (Exception e) {
			LOG.error("Cannot parse middle value from process.conf");
		}

		return result;
	}

	public static void setMiddleValue(String value) {
		setProperty(MIDDLE_VALUE, value);
	}

	private static String getProperty(String key) {
		Properties prop = new Properties();
		String result = "";
		try {
			prop.load(getConfigInputStream(CONFIG_FILE_NAME));
			result = prop.getProperty(key);
		} catch (IOException e) {

		}

		return result;
	}

	private static void setProperty(String key, String value) {
		Properties prop = new Properties();
		try {
			prop.load(getConfigInputStream(CONFIG_FILE_NAME));
			prop.setProperty(key, value);

			OutputStream outStream = getConfigFileOutputStream(
					CONFIG_FILE_NAME);

			if (outStream != null) {
				SimpleDateFormat df = new SimpleDateFormat(
						"dd/MM/yyyy HH-mm-ss");
				prop.store(outStream,
						"#Last Modifired: " + df.format(new Date()));

				outStream.close();
			}
		} catch (IOException e) {

		}
	}

	public static InputStreamReader getConfigInputStream(String name) {
		InputStream in = ProcessUtil.class.getClassLoader()
				.getResourceAsStream(name);
		try {
			return new InputStreamReader(in, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("UnsupportedEncodingException: " + e.getMessage());
		}

		return null;
	}

	public static FileOutputStream getConfigFileOutputStream(String name) {
		URI resourceURI;
		FileOutputStream out = null;
		try {
			resourceURI = ProcessUtil.class.getClassLoader().getResource(name)
					.toURI();
			out = new FileOutputStream(new File(resourceURI));
		} catch (URISyntaxException e) {
			LOG.error("URISyntaxException:" + e.getMessage());
		} catch (FileNotFoundException e) {
			LOG.error("FileNotFoundException:" + e.getMessage());
		}

		return out;
	}
}
