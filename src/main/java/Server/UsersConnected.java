/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vinic
 */
public class UsersConnected {
    private final Set<UserConnected> users;
    private final ReentrantLock mutex;
    public final DefaultTableModel usersTableModel;

    public UsersConnected(DefaultTableModel usersTableModel) {
       this.users = new HashSet<>();
       this.mutex = new ReentrantLock();
       this.usersTableModel = usersTableModel;
    }

    public void addUser( UserConnected user) {
        try {
            mutex.lock();
            users.add(user);
        } finally {
            updateTableModel();
            mutex.unlock();
        }
    }

    public void removeUser(UserConnected user) {
        try {
            mutex.lock();
            users.remove(user);
        } finally {
            updateTableModel();
            mutex.unlock();
        }
    }
    
     private void updateTableModel() {
         SwingUtilities.invokeLater(() -> {
            usersTableModel.setRowCount(0);
            for (UserConnected user : users) {
                usersTableModel.addRow(new Object[]{user.getIp(), user.getEmail()});
            }
         });   
    }
}
