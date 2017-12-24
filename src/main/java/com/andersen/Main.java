package com.andersen;

import com.andersen.hibernate.DeveloperDAOImpl;
import com.andersen.hibernate.SkillDAOImpl;
import com.andersen.hibernate.TeamDAOImpl;
import com.andersen.jdbc.CompanyDAO;
import com.andersen.jdbc.CustomerDAO;
import com.andersen.jdbc.ProjectDAO;
import com.andersen.jdbc.SkillDAO;
import com.andersen.model.Developer;
import com.andersen.model.Skill;
import com.andersen.model.Team;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static final String URL = "jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "admin";

    public static void main(String[] args) throws IOException {

//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             Statement statement = connection.createStatement()) {
//
//            String EXISTS = "SELECT EXISTS(SELECT id FROM skills WHERE id = 0)";
//            String q2 = "delete from developers_skills where skill_id = 4";
//            String q = "delete from skills where id = 4";
//            statement.execute(q2);
//            statement.execute(q);

//            String q = "UPDATE skills SET name='Python' WHERE id=10";
//            System.out.println(statement.executeUpdate(q));
//
//            String query = "select * from skills";
//            ResultSet resultSet = statement.executeQuery(query);
//            ResultSet resultSet = statement.executeQuery(EXISTS);
//
//            Long count = null;
//            while (resultSet.next()) {
//                Long id = resultSet.getLong("id");
//                String name = resultSet.getString("name");
//                Skill skill = new Skill(id, name);
//                System.out.println(skill);
//                count = resultSet.getLong(1);
//            }
//            System.out.println(count);

//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        DBWorker.getConnection();
//        SkillDAO dao = new SkillDAO();
//        System.out.println(dao.isExist(1L));
//        System.out.println(dao.isExist(0L));
//        dao.save(new Skill("Java"));
//        dao.save(new Skill("C++"));
//        dao.save(new Skill("Python"));
//        dao.save(new Skill("Java"));
//        System.out.println(dao.save(new Skill("Pascal")));
//        dao.read(4L);
//        dao.update(10L, new Skill("C#"));
//        dao.delete(3L);
//        System.out.println(dao.isExists(3L));

//        DeveloperDAO dao = new DeveloperDAO();
//        Set<Skill> skills = new HashSet<>();
//        Skill java = new Skill(1L, "Java");
//        Skill cpp = new Skill(2L, "C++");
//        Skill python = new Skill(3L, "Python");
//        Developer dev = new Developer(1L, "Andrew", "Godovsky", "Project Manager", skills, new BigDecimal(2000));
//        Developer dev2 = new Developer(2L, "Anton", "Godovsky", "Project Manager", skills, new BigDecimal(2000));
//        dao.save(dev);
//        System.out.println(dao.read(1L));
//        dao.update(8L, dev);
//        dao.delete(8L);
//        System.out.println(dao.isExists(13L));
//        System.out.println(dao.getById(1L));

//        Set<Developer> devs = new HashSet<>();
//        devs.add(dev);
//        devs.add(dev2);
//        TeamDAO dao = new TeamDAO();
//        Team team = new Team(4L,"Cowboyz", devs);
//        dao.save(team);
//        System.out.println(dao.read(3L));
//        System.out.println(dao.read(1L));
//        dao.update(3L, team);
//        dao.delete(3L);


//        ProjectDAO dao = new ProjectDAO();
//        System.out.println(dao.isExist("Calendar"));
//        System.out.println(dao.isExist("Calendar2"));

//        Set<Team> teams = new HashSet<>();
//        teams.add(team);
//        Project project = new Project(6L, "Nothing", teams);
//        Project project2 = new Project(8L, "BBL", teams);
//        dao.save(project2);
//        System.out.println(dao.read(5L));
//        dao.update(5L, project);
//        dao.delete(5L);

//        Set<Project> projects = new HashSet<>();
//        projects.add(project);
//
//        Set<Project> projects2 = new HashSet<>();
//        projects2.add(project2);

//        CompanyDAO dao = new CompanyDAO();
//        Company company = new Company("GoodBoys", projects);
//        dao.save(company);
//        System.out.println(dao.read(1L));
//        System.out.println(dao.read(2L));
//        Company updateCompany = new Company("GooBoyz", projects);
//        dao.update(1L, updateCompany);

//        CustomerDAO dao = new CustomerDAO();
//        Customer customer = new Customer("Zall", "NeLyanov", "Chyornaya rechka", projects);
//        Customer customer1 = new Customer("Nargiz", "Yusbasheva", "Saint Petersburg", projects2);
//        dao.save(customer1);
//        System.out.println(dao.read(1L));
//        System.out.println(dao.read(6L));
//        dao.update(1L, customer);
//        dao.update(6L, customer1);

        ConsoleHelper consoleHelper = new ConsoleHelper();
        consoleHelper.menu();

//        SkillDAOImpl dao = new SkillDAOImpl();
//        System.out.println(dao.read(7L));
//        dao.update(14L, new Skill("Assembler"));
//        dao.save(new Skill("Prolog"));
//        System.out.println(dao.isExist("Python"));
//        System.out.println(dao.isExist("Java"));
////        System.out.println(dao.isExist("Mockito"));
//        dao.save(new Skill("Mockito"));
//        System.out.println(dao.isExist("Mockito"));
//        System.out.println(dao.isExist(7L));

//        DeveloperDAOImpl dao = new DeveloperDAOImpl();
//        Set<Skill> skills = new HashSet<>();
//        Set<Skill> skills2 = new HashSet<>();
//        Skill java = new Skill(1L, "Java");
//        Skill cpp = new Skill(2L, "C++");
//        Skill csharp = new Skill(4L, "С#");
//        Skill go = new Skill(7L, "Go");
//        Skill python = new Skill(11L, "Python");
//        skills.add(java);
//        skills.add(csharp);
//        skills.add(go);
//
//        skills2.add(cpp);
//        skills2.add(java);
//
//        Developer developer = new Developer(14L,"Andrey", "Serov", "Cowboy", skills, new BigDecimal(2500));
//        Developer developer2 = new Developer(15L, "Cris", "Ron", "Cowboy", skills2, new BigDecimal(3000));
//        dao.save(developer2);
//        dao.update(14L, developer);

//        TeamDAOImpl dao = new TeamDAOImpl();
//        Set<Developer> devs = new HashSet<>();
//        devs.add(developer);
//        devs.add(developer2);
//        dao.save(new Team("Bad boys", devs));
//        System.out.println(dao.read(2L));
//        dao.update(5L, new Team("Bad boyzz", devs));
    }
}
