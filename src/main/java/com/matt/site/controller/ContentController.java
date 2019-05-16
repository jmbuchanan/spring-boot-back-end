package com.matt.site.controller;

import com.matt.site.model.Content;
import com.matt.site.service.ContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contents")
public class ContentController {
    
    @Autowired
    ContentService contentService;
    
    @GetMapping
    public List<Content> getAllSections() {
        
        return contentService.findAll();
        
    }
    

    @GetMapping("{title}")
    public List<Content> getContentsBySubjectTitle(
            @PathVariable("title") String title) {
        
        return contentService.findContentsBySubjectTitle(title);
        
    }
     

    @PostMapping(path="post", consumes = "application/json")
    public void addOrUpdateContent(
        @RequestBody Content content) {
        
        contentService.saveContent(content);
        
    }
    

    @DeleteMapping(path="delete", consumes = "application/json")
    public void deleteContent(
        @RequestBody Content content) {
        
        contentService.deleteContent(content);
        
    }
    
    
    
}
