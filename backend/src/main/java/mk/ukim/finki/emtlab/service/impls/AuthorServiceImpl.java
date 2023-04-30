package mk.ukim.finki.emtlab.service.impls;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dto.AuthorDto;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author addAuthor(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountry()).orElse(null);

        if (country == null){
            return null;
        }

        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);
        return this.authorRepository.save(author);
    }

    @Override
    public Author editAuthor(Long id, AuthorDto authorDto) {
        Author author = this.findAuthorById(id);

        if (author == null){
            return null;
        }

        Country country = this.countryRepository.findById(authorDto.getCountry()).orElse(null);

        if (country == null){
            return null;
        }

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);

        return this.authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        this.authorRepository.deleteById(id);
    }
}
