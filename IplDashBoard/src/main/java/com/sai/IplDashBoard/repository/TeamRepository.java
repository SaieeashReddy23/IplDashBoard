package com.sai.IplDashBoard.repository;

import com.sai.IplDashBoard.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Long> {

    Team findByTeamName(String teamName);
}
