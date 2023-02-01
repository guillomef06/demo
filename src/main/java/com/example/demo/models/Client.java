package com.example.demo.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity(name = "Client")
@Table(name = "client",
uniqueConstraints = {
        @UniqueConstraint(name = "client_email_unique", columnNames = "email"),
        @UniqueConstraint(name = "client_nickName_unique", columnNames = "nick_name")
})
public class Client {

    /* Using a sequence for postgresql, if we leave the generationType on AUTO Hibernate
    will guess the generationType for us depending on the database we use */
    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    private Long id;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Pix> pixs;

    @Column(
            name = "nick_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String nickName;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "bio",
            columnDefinition = "TEXT"
    )
    private String bio;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    private LocalDate dob;

    @Transient
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;
    private Client(){}

    public Client(String nickName, String email, String password,String bio, LocalDate dob) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.dob = dob;
        this.role = Role.USER;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Pix> getPixs() {
        return pixs;
    }

    public void setPixs(List<Pix> pixs) {
        this.pixs = pixs;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", dob=" + dob +
                ", age=" + getAge() +
                '}';
    }
}
