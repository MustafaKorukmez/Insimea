package com.insimea.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hangi eşleşme (match) üzerinden bu mesaj gönderildi.
     * Bir eşleşme birden çok mesaja sahip olabilir (OneToMany).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    /**
     * Mesajı gönderen kullanıcı.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    /**
     * Mesajın içeriği (text). İhtiyaç olursa uzunluğu uzatılabilir.
     */
    @Column(name = "content", length = 2000, nullable = false)
    private String content;

    /**
     * Mesajın gönderildiği tarih ve saat.
     */
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    /**
     * Mesaj detaylarına (görsel, emoji vb.) ihtiyacınız varsa yeni alanlar ekleyebilirsiniz.
     */
}
