/*
 * Copyright (C) 2018 Inera AB (http://www.inera.se)
 *
 * This file is part of sklintyg (https://github.com/sklintyg).
 *
 * sklintyg is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * sklintyg is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.inera.intyg.signaturevalidator.web.responder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import se.riv.clinicalprocess.healthcond.monitoring.rivtabp21.v1.InternalPingForConfigurationResponderInterface;
import se.riv.clinicalprocess.healthcond.monitoring.v1.ConfigurationType;
import se.riv.clinicalprocess.healthcond.monitoring.v1.InternalPingForConfigurationResponseType;
import se.riv.clinicalprocess.healthcond.monitoring.v1.InternalPingForConfigurationType;

import javax.annotation.PostConstruct;
import javax.jws.WebParam;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implements PingForConfiguration and returns various statuses about the health of the application.
 */
@Service
public class InternalPingForConfigurationResponderImpl implements InternalPingForConfigurationResponderInterface {
    private static final Logger LOG = LoggerFactory.getLogger(InternalPingForConfigurationResponderImpl.class);

    @Value("${project.version}")
    private String projectVersion;

    @Value("${buildNumber}")
    private String buildNumberString;

    @Value("${buildTime}")
    private String buildTimeString;

    @Override

    // CHECKSTYLE:OFF LineLength
    public InternalPingForConfigurationResponseType internalPingForConfiguration(
            @WebParam(partName = "LogicalAddress", name = "LogicalAddress", targetNamespace = "urn:riv:itintegration:registry:1",
                    header = true) String logicalAddress,
            @WebParam(partName = "parameters", name = "InternalPingForConfiguration",
                    targetNamespace = "urn:riv:clinicalprocess:healthcond:monitoring:InternalPingForConfigurationResponder:1") InternalPingForConfigurationType parameters) {
        // CHECKSTYLE:ON LineLength
        InternalPingForConfigurationResponseType response = new InternalPingForConfigurationResponseType();
        response.setPingDateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        LOG.info("Version String: " + projectVersion);
        response.setVersion(projectVersion);

        addConfiguration(response, "buildNumber", buildNumberString);
        addConfiguration(response, "buildTime", buildTimeString);
        return response;
    }

    private void addConfiguration(InternalPingForConfigurationResponseType response, String name, String value) {
        ConfigurationType conf = new ConfigurationType();
        conf.setName(name);
        conf.setValue(value);
        response.getConfiguration().add(conf);
    }

    @PostConstruct
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        LOG.info("InternalPingForConfiguration loaded");
    }
}
