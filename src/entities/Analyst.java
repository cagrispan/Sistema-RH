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
public class Analyst extends Employee{
    private int office = 6;

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }
    
    public void add() {
        this.office += Integer.parseInt(super.getLevel());
        EmployeeDAO.add(this);
    }
}
