package pl.sonmiike.parliamentupdater.updater.mappers;

import org.springframework.stereotype.Component;
import pl.sonmiike.parliamentclient.contract.side.VotingOptionsDTO;
import pl.sonmiike.parliamentdata.model.VotingOptions;

@Component
public class VotingOptiosMapper implements IMapper<VotingOptionsDTO, VotingOptions>{


    @Override
    public VotingOptions mapToEntity(VotingOptionsDTO votingOptionsDTO) {
        return map(votingOptionsDTO, new VotingOptions());
    }

    @Override
    public VotingOptions map(VotingOptionsDTO votingOptionsDTO, VotingOptions votingOptions) {
        votingOptions.setOption(votingOptionsDTO.getOption());
        votingOptions.setOptionIndex(votingOptionsDTO.getOptionIndex());
        votingOptions.setVotes(votingOptionsDTO.getVotes());
        return votingOptions;
    }
}
