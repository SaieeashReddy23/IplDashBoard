package com.sai.IplDashBoard.Controllers;


import com.sai.IplDashBoard.model.Match;
import com.sai.IplDashBoard.model.Team;
import com.sai.IplDashBoard.repository.MatchRepository;
import com.sai.IplDashBoard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
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

    @GetMapping("/teams/{teamName}/matches")
    public List<Match> matches(@PathVariable String teamName, @RequestParam int year){

        LocalDate startDate = LocalDate.of(year,1,1);
        LocalDate endDate = LocalDate.of(year+1,1,1);

        return matchRepo.getMatchesBetweenDates(teamName,startDate,endDate);
    }
}
