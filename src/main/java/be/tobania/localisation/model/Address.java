package be.tobania.localisation.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class Address {

	private String region;
	private Double longitude;
	private Double latitude;
	private String info;

}
