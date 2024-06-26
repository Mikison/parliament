package pl.sonmiike.parliamentupdater.updater;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sonmiike.parliamentclient.apiclient.IAskApiClient;
import pl.sonmiike.parliamentclient.contract.ParliamentClubDTO;
import pl.sonmiike.parliamentclient.contract.ParliamentMemberDTO;
import pl.sonmiike.parliamentclient.contract.ParliamentVotingsDTO;
import pl.sonmiike.parliamentdata.model.ParliamentMembers;
import pl.sonmiike.parliamentdata.model.Votes;
import pl.sonmiike.parliamentdata.model.Voting;
import pl.sonmiike.parliamentdata.model.VotingOptions;
import pl.sonmiike.parliamentdata.repositories.IDatabase;
import pl.sonmiike.parliamentlogging.LogClient;
import pl.sonmiike.parliamentlogging.contract.LogDTO;
import pl.sonmiike.parliamentupdater.updater.mappers.IMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class Updater implements IUpdate {

     final IMap mapper;
     final IAskApiClient askApiClient;
     final IDatabase database;




    public Updater(IMap mapper, IAskApiClient askApiClient, IDatabase database) {
        this.mapper = mapper;
        this.askApiClient = askApiClient;
        this.database = database;
    }


    public void purge() {
        database.getMPVotings().deleteAll();
        database.getVotings().deleteAll();
        database.getClubs().findAll().forEach(club -> {
            Iterator<ParliamentMembers> iterator = club.getParliamentMembers().iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
            database.getClubs().save(club);
        });
        database.getMPs().deleteAll();
        database.getClubs().deleteAll();


        log.atInfo().log("Purged");
        LogClient.getClient().sendLogs(new LogDTO(LocalDateTime.now(), "Purged", "INFO"));
    }
    @Override
    public void updateClubsAndMpsAndVotings() {
        saveParliamentClubs(askApiClient.getClubs());
        log.atInfo().log("Clubs updated");
        LogClient.getClient().sendLogs(new LogDTO(LocalDateTime.now(), "Clubs Updated", "INFO"));
        updateParliamentVotings();
        log.atInfo().log("Votings updated");
        LogClient.getClient().sendLogs(new LogDTO(LocalDateTime.now(), "Votings Updated", "INFO"));
        saveParliamentMembers(askApiClient.getMPs());
        log.atInfo().log("Ministers of Parliament updated");
        LogClient.getClient().sendLogs(new LogDTO(LocalDateTime.now(), "Ministers of Parliament Updated", "INFO"));
        updateClubMembers();
        log.atInfo().log("Club members updated");
        LogClient.getClient().sendLogs(new LogDTO(LocalDateTime.now(), "Club members updated", "INFO"));


    }

    private void updateParliamentVotings() {
        List<LocalDateTime> existingVotings = database.getVotings().findAll().stream()
                .map(Voting::getDate)
                .toList();
            askApiClient.getVotings().stream()
                    .filter(votingDto -> !existingVotings.contains(votingDto.getDate()))
                    .forEach(this::saveParliamentVotings);
    }

    private void saveParliamentClubs(List<ParliamentClubDTO> clubsDto) {
        if (database.getClubs().count() == clubsDto.size()) return;
        clubsDto.stream()
                .map(mapper.clubs()::mapToEntity)
                .forEach(club -> {
                    database.getClubs().save(club);
                });

    }

    private void saveParliamentMembers(List<ParliamentMemberDTO> membersDto) {
        List<Long> existingApiIds = database.getMPs().findAll().stream()
                .map(ParliamentMembers::getApiID)
                .toList();
        membersDto.stream()
                .filter(memberDto -> !existingApiIds.contains(memberDto.getApiId()))
                .sorted(Comparator.comparing(ParliamentMemberDTO::getApiId))
                .map(mapper.members()::mapToEntity)
                .forEach(member -> {
                    try {
                        File file = new File(System.getProperty("user.dir") + "/angular-frontend/src/assets/images/" + member.getApiID() + ".jpg");
                        File file2 = new File(System.getProperty("user.dir") + "/angular-frontend/src/assets/images/" + member.getApiID() + "-mini.jpg");
                        if (!file.exists() && !file2.exists()) {

                            downloadImage("https://api.sejm.gov.pl/sejm/term10/MP/" + member.getApiID() + "/photo", member.getApiID() + ".jpg");
                            downloadImage("https://api.sejm.gov.pl/sejm/term10/MP/" + member.getApiID() + "/photo-mini", member.getApiID() + "-mini.jpg");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    var club = database.getClubs().findByNameId(member.getClub()).orElseThrow();
                    member.setParliamentClub(club);
                    database.getMPs().save(member);
                });
    }


    private void saveParliamentVotings(ParliamentVotingsDTO votingDto) {
        var voting = mapper.votings().mapToEntity(votingDto);
        votingDto.getVotingOptions().ifPresent(options -> {
            List<VotingOptions> votingOptions = options.stream()
                    .map(mapper.votingOptions()::mapToEntity)
                    .collect(Collectors.toList());
            voting.setVotingOptions(votingOptions);
        });
        Voting id = database.getVotings().save(voting);
        log.atInfo().log(String.valueOf(id.getId()));
    }

    private void updateClubMembers() {
        database.getClubs().findAll().forEach(club -> {
            List<ParliamentMembers> members = database.getMPs().findAllByClub(club.getNameId());
            club.setParliamentMembers(members);
            database.getClubs().save(club);
        });
    }

    public void updateParliamentMembersVotings(long id) {
            ParliamentMembers member = database.getMPs().findByApiID(id).orElseThrow();
            List<Votes> votes =new ArrayList<>(askApiClient.getMPVotings(member.getApiID()).stream().map(mapper.votes()::mapToEntity).toList());
            votes.forEach(vote -> {
                if (database.getMPVotings().findAllByParliamentMember(member).stream().anyMatch(v -> v.getDate().equals(vote.getDate()))) return;
                database.getVotings().findByDate(vote.getDate()).ifPresent(voting -> {
                    vote.setVoting(voting);
                    vote.setParliamentMember(member);
                    database.getMPVotings().save(vote);
                });
            });
            database.getMPs().save(member);
            log.atInfo().log("Updated MP votes for: " + member.getId());
            LogClient.getClient().sendLogs(new LogDTO(LocalDateTime.now(), "Updated MP votes for: " + member.getId(), "INFO"));

    }



    public List<Votes> votes(long id) {
        return database.getMPVotings().findAllByVotingId(id);
    }

    public void updateAllMPsVotes() {
        List<ParliamentMembers> parliamentMembers = database.getMPs().findAll();

        for (ParliamentMembers member : parliamentMembers) {
            updateParliamentMembersVotings(member.getApiID());
        }
    }



    public static void downloadImage(String imageUrl, String imageName) throws Exception {
        URL url = new URL(imageUrl);
        String savePath = System.getProperty("user.dir") + "/angular-frontend/src/assets/images/" + imageName;

        try (InputStream in = url.openStream();
             FileOutputStream fos = new FileOutputStream(savePath)) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("Image downloaded and saved as: " + savePath);
        }
    }


}







