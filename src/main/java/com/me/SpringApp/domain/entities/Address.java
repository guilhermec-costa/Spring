package com.me.SpringApp.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

	@Column
	private String streetName;

	@Column
	private String houseNumber;

	@Column
	private String zipCode;
}
