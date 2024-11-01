INSERT INTO users (name) VALUES
('Alice'),
('Berry'),
('Clark'),
('Diana'),
('Elliot');

INSERT INTO posts (user_id, author, content, title) VALUES
(1, 'Alice', 'Exploring the basics of SQL and database management.', 'Intro to SQL'),
(1, 'Alice', 'Learning about joins in SQL.', 'SQL Joins Simplified'),
(1, 'Alice', 'Understanding indexes and performance tuning.', 'Intro to SQL'),
(1, 'Alice', 'A quick guide to starting with cloud infrastructure.', 'Cloud 101'),
(2, 'Berry', 'How to set up a secure server on AWS.', 'AWS Server Setup'),
(4, 'Diana', 'Top 5 tips for mastering Python programming.', 'Python Tips'),
(4, 'Diana', 'Getting started with data analysis in Python.', 'Data Analysis Basics'),
(5, 'Elliot', 'Why data security is more important than ever.', 'Intro to SQL'),
(5, 'Elliot', 'A beginner’s guide to Docker and containerization.', 'Docker 101'),
(5, 'Elliot', 'How encryption works to protect data.', 'Data Encryption Explained');

INSERT INTO comments (post_id, name) VALUES
 (1, 'Great introduction to SQL! Helped me a lot.'),
 (1, 'Can you cover more advanced topics in your next post?'),
 (1, 'The join examples were super helpful. Thanks!'),
 (3, 'Indexes were a mystery before this. Great post!'),
 (4, 'Cloud infrastructure is vast! Appreciate the beginner insights.'),
 (5, 'Security is critical in today’s world, good guide!'),
 (6, 'I’ve been wanting to learn Python. This was the perfect intro!'),
 (7, 'Data analysis can be overwhelming. Thanks for breaking it down.'),
 (8, 'Data security is indeed important, appreciate the awareness.'),
 (9, 'Docker has always been confusing for me, nice post!'),
 (10, 'Encryption demystified! This is a must-read.'),
 (10, 'Could you cover more about symmetric vs asymmetric encryption?');