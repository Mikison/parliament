package pl.sonmiike.parliamentupdater.updater.mappers;

import org.springframework.stereotype.Component;
import pl.sonmiike.parliamentclient.contract.ParliamentMemberDTO;
import pl.sonmiike.parliamentdata.model.ParliamentMembers;

@Component
public class ParliamentMembersMapper implements IMapper<ParliamentMemberDTO, ParliamentMembers>{


    @Override
    public ParliamentMembers mapToEntity(ParliamentMemberDTO parliamentMemberDTO) {
        return map(parliamentMemberDTO, new ParliamentMembers());
    }

    @Override
    public ParliamentMembers map(ParliamentMemberDTO parliamentMemberDTO, ParliamentMembers parliamentMembers) {
        parliamentMembers.setActive(parliamentMemberDTO.isActive());
        parliamentMembers.setClub(parliamentMemberDTO.getClub());
        parliamentMembers.setEmail(parliamentMemberDTO.getEmail());
        parliamentMembers.setFirstName(parliamentMemberDTO.getFirstName());
        parliamentMembers.setLastName(parliamentMemberDTO.getLastName());
        parliamentMembers.setBirthDate(parliamentMemberDTO.getBirthDate());
        parliamentMembers.setBirthLocation(parliamentMemberDTO.getBirthLocation());
        parliamentMembers.setDistrictName(parliamentMemberDTO.getDistrictName());
        parliamentMembers.setDistrictNum(parliamentMemberDTO.getDistrictNum());
        parliamentMembers.setApiID(parliamentMemberDTO.getApiId());
        parliamentMembers.setEducationLevel(parliamentMemberDTO.getEducationLevel());
        parliamentMembers.setFirstLastName(parliamentMemberDTO.getFirstLastName());
        parliamentMembers.setLastFirstName(parliamentMemberDTO.getLastFirstName());
        parliamentMembers.setNumberOfVotes(parliamentMemberDTO.getNumberOfVotes());
        parliamentMembers.setSecondName(parliamentMemberDTO.getSecondName());
        parliamentMembers.setVoivodeship(parliamentMemberDTO.getVoivodeship());
        if (parliamentMemberDTO.getWaiverDesc().isPresent())
            parliamentMembers.setWaiverDesc(parliamentMemberDTO.getWaiverDesc().get());
        return parliamentMembers;
    }
}
