package com.andersen.hibernate;

import com.andersen.HibernateWorker;
import com.andersen.idao.ISkillDAO;
import com.andersen.model.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SkillDAOImpl implements ISkillDAO {

    private Session session;

    public SkillDAOImpl() {
        session = HibernateWorker.getSessionFactory().openSession();
    }

    @Override
    public boolean save(Skill skill) {
        return saveAs(skill);
    }

    @Override
    public String read(Long id) {
        Skill skill = session.load(Skill.class, id);
        return skill.toString();
    }

    @Override
    public boolean update(Long id, Skill newSkill) {
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Skill oldSkill = (Skill) session.get(Skill.class, id);
        oldSkill.setName(newSkill.getName());

        session.update(oldSkill);
        transaction.commit();
        return oldSkill != null;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Skill skill = (Skill) session.get(Skill.class, id);
        session.delete(skill);
        transaction.commit();
    }

    public boolean isExist(Long id) {
        return session.get(Skill.class, id) != null;
    }

    public Skill getById(Long id) {
        return session.get(Skill.class, id);
    }
}
