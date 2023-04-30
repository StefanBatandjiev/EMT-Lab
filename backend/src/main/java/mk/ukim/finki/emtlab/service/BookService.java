package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<Book> listAll();

    Page<Book> findAllWithPagination(Pageable pageable);

    Book findBookById(Long id);

    Book addBook(BookDto bookDto);

    Book editBook(Long id, BookDto bookDto);

    void deleteBook(Long id);

    void markBookAsTaken(Long id);
}
