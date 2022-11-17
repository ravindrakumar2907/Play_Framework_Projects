package db;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import model.Registration;

@Singleton
public class DBStore {

	final private static Map<String, Registration> REG_DB = new HashMap<String, Registration>();

	public static void setData(String key, Registration value) {
		REG_DB.put(key, value);
	}

	public static Registration getData(String key) {
		return REG_DB.get(key);
	}

}
