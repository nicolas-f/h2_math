package org.noise_planet.h2math;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.h2gis.functions.factory.H2GISDBFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test functions
 */
public class MedianTest {


    private static Connection connection;
    private Statement st;

    @BeforeClass
    public static void tearUp() throws Exception {
        // Keep a connection alive to not close the DataBase on each unit test
        connection = H2GISDBFactory.createSpatialDataBase(MedianTest.class.getSimpleName());
        MathExtension.load(connection);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        connection.close();
    }

    @Before
    public void setUpStatement() throws Exception {
        st = connection.createStatement();
    }

    @After
    public void tearDownStatement() throws Exception {
        st.close();
    }

    @Test
    public void testSimple() throws SQLException {
        st.execute("DROP TABLE IF EXISTS TESTMEDIAN");
        st.execute("CREATE TABLE TESTMEDIAN(id serial, value double)");
        st.execute("INSERT INTO TESTMEDIAN(value) VALUES (3), (13), (7), (5), (21), (23), (39), (23), (40), (23), (14)," +
                " (12), (56), (23), (29)");
        try(ResultSet rs = st.executeQuery("SELECT MEDIAN(value) med FROM TESTMEDIAN")) {
            assertTrue(rs.next());
            assertEquals(23, rs.getDouble("med"), 1e-16);
        }
    }
}