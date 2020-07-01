package com.example.lamma.Model;

import java.util.List;

public class Upload {
    private String mName;
    private String mYear;
    private String mLength;
    private String mUploadType;
    private int mRating;
    private List<String> mType;
    private String mImageUrl;

    public Upload() {
        //empty constructor
    }

    public Upload(String name, String year, String length, int rating, List<String> type, String imageUrl, String uploadType) {
        if(name.trim().equals("")){
            name = "No name";
        }
        mName = name;
        mYear = year;
        mLength = length;
        mRating = rating;
        mType = type;
        mImageUrl = imageUrl;
        mUploadType = uploadType;
    }
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getLength() {
        return mLength;
    }

    public void setLength(String length) {
        mLength = length;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        mRating = rating;
    }

    public List<String> getType() {
        return mType;
    }

    public void setType(List<String> type) {
        mType = type;
    }

    public String getUploadType() {
        return mUploadType;
    }

    public void setUploadType(String uploadType) {
        mUploadType = uploadType;
    }
}
