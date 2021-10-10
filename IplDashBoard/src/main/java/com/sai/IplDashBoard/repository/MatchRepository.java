package com.sai.IplDashBoard.repository;

import com.sai.IplDashBoard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1Name,String team2Name,Pageable pageable);

    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName ) and m.date between :startDate and :endDate order by date desc")
    List<Match> getMatchesBetweenDates(
            @Param("teamName") String teamName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    default List<Match> findLatestMatchesByTeam(String teamName,int n){
        Pageable page = PageRequest.of(0,n);
        return getByTeam1OrTeam2OrderByDateDesc(teamName,teamName,page);
    }
}
