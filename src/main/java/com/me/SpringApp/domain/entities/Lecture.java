package com.me.SpringApp.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "lecture")
public class Lecture extends BaseEntity {

	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	private Long id;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;

	@OneToOne
	@JoinColumn(name = "resource_id")
	private Resource resource;
}
