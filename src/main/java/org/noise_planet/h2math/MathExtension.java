package org.noise_planet.h2math;

import org.h2gis.functions.factory.H2GISFunctions;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Declaration of math functions
 */
public class MathExtension {

    /**
     * Register math functions
     * @param connection SQL Connection not closed
     */
    public static void load(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        H2GISFunctions.registerFunction(st, new Median(), "");
    }
}
