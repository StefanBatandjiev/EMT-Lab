package mk.ukim.finki.emtlab.service.impls;

import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dto.CountryDto;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country findCountryById(Long id) {
        return this.countryRepository.findById(id).orElse(null);
    }

    @Override
    public Country addCountry(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        return this.countryRepository.save(country);
    }

    @Override
    public Country editCountry(Long id, CountryDto countryDto) {
        Country country = this.findCountryById(id);
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());
        return this.countryRepository.save(country);
    }

    @Override
    public void deleteCountry(Long id) {
        this.countryRepository.deleteById(id);
    }
}
