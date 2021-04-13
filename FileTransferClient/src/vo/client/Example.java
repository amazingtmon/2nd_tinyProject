package vo.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Example {
	private static final String DEFAULT_PROPERTIES_PATH = "d://test.properties";

	private static String IP;

	public static void main(String[] args) throws Exception {
		setServerIP(Example.getKey("serverIp"));
		setServerIP(Example.getKey("oracleIp"));
		System.out.println(IP);
	}

	public static String getKey(String key) throws Exception {
		String value = null;
		InputStream is = new FileInputStream(DEFAULT_PROPERTIES_PATH);
		Properties properties = null;
		try {
			properties = new Properties();
			properties.load(is);
			value = properties.getProperty(key);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				is.close();
			} catch (IOException e) {

			}
		}
		return value;
	}

	public static String getServerIP() {
		return IP;
	}

	public static void setServerIP(String IP) {
		Example.IP = IP;
	}

}

