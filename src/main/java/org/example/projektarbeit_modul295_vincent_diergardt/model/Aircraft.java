package org.example.projektarbeit_modul295_vincent_diergardt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Model Aircraft
 */
@Entity
@Table(name = "aircraft")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacture")
    private String manufacture;

    @Column(name = "gewicht")
    private String gewicht;

    @Column(name = "createdate")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "aircraft")
    @JsonIgnore

    private List<Flight> flights;

    /**
     * Erstellt ein neues Aircraft.
     */
    public Aircraft() {
    }

    /**
     * Erstellt
     */
    @PrePersist
    public void onCreate() {
        this.createDate = LocalDateTime.now();
    }


    /** Getter und Setter
     * erhaltet eine id
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setzt eine id
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Erhaltet ein model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Setzt model.
     *
     * @param model the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Erhaltet eine manufacture.
     *
     * @return the manufacture
     */
    public String getManufacture() {
        return manufacture;
    }

    /**
     * Sets manufacture.
     *
     * @param manufacture the manufacture
     */
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    /**
     * Gets gewicht.
     *
     * @return the gewicht
     */
    public String getGewicht() {
        return gewicht;
    }

    /**
     * Sets gewicht.
     *
     * @param gewicht the gewicht
     */
    public void setGewicht(String gewicht) {
        this.gewicht = gewicht;
    }

    /**
     * Gets flights.
     *
     * @return the flights
     */
    public List<Flight> getFlights() {
        return flights;
    }

    /**
     * Sets flights.
     *
     * @param flights the flights
     */
    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
