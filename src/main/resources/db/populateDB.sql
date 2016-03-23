DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
INSERT INTO meals (date_time, description, calories,  user_id) VALUES
  ('20160318 09:00:00','User breakfast #1', 500 , 100000),
  ('20160319 12:00:00', 'User lunch #1', 1000, 100000),
  ('20160319 19:00:00','User dinner #1', 500,  100000),
  ('20160320 09:00:00','User breakfast #2', 700,  100000),
  ('20160320 12:00:00','User lunch #2', 1000,  100000),
  ('20160319 19:00:00','Admin dinner #1', 500,  100001),
  ('20160320 09:00:00','Admin breakfast #2', 700,  100001),
  ('20160320 12:00:00','Admin lunch #3', 1000,  100001);


