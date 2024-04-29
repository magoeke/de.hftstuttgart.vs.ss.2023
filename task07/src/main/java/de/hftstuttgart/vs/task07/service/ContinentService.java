package de.hftstuttgart.vs.task07.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.hftstuttgart.vs.task07.integration.graphql.CountryClient;
import de.hftstuttgart.vs.task07.integration.graphql.types.Continent;

@Service
public class ContinentService implements IContinentService {

    private final CountryClient countryClient;

    public ContinentService(final CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    @Override
    public List<Continent> getAllContinents() {
        return countryClient.getAllContinents().block();
    }

    @Override
    public Continent getContinentByCodeWithCountries(final String code) {
        return countryClient.getContinentByCodeWithCountries(code).block();
    }

}
