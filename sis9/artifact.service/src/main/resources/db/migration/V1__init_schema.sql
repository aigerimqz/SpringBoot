CREATE TABLE IF NOT EXISTS cursed_artifacts (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    origin VARCHAR(255),
    curse_type VARCHAR(255),
    danger_type VARCHAR(255),
    current_owner_name VARCHAR(255),
    current_owner_email VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS curse_symptoms (
    id UUID PRIMARY KEY,
    artifact_id UUID,
    description TEXT,
    severity VARCHAR(50),
    logged_at TIMESTAMP
);