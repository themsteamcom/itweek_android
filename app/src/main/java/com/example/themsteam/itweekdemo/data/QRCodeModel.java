package com.example.themsteam.itweekdemo.data;

/**
 * Created by Vladimir on 18.08.2017.
 */

public class QRCodeModel {
    private int id;
    private String title;
    private String note;

    public QRCodeModel(int id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
