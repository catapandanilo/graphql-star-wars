package com.github.catapan.graphql;

import java.util.List;
import javax.inject.Inject;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

@GraphQLApi
public class FilmResource {

  @Inject
  GalaxyService service;

  @Query("todosFilmes")
  @Description("Buscar todos os filmes da galaxia tão, tão distante")
  public List<Film> getAllFilms() {
    return service.getAllFilms();
  }

  @Query
  @Description("Buscar um filme da galaxia tão, tão distante")
  public Film getFilm(@Name("filmId") int id) {
    return service.getFilm(id);
  }

  public List<Hero> heroes(@Source Film film) {
    return service.getHeroesByFilm(film);
  }

  @Mutation
  public Hero createHero(Hero hero) {
    service.addHero(hero);
    return hero;
  }

  @Mutation
  public Hero deleteHero(int id) {
    return service.deleteHero(id);
  }

  @Query
  public List<Hero> getHeroesWithSurname(@DefaultValue("Skywalker") String surname) {
    return service.getHeroesBySurname(surname);
  }

}
