/** ***************************************************************************************************************************************************************************** *  * @author :fengguangjing * @createTime:2017-6-14上午10:03:02 * @version:4.2.4 * @modifyTime: * @modifyAuthor: * @description: ***************************************************************************************************************************************************************************** */package com.example.master.helloworld.bean;/** *****************************************************************************************************************************************************************************  *  * @author :fengguangjing * @createTime:2017-6-14上午10:03:02 * @version:4.2.4 * @modifyTime: * @modifyAuthor: * @description: *****************************************************************************************************************************************************************************  */public class ReactBean {	private String bundleAssetName;	private String jSMainModuleName;	private String moduleName;	private boolean remoteable;	public String getBundleAssetName() {		return bundleAssetName;	}	public void setBundleAssetName(String bundleAssetName) {		this.bundleAssetName = bundleAssetName;	}	public String getjSMainModuleName() {		return jSMainModuleName;	}	public void setjSMainModuleName(String jSMainModuleName) {		this.jSMainModuleName = jSMainModuleName;	}	public String getModuleName() {		return moduleName;	}	public void setModuleName(String moduleName) {		this.moduleName = moduleName;	}	public boolean isRemoteable() {		return remoteable;	}	public void setRemoteable(boolean remoteable) {		this.remoteable = remoteable;	}}