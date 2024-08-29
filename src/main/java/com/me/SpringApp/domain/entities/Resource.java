package com.me.SpringApp.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "resource")
public class Resource {

	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	private Long id;

	@Column
	private String name;

	@Column
	private int size;

	@Column
	private String url;
}
