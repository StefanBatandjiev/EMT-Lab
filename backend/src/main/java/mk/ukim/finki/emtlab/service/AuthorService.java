package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<Author> listAll();

    Author findAuthorById(Long id);

    Author addAuthor(AuthorDto authorDto);

    Author editAuthor(Long id, AuthorDto authorDto);

    void deleteAuthor(Long id);
}
