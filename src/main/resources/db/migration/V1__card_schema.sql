CREATE TABLE card (
    id SERIAL PRIMARY KEY,
    name VARCHAR(40),
    code VARCHAR(8),
    memory_cost INT,
    color VARCHAR(6),
    type VARCHAR(7),
    main_text VARCHAR(150),
    lower_text VARCHAR(150),
    level INT,
    created_at TIMESTAMP DEFAULT NOW()
);

INSERT INTO card(name, code, memory_cost, color, type, main_text, lower_text, level)
VALUES
    ('asd', '12d', 1,'PURPLE','EGG','maksdmaksmdkasmdks','aksmdkasfmkmfk',3),
    ( 'other', '13a', 3,'GREEN','DIGIMON','some main text','inheritance effect',4);
