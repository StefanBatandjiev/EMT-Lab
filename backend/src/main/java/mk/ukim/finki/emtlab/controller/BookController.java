package mk.ukim.finki.emtlab.controller;

import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dto.BookDto;
import mk.ukim.finki.emtlab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping({"/api/books"})
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> listAll() {
        return bookService.listAll();
    }

    @GetMapping("/pagination")
    public List<Book> findAllWithPagination(Pageable pageable) {
        return this.bookService.findAllWithPagination(pageable).getContent();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book = this.bookService.findBookById(id);

        if(book == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().body(book);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto){
        Book book = this.bookService.addBook(bookDto);

        if(book == null){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok(book);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto){
        Book book = this.bookService.editBook(id,bookDto);

        if(book == null){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok(book);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Book book = this.bookService.findBookById(id);

        if(book == null){
            return ResponseEntity.notFound().build();
        }else {
            this.bookService.deleteBook(id);
            return ResponseEntity.ok(book);
        }
    }

    @PutMapping("/mark/{id}")
    public ResponseEntity<Book> markBookAsTaken(@PathVariable Long id){
        Book book = this.bookService.findBookById(id);

        if(book == null){
            return ResponseEntity.notFound().build();
        }else {
            this.bookService.markBookAsTaken(id);
            return ResponseEntity.ok(book);
        }
    }
}
