package com.andersen.hibernate;

import com.andersen.HibernateWorker;
import com.andersen.idao.IProjectDAO;
import com.andersen.model.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProjectDAOImpl implements IProjectDAO {

    private Session session;

    public ProjectDAOImpl() {
        session = HibernateWorker.getSessionFactory().openSession();
    }

    @Override
    public boolean save(Project project) {
        return saveAs(project);
    }

    @Override
    public String read(Long id) {
        Project project = session.load(Project.class, id);
        return project.toString();
    }

    @Override
    public boolean update(Long id, Project newProject) {
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Project oldProject = (Project) session.get(Project.class, id);
        oldProject.setName(newProject.getName());
        oldProject.setTeams(newProject.getTeams());

        session.update(oldProject);
        transaction.commit();
        return oldProject != null;
    }

    @Override
    public void delete(Long id) {

        Transaction transaction = null;

        transaction = session.beginTransaction();
        Project project = (Project) session.get(Project.class, id);

        session.delete(project);
        transaction.commit();
    }

    public boolean isExist(Long id) {
        return session.get(Project.class, id) != null;
    }

    public Project getById(Long id) {
        return session.get(Project.class, id);
    }
}
