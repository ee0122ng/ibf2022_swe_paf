package iss.ibf2022.pafday07practice.model;

import java.util.List;

public class Show {

    private Integer id;
    private String name;
    private String language;
    private Double rating;
    private List<String> genres;
    private Integer runtime;
    private String summary;
    private Feedback userComment;

    public Show() {
    }

    public Show(Integer id, String name, String language, Double rating, List<String> genres, Integer runtime, String summary, Feedback userComment) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.rating = rating;
        this.genres = genres;
        this.runtime = runtime;
        this.summary = summary;
        this.userComment = userComment;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getRuntime() {
        return this.runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Feedback getUserComment() {
        return this.userComment;
    }

    public void setUserComment(Feedback userComment) {
        this.userComment = userComment;
    }

    
}
