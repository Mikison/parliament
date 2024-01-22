package pl.sonmiike.parliamentwebapi.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sonmiike.parliamentwebapi.Contract.VotingsDTO;
import pl.sonmiike.parliamentwebapi.Services.IVotingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sejm/votings")
@RequiredArgsConstructor
public class VotingsController {


    private final IVotingService votingService;


    @GetMapping
    public ResponseEntity<List<VotingsDTO>> getByPage(@RequestParam(required = false, defaultValue = "10") int size,
                                                      @RequestParam(required = false, defaultValue = "0") int page){
        List<VotingsDTO> test = votingService.getByPage(size, page);
        return ResponseEntity.ok(test);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VotingsDTO> getById(@PathVariable long id){
        var result = votingService.getById(id);
        if(result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> saveVoting(@RequestBody VotingsDTO votingsDto){
        var id = votingService.save(votingsDto);
        return ResponseEntity.ok().build(); // TODO: created(getLocationUri(id)).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        var result = votingService.delete(id);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id,
                                 @RequestBody VotingsDTO votingsDto){
        var result = votingService.update(id, votingsDto);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
