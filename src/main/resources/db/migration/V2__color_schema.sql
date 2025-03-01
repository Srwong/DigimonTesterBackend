CREATE TABLE colors(
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(6)
);

INSERT INTO colors (name)
VALUES
    ('red'),
    ('blue'),
    ('yellow'),
    ('purple'),
    ('green'),
    ('black'),
    ('white');
