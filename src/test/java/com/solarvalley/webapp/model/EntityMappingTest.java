package com.solarvalley.webapp.model;

import com.solarvalley.webapp.model.enums.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test to verify JPA entity mappings match database schema
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class EntityMappingTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testLeadEntityMapping() {
        Lead lead = new Lead();
        lead.setName("Test User");
        lead.setEmail("test@example.com");
        lead.setPhone("9876543210");
        lead.setSource(LeadSource.CONTACT_FORM);
        lead.setStatus(LeadStatus.NEW);

        Lead savedLead = entityManager.persistAndFlush(lead);

        assertThat(savedLead.getId()).isNotNull();
        assertThat(savedLead.getCreatedAt()).isNotNull();
        assertThat(savedLead.getName()).isEqualTo("Test User");
    }

    @Test
    void testProjectEntityMapping() {
        Project project = new Project();
        project.setTitle("Test Solar Project");
        project.setCategory(ProjectCategory.RESIDENTIAL);
        project.setCapacity("5 kW");

        Project savedProject = entityManager.persistAndFlush(project);

        assertThat(savedProject.getId()).isNotNull();
        assertThat(savedProject.getIsFeatured()).isFalse();
        assertThat(savedProject.getTitle()).isEqualTo("Test Solar Project");
    }

    @Test
    void testTestimonialEntityMapping() {
        Testimonial testimonial = new Testimonial();
        testimonial.setCustomerName("Test Customer");
        testimonial.setRating((byte)5);  // This will auto-convert to Byte
        testimonial.setReviewText("Great service!");

        Testimonial savedTestimonial = entityManager.persistAndFlush(testimonial);

        assertThat(savedTestimonial.getId()).isNotNull();
        assertThat(savedTestimonial.getIsPublished()).isTrue();
        assertThat(savedTestimonial.getRating()).isEqualTo((byte) 5);  // ✅ Cast to byte
    }

    @Test
    void testCompanyStatEntityMapping() {
        CompanyStat stat = new CompanyStat();
        stat.setStatKey("test_stat");
        stat.setStatValue("100");
        stat.setDisplayLabel("Test Stat");
        stat.setDisplayOrder(1);

        CompanyStat savedStat = entityManager.persistAndFlush(stat);

        assertThat(savedStat.getId()).isNotNull();
        assertThat(savedStat.getStatKey()).isEqualTo("test_stat");
    }

    @Test
    void testBrochureEntityMapping() {
        Brochure brochure = new Brochure();
        brochure.setTitle("Test Brochure");
        brochure.setFileUrl("https://s3.amazonaws.com/test.pdf");
        brochure.setDownloadCount(0);

        Brochure savedBrochure = entityManager.persistAndFlush(brochure);

        assertThat(savedBrochure.getId()).isNotNull();
        assertThat(savedBrochure.getIsActive()).isTrue();
        assertThat(savedBrochure.getDownloadCount()).isZero();
    }
}
