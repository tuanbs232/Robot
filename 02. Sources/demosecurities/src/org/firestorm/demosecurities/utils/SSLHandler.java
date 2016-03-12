package org.firestorm.demosecurities.utils;

import java.io.IOException;
import java.util.Properties;

public class SSLHandler {
	static final String KEYSTORE_TYPE = "KEYSTORE_TYPE";

	static final String KEYSTORE_PATH_WIN = "KEYSTORE_PATH_WIN";
	static final String KEYSTORE_PATH_LINUX = "KEYSTORE_PATH_LINUX";

	static final String KEYSTORE_PASSWORD = "KEYSTORE_PASSWORD";

	static final String TRUSTSTORE_TYPE = "TRUSTSTORE_TYPE";

	static final String TRUSTSTORE_PATH_WIN = "TRUSTSTORE_PATH_WIN";
	static final String TRUSTSTORE_PATH_LINUX = "TRUSTSTORE_PATH_LINUX";

	static final String TRUSTSTORE_PASSWORD = "TRUSTSTORE_PASSWORD";

	private Properties props;
	
	public SSLHandler() {
		this.props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("admin.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set client ssl with keystore and truststore 
	 */
	public void setSSL() {
		System.setProperty("javax.net.ssl.trustStoreType",
				props.getProperty(TRUSTSTORE_TYPE));
		String osName = System.getProperty("os.name");
		if (osName.contains("Windows")) {
			System.setProperty("javax.net.ssl.trustStore",
					props.getProperty(TRUSTSTORE_PATH_WIN));
		} else if (osName.contains("Linux")) {
			System.setProperty("javax.net.ssl.trustStore",
					props.getProperty(TRUSTSTORE_PATH_LINUX));
		}
		System.setProperty("javax.net.ssl.trustStorePassword",
				props.getProperty(TRUSTSTORE_PASSWORD));
		
		if (osName.contains("Windows")) {
			System.setProperty("javax.net.ssl.keyStore",
					props.getProperty(KEYSTORE_PATH_WIN));
		} else if (osName.contains("Linux")) {
			System.setProperty("javax.net.ssl.keyStore",
					props.getProperty(KEYSTORE_PATH_LINUX));
		}
		System.setProperty("javax.net.ssl.keyStoreType",
				props.getProperty(TRUSTSTORE_TYPE));
		System.setProperty("javax.net.ssl.keyStorePassword",
				props.getProperty(KEYSTORE_PASSWORD));
		
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
}
