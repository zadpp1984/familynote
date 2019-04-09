package com.cay.familynote.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "FnUser")
@Getter
@Setter
@ToString
public class FnUser {

    @Id
    Long userid;

    @Column
    String username;

    @Column
    String nickname;


}
