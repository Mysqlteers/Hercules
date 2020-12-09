package com.hercules.model;


import com.hercules.service.utility.S3Loader;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;


    private String documentName;


    private String location;
    @ManyToOne
    @JoinColumn(name = "document_owner_id")
    private Case documentCase;

    /* ***********************************************************  Constructors and methods  ************************************************************ */

    public Document() {

    }

    public Document(Case superCase, String documentName, String location) {
        this.documentCase = superCase;
        this.documentName = documentName;
        this.location = location;
    }


    public String getUrl() {
        return S3Loader.getInstance().getS3ObjectUrl(location);
    }

    /* ***********************************************************  getters and setters  ************************************************************ */

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Case getDocumentCase() {
        return documentCase;
    }

    public void setDocumentCase(Case documentCase) {
        this.documentCase = documentCase;
    }
}

