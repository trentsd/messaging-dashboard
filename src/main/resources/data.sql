insert into users (username, email, password, enabled)
  values ('user', 'user.email@email.com', 'pass', true);

insert into authorities (username, authority)
  values ('user', 'ROLE_USER');

insert into users (username, email, password, enabled)
  values ('admin', 'admin.email@email.com', 'adminpass', true);

insert into authorities (username, authority)
  values ('admin', 'ROLE_ADMIN');
