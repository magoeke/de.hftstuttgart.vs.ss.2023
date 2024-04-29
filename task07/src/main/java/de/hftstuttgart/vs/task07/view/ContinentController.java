package de.hftstuttgart.vs.task07.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.hftstuttgart.vs.task07.service.IContinentService;

@Controller
public class ContinentController {

    private final IContinentService continentService;

    public ContinentController(final IContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/continents";
    }

    @GetMapping("/continents")
    public String continents(final Model model) {
        model.addAttribute("continents", continentService.getAllContinents());
        return "continents";
    }

    @GetMapping("/continents/{continentCode}")
    public String continentByCode(@PathVariable final String continentCode, final Model model) {
        model.addAttribute("continent", continentService.getContinentByCodeWithCountries(continentCode));
        return "continent";
    }

}

