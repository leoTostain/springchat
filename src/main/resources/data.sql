INSERT INTO USER_ENTITY (uuid, username, password)
       VALUES ('b0dfe8fb-ca12-4d7d-aa3e-167f0784a39f',
        'admin',
        '{bcrypt}$2y$10$EMAIg0MLv6xnl3wf3jzamO1jcYbC06GLysAKW.eoRiujlWJN73haS')
       ON CONFLICT (username) DO NOTHING;

INSERT INTO authority_entity (user_id, authority)
       VALUES ('1', 'ROLE_ADMIN')
       ON CONFLICT (user_id, authority) DO NOTHING;

