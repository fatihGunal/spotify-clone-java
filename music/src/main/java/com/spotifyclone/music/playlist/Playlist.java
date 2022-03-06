package com.spotifyclone.music.playlist;

import com.spotifyclone.music.profiles.Profile;
import com.spotifyclone.music.song.Song;
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
public class Playlist {
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
    @Column(name = "name")
    private Integer name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "profile_id",
            referencedColumnName = "id"
    )
    private Profile profile;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "song_id",
            referencedColumnName = "id"
    )
    private Song song;

    // no @ManyToMany for this association table,
    // reason: needed extra attribute 'name'
    // TODO: check if it's possible
}
