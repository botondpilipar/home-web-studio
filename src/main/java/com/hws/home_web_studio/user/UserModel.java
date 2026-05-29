package com.hws.home_web_studio.user;

import java.util.Set;

import com.hws.home_web_studio.application.AlreadyInstalledException;
import com.hws.home_web_studio.application.ApplicationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Getter
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    @Getter
    private String userName;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private String role;

    @Column(columnDefinition = "BYTEA", nullable = true)
    private byte[] profilePicture;

    @Column(columnDefinition = "BYTEA", nullable = true)
    private byte[] backgroundImage;

    @Column(nullable = false, length = 64)
    private String passwordHash;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "installed_applications",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "application_id")
    )
    @Getter
    Set<ApplicationModel> installed;

    public void install(ApplicationModel app) throws AlreadyInstalledException {
        if(installed.contains(app)) {
            throw new AlreadyInstalledException(app.getName(), userName);
        }
        installed.add(app);
    }
}
