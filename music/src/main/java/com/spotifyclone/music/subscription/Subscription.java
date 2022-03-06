package com.spotifyclone.music.subscription;

import com.spotifyclone.music.profiles.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subscription {
    @Id
    @SequenceGenerator(
            name = "subscription_id_sequence",
            sequenceName = "subscription_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subscription_id_sequence"
    )
    @Column(name = "id", updatable = false)
    private Integer id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "pricing", nullable = false)
    private BigDecimal pricing;

    @OneToMany(mappedBy = "subscription")
    private List<Profile> profiles;

    // Also in @Column "columnDefinition" changes the type
    //EXAMPLE: varchar (default) columnDefinition="TEXT" = VARCHAR -> TEXT
    // Not everything is covered.
}
