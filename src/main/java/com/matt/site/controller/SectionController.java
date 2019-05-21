package com.matt.site.controller;

import com.matt.site.model.Section;
import com.matt.site.service.SectionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sections")
public class SectionController {
    
    @Autowired
    SectionService sectionService;

    @GetMapping
    public List<Section> getAllSections() {
        
        return sectionService.findAll();
        
    }

    //Returns Section Titles and Numbers as JSON
    @GetMapping("{title}")
    public List<Section> getSectionsBySubjectTitle(
            @PathVariable("title") String title) {
        
        return sectionService.findSectionsBySubjectTitle(title);
        
    }

    //Used in Admin console to add or update Section
    @PostMapping(path="post", consumes = "application/json")
    public void addOrUpdateSection(
        @RequestBody Section section) {
        
        sectionService.saveSection(section);
        
    }

    //Used in Admin console to delete Section
    @DeleteMapping(path="delete", consumes = "application/json")
    public void deleteSection(
        @RequestBody Section section) {
        
        sectionService.deleteSection(section);
        
    }
    
       
}
