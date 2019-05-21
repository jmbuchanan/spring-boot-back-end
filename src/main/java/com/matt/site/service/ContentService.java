package com.matt.site.service;

import com.matt.site.model.Content;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.matt.site.repository.ContentRepository;

@Service
public class ContentService implements ContentRepository {
    
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Content> findContentsBySubjectTitle(String title) {
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        List<Content> contents = new ArrayList<>();
        
        String sql = "SELECT su.title as subject_title, se.number as section_number, se.title as section_title, " +
                "c.paragraph_number as paragraph_number, c.paragraph_text as paragraph_text, " +
                "c.code_example as code_example, c.image_pathway as image_pathway " +
                "FROM Contents c LEFT JOIN Sections se " +
                "ON c.section_id = se.id LEFT JOIN Subjects su " +
                "ON se.subject_id = su.id WHERE su.title = LOWER(?) " +
                "ORDER BY se.number ASC, c.paragraph_number ASC";
        
        jdbcTemplate.query(sql, new Object[] {title},
                (rs, rowNum) -> new Content(rs.getString("subject_title"),
                        rs.getFloat("section_number"), rs.getString("section_title"),
                        rs.getLong("paragraph_number"), rs.getString("paragraph_text"),
                        rs.getString("code_example"), rs.getString("image_pathway"))
        ).forEach(content -> contents.add(content));
        
        return contents;
        
    }
    

        @Override
        public List<Content> findAll() {
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        List<Content> contents = new ArrayList<>();
        
        String sql = "SELECT su.title as subject_title, se.number as section_number, se.title as section_title, " +
                "c.paragraph_number as paragraph_number, c.paragraph_text as paragraph_text, " +
                "c.code_example as code_example, c.image_pathway as image_pathway " +
                "FROM Contents c LEFT JOIN Sections se " +
                "ON c.section_id = se.id LEFT JOIN Subjects su " +
                "ON se.subject_id = su.id ORDER BY se.number ASC, c.paragraph_number ASC";
        
        jdbcTemplate.query(sql,
                (rs, rowNum) -> new Content(rs.getString("subject_title"),
                        rs.getFloat("section_number"), rs.getString("section_title"),
                        rs.getLong("paragraph_number"), rs.getString("paragraph_text"),
                        rs.getString("code_example"), rs.getString("image_pathway"))
                ).forEach(content -> contents.add(content)
        );
        
        return contents;
        
    }
        
        
        @Override
        public void saveContent(Content content) {
            
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);    
        
        String subjectId = jdbcTemplate.queryForObject(
            "SELECT id FROM subjects WHERE title = LOWER(?)",
            new Object[] { content.getSubjectTitle() }, String.class
        );
        
        
        String sectionId = jdbcTemplate.queryForObject(
            "SELECT id FROM sections WHERE subject_id = ? AND number = ?",
            new Object[] { Integer.parseInt(subjectId), content.getSectionNumber() }, String.class
        );
        
	//ON CONFLICT...DO UPDATE is Postgres syntax for save if new or update if exists
        jdbcTemplate.update("INSERT INTO contents (section_id, paragraph_number, " +
                "paragraph_text, code_example, image_pathway) VALUES " +
                "(?, ?, ?, ?, ?) ON CONFLICT (section_id, paragraph_number) " +
                "DO UPDATE SET paragraph_text = excluded.paragraph_text, " +
                "code_example = excluded.code_example, " +
                "image_pathway = excluded.image_pathway",
                Integer.parseInt(sectionId), content.getParagraphNumber(),
                content.getParagraphText(), content.getCodeExample(),
                content.getImagePathway()
        );
                
        
        }
        
        @Override
        public void deleteContent(Content content) {
            
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);    
            
        String subjectId = jdbcTemplate.queryForObject(
            "SELECT id FROM subjects WHERE title = LOWER(?)",
            new Object[] { content.getSubjectTitle() }, String.class
        );
        
        
        String sectionId = jdbcTemplate.queryForObject(
            "SELECT id FROM sections WHERE subject_id = ? AND number = ?",
            new Object[] { Integer.parseInt(subjectId), content.getSectionNumber() }, String.class
        );
        
        jdbcTemplate.update("DELETE FROM contents WHERE " +
                "section_id = ? AND paragraph_number = ?",
            Integer.parseInt(sectionId), content.getParagraphNumber()
        );
        
        }
        
    
}
