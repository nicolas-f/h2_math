package org.noise_planet.h2math_osgi;

import org.h2gis.api.Function;
import org.noise_planet.h2math.Median;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * As H2 function need to be in fragment bundle. This activator need to be placed in another package.
 * @author Nicolas Fortin
 */
public class Activator implements BundleActivator {
    @Override
    public void start(BundleContext ctx) throws Exception {
        reg(ctx, new Median());
    }

    private void reg(BundleContext ctx, Function func) {
        ctx.registerService(Function.class, func, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }
}
