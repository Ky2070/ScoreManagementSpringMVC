/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hcmou.repository;

import com.hcmou.pojo.Class;
import java.util.List;
/**
 *
 * @author nguye
 */
public interface ClassRepository {
    List<Class> getClasses();
    List<Class> getClassesByMajorId(int majorId);
    boolean deleteClass(int classId);
    boolean addOrUpdateClass(Class classes);
}
