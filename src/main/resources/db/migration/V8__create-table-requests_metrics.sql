CREATE TABLE requests_metrics (
    id UUID PRIMARY KEY,
    route VARCHAR(100) NOT NULL ,
    method VARCHAR(7) NOT NULL ,
    quantity INTEGER NOT NULL ,
    date DATE NOT NULL
);