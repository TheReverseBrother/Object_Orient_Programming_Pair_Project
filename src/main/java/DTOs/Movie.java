package DTOs;

import java.util.Objects;

public class Movie
{
    private int ID;
    private String title;
    private String genre;
    private String director;
    private String runtime;
    private String plot;
    private String location;
    private String poster;
    private String rating;
    private String format;
    private String year;
    private String starring;
    private int copies;
    private String barcode;
    private String user_rating;

    public Movie(int ID, String title, String genre, String director, String runtime, String plot, String location, String poster, String rating, String format, String year, String starring, int copies, String barcode, String user_rating)
    {
        this.ID = ID;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
        this.copies = copies;
        this.barcode = barcode;
        this.user_rating = user_rating;
    }

    public int getID()
    {
        return ID;
    }

    public String getTitle()
    {
        return title;
    }

    public String getGenre()
    {
        return genre;
    }

    public String getDirector()
    {
        return director;
    }

    public String getRuntime()
    {
        return runtime;
    }

    public String getPlot()
    {
        return plot;
    }

    public String getLocation()
    {
        return location;
    }

    public String getPoster()
    {
        return poster;
    }

    public String getRating()
    {
        return rating;
    }

    public String getFormat()
    {
        return format;
    }

    public String getYear()
    {
        return year;
    }

    public String getStarring()
    {
        return starring;
    }

    public int getCopies()
    {
        return copies;
    }

    public String getBarcode()
    {
        return barcode;
    }

    public String getUser_rating()
    {
        return user_rating;
    }

    @Override
    public String toString()
    {
        return "Movie{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", runtime='" + runtime + '\'' +
                ", Plot='" + plot + '\'' +
                ", location='" + location + '\'' +
                ", poster='" + poster + '\'' +
                ", rating='" + rating + '\'' +
                ", format='" + format + '\'' +
                ", year='" + year + '\'' +
                ", starring='" + starring + '\'' +
                ", copies=" + copies +
                ", barcode='" + barcode + '\'' +
                ", user_rating='" + user_rating + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return ID == movie.ID &&
                copies == movie.copies &&
                Objects.equals(title, movie.title) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(runtime, movie.runtime) &&
                Objects.equals(plot, movie.plot) &&
                Objects.equals(location, movie.location) &&
                Objects.equals(poster, movie.poster) &&
                Objects.equals(rating, movie.rating) &&
                Objects.equals(format, movie.format) &&
                Objects.equals(year, movie.year) &&
                Objects.equals(starring, movie.starring) &&
                Objects.equals(barcode, movie.barcode) &&
                Objects.equals(user_rating, movie.user_rating);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(ID, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
    }
}
