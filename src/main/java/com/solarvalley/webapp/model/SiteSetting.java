package com.solarvalley.webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entity representing key-value configuration settings
 * (contact info, social links, email settings, etc.)
 */
@Entity
@Table(name = "site_settings", indexes = {
        @Index(name = "idx_setting_key", columnList = "setting_key")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Setting key is required")
    @Column(name = "setting_key", nullable = false, unique = true, length = 100)
    private String settingKey;

    @NotBlank(message = "Setting value is required")
    @Column(name = "setting_value", nullable = false, columnDefinition = "TEXT")
    private String settingValue;

    @Column(name = "setting_type", length = 50)
    private String settingType; // string, email, url, phone, boolean

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
