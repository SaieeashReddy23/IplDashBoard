package com.sai.IplDashBoard.Controllers;


import com.sai.IplDashBoard.model.Team;
import com.sai.IplDashBoard.repository.MatchRepository;
import com.sai.IplDashBoard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepo;
    @Autowired
    private MatchRepository matchRepo;



    @GetMapping("/teams/{teamName}")
    public Team teamDetails(@PathVariable String teamName){
        Team team = teamRepo.findByTeamName(teamName);
        team.setLatestMatches(matchRepo.findLatestMatchesByTeam(teamName,4));
        return team;
    }
}
