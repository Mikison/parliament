package pl.sonmiike.parliamentwebapi.Services;

import pl.sonmiike.parliamentwebapi.Contract.ClubDTO;

import java.util.List;

public interface IClubService {


    List<ClubDTO> getAllClubs();

    ClubDTO getClubById(long id);

    long save(ClubDTO clubDto);

    ClubDTO delete(long id);

    ClubDTO update(long id, ClubDTO clubDto);
}
