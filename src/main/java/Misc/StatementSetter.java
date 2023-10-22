/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Misc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author vinic
 */
@FunctionalInterface
public interface StatementSetter {
    void setParameters(PreparedStatement statement) throws SQLException;
}
