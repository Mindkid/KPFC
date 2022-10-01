INSERT INTO public.users(id, email, name, "password") VALUES
    (0, 'a@b.com', 'test', '1234'),
    (1, 'a@ba.com', 'ta', '1234'),
    (2, 'a@bd.com', 'ti', '1234'),
    (3, 'a@bs.com', 'tp', '1234');

INSERT INTO public.posts(id, body, title, user_id) VALUES
    (0, 'post about test 1', 'tes-1', 0),
    (1, 'post about test 2', 'tes-2', 1),
    (2, 'post about test 3', 'tes-3', 2),
    (3, 'post about test 4', 'tes-4', 2);

INSERT INTO public.comment(id, body, title, post_id) VALUES
    (0, 'comment for post 1', 'comment-1', 0),
    (1, 'comment for post 1', 'comment-2', 0),
    (2, 'comment for post 1', 'comment-3', 0),
    (3, 'comment for post 2', 'comment-1', 1),
    (4, 'comment for post 2', 'comment-2', 1),
    (5, 'comment for post 2', 'comment-3', 1),
    (6, 'comment for post 4', 'comment-1', 3),
    (7, 'comment for post 4', 'comment-2', 3),
    (8, 'comment for post 4', 'comment-3', 3),
    (9, 'comment for post 4', 'comment-1', 3),
    (10, 'comment for post 4', 'comment-1', 3);