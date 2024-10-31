INSERT INTO users (name) VALUES
('Alice'),
('Berry'),
('Clark'),
('Diana'),
('Elliot');

INSERT INTO posts (user_id, author, content, title) VALUES
(1, 'Alice', 'Exploring the basics of SQL and database management.', 'Intro to SQL'),
(1, 'Alice', 'Learning about joins in SQL.', 'SQL Joins Simplified'),
(1, 'Alice', 'Understanding indexes and performance tuning.', 'SQL Indexing 101'),
(1, 'Alice', 'A quick guide to starting with cloud infrastructure.', 'Cloud 101'),
(2, 'Berry', 'How to set up a secure server on AWS.', 'AWS Server Setup'),
(4, 'Diana', 'Top 5 tips for mastering Python programming.', 'Python Tips'),
(4, 'Diana', 'Getting started with data analysis in Python.', 'Data Analysis Basics'),
(5, 'Elliot', 'Why data security is more important than ever.', 'Data Security Essentials'),
(5, 'Elliot', 'A beginner’s guide to Docker and containerization.', 'Docker 101'),
(5, 'Elliot', 'How encryption works to protect data.', 'Data Encryption Explained');