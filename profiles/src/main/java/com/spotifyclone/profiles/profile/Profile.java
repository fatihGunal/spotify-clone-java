package com.spotifyclone.profiles.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile {

    @Id
    @SequenceGenerator(
            name = "profile_id_sequence",
            sequenceName = "profile_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profile_id_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String birthDate;
}
