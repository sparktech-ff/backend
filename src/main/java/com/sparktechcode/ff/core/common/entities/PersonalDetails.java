package com.sparktechcode.ff.core.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class PersonalDetails {

    @NotEmpty(message = "First name required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Formula("CONCAT(COALESCE(first_name, ''), ' ', COALESCE(last_name, ''))")
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private Language language;
}
