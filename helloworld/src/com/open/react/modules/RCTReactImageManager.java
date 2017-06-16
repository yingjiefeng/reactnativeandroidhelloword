/** ***************************************************************************************************************************************************************************** *  * @author :fengguangjing * @createTime:2017-6-15下午5:23:17 * @version:4.2.4 * @modifyTime: * @modifyAuthor: * @description: ***************************************************************************************************************************************************************************** */package com.open.react.modules;import java.io.IOException;import java.io.InputStream;import java.lang.reflect.Field;import java.net.HttpURLConnection;import java.net.URL;import javax.annotation.Nullable;import android.graphics.Bitmap;import android.graphics.BitmapFactory;import android.text.TextUtils;import android.view.View;import android.view.ViewGroup.LayoutParams;import android.widget.ImageView;import com.example.master.helloworld.R;import com.facebook.drawee.backends.pipeline.Fresco;import com.facebook.react.bridge.ReadableArray;import com.facebook.react.uimanager.SimpleViewManager;import com.facebook.react.uimanager.ThemedReactContext;import com.facebook.react.uimanager.ViewProps;import com.facebook.react.uimanager.annotations.ReactProp;import com.facebook.react.views.image.ReactImageView;import com.squareup.picasso.Picasso;/** *****************************************************************************************************************************************************************************  *  * @author :fengguangjing * @createTime:2017-6-15下午5:23:17 * @version:4.2.4 * @modifyTime: * @modifyAuthor: * @description: *****************************************************************************************************************************************************************************  */public class RCTReactImageManager extends SimpleViewManager<ReactImageView> {	public static final String REACT_CLASS = "RCTRCTImageView";	@Override	public String getName() {		return REACT_CLASS;	}	@Override	public ReactImageView createViewInstance(ThemedReactContext context) {		return new ReactImageView(context, Fresco.newDraweeControllerBuilder(), context);	}	// 使用@ReactProp或@ReactPropGroup声明一些在Javascript里可以使用的setter方法	@ReactProp(name = "src")	public void setSrc(final  ImageView view, @Nullable final String  src) {//		view.setImageResource(R.drawable.icon);//		实现你自己的图片下载。//        本地图片统一规则：drawable://act_clothes_icon        if (!TextUtils.isEmpty(src)) {            if (src.startsWith("drawable://")) {                getImageBydrawableName(view, src);//获取drawable图片                return;            }        }		Picasso.with(view.getContext()).load(src).into(view);//获取网络图片	}	//	@ReactProp(name = "width", defaultFloat = 100f)//	  public void setWidth(ImageView view, float width) {//	    view.setLayoutParams(new LayoutParams((int)width, (int)width));//	  }	//	// 使用@ReactProp或@ReactPropGroup声明一些在Javascript里可以使用的setter方法//	@ReactProp(name = "src")//	public void setSrc(final ReactImageView view, @Nullable final ReadableArray  sources) {//		 view.setSource(sources);//	}//	//	public void setSource(@Nullable ReadableArray sources) {//	    this.mSources.clear();//	    if ((sources != null) && (sources.size() != 0))//	    {//	      if (sources.size() == 1)//	        this.mSources.add(new ImageSource(getContext(), sources.getMap(0).getString("uri")));//	      else {//	        for (int idx = 0; idx < sources.size(); ++idx) {//	          ReadableMap source = sources.getMap(idx);//	          this.mSources.add(new ImageSource(getContext(), source.getString("uri"), source.getDouble("width"), source.getDouble("height")));//	        }////	      }////	    }////	    this.mIsDirty = true;//	  }	 /**     * 通过反射获取drawable图片     * @param view     * @param url     */    private void getImageBydrawableName(View view, String url) {        String urls[] = url.split("//");        String drawableName = "";        if(urls != null && urls.length >1) {            drawableName = urls[1];        }        try {            Field f = R.drawable.class.getField(drawableName);            view.setBackgroundResource(f.getInt(f));        } catch (NoSuchFieldException e) {            e.printStackTrace();        } catch (IllegalAccessException e) {            e.printStackTrace();        }    }	  	@ReactProp(name = ViewProps.RESIZE_MODE)	public void setResizeMode(ImageView view, @Nullable String resizeMode) {		ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER;		if ("contain".equals(resizeMode)) {			scaleType = ImageView.ScaleType.CENTER_INSIDE;		}		view.setScaleType(scaleType);	}		 @ReactProp(name = "borderRadius", defaultFloat = 0f)	  public void setBorderRadius(ReactImageView view, float borderRadius) {	    view.setBorderRadius(borderRadius);	  }	   }