package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Document")
public class Document {
    @Id
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "doc_number", nullable = false)
    private String number;

    @Column(name = "doc_date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id", nullable = false)
    private DocumentType docType;

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

    public DocumentType getDocType() {
        return docType;
    }

    public void setDocType(DocumentType docType) {
        this.docType = docType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", version=" + version +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", docType=" + docType +
                '}';
    }
}
