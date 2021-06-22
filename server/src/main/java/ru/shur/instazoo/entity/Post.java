package ru.shur.instazoo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String caption;
    private String location;
    private Integer likes;

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> likedUsers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    /* FetchType.EAGER - когда загружаем пост, сразу загружаются его комменты */
    @OneToMany(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER,
            mappedBy = "post",
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @PrePersist /* задает значение до того как сделана запись в базу данных */
    private void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
