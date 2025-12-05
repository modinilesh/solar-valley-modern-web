package com.solarvalley.webapp.model;

import com.solarvalley.webapp.model.enums.ProjectCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a completed project in the portfolio
 */
@Entity
@Table(name = "projects", indexes = {
        @Index(name = "idx_category", columnList = "category"),
        @Index(name = "idx_featured", columnList = "is_featured"),
        @Index(name = "idx_display_order", columnList = "display_order")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "capacity", length = 50)
    private String capacity;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 20)
    private ProjectCategory category;

    @Column(name = "completion_year")
    private Integer completionYear;

    @Column(name = "is_featured", nullable = false)
    private Boolean isFeatured = false;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder = 0;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectMedia> media = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Helper methods for bidirectional relationship
    public void addMedia(ProjectMedia projectMedia) {
        media.add(projectMedia);
        projectMedia.setProject(this);
    }

    public void removeMedia(ProjectMedia projectMedia) {
        media.remove(projectMedia);
        projectMedia.setProject(null);
    }
}
