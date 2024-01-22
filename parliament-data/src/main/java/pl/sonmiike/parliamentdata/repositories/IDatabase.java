package pl.sonmiike.parliamentdata.repositories;

public interface IDatabase {


    ParliamentClubsRepository getClubs();
    ParliamentMembersRepository getMPs();
    VotingsRepository getVotings();

    VotesRepository getMPVotings();




}
