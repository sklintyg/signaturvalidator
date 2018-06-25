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
package se.inera.intyg.signaturevalidator.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.inera.intyg.infra.xmldsig.model.CertificateInfo;
import se.inera.intyg.infra.xmldsig.model.ValidationResponse;
import se.inera.intyg.signaturevalidator.service.SignatureValidationService;
import se.inera.intyg.signaturevalidator.web.controller.dto.SignatureValidationResponse;
import se.inera.intyg.signaturevalidator.web.controller.dto.ValidationResponseWithSignature;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/validation")
public class SignatureValidationController {

    @Autowired
    private SignatureValidationService signatureValidationService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignatureValidationResponse> validateSignature(@RequestBody String xml) {
        Map<String, ValidationResponse> validationMap = signatureValidationService.validateSignature(xml);
        Map<String, CertificateInfo> certificateMap = signatureValidationService.extractCertificateInfo(xml);

        // Assemble a final response per extracted intygs-id.
        return ResponseEntity.ok(new SignatureValidationResponse(validationMap.keySet().stream()
                .map(intygsId -> ValidationResponseWithSignature.ValidationResponseWithSignatureBuilder.aValidationResponseWithSignature()
                        .withIntygsId(intygsId)
                        .withCertificateInfo(certificateMap.get(intygsId))
                        .withValidationResponse(validationMap.get(intygsId)).build())
                .collect(Collectors.toList())));

    }
}
