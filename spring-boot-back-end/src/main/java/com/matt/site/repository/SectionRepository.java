package com.matt.site.repository;

import com.matt.site.model.Section;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository {
    
    List<Section> findSectionsBySubjectTitle(String title);
    
    List<Section> findAll();
    
    void saveSection(Section section);
    
    void deleteSection(Section section);
    
}
