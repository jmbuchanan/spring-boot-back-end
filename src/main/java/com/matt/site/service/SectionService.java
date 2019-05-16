package com.matt.site.service;

import com.matt.site.model.Section;
import com.matt.site.repository.SectionRepository;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SectionService implements SectionRepository{
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    public List<Section> findSectionsBySubjectTitle(String title) {
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
       
        List<Section> sections = new ArrayList<>();
       
        String sql = "SELECT subjects.title as subject_title, " +
                "sections.number as section_number, " +
                "sections.title as section_title " +
                "from sections left join subjects on " +
                "sections.subject_id = subjects.id " +
                "where subjects.title = LOWER(?)";
       
        jdbcTemplate.query(sql, new Object[] {title},
            (rs, rowNum) -> new Section(rs.getString("subject_title"),
                rs.getFloat("section_number"), rs.getString("section_title"))
        ).forEach(section -> sections.add(section));
        
        return sections;
        
    }
    
        @Override
        public List<Section> findAll() {
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
       
        List<Section> sections = new ArrayList<>();
       
        String sql = "SELECT subjects.title as subject_title, " +
                "sections.number as section_number, " +
                "sections.title as section_title " +
                "from sections left join subjects on " +
                "sections.subject_id = subjects.id ";
       
        jdbcTemplate.query(sql,
            (rs, rowNum) -> new Section(rs.getString("subject_title"),
                rs.getFloat("section_number"), rs.getString("section_title"))
        ).forEach(section -> sections.add(section));
        
        return sections;
        
    }
        
        @Override
        public void saveSection(Section section) {
            
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            
            String subjectId = jdbcTemplate.queryForObject(
                "SELECT id FROM subjects WHERE title = LOWER(?)",
                new Object[] { section.getSubjectTitle() }, String.class);
            
            jdbcTemplate.update("INSERT INTO sections (subject_id, number, title) VALUES " +
                    "(?, ?, ?) ON CONFLICT (subject_id, number) DO UPDATE " +
                    "SET title = excluded.title",
                    Integer.parseInt(subjectId),
                    section.getSectionNumber(),
                    section.getSectionTitle()
            );
            
        }
        
        @Override
        public void deleteSection(Section section) {
            
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            
            String subjectId = jdbcTemplate.queryForObject(
                "SELECT id FROM subjects WHERE title = LOWER(?)",
                new Object[] { section.getSubjectTitle() }, String.class);

            jdbcTemplate.update("DELETE FROM sections WHERE " +
                    "subject_id = ? AND number = ?",
                    Integer.parseInt(subjectId), section.getSectionNumber());
 
        }
    
}
