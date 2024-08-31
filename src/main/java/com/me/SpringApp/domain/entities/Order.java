package com.me.SpringApp.domain.entities;

import com.me.SpringApp.domain.entities.embedded.OrderComposeId;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_order")
public class Order {
	
	@EmbeddedId
	private OrderComposeId id;

	@Column
	private String orderInfo;

	// get all the fields from address, and include them into this order entity
	@Column
	@Embedded
	private Address address;
}
