package com.insimea.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Kullanıcının temel bilgilerini saklayan User tablosuyla bire bir ilişki.
     * Bu tarafta foreign key (user_id) tutulacak.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    /**
     * Kullanıcının kendisi hakkında yazdığı kısa tanıtım/metin.
     */
    @Column(name = "bio", length = 1000)
    private String bio;

    /**
     * Kullanıcının cinsiyeti (örn: MALE, FEMALE, OTHER). İsteğe bağlı enum kullanabilirsiniz.
     */
    @Column(name = "gender")
    private String gender;

    /**
     * Kullanıcının yaşı ya da doğum tarihi. Burada yaş int olarak tutulabilir.
     * Daha sağlam tasarım için doğum tarihi (LocalDate) de tutabilirsiniz.
     */
    @Column(name = "age")
    private Integer age;

    /**
     * Konum bilgisi (şehir, ülke vb.). Örneğin “Istanbul, Turkey”.
     */
    @Column(name = "location")
    private String location;

    /**
     * Profil fotoğrafının URL’si (LinkedIn’den gelen veya kullanıcı yükledi ise).
     */
    @Column(name = "photo_url")
    private String photoUrl;

    /**
     * Kullanıcının tercihleri (örn: aradığı yaş aralığı, cinsiyet tercihi vb.).
     * İsterseniz farklı bir tablo / entity açıp “Preference” entity’si tanımlayabilirsiniz.
     * Burada basit tutmak adına sade bir JSON/Metin olarak saklanabilir.
     */
    @Column(name = "preferences", length = 2000)
    private String preferences;
}
