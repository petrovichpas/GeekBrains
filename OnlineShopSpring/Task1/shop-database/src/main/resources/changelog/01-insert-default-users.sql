INSERT INTO `users` (`name`, `password`)
VALUE ('admin', '$2a$10$gQwCiRNwq7h2tXG6vcL4tut9NWC1gqCi3sjdeP0Y2eS5hMMh4jp82'),
      ('guest', '$2a$10$iq9u7iK3oBL0GGIzD/c/SuTsh2PeMqGxrVeA6Y9BXaBiQc0/6ZUlS');
GO

INSERT INTO `roles` (`name`) VALUE ('ROLE_ADMIN'), ('ROLE_GUEST'); GO

INSERT INTO `users_roles` (`user_id`, `role_id`)
SELECT (SELECT id FROM `users` WHERE `name` = 'admin'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `name` = 'guest'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_GUEST');
GO