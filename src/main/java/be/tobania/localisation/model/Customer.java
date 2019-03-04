package be.tobania.localisation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LATITUDE")
	private Double latitude;

	@Column(name = "LONGITUDE")
	private Double longitude;
	


}