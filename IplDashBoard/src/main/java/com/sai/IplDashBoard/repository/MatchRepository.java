package com.sai.IplDashBoard.repository;

import com.sai.IplDashBoard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1Name,String team2Name,Pageable pageable);

    default List<Match> findLatestMatchesByTeam(String teamName,int n){
        Pageable page = PageRequest.of(0,n);
        return getByTeam1OrTeam2OrderByDateDesc(teamName,teamName,page);
    }
}
