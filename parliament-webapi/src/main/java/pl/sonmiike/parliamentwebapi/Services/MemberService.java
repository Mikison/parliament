package pl.sonmiike.parliamentwebapi.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sonmiike.parliamentdata.model.ParliamentMembers;
import pl.sonmiike.parliamentdata.repositories.Databases;
import pl.sonmiike.parliamentwebapi.Contract.MPDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements IMemberService {


    private final Databases databases;
    private final VotesService votes;

    @Override
    public List<MPDTO> getByPage(int size, int page) {

        return databases.getMPs().findAll().stream().map(MemberService::mapFromMP).toList();
    }

    @Override
    public MPDTO getById(long id) {
        var mp =  databases.getMPs().findByApiID(id).map(MemberService::mapFromMP).orElse(null);
        if (mp==null) return null;
        mp.setVotes(votes.getMPVotes(mp.getApiID()));
        return mp;
    }

    @Override
    public long save(MPDTO mpDto) {
        ParliamentMembers member = getMPFromDto(mpDto);
        var mp = databases.getMPs().save(member);
        return mp.getId();
    }

    @Override
    public MPDTO delete(long id) {
        var mp = databases.getMPs().findByApiID(id).orElse(null);
        if(mp==null) return null;

        var club = mp.getParliamentClub();
        club.getParliamentMembers().remove(mp);;


        databases.getMPVotings().deleteAllByParliamentMember(mp);
        databases.getMPs().delete(mp);
        return mapFromMP(mp);
    }

    @Override
    public MPDTO update(long id, MPDTO mpDto) {
        var mp = databases.getMPs().findByApiID(id).orElse(null);
        if(mp==null) return null;
        databases.getMPs().save(getMPFromDto(mpDto, mp));
        return mpDto;
    }


    public static MPDTO mapFromMP(ParliamentMembers mp) {
        MPDTO mpdto = new MPDTO();
        mpdto.setActive(mp.isActive());
        mpdto.setBirthDate(mp.getBirthDate());
        mpdto.setBirthLocation(mp.getBirthLocation());
        mpdto.setClub(mp.getClub());
        mpdto.setDistrictName(mp.getDistrictName());
        mpdto.setDistrictNum(mp.getDistrictNum());
        mpdto.setEducationLevel(mp.getEducationLevel());
        mpdto.setApiID(mp.getApiID());
        mpdto.setEmail(mp.getEmail());
        mpdto.setFirstLastName(mp.getFirstLastName());
        mpdto.setFirstName(mp.getFirstName());
        mpdto.setLastFirstName(mp.getLastFirstName());
        mpdto.setLastName(mp.getLastName());
        mpdto.setNumberOfVotes(mp.getNumberOfVotes());
        mpdto.setSecondName(mp.getSecondName());
        mpdto.setVoivodeship(mp.getVoivodeship());
        mpdto.setWaiverDesc(mp.getWaiverDesc());
        return mpdto;
    }

    public static ParliamentMembers getMPFromDto(MPDTO mpDto) {
        return getMPFromDto(mpDto, new ParliamentMembers());
    }
    public static ParliamentMembers getMPFromDto(MPDTO mpDto, ParliamentMembers member) {

        member.setActive(mpDto.isActive());
        member.setBirthDate(mpDto.getBirthDate());
        member.setBirthLocation(mpDto.getBirthLocation());
        member.setClub(mpDto.getClub());
        member.setDistrictName(mpDto.getDistrictName());
        member.setDistrictNum(mpDto.getDistrictNum());
        member.setEducationLevel(mpDto.getEducationLevel());
        member.setApiID(mpDto.getApiID());
        member.setEmail(mpDto.getEmail());
        member.setFirstLastName(mpDto.getFirstLastName());
        member.setFirstName(mpDto.getFirstName());
        member.setLastFirstName(mpDto.getLastFirstName());
        member.setLastName(mpDto.getLastName());
        member.setNumberOfVotes(mpDto.getNumberOfVotes());
        member.setSecondName(mpDto.getSecondName());
        member.setVoivodeship(mpDto.getVoivodeship());
        member.setWaiverDesc(mpDto.getWaiverDesc());
        return member;
    }
}
