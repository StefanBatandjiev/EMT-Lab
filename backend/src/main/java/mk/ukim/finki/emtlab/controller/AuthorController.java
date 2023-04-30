package mk.ukim.finki.emtlab.controller;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.dto.AuthorDto;
import mk.ukim.finki.emtlab.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> listAuthors(){
        return this.authorService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        Author author = this.authorService.findAuthorById(id);

        if (author == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(author);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDto authorDto){
        Author author = this.authorService.addAuthor(authorDto);

        if (author == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(author);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto){
        Author author = this.authorService.editAuthor(id, authorDto);

        if (author == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(author);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id){
        Author author = this.authorService.findAuthorById(id);

        if (author == null) {
            return ResponseEntity.notFound().build();
        } else {
            this.authorService.deleteAuthor(id);
            return ResponseEntity.ok(author);
        }
    }
}
