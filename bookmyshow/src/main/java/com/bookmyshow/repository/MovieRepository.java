package com.bookmyshow.repository;

import com.bookmyshow.entity.Feature;
import com.bookmyshow.entity.Genre;
import com.bookmyshow.entity.Language;
import com.bookmyshow.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByLanguagesIn(Collection<Language> languages);

    List<Movie> findAllByGenresIn(Collection<Genre> genres);

    List<Movie> findAllByFeaturesIn(Collection<Feature> features);

    List<Movie> findAllByActorsIdIn(List<Long> ids);

    List<Movie> findAllByActorsNameIn(List<String> names);

    List<Movie> findAllByActorsIdInAndFeaturesInAndGenresInAndLanguagesIn(List<Long> ids, Collection<Feature> features, Collection<Genre> genres, Collection<Language> languages);

    List<Movie> findAllByActorsIdInOrFeaturesInOrGenresInOrLanguagesIn(List<Long> ids, Collection<Feature> features, Collection<Genre> genres, Collection<Language> languages);
}
