package de.hftstuttgart.vs.task07.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import de.hftstuttgart.vs.task07.service.ICountryService;
import jakarta.annotation.Nullable;

@Controller
public class CountryController {

    private final ICountryService countryService;

    public CountryController(final ICountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries/{countryCode}")
    public String continentByCode(@PathVariable final String countryCode, @RequestParam @Nullable final String lang,
            final Model model) {
        model.addAttribute("country", countryService.getCountryByCode(countryCode, lang));
        model.addAttribute("lang", lang);
        return "country";
    }

    @GetMapping("/currencies/{currency}")
    public String countriesByCurrency(@PathVariable final String currency, final Model model) {
        model.addAttribute("currency", currency);
        model.addAttribute("countries", countryService.getCountriesByCurrency(currency));
        return "currency";
    }

}

