package com.solarvalley.webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entity representing dynamic company statistics
 * (years of experience, projects completed, customers served, etc.)
 */
@Entity
@Table(name = "company_stats", indexes = {
        @Index(name = "idx_stat_key", columnList = "stat_key"),
        @Index(name = "idx_display_order", columnList = "display_order")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Stat key is required")
    @Column(name = "stat_key", nullable = false, unique = true, length = 50)
    private String statKey;

    @NotBlank(message = "Stat value is required")
    @Column(name = "stat_value", nullable = false, length = 50)
    private String statValue;

    @NotBlank(message = "Display label is required")
    @Column(name = "display_label", nullable = false, length = 100)
    private String displayLabel;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder = 0;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
