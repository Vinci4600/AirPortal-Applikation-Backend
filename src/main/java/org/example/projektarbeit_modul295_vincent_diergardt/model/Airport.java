package org.example.projektarbeit_modul295_vincent_diergardt.model;

import jakarta.persistence.*;

/**
 * Der Type Airport.
 */
@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "iata_code")
    private String iataCode;

    /**Getter und Setter
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets iata code.
     *
     * @return the iata code
     */
    public String getIataCode() {
        return iataCode;
    }

    /**
     * Sets iata code.
     *
     * @param iataCode the iata code
     */
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }
}


