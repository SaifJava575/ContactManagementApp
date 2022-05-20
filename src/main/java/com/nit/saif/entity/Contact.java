package com.nit.saif.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="contact_table")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="contact_id")
	private Integer cid;
	
	@NotBlank(message = "Name can not be empty")
	@Size(min = 3 ,max = 20,message = "Name should be between 3 to 20 characters")
	@Column(name="contact_name")
	private String name;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Please provide a valid email")
	@Column(name="contact_email")
	private String email;
	
	@NotBlank(message = "Mobile number can not be empty")
	@Column(name="contact_ph_no")
	private String phno;
	
	@Column(name="contact_active_switch")
	private String activeSw;
}
