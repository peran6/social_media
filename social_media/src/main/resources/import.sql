INSERT INTO _user (email, password, role) VALUES ('user1@email.com', '$2a$10$FyDLeUX0vt1KY6y3uY7.NulVZeBjGe5RRYeCbgjCS9TknWIdFsO.C', 'FREE');
INSERT INTO _user (email, password, role) VALUES ('user2@email.com', '$2a$10$FyDLeUX0vt1KY6y3uY7.NulVZeBjGe5RRYeCbgjCS9TknWIdFsO.C', 'FREE');
INSERT INTO _user (email, password, role) VALUES ('user3@email.com', '$2a$10$FyDLeUX0vt1KY6y3uY7.NulVZeBjGe5RRYeCbgjCS9TknWIdFsO.C', 'PREMIUM');
INSERT INTO _user (email, password, role) VALUES ('user4@email.com', '$2a$10$FyDLeUX0vt1KY6y3uY7.NulVZeBjGe5RRYeCbgjCS9TknWIdFsO.C', 'PREMIUM');
INSERT INTO _user (email, password, role) VALUES ('user5@email.com', '$2a$10$FyDLeUX0vt1KY6y3uY7.NulVZeBjGe5RRYeCbgjCS9TknWIdFsO.C', 'FREE');
INSERT INTO _user (email, password, role) VALUES ('user6@email.com', '$2a$10$FyDLeUX0vt1KY6y3uY7.NulVZeBjGe5RRYeCbgjCS9TknWIdFsO.C', 'FREE');
INSERT INTO _user (email, password, role) VALUES ('user7@email.com', '$2a$10$FyDLeUX0vt1KY6y3uY7.NulVZeBjGe5RRYeCbgjCS9TknWIdFsO.C', 'PREMIUM');
INSERT INTO _user (email, password, role) VALUES ('user8@email.com', '$2a$10$FyDLeUX0vt1KY6y3uY7.NulVZeBjGe5RRYeCbgjCS9TknWIdFsO.C', 'PREMIUM');

INSERT INTO post (user_id, content, created_at) VALUES (1, 'post 1 from user 1', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (1, 'post 2 from user 1', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (1, 'post 3 from user 1', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (1, 'post 4 from user 1', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (1, 'post 5 from user 1', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (3, 'post 1 from user 3', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (3, 'post 2 from user 3', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (3, 'post 3 from user 3', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (4, 'post 1 from user 4', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (4, 'post 2 from user 4', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (4, 'post 3 from user 4', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (4, 'post 4 from user 4', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (4, 'post 5 from user 4', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (4, 'post 6 from user 4', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (4, 'post 7 from user 4', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (4, 'post 8 from user 4', NOW());
INSERT INTO post (user_id, content, created_at) VALUES (4, 'post 8 from user 4', NOW());


INSERT INTO comment (user_id, post_id, content, created_at) VALUES (2, 1, 'comment 1 from user 2 to post 1', NOW());
INSERT INTO comment (user_id, post_id, content, created_at) VALUES (2, 1, 'comment 2 from user 2 to post 1', NOW());
INSERT INTO comment (user_id, post_id, content, created_at) VALUES (4, 1, 'comment 3 from user 4 to post 1', NOW());
INSERT INTO comment (user_id, post_id, content, created_at) VALUES (2, 3, 'comment 1 from user 2 to post 3', NOW());
INSERT INTO comment (user_id, post_id, content, created_at) VALUES (1, 6, 'comment 1 from user 1 to post 6', NOW());
INSERT INTO comment (user_id, post_id, content, created_at) VALUES (2, 6, 'comment 1 from user 2 to post 6', NOW());
INSERT INTO comment (user_id, post_id, content, created_at) VALUES (2, 6, 'comment 2 from user 2 to post 6', NOW());
INSERT INTO comment (user_id, post_id, content, created_at) VALUES (2, 15, 'comment 1 from user 2 to post 15', NOW());
INSERT INTO comment (user_id, post_id, content, created_at) VALUES (4, 15, 'comment 2 from user 4 to post 15', NOW());

INSERT INTO follower (user_id, follower_id, followed_at) VALUES (3, 1, NOW());
INSERT INTO follower (user_id, follower_id, followed_at) VALUES (4, 1, NOW());
INSERT INTO follower (user_id, follower_id, followed_at) VALUES (5, 1, NOW());
INSERT INTO follower (user_id, follower_id, followed_at) VALUES (1, 2, NOW());
INSERT INTO follower (user_id, follower_id, followed_at) VALUES (1, 3, NOW());
INSERT INTO follower (user_id, follower_id, followed_at) VALUES (1, 6, NOW());
INSERT INTO follower (user_id, follower_id, followed_at) VALUES (6, 4, NOW());
INSERT INTO follower (user_id, follower_id, followed_at) VALUES (4, 6, NOW());
