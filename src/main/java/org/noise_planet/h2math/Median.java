package org.noise_planet.h2math;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.h2.api.Aggregate;
import org.h2.value.Value;
import org.h2gis.api.AbstractFunction;

import java.sql.Connection;
import java.sql.SQLException;

public class Median extends AbstractFunction implements Aggregate {

    DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();

    public Median() {
        addProperty(PROP_REMARKS, "Return median value of numeric column.");
    }

    @Override
    public void init(Connection conn) throws SQLException {
        descriptiveStatistics = new DescriptiveStatistics();
    }

    @Override
    public int getInternalType(int[] inputTypes) throws SQLException {
        return Value.DOUBLE;
    }

    @Override
    public void add(Object value) throws SQLException {
        if(value instanceof Number) {
            descriptiveStatistics.addValue(((Number) value).doubleValue());
        }
    }

    @Override
    public Object getResult() throws SQLException {
        return descriptiveStatistics.getPercentile(50);
    }
}
