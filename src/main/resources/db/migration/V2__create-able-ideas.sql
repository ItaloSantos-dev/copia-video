create table ideas(
    id UUID PRIMARY KEY,
    title VARCHAR(25) UNIQUE,
    annotations TEXT,
    link_video TEXT,
    user_id UUID,

    CONSTRAINT fk_ideas_user FOREIGN KEY (user_id) REFERENCES users(id)
);