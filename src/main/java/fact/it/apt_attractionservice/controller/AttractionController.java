package fact.it.apt_attractionservice.controller;

import fact.it.apt_attractionservice.model.Attraction;
import fact.it.apt_attractionservice.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class AttractionController {
    // temporary method to fill DB with test data
    @PostConstruct
    public void fillDB() {
        if(attractionRepository.count() == 0) {
            attractionRepository.save(new Attraction(1, "Typhoon", 125, 1, "BBJ", "I001"));
        }
    }

    @Autowired
    private AttractionRepository attractionRepository;

    @GetMapping("/attractions/{attractionCode}")
    public Attraction getAttractionByAttactionCode(@PathVariable String attractionCode) {
        return attractionRepository.findAttractionByAttractionCode(attractionCode);
    }

    @GetMapping("/attractions/type/{typeId}")
    public List<Attraction> getAttractionsByTypeId(@PathVariable Integer typeId) {
        return  attractionRepository.findAttractionByTypeId(typeId);
    }

    @GetMapping("/attractions/themepark/{themeparkCode}")
    public List<Attraction> getAttractionsByThemeparkCode(@PathVariable String themeparkCode) {
        return attractionRepository.findAttractionsByThemeparkCode(themeparkCode);
    }

    @GetMapping("/attractions/type/{typeId}/themepark/{themeparkCode}")
    public List<Attraction> getAttractionsByTypeIdAndThemeparkCode(@PathVariable Integer typeId, @PathVariable String themeparkCode) {
        return attractionRepository.findAttractionsByTypeIdAndThemeparkCode(typeId, themeparkCode);
    }
}
