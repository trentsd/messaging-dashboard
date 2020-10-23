insert into users (username, display_name, password, enabled)
  values ('user', 'Bob User', 'pass', true);

insert into authorities (username, authority)
  values ('user', 'ROLE_USER');

insert into emails (username, email)
  values ('user', 'user@email.com');

insert into users (username, display_name, password, enabled)
  values ('admin', 'Chad Admin', 'adminpass', true);

insert into authorities (username, authority)
  values ('admin', 'ROLE_ADMIN');

insert into emails (username, email)
  values ('admin', 'admin@email.com');
