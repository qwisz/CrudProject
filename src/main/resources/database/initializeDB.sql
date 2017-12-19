CREATE TABLE skills (
  id   BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY(id)
);

CREATE TABLE developers (
  id         BIGINT       NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  speciality VARCHAR(255) NOT NULL,
  salary     DECIMAL      NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE teams (
  id   BIGINT       NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
);

CREATE TABLE projects (
  id   BIGINT       NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE companies (
  id   BIGINT       NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE customers (
  id         BIGINT       NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  address    VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE developers_skills (
  developer_id BIGINT NOT NULL,
  skill_id     BIGINT NOT NULL,
  FOREIGN KEY (developer_id) REFERENCES developers (id)
    ON DELETE CASCADE,
  FOREIGN KEY (skill_id) REFERENCES skills (id)
    ON DELETE CASCADE,
  UNIQUE (developer_id, skill_id)

);

CREATE TABLE teams_developers (
  team_id      BIGINT NOT NULL,
  developer_id BIGINT NOT NULL,
  FOREIGN KEY (team_id) REFERENCES teams (id)
    ON DELETE CASCADE,
  FOREIGN KEY (developer_id) REFERENCES developers (id)
    ON DELETE CASCADE,
  UNIQUE (team_id, developer_id)
);

CREATE TABLE projects_teams (
  project_id BIGINT NOT NULL,
  team_id    BIGINT NOT NULL,
  FOREIGN KEY (project_id) REFERENCES projects (id)
    ON DELETE CASCADE,
  FOREIGN KEY (team_id) REFERENCES teams (id)
    ON DELETE CASCADE,
  UNIQUE (project_id, team_id)
);

CREATE TABLE companies_projects (
  company_id BIGINT NOT NULL,
  project_id BIGINT NOT NULL,
  FOREIGN KEY (company_id) REFERENCES companies (id)
    ON DELETE CASCADE,
  FOREIGN KEY (project_id) REFERENCES projects (id)
    ON DELETE CASCADE,
  UNIQUE (company_id, project_id)
);

CREATE TABLE customers_projects (
  customer_id BIGINT NOT NULL,
  project_id  BIGINT NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES customers (id)
    ON DELETE CASCADE,
  FOREIGN KEY (project_id) REFERENCES projects (id)
    ON DELETE CASCADE,
  UNIQUE (customer_id, project_id)
);