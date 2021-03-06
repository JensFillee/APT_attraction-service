package fact.it.apt_attractionservice.controller;

import fact.it.apt_attractionservice.model.Attraction;
import fact.it.apt_attractionservice.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AttractionController {
    // temporary method to fill DB with test data
    @PostConstruct
    public void fillDB() {
        if(attractionRepository.count() == 0) {
            attractionRepository.save(new Attraction(1, "Typhoon", "Duistere krachten in Land of Legends wekten een eeuwige Typhoon op. Spring in de koets van de Skyriders, vlieg met hen mee en duik 25 meter het diepe in. Help jij heks Wayra in te tomen?", 125, 1, "156545", "A001"));
            attractionRepository.save(new Attraction(2, "Indiana River", "Neem met de hele familie plaats in een boomstammetje en ontdek de jungle in Indiana River. Trotseer de vele bergen en dalen en bereid je voor om nat te worden!", 100, 2, "156545", "A002"));
            attractionRepository.save(new Attraction(3, "Dalton Terror", "Nietsvermoedend 77 m kaarsrecht omhoog, genietend van de frisse lucht, van steil gesproken... Alles rondom jou wordt steeds kleiner", 120, 1, "156554", "A003"));
            attractionRepository.save(new Attraction(4, "Speedy Bob", "sneller dan snel", 120, 4, "156554", "A074"));
            attractionRepository.save(new Attraction(5, "King Kong", "Overleef jij King Kong? ", 100, 5, "156554", "A075"));
        }
    }

    @Autowired
    private AttractionRepository attractionRepository;

    @GetMapping("/attractions/{attractionCode}")
    public Attraction getAttractionByAttractionCode(@PathVariable String attractionCode) {
        return attractionRepository.findAttractionByAttractionCode(attractionCode);
    }

    @GetMapping("/attractions/name/{name}/themepark/{themeparkCode}")
    public List<Attraction> getAttractionsByNameAndThemeparkCode(@PathVariable String name, @PathVariable String themeparkCode) {
        return attractionRepository.findAttractionsByThemeparkCodeEqualsAndNameContaining(name, themeparkCode);
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

    @PostMapping("/attractions")
    public Attraction addAttraction(@RequestBody Attraction attraction) {
        attractionRepository.save(attraction);
        return attraction;
    }

    @PutMapping("/attractions")
    public Attraction updateAttraction(@RequestBody Attraction updatedAttraction) {
        Attraction retrievedAttraction = attractionRepository.findAttractionByAttractionCode(updatedAttraction.getAttractionCode());

        retrievedAttraction.setName(updatedAttraction.getName());
        retrievedAttraction.setDescription(updatedAttraction.getDescription());
        retrievedAttraction.setMinHeight(updatedAttraction.getMinHeight());
        retrievedAttraction.setThemeparkCode(updatedAttraction.getThemeparkCode());
        retrievedAttraction.setTypeId(updatedAttraction.getTypeId());

        attractionRepository.save(retrievedAttraction);

        return retrievedAttraction;
    }

    @DeleteMapping("/attractions/{attractionCode}")
    public ResponseEntity deleteAttraction(@PathVariable String attractionCode) {
        Attraction attraction = attractionRepository.findAttractionByAttractionCode(attractionCode);
        if(attraction != null) {
            attractionRepository.delete(attraction);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
