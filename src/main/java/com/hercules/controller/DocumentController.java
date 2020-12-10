package com.hercules.controller;

import com.hercules.model.Case;
import com.hercules.model.Document;
import com.hercules.service.CaseService;
import com.hercules.service.DocService;
import com.hercules.service.utility.S3Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class DocumentController {

    @Autowired
    CaseService caseService;

    @Autowired
    DocService docService;

    private S3Loader s3Loader = S3Loader.getInstance();

    @PostMapping("/deleteDocument")
    public String removeDocument(@RequestParam(name = "document") long documentId, @RequestParam("caseId") long caseId){
        Case myCase = caseService.findById(caseId).get();
        Document myDoc = docService.findById(documentId).get();
        myCase.removeDocument(myDoc);
        docService.deleteById(myDoc.getDocumentId());
        caseService.save(myCase);
        return "redirect:/caseDetails/"+myCase.getCaseId();

    }

    @PostMapping("/addDocument")
    public String addDocument(@RequestParam(name = "file") MultipartFile file, @RequestParam("name") String name,  @RequestParam("caseId") long caseId) throws IOException {
        Case myCase = caseService.findById(caseId).get();

        s3Loader.uploadFile(file, "documents/"+name);

        Document myDoc = new Document();
        myDoc.setDocumentName(name);
        myDoc.setLocation("documents/"+name);

        myCase.addDocument(myDoc);
        caseService.save(myCase);
        return "redirect:/caseDetails/"+myCase.getCaseId();

    }
}