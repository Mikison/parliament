package pl.sonmiike.parliamentwebapi.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sonmiike.parliamentwebapi.Contract.MPDTO;
import pl.sonmiike.parliamentwebapi.Services.IMemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sejm/members")
public class MemberController {


    private final IMemberService memberService;


    @GetMapping
    public ResponseEntity<List<MPDTO>> getPage(@RequestParam(required = false, defaultValue = "10") int size,
                                               @RequestParam(required = false, defaultValue = "0") int page){
        return ResponseEntity.ok(memberService.getByPage(size, page));
    }


    @PostMapping
    public ResponseEntity<?> saveMember(@RequestBody MPDTO mpdto){
        var id = memberService.save(mpdto);
        return ResponseEntity.ok().build(); // TODO: created(getLocationUri(id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MPDTO> getById(@PathVariable long id){
        var result = memberService.getById(id);
        if(result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        var result = memberService.delete(id);

        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id,
                                 @RequestBody MPDTO mpdto){
        var result = memberService.update(id, mpdto);
        if(result==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }


}
