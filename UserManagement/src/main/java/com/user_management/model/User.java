package com.user_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.user_management.constant.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = constant.users)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	@Id
	private String email;
	private String name;
	private String userPassword;
	private String role;

}
