package com.solarvalley.webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity representing daily analytics summary data
 */
@Entity
@Table(name = "analytics_summary", indexes = {
        @Index(name = "idx_date", columnList = "date")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date is required")
    @Column(name = "date", nullable = false, unique = true)
    private LocalDate date;

    @Column(name = "page_views", nullable = false)
    private Integer pageViews = 0;

    @Column(name = "unique_visitors", nullable = false)
    private Integer uniqueVisitors = 0;

    @Column(name = "leads_generated", nullable = false)
    private Integer leadsGenerated = 0;

    @Column(name = "whatsapp_clicks", nullable = false)
    private Integer whatsappClicks = 0;

    @Column(name = "call_clicks", nullable = false)
    private Integer callClicks = 0;

    @Column(name = "brochure_downloads", nullable = false)
    private Integer brochureDownloads = 0;

    @Column(name = "video_plays", nullable = false)
    private Integer videoPlays = 0;

    @Column(name = "exit_popup_shows", nullable = false)
    private Integer exitPopupShows = 0;

    @Column(name = "exit_popup_conversions", nullable = false)
    private Integer exitPopupConversions = 0;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
