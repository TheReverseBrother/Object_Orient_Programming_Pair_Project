package DAOs;

import DTOs.Movie;
import Exceptions.DAOException;

import java.util.List;

public interface MovieDAOInterface
{
    public List<Movie> findAllMovies() throws DAOException;
    public Movie findMovieByTitle(String title) throws DAOException;
    public List<Movie> findMovieByDirector(String Director) throws DAOException;
}
