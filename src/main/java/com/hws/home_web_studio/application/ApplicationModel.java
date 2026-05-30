package com.hws.home_web_studio.application;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import com.hws.home_web_studio.user.UserModel;

@Entity
@Table(name = "applications")
public class ApplicationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter @Setter
    private String version;

    @Column(columnDefinition = "BYTEA", nullable = false)
    private byte[] icon;

    @ManyToMany(mappedBy = "installed", fetch = FetchType.LAZY)
    @Getter @Setter
    private Set<UserModel> users;

    public void install(UserModel newUser) throws AlreadyInstalledException {
        if(users.contains(newUser)) {
            throw new AlreadyInstalledException(name, newUser.getUserName());
        }
        users.add(newUser);
    }
}
