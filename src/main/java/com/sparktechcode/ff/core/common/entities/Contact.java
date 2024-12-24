package com.sparktechcode.ff.core.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Contact {

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;
}
