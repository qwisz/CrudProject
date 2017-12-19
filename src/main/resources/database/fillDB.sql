INSERT INTO mydb.skills (name) VALUES ('Java');
INSERT INTO mydb.skills (name) VALUES ('C++');
INSERT INTO mydb.skills (name) VALUES ('Python');
INSERT INTO mydb.skills (name) VALUES ('C#');


INSERT INTO mydb.teams(name) values ('Best Team');
INSERT INTO mydb.teams(name) values ('Good Team');
INSERT INTO mydb.teams_developers(team_id, developer_id) values (1, 1);
insert into mydb.teams_developers(team_id, developer_id) values (1, 2);
insert into mydb.teams_developers(team_id, developer_id) values (2, 2);
insert into mydb.teams_developers(team_id, developer_id) values (2, 3);

insert into projects(name) values ('Calendar');
insert into projects(name) values ('Test system');
insert into mydb.projects_teams(project_id, team_id) values (1, 1);
insert into mydb.projects_teams(project_id, team_id) values (2, 1);
insert into mydb.projects_teams(project_id, team_id) values (2, 2);

insert into companies(name) values ('Tesla');
insert into companies(name) values ('Apple');
insert into mydb.companies_projects(company_id, project_id) values (1, 2);
insert into mydb.companies_projects(company_id, project_id) values (2, 2);
insert into mydb.companies_projects(company_id, project_id) values (2, 1);

insert into customers(first_name, last_name, address) values ('Zaal', 'Lyanov', 'Chyornaya Rechka');
insert into customers(first_name, last_name, address) values ('Nargis', 'Yusbasheva', 'SPb');
insert into customers_projects(customer_id, project_id) values (1, 2);
insert into customers_projects(customer_id, project_id) values (2, 1);