package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity
@Table(name = "Document_Type")
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "doc_code", length = 3, nullable = false, unique = true)
    private String code;

    @Column(name = "doc_name", length = 255, nullable = false, unique = true)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    @Override
    public String toString() {
        return "DocumentType{" +
                "id=" + id +
                ", version=" + version +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
