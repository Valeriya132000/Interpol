package ru.interpol.controllers.app;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.interpol.domain.dto.CriminalPersonDto;
import ru.interpol.domain.dto.DescriptionDto;
import ru.interpol.domain.dto.DeveloperDto;
import ru.interpol.domain.dto.DeveloperPersonDto;
import ru.interpol.domain.dto.FilterDto;
import ru.interpol.domain.dto.InterpolDto;
import ru.interpol.services.DescriptionService;
import ru.interpol.services.DeveloperService;
import ru.interpol.services.InterpolService;

@Controller
public class AppController {
    @Autowired
    private InterpolService interpolService;
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private DescriptionService descriptionService;
    private int activePage;
    private FilterDto filter;
    private FilterDto filterArchive;

    public AppController() {
        this.activePage = 0;
        this.filter = new FilterDto();
        this.filterArchive = new FilterDto();
    }

    @GetMapping("/")
    public String viewHomePage(Model model)  {
        model.addAttribute("interpolList", interpolService.searchWithFilter(filter.getSearchString()));
        model.addAttribute("interpolArchiveList", interpolService.searchArchiveWithFilter(filterArchive.getSearchString()));
        model.addAttribute("developerList", developerService.getAllDevelopers());
        model.addAttribute("descriptionList", descriptionService.getAllDescriptions());
        model.addAttribute("criminalPersonDto", new CriminalPersonDto());
        model.addAttribute("developerPersonDto", new DeveloperPersonDto());
        model.addAttribute("descriptionDto", new DescriptionDto());

        InterpolDto interpolDto = new InterpolDto();
        interpolDto.setCriminalPerson(new CriminalPersonDto());
        model.addAttribute("interpolDto", interpolDto);

        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setDeveloperPerson(new DeveloperPersonDto());
        model.addAttribute("developerDto", developerDto);

        model.addAttribute("descriptionDto", new DescriptionDto());
        model.addAttribute("filterDto", filter);
        model.addAttribute("filterArchiveDto", filterArchive);

        model.addAttribute("activePage", this.activePage);

        return "index";
    }

    @PostMapping("/createCriminalPerson")
    public String createCriminalPerson(@ModelAttribute("criminalPersonDto") CriminalPersonDto criminalPersonDto) {
        InterpolDto interpolDto = new InterpolDto();
        interpolDto.setCriminalPerson(criminalPersonDto);
        interpolService.createInterpol(interpolDto);
        this.activePage = 0;

        return "redirect:/";
    }

    @DeleteMapping("/deleteCriminalPerson/{id}")
    public String deleteCriminalPerson(@PathVariable(value = "id") long id) {
        interpolService.deleteInterpol(id);
        this.activePage = 0;

        return "redirect:/";
    }

    @PutMapping("/updateCriminalPerson")
    public String updateCriminalPerson(@ModelAttribute("interpolDto") InterpolDto interpolDto) {
        interpolService.updateInterpol(interpolDto);
        this.activePage = 0;

        return "redirect:/";
    }

    @DeleteMapping("/archiveCriminalPerson/{id}")
    public String archiveCriminalPerson(@PathVariable(value = "id") long id) {
        interpolService.moveToInterpolArchive(id);
        this.activePage = 0;

        return "redirect:/";
    }

    @PostMapping("/createDeveloperPerson")
    public String createDeveloperPerson(@ModelAttribute("developerPersonDto") DeveloperPersonDto developerPersonDto) {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setDeveloperPerson(developerPersonDto);
        developerService.createDeveloper(developerDto);
        this.activePage = 1;

        return "redirect:/";
    }

    @PutMapping("/updateDeveloperPerson")
    public String updateDeveloperPerson(@ModelAttribute("developerDto") DeveloperDto developerDto) {
        developerService.updateDeveloper(developerDto);
        this.activePage = 1;

        return "redirect:/";
    }

    @DeleteMapping("/deleteDeveloperPerson/{id}")
    public String deleteDeveloperPerson(@PathVariable(value = "id") long id) {
        developerService.deleteDeveloper(id);
        this.activePage = 1;

        return "redirect:/";
    }

    @PostMapping("/createDescription")
    public String createDescription(@ModelAttribute("descriptionDto") DescriptionDto descriptionDto) {
        descriptionService.createDescription(descriptionDto);
        this.activePage = 1;

        return "redirect:/";
    }

    @PutMapping("/updateDescription")
    public String updateDescription(@ModelAttribute("descriptionDto") DescriptionDto descriptionDto) {
        descriptionService.updateDescription(descriptionDto);
        this.activePage = 1;

        return "redirect:/";
    }

    @DeleteMapping("/deleteDescription/{id}")
    public String deleteDescription(@PathVariable(value = "id") long id) {
        descriptionService.deleteDescription(id);
        this.activePage = 1;

        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("filterDto") FilterDto filterDto) {
        this.filter = filterDto;
        this.activePage = 0;

        return "redirect:/";
    }

    @PostMapping("/searchArchive")
    public String searchArchive(@ModelAttribute("filterDto") FilterDto filterDto) {
        this.filterArchive = filterDto;
        this.activePage = 0;

        return "redirect:/";
    }
}
