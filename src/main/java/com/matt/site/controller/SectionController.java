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

    @GetMapping("{title}")
    public List<Section> getSectionsBySubjectTitle(
            @PathVariable("title") String title) {
        
        return sectionService.findSectionsBySubjectTitle(title);
        
    }

    @PostMapping(path="post", consumes = "application/json")
    public void addOrUpdateSection(
        @RequestBody Section section) {
        
        sectionService.saveSection(section);
        
    }

    @DeleteMapping(path="delete", consumes = "application/json")
    public void deleteSection(
        @RequestBody Section section) {
        
        sectionService.deleteSection(section);
        
    }
    
       
}
