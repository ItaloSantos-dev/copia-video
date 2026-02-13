DELETE FROM users WHERE id='32f8f840-117e-4f9d-bcfc-104be5db892e';

ALTER TABLE users ADD COLUMN created_at DATE NOT NULL ;

INSERT INTO users (id, name, email, password, role, created_at) VALUES ('32f8f840-117e-4f9d-bcfc-104be5db892e', 'admin', 'admin@gmail.com', '$2a$10$xDH4zpK5Ai/e7RmMK.2Qxutivwo75oe/pBqXEthlbUnn9JhqLYjUa', '0', now());