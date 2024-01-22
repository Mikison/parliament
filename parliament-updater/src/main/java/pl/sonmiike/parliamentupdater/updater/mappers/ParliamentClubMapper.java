package pl.sonmiike.parliamentupdater.updater.mappers;

import org.springframework.stereotype.Component;
import pl.sonmiike.parliamentclient.contract.ParliamentClubDTO;
import pl.sonmiike.parliamentdata.model.ParliamentClub;

@Component
public class ParliamentClubMapper implements IMapper<ParliamentClubDTO, ParliamentClub>{
    @Override
    public ParliamentClub mapToEntity(ParliamentClubDTO parliamentClubDTO) {
        return map(parliamentClubDTO, new ParliamentClub());
    }

    @Override
    public ParliamentClub map(ParliamentClubDTO parliamentClubDTO, ParliamentClub parliamentClub) {
        parliamentClub.setEmail(parliamentClubDTO.getEmail());
        parliamentClub.setFax(parliamentClubDTO.getFax());
        parliamentClub.setName(parliamentClubDTO.getName());
        parliamentClub.setPhone(parliamentClubDTO.getPhone());
        parliamentClub.setMembersCount(parliamentClubDTO.getMembersCount());
        parliamentClub.setNameId(parliamentClubDTO.getNameId());

        return parliamentClub;
    }
}
