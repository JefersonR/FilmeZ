package com.app.jeferson.filmez.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jeferson on 28/01/2017.
 */
public class MovieDetailModel extends RealmObject implements Serializable
{
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Rated")
    @Expose
    private String rated;
    @SerializedName("Released")
    @Expose
    private String released;
    @SerializedName("Runtime")
    @Expose
    private String runtime;
    @SerializedName("Genre")
    @Expose
    private String genre;
    @SerializedName("Director")
    @Expose
    private String director;
    @SerializedName("Writer")
    @Expose
    private String writer;
    @SerializedName("Actors")
    @Expose
    private String actors;
    @SerializedName("Plot")
    @Expose
    private String plot;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Awards")
    @Expose
    private String awards;
    @SerializedName("Poster")
    @Expose
    private String poster;
    @SerializedName("Metascore")
    @Expose
    private String metascore;
    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;
    @SerializedName("imdbVotes")
    @Expose
    private String imdbVotes;
    @PrimaryKey
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("tomatoMeter")
    @Expose
    private String tomatoMeter;
    @SerializedName("tomatoImage")
    @Expose
    private String tomatoImage;
    @SerializedName("tomatoRating")
    @Expose
    private String tomatoRating;
    @SerializedName("tomatoReviews")
    @Expose
    private String tomatoReviews;
    @SerializedName("tomatoFresh")
    @Expose
    private String tomatoFresh;
    @SerializedName("tomatoRotten")
    @Expose
    private String tomatoRotten;
    @SerializedName("tomatoConsensus")
    @Expose
    private String tomatoConsensus;
    @SerializedName("tomatoUserMeter")
    @Expose
    private String tomatoUserMeter;
    @SerializedName("tomatoUserRating")
    @Expose
    private String tomatoUserRating;
    @SerializedName("tomatoUserReviews")
    @Expose
    private String tomatoUserReviews;
    @SerializedName("tomatoURL")
    @Expose
    private String tomatoURL;
    @SerializedName("DVD")
    @Expose
    private String dVD;
    @SerializedName("BoxOffice")
    @Expose
    private String boxOffice;
    @SerializedName("Production")
    @Expose
    private String production;
    @SerializedName("Website")
    @Expose
    private String website;
    @SerializedName("Response")
    @Expose
    private String response;

    private Date date;

    private final static long serialVersionUID = -3260180359402731223L;

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

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
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

    public String getTomatoMeter() {
        return tomatoMeter;
    }

    public void setTomatoMeter(String tomatoMeter) {
        this.tomatoMeter = tomatoMeter;
    }

    public String getTomatoImage() {
        return tomatoImage;
    }

    public void setTomatoImage(String tomatoImage) {
        this.tomatoImage = tomatoImage;
    }

    public String getTomatoRating() {
        return tomatoRating;
    }

    public void setTomatoRating(String tomatoRating) {
        this.tomatoRating = tomatoRating;
    }

    public String getTomatoReviews() {
        return tomatoReviews;
    }

    public void setTomatoReviews(String tomatoReviews) {
        this.tomatoReviews = tomatoReviews;
    }

    public String getTomatoFresh() {
        return tomatoFresh;
    }

    public void setTomatoFresh(String tomatoFresh) {
        this.tomatoFresh = tomatoFresh;
    }

    public String getTomatoRotten() {
        return tomatoRotten;
    }

    public void setTomatoRotten(String tomatoRotten) {
        this.tomatoRotten = tomatoRotten;
    }

    public String getTomatoConsensus() {
        return tomatoConsensus;
    }

    public void setTomatoConsensus(String tomatoConsensus) {
        this.tomatoConsensus = tomatoConsensus;
    }

    public String getTomatoUserMeter() {
        return tomatoUserMeter;
    }

    public void setTomatoUserMeter(String tomatoUserMeter) {
        this.tomatoUserMeter = tomatoUserMeter;
    }

    public String getTomatoUserRating() {
        return tomatoUserRating;
    }

    public void setTomatoUserRating(String tomatoUserRating) {
        this.tomatoUserRating = tomatoUserRating;
    }

    public String getTomatoUserReviews() {
        return tomatoUserReviews;
    }

    public void setTomatoUserReviews(String tomatoUserReviews) {
        this.tomatoUserReviews = tomatoUserReviews;
    }

    public String getTomatoURL() {
        return tomatoURL;
    }

    public void setTomatoURL(String tomatoURL) {
        this.tomatoURL = tomatoURL;
    }

    public String getDVD() {
        return dVD;
    }

    public void setDVD(String dVD) {
        this.dVD = dVD;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MovieDetailModel{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rated='" + rated + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", metascore='" + metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", tomatoMeter='" + tomatoMeter + '\'' +
                ", tomatoImage='" + tomatoImage + '\'' +
                ", tomatoRating='" + tomatoRating + '\'' +
                ", tomatoReviews='" + tomatoReviews + '\'' +
                ", tomatoFresh='" + tomatoFresh + '\'' +
                ", tomatoRotten='" + tomatoRotten + '\'' +
                ", tomatoConsensus='" + tomatoConsensus + '\'' +
                ", tomatoUserMeter='" + tomatoUserMeter + '\'' +
                ", tomatoUserRating='" + tomatoUserRating + '\'' +
                ", tomatoUserReviews='" + tomatoUserReviews + '\'' +
                ", tomatoURL='" + tomatoURL + '\'' +
                ", dVD='" + dVD + '\'' +
                ", boxOffice='" + boxOffice + '\'' +
                ", production='" + production + '\'' +
                ", website='" + website + '\'' +
                ", response='" + response + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieDetailModel that = (MovieDetailModel) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (rated != null ? !rated.equals(that.rated) : that.rated != null) return false;
        if (released != null ? !released.equals(that.released) : that.released != null)
            return false;
        if (runtime != null ? !runtime.equals(that.runtime) : that.runtime != null) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
        if (director != null ? !director.equals(that.director) : that.director != null)
            return false;
        if (writer != null ? !writer.equals(that.writer) : that.writer != null) return false;
        if (actors != null ? !actors.equals(that.actors) : that.actors != null) return false;
        if (plot != null ? !plot.equals(that.plot) : that.plot != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null)
            return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (awards != null ? !awards.equals(that.awards) : that.awards != null) return false;
        if (poster != null ? !poster.equals(that.poster) : that.poster != null) return false;
        if (metascore != null ? !metascore.equals(that.metascore) : that.metascore != null)
            return false;
        if (imdbRating != null ? !imdbRating.equals(that.imdbRating) : that.imdbRating != null)
            return false;
        if (imdbVotes != null ? !imdbVotes.equals(that.imdbVotes) : that.imdbVotes != null)
            return false;
        if (imdbID != null ? !imdbID.equals(that.imdbID) : that.imdbID != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (tomatoMeter != null ? !tomatoMeter.equals(that.tomatoMeter) : that.tomatoMeter != null)
            return false;
        if (tomatoImage != null ? !tomatoImage.equals(that.tomatoImage) : that.tomatoImage != null)
            return false;
        if (tomatoRating != null ? !tomatoRating.equals(that.tomatoRating) : that.tomatoRating != null)
            return false;
        if (tomatoReviews != null ? !tomatoReviews.equals(that.tomatoReviews) : that.tomatoReviews != null)
            return false;
        if (tomatoFresh != null ? !tomatoFresh.equals(that.tomatoFresh) : that.tomatoFresh != null)
            return false;
        if (tomatoRotten != null ? !tomatoRotten.equals(that.tomatoRotten) : that.tomatoRotten != null)
            return false;
        if (tomatoConsensus != null ? !tomatoConsensus.equals(that.tomatoConsensus) : that.tomatoConsensus != null)
            return false;
        if (tomatoUserMeter != null ? !tomatoUserMeter.equals(that.tomatoUserMeter) : that.tomatoUserMeter != null)
            return false;
        if (tomatoUserRating != null ? !tomatoUserRating.equals(that.tomatoUserRating) : that.tomatoUserRating != null)
            return false;
        if (tomatoUserReviews != null ? !tomatoUserReviews.equals(that.tomatoUserReviews) : that.tomatoUserReviews != null)
            return false;
        if (tomatoURL != null ? !tomatoURL.equals(that.tomatoURL) : that.tomatoURL != null)
            return false;
        if (dVD != null ? !dVD.equals(that.dVD) : that.dVD != null) return false;
        if (boxOffice != null ? !boxOffice.equals(that.boxOffice) : that.boxOffice != null)
            return false;
        if (production != null ? !production.equals(that.production) : that.production != null)
            return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;
        return response != null ? response.equals(that.response) : that.response == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (rated != null ? rated.hashCode() : 0);
        result = 31 * result + (released != null ? released.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (writer != null ? writer.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        result = 31 * result + (plot != null ? plot.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (awards != null ? awards.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (metascore != null ? metascore.hashCode() : 0);
        result = 31 * result + (imdbRating != null ? imdbRating.hashCode() : 0);
        result = 31 * result + (imdbVotes != null ? imdbVotes.hashCode() : 0);
        result = 31 * result + (imdbID != null ? imdbID.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (tomatoMeter != null ? tomatoMeter.hashCode() : 0);
        result = 31 * result + (tomatoImage != null ? tomatoImage.hashCode() : 0);
        result = 31 * result + (tomatoRating != null ? tomatoRating.hashCode() : 0);
        result = 31 * result + (tomatoReviews != null ? tomatoReviews.hashCode() : 0);
        result = 31 * result + (tomatoFresh != null ? tomatoFresh.hashCode() : 0);
        result = 31 * result + (tomatoRotten != null ? tomatoRotten.hashCode() : 0);
        result = 31 * result + (tomatoConsensus != null ? tomatoConsensus.hashCode() : 0);
        result = 31 * result + (tomatoUserMeter != null ? tomatoUserMeter.hashCode() : 0);
        result = 31 * result + (tomatoUserRating != null ? tomatoUserRating.hashCode() : 0);
        result = 31 * result + (tomatoUserReviews != null ? tomatoUserReviews.hashCode() : 0);
        result = 31 * result + (tomatoURL != null ? tomatoURL.hashCode() : 0);
        result = 31 * result + (dVD != null ? dVD.hashCode() : 0);
        result = 31 * result + (boxOffice != null ? boxOffice.hashCode() : 0);
        result = 31 * result + (production != null ? production.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        return result;
    }
}
