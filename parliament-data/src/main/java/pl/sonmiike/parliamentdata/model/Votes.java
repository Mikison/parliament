package pl.sonmiike.parliamentdata.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Votes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ParliamentMembers parliamentMember;


    private LocalDateTime date;

    @ManyToOne
    private Voting voting;

    @Column(length = 2048)
    private String voteTitle;
    private String vote;


}
