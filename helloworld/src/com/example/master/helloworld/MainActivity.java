package com.example.master.helloworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.facebook.react.LifecycleState;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

/**
 * 
 *****************************************************************************************************************************************************************************
 * 1.react-native start 开启服务
 * 2.下载index.android.bundle
 * http://localhost:8081/index.android.bundle?platform=android
 * 
 * @author :fengguangjing
 * @createTime:2017-6-13下午5:42:56
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
	private String bundleAssetName;
	private String jSMainModuleName;
	private String moduleName;
	private boolean remoteable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundleAssetName = getIntent().getStringExtra("bundleAssetName");
        jSMainModuleName = getIntent().getStringExtra("jSMainModuleName");
        moduleName = getIntent().getStringExtra("moduleName");
        
        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName(bundleAssetName)
                .setJSMainModuleName(jSMainModuleName)
                
                .addPackage(new MainReactPackage())
//                .addPackage(new CommonReactPackage())//自定义module
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        mReactRootView.startReactApplication(mReactInstanceManager, moduleName, null);

        setContentView(mReactRootView);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy();
        }
    }
    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
    
    
    public static void startMainActivity(Context context,String bundleAssetName,String jSMainModuleName,String moduleName){
    	Intent intent = new Intent();
    	intent.setClass(context, MainActivity.class);
    	intent.putExtra("bundleAssetName", bundleAssetName);
    	intent.putExtra("jSMainModuleName", jSMainModuleName);
    	intent.putExtra("moduleName", moduleName);
    	context.startActivity(intent);
    	
    }
}
