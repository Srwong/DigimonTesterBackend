CREATE TABLE Color(
    id SERIAL PRIMARY KEY,
    name VARCHAR(6)
);

INSERT INTO Color (name)
VALUES
    ('red'),
    ('blue'),
    ('yellow'),
    ('purple'),
    ('green'),
    ('black'),
    ('white');