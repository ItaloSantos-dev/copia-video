CREATE TABLE searchs_metrics (
    id UUID PRIMARY KEY,
    search VARCHAR(100) NOT NULL ,
    quantity INTEGER NOT NULL ,
    date DATE NOT NULL
);