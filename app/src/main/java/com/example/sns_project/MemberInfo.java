package com.example.sns_project;


public class  MemberInfo {


    private String user_id;
    private String name;
    private String phone;
    private String birth;
    private String address;
    private String photoUri;

    public MemberInfo(String user_id, String name, String phone, String birth, String address, String photoUri){
        this.user_id = user_id;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
        this.photoUri = photoUri;
    }
    public MemberInfo(String user_id, String name, String phone, String birth, String address){
        this.user_id = user_id;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getBirth() {
        return this.birth;
    }
    public void setBirth(String birth){ this.birth = birth; }

    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getPhotoUri() {
        return this.photoUri;
    }
    public void setPhotoUri(String photoUri){
        this.photoUri = photoUri;
    }
}
