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

    /*
      TODO:
      1. Add query document names
      2. Assign variables as needed in the query document
    */

    private final GraphQlClient graphQlClient;

    public CountryClient() {
        final WebClient webClient = WebClient.create("https://countries.trevorblades.com/graphql");
        graphQlClient = HttpGraphQlClient.builder(webClient).build();
    }

    public Mono<Country> getCountryByCountryCode(final String countryCode, final String lang) {
        return graphQlClient.documentName("TODO").variable("TODO", "TODO").retrieve("country").toEntity(Country.class);
    }

    public Mono<List<Country>> getCountriesByCurrency(final String currency) {
        return graphQlClient
                .documentName("TODO")
                .variable("TODO", "TODO")
                .retrieve("countries")
                .toEntityList(Country.class);
    }

    public Mono<List<Continent>> getAllContinents() {
        return graphQlClient.documentName("TODO").retrieve("continents").toEntityList(Continent.class);
    }

    public Mono<Continent> getContinentByCodeWithCountries(final String code) {
        return graphQlClient
                .documentName("TODO")
                .variable("TODO", "TODO")
                .retrieve("continent")
                .toEntity(Continent.class);
    }

}
