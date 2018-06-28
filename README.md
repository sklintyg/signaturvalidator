# Intyg XML Digital Signature validator

PoC for validating XML signatures. Accepts POST with Content-Type application/xml at /api/validation.

**Important:** Must run with:

    -Djavax.xml.transform.TransformerFactory=com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl
    
If Saxon somehow gets loaded as XSLT transformer, the reference validation won't work due to how Saxon outputs namespaces.

### Using the API

The PoC accepts a HTTP POST with Content-Type application/xml at _/api/validation_.

If you have the XML you want to validate in a file called "validate-me.xml" one can use the following _curl_ command to validate:

    > curl -X POST -H "Content-Type: application/xml" -d @validate-me.xml http://localhost:8595/api/validation
    
    {
      "result": [
        {
          "intygsId": "b107dc65-5868-4a9e-a523-781c8b99dec7",
          "validationResponse": {
            "signatureValid": "OK",
            "referencesValid": "OK",
            "valid": true
          },
          "certificateInfo": {
            "subject": "SERIALNUMBER=TSTNMT2321000156-1019, GIVENNAME=Ingrid, SURNAME=Andersson Larsson, CN=Ingrid Andersson Larsson, O=Nordic MedTest, L=Öster län, C=SE",
            "issuer": "CN=SITHS Type 1 CA v1 PP, O=Inera AB, C=SE",
            "alg": "SHA1withRSA",
            "certificateType": "X.509"
          }
        }
      ]
    }
    
### Implementation
The signaturevalidator is just a wrapper around our xmldsig library (see https://github.com/sklintyg/infra).

### Building and running

This application is built using gradle.

Run using the gradle wrapper and gretty:

    ./gradlew build appRun
    
Runs at port 8595 by default.
