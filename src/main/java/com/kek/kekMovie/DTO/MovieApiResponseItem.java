package com.kek.kekMovie.DTO;

import java.util.List;
import java.util.Map;

public class MovieApiResponseItem {
    private String title;
    private List<Object> content;

    public MovieApiResponseItem(String title, List<Object> content) {
        this.title = title;
        this.content = content;
    }

    protected MovieApiResponseItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Object> getContent() {
        return content;
    }

    public void setContent(List<Object> content) {
        this.content = content;
    }
}
