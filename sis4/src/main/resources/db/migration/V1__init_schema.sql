CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    event_date TIMESTAMP NOT NULL,
    organizer_email VARCHAR(255) NOT NULL,
    ticket_price NUMERIC(10, 2)

);