INSERT INTO users (id, email, password, first_name, last_name) VALUES (
    9999999,
    'testuser@gmail.com',
    'password',
    'Test',
    'User'
);


INSERT INTO posts (id, text, image_url, author_id, comment) VALUES (
    10000,
    'The classic',
    'https://i.imgur.com/fhgzVEt.jpeg',
    9999999,
    false
),
(
    10001,
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
    '',
    9999999,
    false
);

