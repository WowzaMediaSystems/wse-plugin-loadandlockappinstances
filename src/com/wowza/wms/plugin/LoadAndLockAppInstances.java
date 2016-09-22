/**
 * Wowza server software and all components Copyright 2006 - 2016, Wowza Media Systems, LLC, licensed pursuant to the Wowza Media Software End User License Agreement.
 */
package com.wowza.wms.plugin;

import com.wowza.util.StringUtils;
import com.wowza.wms.application.IApplicationInstance;
import com.wowza.wms.application.WMSProperties;
import com.wowza.wms.logging.*;
import com.wowza.wms.server.*;
import com.wowza.wms.vhost.IVHost;
import com.wowza.wms.vhost.VHostSingleton;

public class LoadAndLockAppInstances implements IServerNotify2 {

	public static final String CLASS_NAME = "LoadAndLockAppInstances";
	public static final String PROP_NAME_PREFIX = "loadAndLock";
	
	private String vhostName = IVHost.VHOST_DEFAULT;
	private String appNames = ""; // live/_definst_

	public void onServerConfigLoaded(IServer server) {
	}

	public void onServerCreate(IServer server) {
	}

	public void onServerInit(IServer server) {
		WMSLoggerFactory.getLogger(null).info(CLASS_NAME + ".onServerInit");
		WMSProperties props = server.getProperties();
		this.vhostName  = props.getPropertyStr(PROP_NAME_PREFIX + "VHost", this.vhostName);
		this.appNames  = props.getPropertyStr(PROP_NAME_PREFIX + "Applications", this.appNames);
		
		if(!StringUtils.isEmpty(this.appNames)){
			String[] contexts = this.appNames.split(",");
			for (String context : contexts)
			{
				String[] parts = context.trim().split("/");
				String appName = parts[0].trim();
				String appInstName = IApplicationInstance.DEFAULT_APPINSTANCE_NAME;
				if (parts.length > 1)
				{
					appInstName = parts[1].trim();
				}
				if (!StringUtils.isEmpty(vhostName) && !StringUtils.isEmpty(appName) && !StringUtils.isEmpty(appInstName))
				{
					loadAndLockAppInstance(vhostName, appName, appInstName);
				}
			}
		}
	}

	public void onServerShutdownStart(IServer server) {
	}

	public void onServerShutdownComplete(IServer server) {
	}
	
	private void loadAndLockAppInstance(String vhostName, String appName, String appInstanceName)
	{
		IVHost vhost = VHostSingleton.getInstance(vhostName);
		if(vhost != null)
		{
			if (vhost.startApplicationInstance(appName, appInstanceName))
			{
				vhost.getApplication(appName).getAppInstance(appInstanceName).setApplicationTimeout(0);
			}
			else
			{
				WMSLoggerFactory.getLoggerObj(vhost).warn(CLASS_NAME + ".loadAndLockAppInstance: Application folder ([install-location]/applications/" + appName + ") is missing", WMSLoggerIDs.CAT_vhost, WMSLoggerIDs.EVT_comment);
			}
		}
	}

}
