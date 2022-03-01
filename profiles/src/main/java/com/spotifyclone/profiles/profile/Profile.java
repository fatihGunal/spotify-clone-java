package com.spotifyclone.profiles.profile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Profile {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String birthDate;
    // TODO: delete this comment after
}
