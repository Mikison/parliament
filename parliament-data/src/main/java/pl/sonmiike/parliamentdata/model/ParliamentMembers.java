package pl.sonmiike.parliamentdata.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ParliamentMembers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean active;
    private LocalDate birthDate;
    private String birthLocation;
    private String club;
    private String districtName;
    private String districtNum;
    private String educationLevel;
    private long apiID;
    private String email;
    private String firstLastName;
    private String firstName;
    private String lastFirstName;
    private String lastName;
    private String numberOfVotes;
    private String secondName;
    private String voivodeship;
    private String waiverDesc = "";


    @ManyToOne
    private ParliamentClub parliamentClub;


}
