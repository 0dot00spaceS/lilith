package de.huxhorn.lilith.sandbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackClassicSandbox
{
	public static class InnerClass
	{
		public static void execute()
		{
			final Logger logger = LoggerFactory.getLogger(InnerClass.class);
			if(logger.isDebugEnabled()) logger.debug("Foo!");
		}
	}


	public static void main(String args[])
	{
		final Logger logger = LoggerFactory.getLogger(LogbackClassicSandbox.class);
		if(logger.isDebugEnabled()) logger.debug("args: {}", args);

		InnerClass.execute();
	}
}