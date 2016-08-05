package net.viotech.msstream.javaserver.cli;

public class CliConfSingleton {
	public static String ipAddrDB;

	
	public static void defaultValue() {
		if(ipAddrDB==null)
			ipAddrDB = "db:3306";
	}	
}
