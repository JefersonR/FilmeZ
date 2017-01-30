package com.app.jeferson.filmez.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jeferson on 22/03/2016.
 */
public class CardViewItems implements Serializable {

    @SerializedName("Search")
    @Expose
    private List<Search> search = null;
    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("Response")
    @Expose
    private String response;
    private final static long serialVersionUID = -865143507817801314L;

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardViewItems that = (CardViewItems) o;

        if (search != null ? !search.equals(that.search) : that.search != null) return false;
        if (totalResults != null ? !totalResults.equals(that.totalResults) : that.totalResults != null)
            return false;
        return response != null ? response.equals(that.response) : that.response == null;

    }

    @Override
    public int hashCode() {
        int result = search != null ? search.hashCode() : 0;
        result = 31 * result + (totalResults != null ? totalResults.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CardViewItems{" +
                "search=" + search +
                ", totalResults='" + totalResults + '\'' +
                ", response='" + response + '\'' +
                '}';
    }

    public static class Search implements Serializable
    {

        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Year")
        @Expose
        private String year;
        @SerializedName("imdbID")
        @Expose
        private String imdbID;
        @SerializedName("Type")
        @Expose
        private String type;
        @SerializedName("Poster")
        @Expose
        private String poster;
        private final static long serialVersionUID = 3942908683226451058L;

        public Search(String title, String year, String imdbID, String type, String poster) {
            this.title = title;
            this.year = year;
            this.imdbID = imdbID;
            this.type = type;
            this.poster = poster;
        }

        public Search(){

        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getImdbID() {
            return imdbID;
        }

        public void setImdbID(String imdbID) {
            this.imdbID = imdbID;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Search search = (Search) o;

            if (title != null ? !title.equals(search.title) : search.title != null) return false;
            if (year != null ? !year.equals(search.year) : search.year != null) return false;
            if (imdbID != null ? !imdbID.equals(search.imdbID) : search.imdbID != null)
                return false;
            if (type != null ? !type.equals(search.type) : search.type != null) return false;
            return poster != null ? poster.equals(search.poster) : search.poster == null;

        }

        @Override
        public int hashCode() {
            int result = title != null ? title.hashCode() : 0;
            result = 31 * result + (year != null ? year.hashCode() : 0);
            result = 31 * result + (imdbID != null ? imdbID.hashCode() : 0);
            result = 31 * result + (type != null ? type.hashCode() : 0);
            result = 31 * result + (poster != null ? poster.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Search{" +
                    "title='" + title + '\'' +
                    ", year='" + year + '\'' +
                    ", imdbID='" + imdbID + '\'' +
                    ", type='" + type + '\'' +
                    ", poster='" + poster + '\'' +
                    '}';
        }
    }



}
