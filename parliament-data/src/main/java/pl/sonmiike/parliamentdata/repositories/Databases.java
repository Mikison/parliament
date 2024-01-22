package pl.sonmiike.parliamentdata.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Getter
@Repository
public class Databases implements IDatabase {

    private final ParliamentClubsRepository clubs;
    private final ParliamentMembersRepository MPs;
    private final VotingsRepository votings;
    private final VotesRepository mpVotings;

    @Override
    public VotesRepository getMPVotings() {
        return mpVotings;
    }
}