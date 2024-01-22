package pl.sonmiike.parliamentwebapi.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.sonmiike.parliamentdata.model.Voting;
import pl.sonmiike.parliamentdata.repositories.IDatabase;
import pl.sonmiike.parliamentwebapi.Contract.MPDTO;
import pl.sonmiike.parliamentwebapi.Contract.VotingsDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VotingService implements IVotingService {

    private final IDatabase database;
    private final VotesService votes;
    @Override
    public List<VotingsDTO> getByPage(int size, int page) {
        return database.getVotings().findAll(PageRequest.of(page, size)).stream().map(this::mapFromVoting).toList();
    }

    @Override
    public VotingsDTO getById(long id) {
        var voting = database.getVotings().findById(id).orElse(null);
        if(voting==null) return null;
        List<MPDTO> participants = votes.getVotingParticipants(voting.getDate());

        VotingsDTO mapped = mapFromVoting(voting);
        mapped.setParticipants(participants);

        return mapped;
    }

    @Override
    public long save(VotingsDTO votingsDto) {
        Voting voting = getVotingFromDto(votingsDto);
        var v =database.getVotings().save(voting);
        return v.getId();
    }

    @Override
    public VotingsDTO delete(long id) {
        var voting = database.getVotings().findById(id).orElse(null);
        if(voting==null) return null;
        database.getMPVotings().deleteAllByVotingId(id);
        database.getVotings().delete(voting);
        return mapFromVoting(voting);
    }

    @Override
    public VotingsDTO update(long id, VotingsDTO votingsDto) {
        var voting = database.getVotings().findById(id).orElse(null);
        if(voting==null) return null;
        database.getVotings().save(getVotingFromDto(votingsDto));
        return votingsDto;
    }

    private VotingsDTO mapFromVoting(Voting voting) {
        VotingsDTO votingsDTO = new VotingsDTO();
        votingsDTO.setId(voting.getId());
        votingsDTO.setDate(voting.getDate());
        votingsDTO.setSitting(voting.getSitting());
        votingsDTO.setSittingDay(voting.getSittingDay());
        votingsDTO.setTitle(voting.getTitle());
        votingsDTO.setTopic(voting.getTopic());
        votingsDTO.setYes(voting.getYes());
        votingsDTO.setNo(voting.getNo());
        votingsDTO.setAbstain(voting.getAbstain());
        votingsDTO.setTotalVoted(voting.getTotalVoted());
        votingsDTO.setKind(voting.getKind());
        return votingsDTO;
    }

    private static Voting getVotingFromDto(VotingsDTO votingsDto) {
        return getVotingFromDto(votingsDto, new Voting());
    }

    private static Voting getVotingFromDto(VotingsDTO votingsDto, Voting voting) {
        voting.setDate(votingsDto.getDate());
        voting.setSitting(votingsDto.getSitting());
        voting.setSittingDay(votingsDto.getSittingDay());
        voting.setTitle(votingsDto.getTitle());
        voting.setTopic(votingsDto.getTopic());
        voting.setYes(votingsDto.getYes());
        voting.setNo(votingsDto.getNo());
        voting.setAbstain(votingsDto.getAbstain());
        voting.setTotalVoted(votingsDto.getTotalVoted());
        voting.setKind(votingsDto.getKind());
        voting.setNotParticipating(460 - voting.getTotalVoted());
        voting.setTerm(10);

        return voting;

    }

}
