package com.frisk.friskutility.ImageUtils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.frisk.friskutility.ImageUtils.TextDrawable;
import com.frisk.friskutility.Models.Image;
import com.frisk.friskutility.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class FriskCircleImageView extends CircleImageView {
    private Context context;
    private Drawable placeHolder;
    private Drawable textDrawable;
    private Image image;
    private boolean defaultPlaceHolderEnabled;
    private boolean enabledTextDrawable;
    private int maxWidth = 96, maxHeight = 96;
    private boolean loadMain;
    private boolean scalable;

    public void setScalable(boolean scalable) {
        this.scalable = scalable;
    }

    private void scale(int width, int height) {
        this.maxHeight = height;
        this.maxWidth = width;
    }

    private String name;

    private boolean isLoadMain() {
        return loadMain;
    }

    public void setLoadMain(boolean loadMain) {
        this.loadMain = loadMain;
    }

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
        image.setThumbnailUrl(file);
        configure(image, name, placeHolder);

    }

    private void configure(Image image, String name, Drawable placeHolder) {
        this.image = image;
        this.placeHolder = placeHolder;
        this.name = name;
        if (!TextUtils.isEmpty(this.name) && enabledTextDrawable) {
            String latter = this.name.substring(0, 1);
            textDrawable = TextDrawable.getTextDrawable(latter, maxWidth, maxHeight);

        }
        if (placeHolder == null && defaultPlaceHolderEnabled) {
            this.placeHolder = context.getResources()
                    .getDrawable(R.drawable.ic_account_circle_grey_24dp);
        }
        loadImage();
    }

    public FriskCircleImageView(Context context) {
        super(context);
        init(context);
    }

    public FriskCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FriskCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

    }

    private void loadImage() {
        RequestOptions requestOptions =
                new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .format(DecodeFormat.PREFER_ARGB_8888);

        if (enabledTextDrawable && textDrawable != null) {
            requestOptions.error(textDrawable);
            requestOptions.placeholder(textDrawable);

        } else {
            if (defaultPlaceHolderEnabled && placeHolder != null) {
                requestOptions.error(placeHolder);
                requestOptions.placeholder(placeHolder);
            }
        }
        if(scalable){
            requestOptions.override(maxWidth, maxHeight);
        }


        if (context!= null && image !=null)
            try {
                Glide.with(context.getApplicationContext())
                        .asBitmap()
                        .apply(requestOptions)
                        .load(isLoadMain() ? image.getMediaUrl() : image.getThumbnailUrl())
                        .thumbnail(0.4f)
                        .into(this);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void defaultWhenNull() {
        if (placeHolder == null && defaultPlaceHolderEnabled) {
            this.placeHolder = context.getResources()
                    .getDrawable(R.drawable.ic_account_circle_grey_24dp);
            this.setImageDrawable(placeHolder);
        }
    }

    public void setTextDrawableWhenNull() {
        if (!TextUtils.isEmpty(this.name) && enabledTextDrawable) {
            String latter = this.name.substring(0, 1);
            textDrawable = TextDrawable.getTextDrawable(latter, maxWidth, maxHeight);
            this.setImageDrawable(textDrawable);
        }
    }


    public void setPlaceHolder() {
        loadImage();
    }
}
