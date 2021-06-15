package ru.shur.instazoo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import ru.shur.instazoo.entity.enums.ERole;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) /* колонка  не может быть пустая */
    private String name;

    @Column(unique = true, updatable = false) /* уникальный, необновляемый */
    private String username;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "text") /* увеличивает количество символов */
    private String bio;

    @Column(length = 3000) /* пароль будет закодирован во множество символов */
    private String password;

    @ElementCollection(targetClass = ERole.class)
    /* создаем отдельную таблицу в которой лежат роль юзера и его Id */
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roles = new HashSet<>();


    /* CascadeType.ALL - при удалении пользователя удаляются все его посты
    *  FetchType.LAZY - подгружаются только нужные данные, а не все посты
    *  orphanRemoval = true - при удалении поста, пост удаляется из базы */
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<Post> posts = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User() {
    }

    @PrePersist /* задает значение до того как сделана запись в базу данных */
    private void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
