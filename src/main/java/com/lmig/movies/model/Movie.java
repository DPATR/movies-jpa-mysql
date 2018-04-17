/*
 * Copyright (C) 2018, Liberty Mutual Group
 *
 * Created on Apr 10, 2018
 */

package com.lmig.movies.model;

import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Integer year;
    private String image_url;
    private String certificate;
    private Integer runtime;
    private Float imdb_rating;
    private String description;
    private Integer metascore;
    private Integer votes;
    private Integer gross;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies_directors",
        joinColumns = @JoinColumn(name = "movies_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "directors_id", referencedColumnName = "id"))
    private List<Director> director = new ArrayList<Director>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies_stars",
        joinColumns = @JoinColumn(name = "movies_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "stars_id", referencedColumnName = "id"))
    private List<Star> star = new ArrayList<Star>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies_genres",
        joinColumns = @JoinColumn(name = "movies_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "genres_id", referencedColumnName = "id"))
    private List<Genre> genre = new ArrayList<Genre>();
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }
    
    public Float getImdb_rating() {
        return imdb_rating;
    }

    public void setImdb_rating(Float imdb_rating) {
        this.imdb_rating = imdb_rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMetascore() {
        return metascore;
    }

    public void setMetascore(Integer metascore) {
        this.metascore = metascore;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Integer getGross() {
        return gross;
    }

    public void setGross(Integer gross) {
        this.gross = gross;
    }

    public List<Director> getDirector() {
        return director;
    }

    public void setDirector(List<Director> director) {
        this.director = director;
    }
    
    public List<Star> getStar() {
        return star;
    }

    public void setStar(List<Star> star) {
        this.star = star;
    }
    
    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

}
