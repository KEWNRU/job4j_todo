CREATE TABLE categorys (
   id SERIAL PRIMARY KEY,
   name TEXT UNIQUE NOT NULL
);

ALTER TABLE tasks ADD COLUMN category_id int REFERENCES categorys(id);
