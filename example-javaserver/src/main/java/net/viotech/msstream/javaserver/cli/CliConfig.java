package net.viotech.msstream.javaserver.cli;

import com.lexicalscope.jewel.cli.Option;

public interface CliConfig {
	
	@Option(shortName="db", longName = "database", defaultToNull=true)
	String getIpAddrDB();
	


}
