package by.vitstep.organizer.model.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "org_user")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    private static final String SEQ_NAME = "org_user_id_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    Long id;

    // Данная колонка имеет тип READ_ONLY потому что её значение генерируется базой данных
    @Column(insertable = false, updatable = false)
    UUID uuid;
    @Column(unique = true)
    String login;
    String password;
    String name;
    @OneToOne(cascade = CascadeType.ALL,optional = false)
    Contacts contacts;
    LocalDate birthday;
    @ManyToMany(mappedBy = "user")
    List<Friend> friendList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Authority> authorities;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<FriendGroup> friendGroup;

    public void addFriendGroup(FriendGroup friendGroup) {
        if (this.friendGroup == null) {
            this.friendGroup = new ArrayList<>();
        }
        this.friendGroup.add(friendGroup);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
