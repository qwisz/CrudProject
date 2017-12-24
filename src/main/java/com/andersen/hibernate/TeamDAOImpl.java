package com.andersen.hibernate;

import com.andersen.HibernateWorker;
import com.andersen.idao.ITeamDAO;
import com.andersen.model.Team;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TeamDAOImpl implements ITeamDAO {

    private Session session;

    public TeamDAOImpl() {
        session = HibernateWorker.getSessionFactory().openSession();
    }

    @Override
    public boolean save(Team team) {
        return saveAs(team);
    }

    @Override
    public String read(Long id) {
        Team team = session.load(Team.class, id);
        return team.toString();
    }

    @Override
    public boolean update(Long id, Team newTeam) {
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Team oldTeam = (Team) session.get(Team.class, id);
        oldTeam.setName(newTeam.getName());
        oldTeam.setDevelopers(newTeam.getDevelopers());

        session.update(oldTeam);
        transaction.commit();
        return oldTeam != null;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Team team = (Team) session.get(Team.class, id);

        session.delete(team);
        transaction.commit();
    }

    public boolean isExist(Long id) {
        return session.get(Team.class, id) != null;
    }

    public Team getById(Long id) {
        return session.get(Team.class, id);
    }
}
