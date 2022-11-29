package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.dao.*;
import org.example.dao.impl.*;
import org.example.entity.*;
import org.example.utils.HibernateUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtils.getEntityManagerFactory().createEntityManager();
        CityDao cityDao = new CityDaoImpl(entityManager);
        CoachContractDao coachContractDao = new CoachContractDaoImpl(entityManager);
        PlayerContractDao playerContractDao = new PlayerContractDaoImpl(entityManager);
        MatchDao matchDao = new MatchDaoImpl(entityManager);
        TeamDao teamDao = new TeamDaoImpl(entityManager);
        TeamResultDao resultDao = new TeamResultDaoImpl(entityManager);

        City city1 = new City("Shiraz");
        City city2 = new City("Tehran");

        Stadium stadium1 = new Stadium("hafezie", city1);
        Stadium stadium2 = new Stadium("azadi", city2);

        Team team1 = new Team("barq", city1);
        Team team2 = new Team("perspolis", city2);
        Team team3 = new Team("esteqlal", city2);
        Team team4 = new Team("fajr", city1);

        Coach coach1 = new Coach("gholam Peyravani");
        Coach coach2 = new Coach("yahya golmohamadi");
        Coach coach3 = new Coach("farhad majidi");

        Player player1 = new Player("gholamreza Rezayi");
        Player player2 = new Player("alireza beyranvand");
        Player player3 = new Player("kave rezayi");
        Player player4 = new Player("milad zare");

        Season season1 = new Season("khalij fars 1401"
                , LocalDate.of(1401, 1, 1)
                , LocalDate.of(1401, 9, 30));

        Season season2 = new Season("khalij fars 1402"
                , LocalDate.of(1402, 1, 1)
                , LocalDate.of(1402, 9, 30));

        TransferWindow window1 = new TransferWindow("1402", season1, season2);

        CoachContract coachContract1 = new CoachContract(team1, coach1, season1, 2);
        CoachContract coachContract2 = new CoachContract(team2, coach2, season1, 2);
        CoachContract coachContract3 = new CoachContract(team1, coach3, season1, 1);

        PlayerContract playerContract1 = new PlayerContract(team1, player1, season1, 10);
        PlayerContract playerContract2 = new PlayerContract(team2, player1, season2, 15);
        PlayerContract playerContract3 = new PlayerContract(team2, player2, season2, 15);
        PlayerContract playerContract4 = new PlayerContract(team3, player3, season2, 15);
        PlayerContract playerContract5 = new PlayerContract(team3, player4, season1, 15);

        Transfer transfer1 = new Transfer(window1, team1, team2, player1, 100
                , LocalDate.of(1401, 11, 1), playerContract2);

        Match match1 = new Match(season1, 1, team1, 3, team4, 4, stadium1);
        Match match2 = new Match(season1, 1, team2, 6, team3, 0, stadium2);
        Match match3 = new Match(season1, 2, team2, 6, team1, 0, stadium2);
        Match match4 = new Match(season2, 1, team1, 3, team4, 3, stadium1);

        //season.addTeam(team);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(stadium1);
        entityManager.persist(team3);
        entityManager.persist(team4);
        /*entityManager.persist(coach);
        entityManager.persist(player);*/
        entityManager.persist(playerContract1);
        entityManager.persist(playerContract3);
        entityManager.persist(playerContract4);
        entityManager.persist(playerContract5);
        entityManager.persist(coachContract1);
        entityManager.persist(coachContract2);
        entityManager.persist(coachContract3);
        //entityManager.persist(window1);
        entityManager.persist(transfer1);
        entityManager.persist(match1);
        entityManager.persist(match2);
        entityManager.persist(match3);
        entityManager.persist(match4);
        transaction.commit();

        /*System.out.println("City + number of teams:");
        System.out.println(Arrays.deepToString(cityDao.countTeams().toArray()));
        System.out.println(Arrays.deepToString(coachContractDao.mostExpensiveCoaches().toArray()));
        System.out.println(Arrays.deepToString(playerContractDao.showBySalary(season1).toArray()));
        System.out.println(Arrays.deepToString(playerContractDao.showBySalary(season2).toArray()));*/

       /* System.out.println(Arrays.deepToString(matchDao.findDerbies(season1).toArray()));
        System.out.println(team2.getName()+" "+matchDao.calculateTeamPoints(season1,team2));
        System.out.println(team1.getName()+" "+matchDao.calculateTeamPoints(season1,team1));
        System.out.println(team3.getName()+" "+matchDao.calculateTeamPoints(season1,team3));
        System.out.println(team4.getName()+" "+matchDao.calculateTeamPoints(season1,team4));*/

        /*System.out.println(matchDao.findSeasonTeamsId(season1));
        System.out.println(matchDao.findSeasonTeamsId(season2));
*/
        Set<Long> teamsId = matchDao.findSeasonTeamsId(season1);
        // teamsId.forEach(Long-> System.out.println(teamDao.findById(Long)));
        resultDao.saveAllResults(season1);

        System.out.println(Arrays.deepToString(resultDao.findChampion(season1).toArray()));


        /*System.out.println(stadium);
        System.out.println(stadium.getId());*/

    }
}

//playerContract1.terminate(LocalDate.of(1401,11,1));