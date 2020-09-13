package com.frisk.friskutility.Models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Faizan for Netree  on 18,January,2019
 * Version foop 2.0
 * Revision foop 2.0
 */


public class Image implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("linkedTxnRefId")
    @Expose
    private String linkedTxnRefId;
    //brl brp
    @SerializedName("linkedTxnRefType")
    @Expose
    private String linkedTxnRefType;
    @SerializedName("mediaPid")
    @Expose
    private String mediaPid;
    @SerializedName("mediaUrl")
    @Expose
    private String mediaUrl;
    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;

    @SerializedName("professionalTitle")
    @Expose
    private String professionalTitle;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("profileName")
    @Expose
    private String profileName;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("connectFlag")
    @Expose
    private boolean connectFlag;

    @SerializedName("acceptRejectPid")
    @Expose
    private String acceptRejectPid;

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isConnectFlag() {
        return connectFlag;
    }

    public void setConnectFlag(boolean connectFlag) {
        this.connectFlag = connectFlag;
    }

    public String getAcceptRejectPid() {
        return acceptRejectPid;
    }

    public void setAcceptRejectPid(String acceptRejectPid) {
        this.acceptRejectPid = acceptRejectPid;
    }

    public String getLinkedTxnRefId() {
        return linkedTxnRefId;
    }

    public void setLinkedTxnRefId(String linkedTxnRefId) {
        this.linkedTxnRefId = linkedTxnRefId;
    }

    public String getLinkedTxnRefType() {
        return linkedTxnRefType;
    }

    public void setLinkedTxnRefType(String linkedTxnRefType) {
        this.linkedTxnRefType = linkedTxnRefType;
    }

    public String getMediaPid() {
        return mediaPid;
    }

    public void setMediaPid(String mediaPid) {
        this.mediaPid = mediaPid;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public enum TYPE {

        PRODUCT("p"), BUSINESS_LOGO("bl"), BUSINESS_PROFILE("bp"), BUSINESS_BACKGROUND("bb"), CONTACT_PROFILE("cp"),GROUP_HEADER("group_header"),
        CONTACT_BACKGROUND("cb"), BRAND("b"), BRAND_LOGO("brl"), BRAND_BANNER("brb"), PURCHASE_INVOICE("pi"), SALES_INVOICE("si"), PROMO("promo"),
        CONTACT_GROUP_PROFILE("cgp"), CONTACT_GROUP_BACKGROUND("cgb"), CATEGORY("c"),
        BLOG("blog"), LEAD("lead"), ANNOUNCE("announce"), QUERY("query"), BRAND_PROFILE("brp"),GENERALPOST("genpost"),GENERALPOSTMULTIPLE("genpost_multiple");

        TYPE(String type) {
            this.type = type;
        }

        private String type;

        public String getType() {
            return type;
        }
    }


}
