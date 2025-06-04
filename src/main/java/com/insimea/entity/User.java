package com.insimea.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "linkedin_id"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * LinkedIn tarafından verilen benzersiz kullanıcı ID'si.
     * Bir kullanıcı en fazla bir kez kayıt olabileceği için uniqueConstraints ile garanti ediyoruz.
     */
    @Column(name = "linkedin_id", nullable = false, updatable = false)
    private String linkedinId;

    /**
     * Kullanıcının LinkedIn'den çekilen email adresi.
     * Aynı email ile ikinci kez kayıt olunamasın diye unique constraint ekliyoruz.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Kullanıcı adı (LinkedIn profilinden geliyor olabilir).
     * Örn: "Mustafa Körükmez"
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * Kullanıcının kayıt olduğu tarih.
     * Örneğin, LinkedIn OAuth login başarılı olduktan sonra set edilebilir.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Profil ile 1-1 ilişkiliyoruz. Bir kullanıcıya ait profil (Profile) olacaktır.
     * FetchType.LAZY tercih edilir, gerektiğinde veriyi çağıracağız.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;

    /**
     * Kullanıcının yaptığı swipe (like / dislike) kayıtlarını tutmak için.
     * Bir kullanıcı birden çok kullanıcıyı beğenebilir/ayrım yapabilir => OneToMany
     */
    @OneToMany(mappedBy = "swiper", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Swipe> swipes = new HashSet<>();

    /**
     * Kullanıcının eşleşmeleri. Hem user1 hem user2 olarak eşleşme içinde yer alabilir.
     * Bu örnekte, Match entity'si içinde user1 ve user2 tarafı ManyToOne olarak tanımlı.
     * Buradaki mappedBy için Match sınıfındaki ilişki alanlarına bakacağız.
     */
    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Match> matchesAsUser1 = new HashSet<>();

    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Match> matchesAsUser2 = new HashSet<>();

    /**
     * Kullanıcının gönderdiği ve aldığı mesajları saklamaya gerek yok,
     * çünkü Message entity’si içinde sender ve recipient ManyToOne ile User’a bağlı olacak.
     * Burada karşılıklı ilişki eklememiz zorunlu değil. İhtiyaç doğrultusunda eklenebilir.
     */

    // (Opsiyonel) Kullanıcının rollerini tutmak isterseniz:
    // @ElementCollection(fetch = FetchType.EAGER)
    // @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    // @Column(name = "role")
    // private Set<String> roles = new HashSet<>();
}
