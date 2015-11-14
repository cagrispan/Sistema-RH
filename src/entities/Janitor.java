/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DAOs.EmployeeDAO;

/**
 *
 * @author TUNTS
 */
public class Janitor extends Employee{
    public float calcBonus(float salary) {
        return salary*0.5f;
    }
}
