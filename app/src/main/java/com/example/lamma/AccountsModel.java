package com.example.lamma;

public class AccountsModel {
    private String mEmail;
    private String mName;
    private String mPhone;
    private String mUID;

    public AccountsModel() {
    }

    public String getmUID() {
        return mUID;
    }

    public void setmUID(String mUID) {
        this.mUID = mUID;
    }

    public AccountsModel(String email, String name, String phone, String id) {
        mEmail = email;
        mName = name;
        mPhone = phone;
        mUID=id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }
}
