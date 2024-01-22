package pl.sonmiike.parliamentdata.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Voting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int abstain;
    private LocalDateTime date;
    private String description;
    private String kind;
    private int no;
    private int notParticipating;
    private int sitting;
    private int sittingDay;
    private int term;
    @Column(length = 2048)
    private String title;

    private String topic = "";
    private int totalVoted;
    private int votingNumber;
    @ElementCollection
    private List<VotingOptions> votingOptions;
    private int yes;

}
