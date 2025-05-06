package com.example.mobil_programlama_odev.BooksApiRetrofit;

import java.util.List;

public class VolumeInfo {
    private String title;
    private List<String> authors;
    private String description;
    private Integer pageCount;
    private String publishedDate;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getPublishedDate() {
        return publishedDate;
    }
}
