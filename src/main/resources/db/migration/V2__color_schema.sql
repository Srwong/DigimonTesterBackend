CREATE TABLE CardType(
    id SERIAL PRIMARY KEY,
    name VARCHAR(7)
);

INSERT INTO CardType(name)
VALUES
    ( 'tamer'),
    ('digimon'),
    ('egg'),
    ('option');
