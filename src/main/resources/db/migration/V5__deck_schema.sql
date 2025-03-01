CREATE TABLE decks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    owner_id INT NOT NULL,
    main_deck VARCHAR(600),
    eggs_deck VARCHAR(60),
    CONSTRAINT fk_owner
        FOREIGN KEY (owner_id)
            REFERENCES users(id)
            ON DELETE CASCADE
)
