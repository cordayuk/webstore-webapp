insert into Product (id, name, description, price, picture) values(1, 'Phone', 'This is a phone', 2.99, null);

insert into Role (name) values ('ROLE_CUSTOMER'), ('ROLE_ADMIN');

insert into User (id, email, name, role_id, password, is_Non_Expired, is_Non_Locked, is_Enabled) values (1, 'test@test.com', 'Chris', 1,'$2a$10$XYeP/Nk0k7Tw/fE.Lp7z9.aYyxD2sgumh7IzIs4jeEA2GsqDavJgi', true, true, true);

insert into User (id, email, name, role_id, password, is_Non_Expired, is_Non_Locked, is_Enabled) values (2, 'admin', 'MrAdmin', 2,'$2a$10$XYeP/Nk0k7Tw/fE.Lp7z9.aYyxD2sgumh7IzIs4jeEA2GsqDavJgi', true, true, true);