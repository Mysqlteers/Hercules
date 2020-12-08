package com.hercules.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class Document {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name = "document_id")
    private Long documentId;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case superCase;

    private String documentName;


    private String location;

    public Document() {

    }

    public Document(Case superCase,Long documentId, String documentName, String location){
        this.superCase = superCase;
        this.documentId = documentId;
        this.documentName = documentName;
        this.location = location;
    }

    public Document(Case superCase, String documentName, String location){
        this.superCase = superCase;
        this.documentName = documentName;
        this.location = location;
    }

   public String getUrl(String location){
        return "";
   }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Case getSuperCase() {
        return superCase;
    }

    public void setSuperCase(Case superCase) {
        this.superCase = superCase;
    }

    public Long getDocumentId() { return documentId; }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
