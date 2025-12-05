-- ============================================
-- Solar Valley Webapp - Initial Schema
-- Version: 1.0
-- Description: Create all base tables for the application
-- ============================================

-- ============================================
-- TABLE: leads
-- Purpose: Store all lead/inquiry data from various sources
-- ============================================
CREATE TABLE leads (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    service_interest VARCHAR(100),
    property_type ENUM('residential', 'commercial', 'industrial'),
    energy_consumption VARCHAR(50),
    message TEXT,
    source VARCHAR(50) NOT NULL COMMENT 'contact_form, quote_request, exit_popup, whatsapp, call',
    status ENUM('new', 'contacted', 'in_progress', 'converted', 'closed') DEFAULT 'new',
    ip_address VARCHAR(45),
    user_agent TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_created_at (created_at),
    INDEX idx_status (status),
    INDEX idx_source (source),
    INDEX idx_email (email),
    INDEX idx_phone (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLE: projects
-- Purpose: Store portfolio projects (residential, commercial, industrial)
-- ============================================
CREATE TABLE projects (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    location VARCHAR(100),
    capacity VARCHAR(50) COMMENT 'e.g., 5 kW, 100 kW',
    category ENUM('residential', 'commercial', 'industrial') NOT NULL,
    completion_year INT,
    is_featured BOOLEAN DEFAULT FALSE,
    display_order INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_category (category),
    INDEX idx_featured (is_featured),
    INDEX idx_display_order (display_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLE: project_media
-- Purpose: Store images and videos for projects (S3 URLs)
-- ============================================
CREATE TABLE project_media (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    project_id BIGINT NOT NULL,
    media_type ENUM('image', 'video') NOT NULL,
    file_url VARCHAR(500) NOT NULL COMMENT 'S3 URL or embed link',
    thumbnail_url VARCHAR(500) COMMENT 'For videos',
    display_order INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    INDEX idx_project_id (project_id),
    INDEX idx_media_type (media_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLE: testimonials
-- Purpose: Store customer reviews and ratings
-- ============================================
CREATE TABLE testimonials (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    rating TINYINT NOT NULL CHECK (rating >= 1 AND rating <= 5),
    review_text TEXT NOT NULL,
    review_date DATE,
    is_featured BOOLEAN DEFAULT FALSE,
    is_published BOOLEAN DEFAULT TRUE,
    display_order INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_featured (is_featured),
    INDEX idx_published (is_published),
    INDEX idx_rating (rating)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLE: company_stats
-- Purpose: Store dynamic company statistics (years, projects count, customers)
-- ============================================
CREATE TABLE company_stats (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    stat_key VARCHAR(50) UNIQUE NOT NULL COMMENT 'years_experience, projects_completed, customers_served',
    stat_value VARCHAR(50) NOT NULL,
    display_label VARCHAR(100) NOT NULL,
    display_order INT DEFAULT 0,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_stat_key (stat_key),
    INDEX idx_display_order (display_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLE: brochures
-- Purpose: Store downloadable brochures (PDFs on S3)
-- ============================================
CREATE TABLE brochures (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    file_url VARCHAR(500) NOT NULL COMMENT 'S3 URL',
    file_size BIGINT COMMENT 'in bytes',
    download_count INT DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_is_active (is_active)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLE: admins
-- Purpose: Store admin user credentials (BCrypt hashed passwords)
-- ============================================
CREATE TABLE admins (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL COMMENT 'BCrypt hashed',
    email VARCHAR(100) NOT NULL,
    last_login TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_username (username),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLE: site_settings
-- Purpose: Store key-value configuration (contact info, social links, etc.)
-- ============================================
CREATE TABLE site_settings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    setting_key VARCHAR(100) UNIQUE NOT NULL,
    setting_value TEXT NOT NULL,
    setting_type VARCHAR(50) COMMENT 'string, email, url, phone, boolean',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_setting_key (setting_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLE: analytics_summary
-- Purpose: Store daily analytics aggregates
-- ============================================
CREATE TABLE analytics_summary (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL UNIQUE,
    page_views INT DEFAULT 0,
    unique_visitors INT DEFAULT 0,
    leads_generated INT DEFAULT 0,
    whatsapp_clicks INT DEFAULT 0,
    call_clicks INT DEFAULT 0,
    brochure_downloads INT DEFAULT 0,
    video_plays INT DEFAULT 0,
    exit_popup_shows INT DEFAULT 0,
    exit_popup_conversions INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_date (date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- END OF INITIAL SCHEMA
-- ============================================
