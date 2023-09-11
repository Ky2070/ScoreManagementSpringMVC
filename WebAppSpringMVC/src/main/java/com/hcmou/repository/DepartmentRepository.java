/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hcmou.repository;

import com.hcmou.pojo.Department;
import java.util.List;

/**
 *
 * @author nguye
 */
public interface DepartmentRepository {
    List<Department> getDepartments();
    boolean addOrUpdateDepartment(Department department);
    boolean deleteDepartment(int departmentId);
}
