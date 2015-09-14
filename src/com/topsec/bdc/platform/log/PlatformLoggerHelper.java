package com.topsec.bdc.platform.log;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;


/**
 * 
 * Logging configuration.
 * 
 * Logging bundle just provide logging function for other bundle.
 * 
 * @title PlatformLoggerHelper
 * @package com.byw.dalek.platform.log
 * @author baiyanwei
 * @version 1.0.0
 * @date May 23, 2015
 * 
 */
public class PlatformLoggerHelper {

    /**
     * The Logging configuration path in startup.
     */
    public final static String LOGGING_CONFIGURATION_PATH = "loggingConfigurationPath";
    /**
     * Default configuration name.
     */
    public final static String DEFAULT_LOGGING_CONFIGURATION_PATH = "configuration/logging.xml";

    /**
     * @param logConfigruationPath
     * @throws Exception
     *             read and set the logging configuration from starting parameter
     */
    public LoggerContext initConfigurationForLogging() throws Exception {

        String logConfigruationPath = getLoggingConfigurationPath();
        //
        if (logConfigruationPath == null || logConfigruationPath.trim().equals("")) {
            throw new Exception("invalid logging configuration path.");
        }
        //
        try {
            //
            LoggerContext logContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            // add platform logger into getFrameworkPackages.
            logContext.getFrameworkPackages().add(PlatformLogger.class.getName());
            //
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(logContext);
            logContext.reset();
            // load configuration content
            configurator.doConfigure(logConfigruationPath);
            //
            PlatformLogger theLogger = PlatformLogger.getLogger(PlatformLoggerHelper.class);
            //
            theLogger.info("Platform Logging System is started! Using Configuration file: " + logConfigruationPath);

            return logContext;
        } catch (JoranException je) {
            throw je;
        }
        //
    }

    /**
     * Gets the file path of platform logger.
     * 
     * @return
     */
    public String getLoggingConfigurationPath() {

        String path = System.getProperty(LOGGING_CONFIGURATION_PATH);
        if (path == null || path.trim().length() == 0) {
            path = DEFAULT_LOGGING_CONFIGURATION_PATH;
        }
        return path;
    }
}
