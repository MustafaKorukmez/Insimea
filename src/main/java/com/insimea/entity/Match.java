package com.insimea.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches",
        uniqueConstraints = {
                // Aynı iki kullanıcı çifti birden fazla eşleşme oluşturamasın:
                @UniqueConstraint(columnNames = {"user1_id", "user2_id"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Eşleşmedeki birinci kullanıcı (genellikle ID’si küçük olan olabilir, ama iş mantığınızda “her iki taraf için de
     * hem user1 hem user2 bağımsız ele alınmalı” diyebilirsiniz).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;

    /**
     * Eşleşmedeki ikinci kullanıcı.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;

    /**
     * Eşleşmenin gerçekleştiği zaman. Yani ikinci taraf da karşılıklı like yaptığında set edilir.
     */
    @Column(name = "matched_at", nullable = false)
    private LocalDateTime matchedAt;

    /**
     * Eşleşme aktif mi? (Örneğin kullanıcı sohbeti silmiş veya bloklamışsa inaktif hale getirmek için).
     */
    @Column(name = "active", nullable = false)
    private Boolean active;
}
