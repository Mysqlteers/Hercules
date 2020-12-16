package com.hercules.controller;

import com.hercules.model.Contact;
import com.hercules.model.Tool;
import com.hercules.service.ContactService;
import com.hercules.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ToolController
{
    @Autowired
    ToolService ts;

    @Autowired
    ContactService cs;

    @GetMapping("/tools")
    public String tools(Model model) {
        model.addAttribute("tools", ts.findAll().toArray());
        model.addAttribute("contacts", cs.findAll().toArray());
        return "tools";
    }

    @PostMapping("/updateTool")
    public String updateTool(@ModelAttribute Tool tool, @RequestParam(name = "contactId", required = true) long contactId){
        tool=ts.save(tool);
        if (contactId!=-1)
        {
            Contact contact = cs.findContactByCaseId(contactId).get();
            contact.addTool(tool);
            cs.save(contact);
        }
        return "redirect:/tools";
    }

    @GetMapping("/deleteTool/{toolId}")
    public String deleteTool(@PathVariable("toolId") long toolId){
        System.out.println("deleting tool with id = "+toolId);
        ts.deleteById(toolId);
        return "redirect:/tools";
    }

}
