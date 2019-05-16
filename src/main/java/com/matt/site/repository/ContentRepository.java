package com.matt.site.repository;

import com.matt.site.model.Content;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository {
    
    List<Content> findContentsBySubjectTitle(String title);
    
    List<Content> findAll();
    
    void saveContent(Content content);
    
    void deleteContent(Content content);

}
