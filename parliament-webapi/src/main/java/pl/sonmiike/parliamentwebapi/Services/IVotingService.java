package pl.sonmiike.parliamentwebapi.Services;

import pl.sonmiike.parliamentwebapi.Contract.VotingsDTO;

import java.util.List;

public interface IVotingService {



    List<VotingsDTO> getByPage(int size, int page);

    VotingsDTO getById(long id);

    long save(VotingsDTO votingsDto);

    VotingsDTO delete(long id);

    VotingsDTO update(long id, VotingsDTO votingsDto);



}
