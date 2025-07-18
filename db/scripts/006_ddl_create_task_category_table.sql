CREATE TABLE task_category (
                               id serial PRIMARY KEY,
                               task_id int not null REFERENCES tasks(id),
                               category_id int not null REFERENCES categorys(id),
                               UNIQUE (task_id, category_id)
);