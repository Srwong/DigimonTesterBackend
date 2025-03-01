CREATE TABLE cards (
    id SERIAL PRIMARY KEY,
    name VARCHAR(40),
    code VARCHAR(10),
    memory_cost INT,
    evolution_cost INT,
    color VARCHAR(6),
    type VARCHAR(7),
    main_text VARCHAR(150),
    lower_text VARCHAR(150),
    level INT,
    created_at TIMESTAMP DEFAULT NOW()
);
