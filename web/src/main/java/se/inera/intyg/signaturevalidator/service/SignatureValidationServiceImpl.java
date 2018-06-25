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
package se.inera.intyg.signaturevalidator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.inera.intyg.infra.xmldsig.model.CertificateInfo;
import se.inera.intyg.infra.xmldsig.model.ValidationResponse;
import se.inera.intyg.infra.xmldsig.service.XMLDSigService;

import java.util.Map;

@Service
public class SignatureValidationServiceImpl implements SignatureValidationService {

    @Autowired
    private XMLDSigService xmldSigService;

    @Override
    public Map<String, ValidationResponse> validateSignature(String xml) {
        Map<String, ValidationResponse> map = xmldSigService.validateSignatureValidityMulti(xml, true);
        return map;
    }

    @Override
    public Map<String, CertificateInfo> extractCertificateInfo(String xml) {
        return xmldSigService.extractCertificateInfo(xml);
    }
}
