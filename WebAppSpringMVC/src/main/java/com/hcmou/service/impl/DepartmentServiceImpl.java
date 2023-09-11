/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.Department;
import com.hcmou.repository.DepartmentRepository;
import com.hcmou.service.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{
    
    @Autowired
    private DepartmentRepository departRepo;

    @Override
    public List<Department> getDepartments() {
        return this.departRepo.getDepartments();
    }   

    @Override
    public boolean addOrUpdateDepartment(Department department) {
        return this.departRepo.addOrUpdateDepartment(department);
    }

    @Override
    public boolean deleteDepartment(int departmentId) {
        return this.departRepo.deleteDepartment(departmentId);
    }
}
