package com.spotifyclone.fraud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FraudCheckHistory {
    @Id
    @SequenceGenerator(
            name = "fraud_id_sequence",
            sequenceName = "fraud_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "profile_id", unique = true)
    private Long profileId;
    @Column(name = "is_fraudster")
    private Boolean isFraudster;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
