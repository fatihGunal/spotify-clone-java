package com.spotifyclone.music.profiles;

import com.spotifyclone.music.playlist.Playlist;
import com.spotifyclone.music.subscription.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(
                name = "email_unique",
                columnNames = "email"
        ), @UniqueConstraint(
                name = "username_unique",
                columnNames = "user_name"
        )}
)
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
    @Column(name = "id", updatable = false)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "user_name")
    private String userName;

    // go for ManyToOne when possible = jpa specification -
    // (readable, easy to understand)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "subscription_id",
            referencedColumnName = "id"
    )
    private Subscription subscription;

    @OneToMany(mappedBy = "profile")
    private List<Playlist> playlists;
}
