package com.frisk.friskutility.ImageUtils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.frisk.friskutility.Models.Image;
import com.frisk.friskutility.R;


public class FriskImageView extends AppCompatImageView {
    private Context context;
    private Drawable placeHolder;
    private Drawable textDrawable;
    private Image image;
    private boolean defaultPlaceHolderEnabled;
    private boolean enabledTextDrawable;
    private int maxWidth = 96, maxHeight = 96;
    private boolean loadMain;
    private boolean scalable;

    public boolean isScalable() {
        return scalable;
    }

    public void setScalable(boolean scalable) {
        this.scalable = scalable;
    }

    public void scale(int width, int height) {
        this.maxHeight = height;
        this.maxWidth = width;
    }

    private boolean isLoadMain() {
        return loadMain;
    }

    public void setLoadMain(boolean loadMain) {
        this.loadMain = loadMain;
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaceHolder(Drawable placeHolder) {
        this.placeHolder = placeHolder;
    }

    public void setEnabledTextDrawable(boolean enabledTextDrawable) {
        this.enabledTextDrawable = enabledTextDrawable;
    }

    public void setDefaultPlaceHolderEnabled(boolean defaultPlaceHolderEnabled) {
        this.defaultPlaceHolderEnabled = defaultPlaceHolderEnabled;
    }

    public void setImage(Image image) {
        setImage(image, null, null);
    }

    public void setImage(String file) {

        setImage(file, null);
    }

    public void setImage(String file, String name) {
        setImage(file, name, null);
    }

    public void setImage(Image image, String name) {
        setImage(image, name, null);
    }

    private void setImage(Image image, String name, Drawable placeHolder) {

        configure(image, name, placeHolder);
    }

    private void setImage(String file, String name, Drawable placeHolder) {
        Image image = new Image();
        if (isLoadMain()) {
            image.setMediaUrl(file);
        } else {
            image.setThumbnailUrl(file);
        }
        configure(image, name, placeHolder);

    }



    private void configure(Image image, String name, Drawable placeHolder) {
        this.image = image;
        this.placeHolder = placeHolder;
        this.name = name;
        if (!TextUtils.isEmpty(this.name) && enabledTextDrawable) {
            if(name.length()>2){
                String latter = this.name.substring(0, 2);
                textDrawable = TextDrawable.getTextDrawable(latter, maxWidth, maxHeight);
            }
            else {
                String latter = this.name.substring(0, 1);
                textDrawable = TextDrawable.getTextDrawable(latter, maxWidth, maxHeight);
            }
        }
        if (placeHolder == null && defaultPlaceHolderEnabled) {
            this.placeHolder = context.getResources()
                    .getDrawable(R.drawable.ic_image_search_24px);
        }
        loadImage();
    }

    private void loadImage() {
        RequestOptions requestOptions =
                new RequestOptions()
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .format(DecodeFormat.PREFER_ARGB_8888);
        if (scalable) {
            requestOptions.override(maxWidth, maxHeight);
        }

        if (enabledTextDrawable && textDrawable != null) {
            requestOptions.error(textDrawable);
            requestOptions.placeholder(textDrawable);

        } else {
            if (defaultPlaceHolderEnabled && placeHolder != null) {
                requestOptions.error(placeHolder);
                requestOptions.placeholder(placeHolder);
            }
        }

        if (context == null) {
            return;
        } else{
            Glide.with(context.getApplicationContext())
                    .asBitmap()
                    .apply(requestOptions)
                    .load(isLoadMain() ? image.getMediaUrl() : image.getThumbnailUrl())
                    .thumbnail(0.2f)
                    .into(this);
        }
    }

    private void defaultWhenNull() {
        if (placeHolder == null && defaultPlaceHolderEnabled) {
            this.placeHolder = context.getResources()
                    .getDrawable(R.drawable.ic_account_circle_grey_24dp);
            this.setImageDrawable(placeHolder);
        }

    }
    private void defaultWhenFivNull() {
        if (placeHolder == null && defaultPlaceHolderEnabled) {
            this.placeHolder = context.getResources()
                    .getDrawable(R.drawable.defaultimage);
            this.setImageDrawable(placeHolder);
        }

    }

    public Drawable setImageDrawable(String uri){
        if(uri != null){
            Drawable res = null;
            try {
                int imageResource = context.getResources().getIdentifier(uri, null,context.getPackageName());
                res = context.getResources().getDrawable(imageResource);
                return res;
            } catch (Resources.NotFoundException e) {
                int imageResource = context.getResources().getIdentifier("@drawable/defaultproduct", null,context.getPackageName());
                res = context.getResources().getDrawable(imageResource);
                return res;
            }
        }else{
            int imageResource = context.getResources().getIdentifier("@drawable/defaultproduct", null,context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            return res;
        }
    }

    public void setTextDrawableWhenNull() {
        if (!TextUtils.isEmpty(this.name) && enabledTextDrawable) {
            String latter = this.name.substring(0, 1);

            textDrawable = TextDrawable.getTextDrawable(latter, maxWidth , maxHeight);
            this.setImageDrawable(textDrawable);
        }
        else
            defaultPlaceHolderEnabled = true;
        defaultWhenNull();
    }
    public void setTextDrawableFivWhenNull() {
        if (!TextUtils.isEmpty(this.name) && enabledTextDrawable) {
            String latter = this.name.substring(0, 1);

            textDrawable = TextDrawable.getTextDrawable(latter, maxWidth , maxHeight);
            this.setImageDrawable(textDrawable);
        }
        else
            defaultPlaceHolderEnabled = true;
        defaultWhenFivNull();
    }

    public FriskImageView(Context context) {
        super(context);
        init(context);

    }

    private void init(Context context) {
        this.context = context;

    }

    public FriskImageView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        init(context);

    }

    public FriskImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }
}
