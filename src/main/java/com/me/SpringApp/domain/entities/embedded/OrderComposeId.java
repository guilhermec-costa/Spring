package com.me.SpringApp.domain.entities.embedded;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderComposeId implements Serializable {
	
	private String username;

	private LocalDateTime OrderDate;
}
