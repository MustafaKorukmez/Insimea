package com.insimea.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hangi kullanıcıya ait tercih bilgileri.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    /**
     * Kullanıcının görmek istediği en düşük yaş.
     */
    @Column(name = "min_age")
    private Integer minAge;

    /**
     * Kullanıcının görmek istediği en yüksek yaş.
     */
    @Column(name = "max_age")
    private Integer maxAge;

    /**
     * Kullanıcının tercih ettiği cinsiyet (örn: FEMALE, MALE, ANY).
     */
    @Column(name = "preferred_gender")
    private String preferredGender;

    /**
     * Maksimum mesafe (km) tercihi. (örn: en fazla 50 km).
     */
    @Column(name = "max_distance_km")
    private Integer maxDistanceKm;

    // Daha farklı tercihler de eklenebilir (hobi, eğitim seviyesi vb.).
}
