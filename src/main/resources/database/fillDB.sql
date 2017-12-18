INSERT INTO mydb.skills (name) VALUES ('Java');
INSERT INTO mydb.skills (name) VALUES ('C++');
INSERT INTO mydb.skills (name) VALUES ('Python');
INSERT INTO mydb.skills (name) VALUES ('C#');


INSERT INTO mydb.developers(first_name, last_name, speciality, salary) VALUES ('Anton', 'Barybin', 'Developer', 2000);
INSERT INTO mydb.developers(first_name, last_name, speciality, salary) VALUES ('Denis', 'Barybin', 'QA', 2500);
INSERT INTO mydb.developers_skills(developer_id, skill_id) VALUES (1, 1);
INSERT INTO mydb.developers_skills(developer_id, skill_id) VALUES (1, 3);
INSERT INTO mydb.developers_skills(developer_id, skill_id) VALUES (2, 2);
INSERT INTO mydb.developers_skills(developer_id, skill_id) VALUES (2, 4);