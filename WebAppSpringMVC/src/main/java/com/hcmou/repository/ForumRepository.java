/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hcmou.repository;

import com.hcmou.pojo.Forum;
import java.util.List;

/**
 *
 * @author nguye
 */
public interface ForumRepository {
    List<Forum> getForums();
    List<Forum> getForumBySubjectTeacher(int subjectTeacherId);
    boolean addForum(Forum forum);
    boolean deleteForum(int forumId);
}
