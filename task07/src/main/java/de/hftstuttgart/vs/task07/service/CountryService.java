package de.hftstuttgart.vs.task07.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.hftstuttgart.vs.task07.integration.graphql.CountryClient;
import de.hftstuttgart.vs.task07.integration.graphql.types.Country;

@Service
public class CountryService implements ICountryService {

    private final CountryClient countryClient;

    public CountryService(final CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    @Override
    public Country getCountryByCode(final String countryCode, final String lang) {
        return countryClient.getCountryByCountryCode(countryCode, lang).block();
    }

    @Override
    public List<Country> getCountriesByCurrency(final String currency) {
        return countryClient.getCountriesByCurrency(currency).block();
    }

}
