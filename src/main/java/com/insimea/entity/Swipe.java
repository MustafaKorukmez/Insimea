package com.insimea.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "swipes",
        uniqueConstraints = {
                // Aynı kullanıcı aynı kullanıcıyı birden fazla kez swipe edemesin diye:
                @UniqueConstraint(columnNames = {"swiper_id", "swiped_id"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Swipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Swipe işlemini yapan kullanıcı (A).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "swiper_id", nullable = false)
    private User swiper;

    /**
     * Swipe işleminin hedefi olan kullanıcı (B).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "swiped_id", nullable = false)
    private User swiped;

    /**
     * Beğeni mi (true) yoksa reddetme mi (false)?
     */
    @Column(name = "is_like", nullable = false)
    private Boolean isLike;

    /**
     * Hangi tarihte swipe işlemi gerçekleştirildi.
     */
    @Column(name = "swiped_at", nullable = false)
    private LocalDateTime swipedAt;
}
