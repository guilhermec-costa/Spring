package com.me.SpringApp.domain.entities;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Course extends BaseEntity {
	
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@ManyToMany
	@JoinTable(
		name = "courses_authors",
		joinColumns = {
			@JoinColumn(name = "course_id")
		},
		inverseJoinColumns = {
			@JoinColumn(name = "author_id")
		}
	)
	private List<Author> authors;

	@OneToMany(mappedBy = "course")
	private List<Section> sections;
}
