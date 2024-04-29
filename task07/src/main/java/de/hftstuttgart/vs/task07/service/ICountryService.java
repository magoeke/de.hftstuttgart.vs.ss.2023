package de.hftstuttgart.vs.task07.service;

import java.util.List;

import de.hftstuttgart.vs.task07.integration.graphql.types.Country;
import jakarta.annotation.Nullable;

public interface ICountryService {

    Country getCountryByCode(String countryCode, @Nullable String lang);

    List<Country> getCountriesByCurrency(String currency);

}
