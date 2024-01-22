package pl.sonmiike.parliamentupdater.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sonmiike.parliamentupdater.updater.Updater;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/sejm/update")
@Slf4j
public class UpdaterController {

    private final Updater updater;

    // UPDATE BASIC DATA
    @GetMapping
    public ResponseEntity updateData() {
        new Thread(updater::updateClubsAndMpsAndVotings).start();
        return ResponseEntity.ok("Update of Parliament Votings, Parliament Clubs and Minister of Parlaiment\nStarted at:  " + LocalDateTime.now());
    }


    @GetMapping("/MP/{id}")
    public ResponseEntity updateVotingsThatMPsParticipatedIn(@PathVariable int id) {
        new Thread(() -> updater.updateParliamentMembersVotings(id)).start();
        return ResponseEntity.ok("Update of this Minister of Parliament Voting\nStarted at:  " + LocalDateTime.now());
    }

    @GetMapping("/voting/{id}")
    public ResponseEntity<?> getVotesForVoting(@PathVariable int id) {
        return ResponseEntity.ok(updater.votes(id));
    }

    @GetMapping("/MP/all")
    public ResponseEntity updateVotes() {
        new Thread(updater::updateAllMPsVotes).start();
        return ResponseEntity.ok("Update ALL MPs Votes\nStarted at:  " + LocalDateTime.now());
    }

    @GetMapping("/purge")
    public ResponseEntity purge() {
        new Thread(updater::purge).start();
        return ResponseEntity.ok("Purge\nStarted at:  " + LocalDateTime.now());
    }
}
