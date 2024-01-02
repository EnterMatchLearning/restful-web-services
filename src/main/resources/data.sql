INSERT INTO `user_details` (`id`, `name`, `birth_date`)
VALUES (10001, 'Louis', current_date());

INSERT INTO `user_details` (`id`, `name`, `birth_date`)
VALUES (10002, 'Mike', current_date());

INSERT INTO `user_details` (`id`, `name`, `birth_date`)
VALUES (10003, 'Rat', current_date());

INSERT INTO post(id, description, user_id)
VALUES(20001, 'I want to learn AWS', 10001);

INSERT INTO post(id, description, user_id)
VALUES(20002, 'I want to learn Spring Boot', 10001);

INSERT INTO post(id, description, user_id)
VALUES(20003, 'I want to learn Spring Cloud', 10002);

INSERT INTO post(id, description, user_id)
VALUES(20004, 'I want to learn Spring Batch', 10003);