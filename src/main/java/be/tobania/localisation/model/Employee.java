package be.tobania.localisation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TOBANIA_EMPLOYEE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "FIRST_NAME")
    private String fistName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "TECHNOLOGY")
    private String technology;

    @Column(name = "CLIENT")
    private String client;

    @Column(name = "PLACE_OF_PROJECT")
    private String placeOfProject;

    @Column(name = "REGION")
    private String region;

}