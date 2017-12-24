INSERT INTO mydb.skills (name) VALUES ('Java');
INSERT INTO mydb.skills (name) VALUES ('C++');
INSERT INTO mydb.skills (name) VALUES ('Python');
INSERT INTO mydb.skills (name) VALUES ('C#');


INSERT INTO mydb.developers(first_name, last_name, speciality, salary) VALUES ('Anton', 'Barybin', 'Developer', 2000);
INSERT INTO mydb.developers(first_name, last_name, speciality, salary) VALUES ('Denis', 'Barybin', 'QA', 2500);
INSERT INTO mydb.developers(first_name, last_name, speciality, salary) VALUES ('Mikhail', 'Kozlov', 'Developer', 2200);
INSERT INTO mydb.developers_skills(developer_id, skill_id) VALUES (1, 1);
INSERT INTO mydb.developers_skills(developer_id, skill_id) VALUES (1, 3);
INSERT INTO mydb.developers_skills(developer_id, skill_id) VALUES (2, 2);
INSERT INTO mydb.developers_skills(developer_id, skill_id) VALUES (2, 4);
INSERT INTO mydb.developers_skills(developer_id, skill_id) VALUES (3, 2);
INSERT INTO mydb.developers_skills(developer_id, skill_id) VALUES (3, 3);

insert into mydb.teams(name) values ('Best Team')
insert into mydb.teams(name) values ('Good Team')
insert into mydb.teams_developers(team_id, developer_id) values (1, 1)
insert into mydb.teams_developers(team_id, developer_id) values (1, 2)
insert into mydb.teams_developers(team_id, developer_id) values (2, 2)
insert into mydb.teams_developers(team_id, developer_id) values (2, 3)

insert into project(name) values ('Calendar')
insert into project(name) values ('Test system')
insert into mydb.projects_teams(project_id, team_id) values (1, 1)
insert into mydb.projects_teams(project_id, team_id) values (2, 1)
insert into mydb.projects_teams(project_id, team_id) values (2, 2)

insert into companies(name) values ('Tesla')
insert into companies(name) values ('Apple')
insert into mydb.companies_projects(company_id, project_id) values (1, 2)
insert into mydb.companies_projects(company_id, project_id) values (2, 2)
insert into mydb.companies_projects(company_id, project_id) values (2, 1)

insert into customers(first_name, last_name, address) values ('Zaal', 'Lyanov', 'Chyornaya Rechka')
insert into customers(first_name, last_name, address) values ('Nargis', 'Yusbasheva', 'SPb')
insert into customerts_projetcs(customer_id, project_id) values (1, 2)
insert into customerts_projetcs(customer_id, project_id) values (2, 1)