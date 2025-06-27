package com.example.Projeto_final_back_end.entity;

public class BrasilApiLivroResponse {
    private String title;
    private String subtitle;
    private java.util.List<String> authors;
    private String publisher;
    private String synopsis;
    private int year;
    private int page_count;
    private String location;
    private String format;
    private java.util.List<String> subjects;
    private String cover_url;

    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public java.util.List<String> getAuthors() { return authors; }
    public String getPublisher() { return publisher; }
    public String getSynopsis() { return synopsis; }
    public int getYear() { return year; }
    public int getPage_count() { return page_count; }
    public String getLocation() { return location; }
    public String getFormat() { return format; }
    public java.util.List<String> getSubjects() { return subjects; }
    public String getCover_url() { return cover_url; }
}
