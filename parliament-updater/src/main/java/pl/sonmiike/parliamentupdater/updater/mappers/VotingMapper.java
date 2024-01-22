package pl.sonmiike.parliamentupdater.updater.mappers;

import org.springframework.stereotype.Component;
import pl.sonmiike.parliamentclient.contract.ParliamentVotingsDTO;
import pl.sonmiike.parliamentdata.model.Voting;

@Component
public class VotingMapper implements IMapper<ParliamentVotingsDTO, Voting> {
    @Override
    public Voting mapToEntity(ParliamentVotingsDTO parliamentVotingsDTO) {
        return map(parliamentVotingsDTO, new Voting());
    }

    @Override
    public Voting map(ParliamentVotingsDTO parliamentVotingsDTO, Voting voting) {
        voting.setAbstain(parliamentVotingsDTO.getAbstain());
        voting.setDate(parliamentVotingsDTO.getDate());
        voting.setDescription(parliamentVotingsDTO.getDescription());
        voting.setKind(parliamentVotingsDTO.getKind());
        voting.setNo(parliamentVotingsDTO.getNo());
        voting.setNotParticipating(parliamentVotingsDTO.getNotParticipating());
        voting.setSitting(parliamentVotingsDTO.getSitting());
        voting.setSittingDay(parliamentVotingsDTO.getSittingDay());
        voting.setTerm(parliamentVotingsDTO.getTerm());
        voting.setTitle(parliamentVotingsDTO.getTitle());
        voting.setVotingNumber(parliamentVotingsDTO.getVotingNumber());
        if (parliamentVotingsDTO.getTopic().isPresent()) voting.setTopic(parliamentVotingsDTO.getTopic().get());
        voting.setTotalVoted(parliamentVotingsDTO.getTotalVoted());
        if (parliamentVotingsDTO.getVotingOptions().isPresent()) {
            voting.setVotingOptions(parliamentVotingsDTO.getVotingOptions().get().stream().map(votingOptionsDTO -> {
                VotingOptiosMapper votingOptionsMapper = new VotingOptiosMapper();
                return votingOptionsMapper.mapToEntity(votingOptionsDTO);
            }).collect(java.util.stream.Collectors.toList()));
        }
        voting.setYes(parliamentVotingsDTO.getYes());
        return voting;
    }
}
