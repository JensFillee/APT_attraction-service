package fact.it.apt_attractionservice.repository;

import fact.it.apt_attractionservice.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {
    Attraction findAttractionByAttractionCode(String attractionCode);

    @Query("select a from Attraction a where a.themeparkCode = :themeparkCode and a.name like %:name%")
    List<Attraction> findAttractionsByThemeparkCodeEqualsAndNameContaining(@Param("name") String name, @Param("themeparkCode") String themeparkCode);

    List<Attraction> findAttractionByTypeId(Integer typeId);
    List<Attraction> findAttractionsByThemeparkCode(String themeparkCode);
    List<Attraction> findAttractionsByTypeIdAndThemeparkCode(Integer typeId, String themeparkCode);
}
