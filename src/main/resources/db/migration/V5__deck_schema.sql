CREATE TABLE decks (
    id SERIAL PRIMARY KEY,
    name CHAR(30) NOT NULL,
    owner_id INT NOT NULL,
    main_deck TEXT,
    eggs_deck CHAR(60),
    CONSTRAINT fk_owner
        FOREIGN KEY (owner_id)
            REFERENCES users(id)
            ON DELETE CASCADE
)
