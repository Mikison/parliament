package pl.sonmiike.parliamentwebapi.Services;

import pl.sonmiike.parliamentwebapi.Contract.MPDTO;

import java.util.List;

public interface IMemberService {

    List<MPDTO> getByPage(int size, int page);

    MPDTO getById(long id);

    long save(MPDTO mpDto);

    MPDTO delete(long id);

    MPDTO update(long id, MPDTO mpDto);
}
