package com.hercules.controller;

import com.hercules.model.Case;
import com.hercules.model.Document;
import com.hercules.service.CaseService;
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

    private S3Loader s3Loader = S3Loader.getInstance();

    @GetMapping("/dokumenter")
    public String dokumenterHomer(Model model){
        model.addAttribute("case",caseService.findById((long)1).get());
        return "dokumenterHome";
    }
    
    @PostMapping("/deleteDocument")
    public String removeDocument(Model model, @RequestParam(name = "document") long documentId, @RequestParam("caseId") long caseId){
        Case myCase = caseService.findById(caseId).get();
        Document myDoc = new Document();
        for (Document document: myCase.getDocumentsAsList()) {
            if(document.getDocumentId() == documentId) {
                myDoc = document;
            }
        }
        myCase.removeDocument(myDoc);
        caseService.save(myCase);
        model.addAttribute("case",myCase);
        return "dokumenterHome";

    }

    @PostMapping("/addDocument")
    public String addDocument(Model model, @RequestParam(name = "file") MultipartFile file, @RequestParam("navn") String name,  @RequestParam("caseId") long caseId) throws IOException {
        Case myCase = caseService.findById(caseId).get();
        String fileType = s3Loader.multipartFileToFile(file,"temppicture").getName().split(".")[s3Loader.multipartFileToFile(file,"someName").getName().split(".").length-1];
        s3Loader.uploadImage(S3Loader.multipartFileToFile(file, "temppicture"), "documents/"+name+"."+fileType);
        Document myDoc = new Document();
        myDoc.setDocumentName(name);
        myDoc.setLocation("documents/"+name);
        myCase.addDocument(myDoc);
        caseService.save(myCase);
        model.addAttribute("case",myCase);
        return "dokumenterHome";

    }
}