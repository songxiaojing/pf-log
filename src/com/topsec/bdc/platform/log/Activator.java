package com.topsec.bdc.platform.log;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import ch.qos.logback.classic.LoggerContext;


/**
 * 
 * The OSGI bundle Activator.
 * 
 * Start And configure Platform logging function.
 * 
 * @title Activator
 * @package com.byw.dalek.platform.log
 * @author baiyanwei
 * @version 1.0.0
 * @date May 23, 2015
 * 
 */
public class Activator implements BundleActivator {

    /**
     * OSGI bundle
     */
    private static BundleContext BUNDLE_CONTEXT = null;

    /**
     * The hole application logger context
     */
    public LoggerContext _platformLoggerContext = null;

    /**
     * Get the bundle logging's context.
     * 
     * @return
     */
    public static BundleContext getContext() {

        return BUNDLE_CONTEXT;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {

        Activator.BUNDLE_CONTEXT = bundleContext;
        //
        this._platformLoggerContext = new PlatformLoggerHelper().initConfigurationForLogging();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

        Activator.BUNDLE_CONTEXT = null;
        _platformLoggerContext = null;
    }

}
