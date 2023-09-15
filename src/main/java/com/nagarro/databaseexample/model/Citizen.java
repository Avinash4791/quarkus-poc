package com.nagarro.databaseexample.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String gender;

    @JsonManagedReference
    @OneToOne(mappedBy = "citizen", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AadharCard aadharCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AadharCard getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(AadharCard aadharCard) {
        this.aadharCard = aadharCard;
    }
}
