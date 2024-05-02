package de.hftstuttgart.vs.task07.integration.graphql;

import java.util.List;

import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import de.hftstuttgart.vs.task07.integration.graphql.types.Continent;
import de.hftstuttgart.vs.task07.integration.graphql.types.Country;
import reactor.core.publisher.Mono;

@Service
public class CountryClient {

    private final GraphQlClient graphQlClient;

    public CountryClient() {
        final WebClient webClient = WebClient.create("https://countries.trevorblades.com/graphql");
        graphQlClient = HttpGraphQlClient.builder(webClient).build();
    }

    public Mono<Country> getCountryByCountryCode(final String countryCode, final String lang) {
        return graphQlClient
                .documentName("getCountry")
                .variable("countryCode", countryCode)
                .variable("lang", lang)
                .retrieve("country")
                .toEntity(Country.class);
    }

    public Mono<List<Country>> getCountriesByCurrency(final String currency) {
        return graphQlClient
                .documentName("getAllCountriesWithCurrency")
                .variable("currency", currency)
                .retrieve("countries")
                .toEntityList(Country.class);
    }

    public Mono<List<Continent>> getAllContinents() {
        return graphQlClient
                .documentName("getAllContinents")
                .retrieve("continents")
                .toEntityList(Continent.class);
    }

    public Mono<Continent> getContinentByCodeWithCountries(final String code) {
        return graphQlClient
                .documentName("getContinentWithCountries")
                .variable("continentCode", code)
                .retrieve("continent")
                .toEntity(Continent.class);
    }

}
