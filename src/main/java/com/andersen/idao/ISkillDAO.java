package com.andersen.idao;

import com.andersen.model.Skill;

public interface ISkillDAO extends DAO<Skill> {

    boolean save(Skill skill);

    boolean update(Long id, Skill newSkill);
}
