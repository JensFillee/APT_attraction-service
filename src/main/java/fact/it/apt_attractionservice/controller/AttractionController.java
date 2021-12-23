package fact.it.apt_attractionservice.controller;

import fact.it.apt_attractionservice.model.Attraction;
import fact.it.apt_attractionservice.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(attractionRepository.findAttractionByAttractionCode("I001").getName());
    }

    @Autowired
    private AttractionRepository attractionRepository;

    @GetMapping("/attractions/{attractionCode}")
    public Attraction getAttractionByAttractionCode(@PathVariable String attractionCode) {
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

    @PostMapping("/attractions")
    public Attraction addAttraction(@RequestBody Attraction attraction) {
        attractionRepository.save(attraction);
        return attraction;
    }

    @PutMapping("/attractions")
    public Attraction updateAttraction(@RequestBody Attraction updatedAttraction) {
        Attraction retrievedAttraction = attractionRepository.findAttractionByAttractionCode(updatedAttraction.getAttractionCode());

        retrievedAttraction.setName(updatedAttraction.getName());
        retrievedAttraction.setMinHeight(updatedAttraction.getMinHeight());
        retrievedAttraction.setThemeparkCode(updatedAttraction.getThemeparkCode());
        retrievedAttraction.setTypeId(updatedAttraction.getTypeId());

        attractionRepository.save(retrievedAttraction);

        return retrievedAttraction;
    }

    @DeleteMapping("/attractions")
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
