/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hcmou.repository;

import com.hcmou.pojo.Score;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kiet
 */

public interface ScoreRepository {
    List<Score> getScores(Map<String, String> params);
}
