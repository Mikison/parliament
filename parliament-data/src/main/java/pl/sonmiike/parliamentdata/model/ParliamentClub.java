package pl.sonmiike.parliamentdata.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ParliamentClub {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String fax;
    private String nameId;
    private int membersCount;
    private String name;
    private String phone;


    @OneToMany(fetch = FetchType.EAGER)
    private List<ParliamentMembers> parliamentMembers;

}
