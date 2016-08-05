package net.viotech.msstream.javaserver.cli;

import com.lexicalscope.jewel.cli.CliFactory;

public class CliConfArgs {

	public static void getParametersFromArgs(String[] args) {
		
			CliConfig cliconf = CliFactory.parseArguments(CliConfig.class, args);

			CliConfSingleton.ipAddrDB = cliconf.getIpAddrDB();
			CliConfSingleton.defaultValue();
	}
	
}
