package com.example.demo.Entity;

import java.time.LocalDateTime;

import com.example.demo.Enum.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")



public class User {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private int userId;

	    @Column(name = "user_name")
	    private String userName;

	    private String email;
	    private String password;
	    private String phone;
	    private String address;

	    @Enumerated(EnumType.STRING)
	    private UserRole role;

	    private LocalDateTime createdAt;

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		public User(int userId, String userName, String email, String password, String phone, String address,
				UserRole role, LocalDateTime createdAt) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.email = email;
			this.password = password;
			this.phone = phone;
			this.address = address;
			this.role = role;
			this.createdAt = createdAt;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public UserRole getRole() {
			return role;
		}

		public void setRole(UserRole role) {
			this.role = role;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
					+ ", phone=" + phone + ", address=" + address + ", role=" + role + ", createdAt=" + createdAt + "]";
		}
	
	
	
}
