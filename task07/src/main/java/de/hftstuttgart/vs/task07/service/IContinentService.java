package de.hftstuttgart.vs.task07.service;

import java.util.List;

import de.hftstuttgart.vs.task07.integration.graphql.types.Continent;

public interface IContinentService {

    List<Continent> getAllContinents();

    Continent getContinentByCodeWithCountries(String code);

}
