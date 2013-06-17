package com.pis.common.log4j;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class ClassDailyRollingFileAppender extends FileAppender {
	protected String datePattern;
	private String rootDir;
	private Map<String, Appender> appenders = new ConcurrentHashMap<String, Appender>();

	@Override
	public synchronized void doAppend(LoggingEvent event) {
		String className = event.getLoggerName();
		Appender append = appenders.get(className);
		if (append != null) {
			append.doAppend(event);
			return;
		}

		File fileRootDir = new File(rootDir);
		if (!fileRootDir.exists())
			fileRootDir.mkdirs();
		File classDir = new File(fileRootDir, className);
		if (!classDir.exists())
			classDir.mkdir();
		File logFile = new File(classDir, fileName);
		if (!logFile.exists())
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				LogLog.error(e.getMessage(), e);
			}
		try {
			append = new DailyRollingFileAppenderFixed(getLayout(), logFile.getAbsolutePath(), datePattern);
		} catch (IOException e) {
			LogLog.error(e.getMessage(), e);
		}

		append.doAppend(event);
		appenders.put(className, append);
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	@Override
	public synchronized void close() {
		for (Appender appender : appenders.values()) {
			appender.close();
		}
		super.close();
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

}
