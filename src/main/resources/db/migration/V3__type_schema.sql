CREATE TABLE card_types(
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(7)
);

INSERT INTO card_types(name)
VALUES
    ( 'tamer'),
    ('digimon'),
    ('egg'),
    ('option');
