CREATE TABLE requests_metrics (
    id UUID PRIMARY KEY,
    route VARCHAR(100),
    method VARCHAR(7),
    quantity INTEGER,
    date DATE
);