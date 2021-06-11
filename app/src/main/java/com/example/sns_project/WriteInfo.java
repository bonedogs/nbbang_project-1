package com.example.sns_project;

public class WriteInfo {
    private String title;
    private String publisher;
    private String writedate;
    private String deaddate;
    private int minnum;
    private String region;
    private String store;
    private String category;
    private String object;
    private String contents;

    public WriteInfo(String title, String publisher, String writedate, String deaddate, int minnum, String region, String store, String category, String object, String contents){
        this.title = title;
        this.publisher = publisher;
        this.writedate = writedate;
        this.deaddate = deaddate;
        this.minnum = minnum;
        this.region = region;
        this.store = store;
        this.category = category;
        this.object = object;
        this.contents = contents;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getPublisher() {
        return this.publisher;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getWritedate() {
        return this.writedate;
    }
    public void setWritedate(String writedate){
        this.writedate = writedate;
    }

    public String getDeaddate() {
        return this.deaddate;
    }
    public void setDeaddate(String deaddate){
        this.deaddate = deaddate;
    }

    public int getMinnum() {
        return this.minnum;
    }
    public void setMinnum(int minnum){
        this.minnum = minnum;
    }

    public String getRegion() {
        return this.region;
    }
    public void setRegion(String region){
        this.region = region;
    }

    public String getStore() {
        return this.store;
    }
    public void setStore(String store){
        this.store = store;
    }

    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public String getObject() {
        return this.object;
    }
    public void setObject(String object){
        this.object = object;
    }

    public String getContents() {
        return this.contents;
    }
    public void setContents(String contents){
        this.contents = contents;
    }

}
