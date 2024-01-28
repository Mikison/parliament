package pl.sonmiike.parliamentwebapi.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.sonmiike.parliamentdata.model.ParliamentClub;
import pl.sonmiike.parliamentdata.repositories.IDatabase;
import pl.sonmiike.parliamentwebapi.Contract.ClubDTO;
import pl.sonmiike.parliamentwebapi.Contract.MPDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService implements IClubService {


    private final IDatabase database;

    @Override
    @Cacheable("clubs")
    public List<ClubDTO> getAllClubs() {
        return this.database.getClubs().findAll().stream().map(this::mapFromClub).toList();
    }

    @Override
    public ClubDTO getClubById(long id) {
        return database.getClubs().findById(id).map(this::mapFromClub).orElse(null);
    }

    @Override
    public long save(ClubDTO clubDto) {
        ParliamentClub club = getClubFromDto(clubDto);
        var saveClub = database.getClubs().save(club);
        return saveClub.getId();
    }

    @Override
    @CacheEvict(value = "clubs", allEntries = true)
    public ClubDTO delete(long id) {
        var club = database.getClubs().findById(id).orElse(null);
        if(club==null) return null;
        database.getClubs().delete(club);
        return mapFromClub(club);
    }

    @Override
    public ClubDTO update(long id, ClubDTO clubDto) {
        var club = database.getClubs().findById(id).orElse(null);
        if(club==null) return null;
        database.getClubs().save(getClubFromDto(clubDto, club));
        return clubDto;
    }

    private ClubDTO mapFromClub(ParliamentClub club) {
        List<MPDTO> members = club.getParliamentMembers().stream().map(MemberService::mapFromMP).toList();
        ClubDTO clubDTO = new ClubDTO();
        clubDTO.setId(club.getId());
        clubDTO.setName(club.getName());
        clubDTO.setNameId(club.getNameId());
        clubDTO.setMembersCount(club.getMembersCount());
        clubDTO.setEmail(club.getEmail());
        clubDTO.setMembers(members);
        return clubDTO;
    }
    private static ParliamentClub getClubFromDto(ClubDTO clubDto) {
        return getClubFromDto(clubDto, new ParliamentClub() );
    }

    private static ParliamentClub getClubFromDto(ClubDTO clubDto, ParliamentClub club) {
        club.setName(clubDto.getName());
        club.setNameId(clubDto.getNameId());
        club.setMembersCount(clubDto.getMembersCount());
        club.setEmail(clubDto.getEmail());
        return club;
    }
}
