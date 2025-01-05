package com.family.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "surname_1")
    private String surname1;
    @Column(name = "surname_2")
    private String surname2;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date birthDate;
    private String birthPlace;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date deathDate;
    private String deathPlace;
    private String occupation;
    @OneToOne
    @JoinColumn(name = "partner")
    @JsonIgnoreProperties({"father", "mother", "partner"})
    private Member partner;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date weddingDate;
    private String weddingPlace;
    @ManyToOne
    @JoinColumn(name = "father")
    @JsonIgnoreProperties({"father", "mother", "partner"})
    private Member father;
    @ManyToOne
    @JoinColumn(name = "mother")
    @JsonIgnoreProperties({"father", "mother", "partner"})
    private Member mother;
    private String biography;
    private String career;
    private String hobbies;
    private String annotations;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Link> links;

    @ManyToMany(mappedBy = "members")
    private List<Picture> pictures;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<File> files;


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

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getDeathPlace() {
        return deathPlace;
    }

    public void setDeathPlace(String deathPlace) {
        this.deathPlace = deathPlace;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Member getPartner() {
        return partner;
    }

    public void setPartner(Member partner) {
        this.partner = partner;
    }

    public Date getWeddingDate() {
        return weddingDate;
    }

    public void setWeddingDate(Date weddingDate) {
        this.weddingDate = weddingDate;
    }

    public String getWeddingPlace() {
        return weddingPlace;
    }

    public void setWeddingPlace(String weddingPlace) {
        this.weddingPlace = weddingPlace;
    }

    public Member getFather() {
        return father;
    }

    public void setFather(Member father) {
        this.father = father;
    }

    public Member getMother() {
        return mother;
    }

    public void setMother(Member mother) {
        this.mother = mother;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
