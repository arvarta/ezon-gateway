package org.ezon.redis;

import java.time.LocalDateTime;

public class User {
	private Long id;
	private String email;
	private String password;
	private String name;
	private String tel;
	private String status;
	private LocalDateTime agreeAge;
	private LocalDateTime agreeTerms;
	private LocalDateTime agreePrivacy;
	private LocalDateTime agreeMarketing;
	private LocalDateTime createdAt;
	private LocalDateTime deletedAt;
	private LocalDateTime lastLoginAt;
	private String profile;
	private String companyName;
	private String role;
	private String businessNum;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getAgreeAge() {
		return agreeAge;
	}
	public void setAgreeAge(LocalDateTime agreeAge) {
		this.agreeAge = agreeAge;
	}
	public LocalDateTime getAgreeTerms() {
		return agreeTerms;
	}
	public void setAgreeTerms(LocalDateTime agreeTerms) {
		this.agreeTerms = agreeTerms;
	}
	public LocalDateTime getAgreePrivacy() {
		return agreePrivacy;
	}
	public void setAgreePrivacy(LocalDateTime agreePrivacy) {
		this.agreePrivacy = agreePrivacy;
	}
	public LocalDateTime getAgreeMarketing() {
		return agreeMarketing;
	}
	public void setAgreeMarketing(LocalDateTime agreeMarketing) {
		this.agreeMarketing = agreeMarketing;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
	public LocalDateTime getLastLoginAt() {
		return lastLoginAt;
	}
	public void setLastLoginAt(LocalDateTime lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getBusinessNum() {
		return businessNum;
	}
	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}
	
	
}
