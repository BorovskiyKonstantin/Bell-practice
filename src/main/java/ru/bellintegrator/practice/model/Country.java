package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity
@Table(name = "Citizenship")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "citizenship_code", length = 3, nullable = false, unique = true)
    private String code;

    @Column(name = "citizenship_name", nullable = false, unique = true)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", version=" + version +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
