insert into users (username, email, password, enabled)
  values ('user', 'email@email.com', 'pass', true);

insert into authorities (username, authority)
  values ('user', 'ROLE_USER');
