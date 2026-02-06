ALTER TABLE  ideas
ADD CONSTRAINT unique_user_title
UNIQUE (user_id, title);