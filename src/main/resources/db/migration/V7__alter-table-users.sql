DELETE FROM users WHERE id='32f8f840-117e-4f9d-bcfc-104be5db892e';

ALTER TABLE users ADD COLUMN created_at DATE NOT NULL ;

INSERT INTO users (id, name, email, password, role, created_at) VALUES ('32f8f840-117e-4f9d-bcfc-104be5db892e', 'admin', 'admin@.com', '$2a$10$wHbheI2D8VC4Uz.6QGq.XOsyLXobC./cDJv8NR4/dPRFpuHd9PY/m', '0', now());