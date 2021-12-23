package fact.it.apt_attractionservice.model;

import javax.persistence.*;

@Entity
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int minHeight;
    private int typeId;

    private String themeparkCode;

    @Column(unique = true)
    private String attractionCode;

    public Attraction(int id, String name, int minHeight, int typeId, String themeparkCode, String attractionCode) {
        this.id = id;
        this.name = name;
        this.minHeight = minHeight;
        this.typeId = typeId;
        this.themeparkCode = themeparkCode;
        this.attractionCode = attractionCode;
    }

    public Attraction() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getThemeparkCode() {
        return themeparkCode;
    }

    public void setThemeparkCode(String themeparkCode) {
        this.themeparkCode = themeparkCode;
    }

    public String getAttractionCode() {
        return attractionCode;
    }

    public void setAttractionCode(String attractionCode) {
        this.attractionCode = attractionCode;
    }
}
