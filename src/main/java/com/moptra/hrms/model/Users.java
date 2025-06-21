package com.moptra.hrms.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "users")
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    private String designation;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "mobile_number")
    private String mobileNumber;

    private String name;

    @Column(nullable = false)
    private String password;

    @Column(name = "project_manager")
    private String projectManager;

    private String projects;

    @Column(name = "role_name")
    private String roleName;

    public Users() {
    }

    public Users(Long id, String designation, String email, String employeeId, LocalDate joiningDate, String mobileNumber, String name, String password, String projectManager, String projects, String roleName) {
        this.id = id;
        this.designation = designation;
        this.email = email;
        this.employeeId = employeeId;
        this.joiningDate = joiningDate;
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.password = password;
        this.projectManager = projectManager;
        this.projects = projects;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", email='" + email + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", joiningDate=" + joiningDate +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", projects='" + projects + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
