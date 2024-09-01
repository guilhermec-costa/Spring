package com.me.SpringApp.domain.Author.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.me.SpringApp.domain.entities.BaseEntity;
import com.me.SpringApp.domain.entities.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "author")
@Entity
@NamedQuery(name = "AuthorEntity.findByNamedQuery", query = "select a from AuthorEntity a where a.age >= :age")
public class AuthorEntity extends BaseEntity {

	@Id
	@SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence")
	private Long id;

	@Column(name = "first_name", length = 35)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(unique = true, nullable = false)
	private String email;

	@Column
	private int age;

	@ManyToMany(mappedBy = "authors")
	private List<Course> courses;
}
