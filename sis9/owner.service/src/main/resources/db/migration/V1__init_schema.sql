CREATE TABLE IF NOT EXISTS owners (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ownerships (
        id UUID PRIMARY KEY,
        artifact_id UUID,
        owner_id UUID,
        acquired_at TIMESTAMP
);