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

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "TECHNOLOGY")
    private String technology;

    @Column(name = "CLIENT")
    private String client;

    @Column(name = "PLACE_OF_PROJECT")
    private String placeOfProject;

    @Column(name = "WORK_REGION")
    private String workRegion;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "HOME_REGION")
    private String homeRegion;

}