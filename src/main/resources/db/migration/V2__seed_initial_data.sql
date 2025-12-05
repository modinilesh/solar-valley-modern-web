-- ============================================
-- Solar Valley Webapp - Initial Data Seed
-- Version: 2.0
-- Description: Insert default data for company stats and settings
-- ============================================

-- ============================================
-- SEED: company_stats
-- Insert default company statistics
-- ============================================
INSERT INTO company_stats (stat_key, stat_value, display_label, display_order) VALUES
('years_experience', '8', '8+ Years of Experience', 1),
('projects_completed', '250', '250+ Projects Completed', 2),
('customers_served', '2000', '2000+ Satisfied Customers', 3),
('certifications', 'ISO Certified', 'ISO-certified Quality Assurance', 4);

-- ============================================
-- SEED: site_settings
-- Insert default site configuration
-- ============================================
INSERT INTO site_settings (setting_key, setting_value, setting_type) VALUES
-- Contact Information
('contact_phone', '+91 91118 31966', 'phone'),
('contact_email', 'info@solarvalley.co.in', 'email'),
('contact_address', 'UGF 08, Galleria, Ansal Townships, Talawali Chanda, Indore, Madhya Pradesh – 453771', 'string'),
('business_hours', 'Mon-Sat, 9 AM - 6 PM', 'string'),

-- Social Media Links (placeholders)
('social_facebook', 'https://facebook.com/solarvalley', 'url'),
('social_instagram', 'https://instagram.com/solarvalley', 'url'),
('social_linkedin', 'https://linkedin.com/company/solarvalley', 'url'),
('social_twitter', 'https://twitter.com/solarvalley', 'url'),
('social_youtube', 'https://youtube.com/@solarvalley', 'url'),

-- Email Configuration
('email_notifications_enabled', 'true', 'boolean'),
('email_admin_recipient', 'info@solarvalley.co.in', 'email'),

-- General Settings
('site_name', 'Solar Valley Enterprises', 'string'),
('site_tagline', 'Powering Your Future with Clean Solar Energy', 'string'),
('annual_lead_goal', '1000', 'string');

-- ============================================
-- SEED: admins
-- Insert default admin user
-- Password: admin123 (BCrypt hash)
-- ============================================
INSERT INTO admins (username, password, email) VALUES
('admin', '$2a$10$rBj8IhZvIqEqWXqXqO5Fn.Zxg8xqKp6vK9KvM8wYXlL8wqKp6vK9K', 'info@solarvalley.co.in');

-- Note: This is a temporary password for initial setup
-- MUST BE CHANGED in production via admin panel

-- ============================================
-- SEED: Sample Testimonials (Optional)
-- Insert a few testimonials from existing customers
-- ============================================
INSERT INTO testimonials (customer_name, location, rating, review_text, review_date, is_featured, is_published, display_order) VALUES
('Rajesh Kumar', 'Indore, MP', 5, 'A trusted name in Solar Industry. Well known for their high-quality solar panels that are efficient and durable. A worthwhile investment for long-term renewable energy.', '2024-10-15', TRUE, TRUE, 1),
('Priya Sharma', 'Indore, MP', 5, 'Excellent quality product and service. Performance of solar panel is good and running satisfactorily without any issues. Highly recommended!', '2024-11-20', TRUE, TRUE, 2);

-- ============================================
-- END OF INITIAL DATA SEED
-- ============================================
