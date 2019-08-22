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
    
    /*
     * Returns Subject Title, Section ID, Paragraph Text,
     * Code Examples, and Images as JSON
     */

    @GetMapping("{title}")
    public List<Content> getContentsBySubjectTitle(
            @PathVariable("title") String title) {
        
        return contentService.findContentsBySubjectTitle(title);
        
    }
     
    //Service is called when using the Admin console to add Content
    @PostMapping(path="post", consumes = "application/json")
    public void addOrUpdateContent(
        @RequestBody Content content) {
        
        contentService.saveContent(content);
        
    }
    
    //Service is called when using the Admin console to delete Content
    @DeleteMapping(path="delete", consumes = "application/json")
    public void deleteContent(
        @RequestBody Content content) {
        
        contentService.deleteContent(content);
        
    }
    
    
    
}
