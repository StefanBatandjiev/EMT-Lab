package mk.ukim.finki.emtlab.data;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.enums.Category;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.BookRepository;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    void init(){
        Country country1 = new Country("England", "Europe");
        Country country2 = new Country("France", "Europe");
        Country country3 = new Country("USA", "North America");
        Country country4 = new Country("Japan", "Asia");
        Country country5 = new Country("South Africa", "Africa");
        this.countryRepository.save(country1);
        this.countryRepository.save(country2);
        this.countryRepository.save(country3);
        this.countryRepository.save(country4);
        this.countryRepository.save(country5);
        Author author1 = new Author("William", "Shakespeare", country1);
        Author author2 = new Author("Stephen","King", country3);
        Author author3 = new Author("Leo","Tolstoy", country2);
        Author author4 = new Author("Joanne","Rowling", country1);
        Author author5 = new Author("James","Joyce", country4);
        Author author6 = new Author("William2", "Shakespeare2", country4);
        Author author7 = new Author("Stephen2","King2", country5);
        Author author8 = new Author("Leo2","Tolstoy2", country3);
        Author author9 = new Author("Joanne2","Rowling2", country5);
        Author author10 = new Author("James2","Joyce2", country2);
        this.authorRepository.save(author1);
        this.authorRepository.save(author2);
        this.authorRepository.save(author3);
        this.authorRepository.save(author4);
        this.authorRepository.save(author5);
        this.authorRepository.save(author6);
        this.authorRepository.save(author7);
        this.authorRepository.save(author8);
        this.authorRepository.save(author9);
        this.authorRepository.save(author10);
        Book book1 = new Book("Anna Karenina", Category.BIOGRAPHY,author1,20);
        Book book2 = new Book("The Great Gatsby", Category.DRAMA, author2, 67);
        Book book3 = new Book("To Kill A Mockingbird", Category.HISTORY, author3, 300);
        Book book4 = new Book("Game Of Thrones", Category.CLASSICS,author4,212);
        Book book5 = new Book("Beloved", Category.DRAMA, author5, 123);
        Book book6 = new Book("Invisible Man", Category.FANTASY, author6, 70);
        Book book7 = new Book("A Passage To India", Category.NOVEL,author7,22);
        Book book8 = new Book("The Color Purple", Category.DRAMA, author8, 189);
        Book book9 = new Book("The Lord Of The Rings", Category.THRILLER, author9, 359);
        Book book10 = new Book("Harry Potter and Philosopher's Stone", Category.FANTASY,author2,637);
        Book book11 = new Book("The Book Thief", Category.CLASSICS, author10, 17);
        Book book12 = new Book("Alice's Adventures in Wonderland", Category.FANTASY, author3, 120);
        this.bookRepository.save(book1);
        this.bookRepository.save(book2);
        this.bookRepository.save(book3);
        this.bookRepository.save(book4);
        this.bookRepository.save(book5);
        this.bookRepository.save(book6);
        this.bookRepository.save(book7);
        this.bookRepository.save(book8);
        this.bookRepository.save(book9);
        this.bookRepository.save(book10);
        this.bookRepository.save(book11);
        this.bookRepository.save(book12);
    }
}
