package com.andersen.idao;

import com.andersen.model.Developer;

public interface IDeveloperDAO extends DAO<Developer> {

    boolean save(Developer developer);

    boolean update(Long id, Developer newDeveloper);
}
