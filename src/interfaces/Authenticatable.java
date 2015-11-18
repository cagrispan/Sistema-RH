/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author TUNTS
 */
public interface Authenticatable {
    public boolean authenticate(String systemName, String user, String password);
}
