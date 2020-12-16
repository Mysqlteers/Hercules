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
    public String updateTool(@ModelAttribute Tool tool, @RequestParam(name = "contactId") long contactId){
        System.out.println("contact = "+contactId);
        System.out.println("tool = "+tool.getToolId());
        if (contactId!=-1 &&  cs.findContactByCaseId(contactId).isPresent())
        {
            Contact contact = cs.findContactByCaseId(contactId).get();
            tool.setInfo(contact);
            contact.addTool(tool);
            cs.save(contact);
            ts.save(tool);
        }
        ts.save(tool);
        return "redirect:/tools";
    }

    @GetMapping("/deleteTool/{toolId}")
    public String deleteTool(@PathVariable("toolId") long toolId){
        System.out.println("deleting tool with id = "+toolId);
        ts.deleteById(toolId);
        return "redirect:/tools";
    }

}
