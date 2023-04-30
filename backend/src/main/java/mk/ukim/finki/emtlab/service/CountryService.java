package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dto.CountryDto;

import java.util.List;

public interface CountryService {

    List<Country> listAll();

    Country findCountryById(Long id);

    Country addCountry(CountryDto countryDto);

    Country editCountry(Long id, CountryDto countryDto);

    void deleteCountry(Long id);
}
