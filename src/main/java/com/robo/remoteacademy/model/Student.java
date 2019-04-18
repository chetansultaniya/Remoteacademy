package com.robo.remoteacademy.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student")
public class Student extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "student_id")
	private String studentId;
	
	@NotNull
	@Column(name = "password")
	private String password;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "mobile")
	private String mobile;
	
	@NotNull
	@Column(name = "email")
	private String email;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Column(name = "class")
	private String studentClass;
	
	@Column(name = "course")
	private String course;
	
	@Column(name = "institute")
	private String institute;
	
	@Column(name = "image")
	private String image;

	
	
	@OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL)
	private Set<SubjectJoiners> subjectJoiners;
	
	
	public Student() {
		
	}

	
	
	
	public Student(String studentId, @NotNull String password, @NotNull String name, @NotNull String mobile,
			@NotNull String email, String dob, String gender, String country, String state, String city, String address,
			String pincode, String studentClass, String course, String institute, String image,
			Set<SubjectJoiners> subjectJoiners) {
		super();
		this.studentId = studentId;
		this.password = password;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.dob = dob;
		this.gender = gender;
		this.country = country;
		this.state = state;
		this.city = city;
		this.address = address;
		this.pincode = pincode;
		this.studentClass = studentClass;
		this.course = course;
		this.institute = institute;
		this.image = image;
		this.subjectJoiners = subjectJoiners;
	}




	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<SubjectJoiners> getSubjectJoiners() {
		return subjectJoiners;
	}

	public void setSubjectJoiners(Set<SubjectJoiners> subjectJoiners) {
		this.subjectJoiners = subjectJoiners;
	}
	
	
	
}
