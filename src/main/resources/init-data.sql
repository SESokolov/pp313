INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER'), (3, 'ROLE_GUEST');
INSERT INTO users (email, enabled, name, last_name, password) VALUES ('admin', true, 'Василий', 'Пупкин', '$2y$10$kbBc2/YyhalAHuK.SRiFPeu/iENCtVjUS9sK4A3/4b5EXdgqcj0cy');
INSERT INTO users (email, enabled, name, last_name, password) VALUES ('user@mail.ru', true, 'Юзер', 'Юзеров', '$2a$10$yTN7pfgIfnqQ/CZ99/yJwuFxl/er3lyvjBRRVgBeAzGiS/sxebt.S');
INSERT INTO users_roles VALUES (1, 1), (1, 2), (2, 2);
