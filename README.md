# market
Sales web application with admin panel, login&regisration forms, possibility to create/read/update/delete products or users, and support user's product list.

Tech stack:
Java, Spring(Boot, MVC, Security, Data), Hibernate, MySQL, Thymeleaf, HTML/CSS(Sass), JavaScript(jQuery).

Some screenshots:

![loginform](https://user-images.githubusercontent.com/62611772/187517304-9da4ed7a-68db-4092-bacf-bc6a8c06dea7.png)
![registerform](https://user-images.githubusercontent.com/62611772/187517393-7aa73356-6d00-4d99-a7b1-6723e16f50a4.png)
![users](https://user-images.githubusercontent.com/62611772/187517487-889d01c9-62f2-4530-af19-fd2876b64773.png)
![userschange](https://user-images.githubusercontent.com/62611772/187517491-46960d04-f7d5-4663-b976-2d404da2e425.png)

To compile my app in your own mashine you need 'market_db' database and the following tables:

CREATE TABLE market_db.user (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_of_money` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
); 

CREATE TABLE market_db.role (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE market_db.users_roles (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);

CREATE TABLE market_db.products (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE market_db.products (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);
