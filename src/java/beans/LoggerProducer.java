/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 *
 * @author araki
 */
@Dependent
public class LoggerProducer {
    @Inject
    InjectionPoint point;
    @Produces
    public Logger getLogger(){
        Logger logger = Logger.getLogger(point.getMember().getDeclaringClass().getName());
        logger.setLevel(Level.ALL);
        return logger;
    }
}
