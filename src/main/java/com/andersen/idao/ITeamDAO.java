package com.andersen.idao;

import com.andersen.model.Team;

public interface ITeamDAO extends DAO<Team> {

    boolean save(Team team);

    boolean update(Long id, Team newTeam);
}
