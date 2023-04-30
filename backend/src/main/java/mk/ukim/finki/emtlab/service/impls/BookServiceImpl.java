package mk.ukim.finki.emtlab.service.impls;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dto.BookDto;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.BookRepository;
import mk.ukim.finki.emtlab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Book findBookById(Long id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book addBook(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElse(null);

        if (author == null){
            return null;
        }

        Book book = new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());

        return this.bookRepository.save(book);
    }

    @Override
    public Book editBook(Long id, BookDto bookDto) {
        Book book = this.findBookById(id);

        if (book == null){
            return null;
        }

        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElse(null);

        if (author == null){
            return null;
        }

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void markBookAsTaken(Long id) {
        Book book = this.findBookById(id);

        if (book == null){
            return;
        }

        book.setAvailableCopies(book.getAvailableCopies()-1);
        this.bookRepository.save(book);
    }
}
