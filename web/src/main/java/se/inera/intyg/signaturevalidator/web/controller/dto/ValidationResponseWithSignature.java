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
package se.inera.intyg.signaturevalidator.web.controller.dto;

import se.inera.intyg.infra.xmldsig.model.CertificateInfo;
import se.inera.intyg.infra.xmldsig.model.ValidationResponse;

public class ValidationResponseWithSignature {
    private String intygsId;
    private ValidationResponse validationResponse;
    private CertificateInfo certificateInfo;

    public String getIntygsId() {
        return intygsId;
    }

    public void setIntygsId(String intygsId) {
        this.intygsId = intygsId;
    }

    public ValidationResponse getValidationResponse() {
        return validationResponse;
    }

    public void setValidationResponse(ValidationResponse validationResponse) {
        this.validationResponse = validationResponse;
    }

    public CertificateInfo getCertificateInfo() {
        return certificateInfo;
    }

    public void setCertificateInfo(CertificateInfo certificateInfo) {
        this.certificateInfo = certificateInfo;
    }


    public static final class ValidationResponseWithSignatureBuilder {
        private String intygsId;
        private ValidationResponse validationResponse;
        private CertificateInfo certificateInfo;

        private ValidationResponseWithSignatureBuilder() {
        }

        public static ValidationResponseWithSignatureBuilder aValidationResponseWithSignature() {
            return new ValidationResponseWithSignatureBuilder();
        }

        public ValidationResponseWithSignatureBuilder withIntygsId(String intygsId) {
            this.intygsId = intygsId;
            return this;
        }

        public ValidationResponseWithSignatureBuilder withValidationResponse(ValidationResponse validationResponse) {
            this.validationResponse = validationResponse;
            return this;
        }

        public ValidationResponseWithSignatureBuilder withCertificateInfo(CertificateInfo certificateInfo) {
            this.certificateInfo = certificateInfo;
            return this;
        }

        public ValidationResponseWithSignature build() {
            ValidationResponseWithSignature validationResponseWithSignature = new ValidationResponseWithSignature();
            validationResponseWithSignature.setIntygsId(intygsId);
            validationResponseWithSignature.setValidationResponse(validationResponse);
            validationResponseWithSignature.setCertificateInfo(certificateInfo);
            return validationResponseWithSignature;
        }
    }
}
