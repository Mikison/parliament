package pl.sonmiike.parliamentwebapi.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sonmiike.parliamentwebapi.Contract.ClubDTO;
import pl.sonmiike.parliamentwebapi.Services.IClubService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sejm/clubs")
public class ClubController {


    private final IClubService clubService;


    @GetMapping
    public ResponseEntity<List<ClubDTO>> getAll() {
        return ResponseEntity.ok(this.clubService.getAllClubs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getById(@PathVariable long id) {
        var club = this.clubService.getClubById(id);
        if(club==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(club);
    }

    @PostMapping
    public ResponseEntity<?> saveClub(@RequestBody ClubDTO clubDto){
        var id = clubService.save(clubDto);
        return ResponseEntity.ok().build(); // TODO: created(getLocationUri(id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        var result = clubService.delete(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id,
                                 @RequestBody ClubDTO clubDto){
        var result = clubService.update(id, clubDto);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }












}
