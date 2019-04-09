package com.cay.familynote.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "FnLoginUser", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Table(name = "FnLoginUser", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Getter
@Setter
@ToString
public class FnLoginUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    Long userid;

    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "nickname")
    String nickname;

}
