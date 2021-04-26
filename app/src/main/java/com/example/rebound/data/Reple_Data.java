package com.example.rebound.data;

public class Reple_Data {
    String reply;
    String id;
    String date;
    String post_date;

    public Reple_Data(String reply, String id, String date) {
        this.reply = reply;
        this.id = id;
        this.date = date;
        this.post_date = post_date;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }
}
