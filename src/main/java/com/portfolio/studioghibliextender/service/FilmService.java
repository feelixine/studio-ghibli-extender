package com.portfolio.studioghibliextender.service;

import com.portfolio.studioghibliextender.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    private RestTemplate template = new RestTemplate();

    Film[] filmArray = template.getForObject("https://ghibliapi.herokuapp.com/films/", Film[].class);

    public Object[] findAllFilmsComplete() {
        return template.getForObject("https://ghibliapi.herokuapp.com/films/", Object[].class);
    }

    public Film[] findAllFilms() {
        return filmArray;
    }

    public List<Film> findFilmsByTitle(String title) {
        List<Film> filmList = new ArrayList<>();
        for (int i = 0; i < filmArray.length; i++) {
            Film film = filmArray[i];
            String englishTitle = film.getTitle().toLowerCase();
            String romajiTitle = film.getOriginal_title_romanised().toLowerCase();
            if (englishTitle.contains(title.toLowerCase()) || romajiTitle.contains(title.toLowerCase())) {
                filmList.add(film);
            }
        }
        return filmList;
    }

    public Map<Integer, String> findDirectors() {
        Map<Integer, String> map = new HashMap<>();
        List<Film> filmList = Arrays.asList(filmArray);
        int mapKey = 1;
        for (Film film : filmList) {
            String director = film.getDirector();
            if (!map.containsValue(director)) {
                map.put(mapKey, director);
                mapKey++;
            }
        }
        return map;
    }

    public List<Film> findFilmsByNewest() {
        List<Film> filmList = Arrays.asList(filmArray);
        orderFilmsByReleaseDate(filmList);
        return filmList;
    }

    public List<Film> sortFilms(Integer order, String field) {
        List<Film> filmList = Arrays.asList(filmArray);
        orderFilmsByField(filmList, field);
        // order the films either ascending or descending
        if (order == 1) {
            Collections.reverse(filmList);
        }
        ;
        return filmList;
    }

    private List<Film> orderFilmsByReleaseDate(List<Film> list) {
        list.sort(Comparator.comparing(film -> film.getRelease_date()));
        Collections.reverse(list);
        return list;
    }

    private List<Film> orderFilmsByField(List<Film> list, String field) {
        if (field.equals("running_time")) {
            list.sort(Comparator.comparing(film -> film.getRunning_time()));
        } else if (field.equals("rt_score")) {
            list.sort(Comparator.comparing(film -> film.getRt_score()));
        } else {
            System.out.println(field + " is not a valid field");
        }
        ;
        return list;
    }
}