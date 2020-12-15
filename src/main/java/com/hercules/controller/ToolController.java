package com.hercules.controller;

import com.hercules.model.Tool;
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

    @GetMapping("/tools")
    public String tools(Model model) {
        model.addAttribute("tools", ts.findAll().toArray());
        return "tools";
    }

    @PostMapping("/updateTool")
    public String updateTool(@ModelAttribute Tool tool){
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
