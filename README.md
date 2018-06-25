# Intyg XML Digital Signature validator

PoC for validating XML signatures. Accepts POST with Content-Type application/xml at /api/validator and will return:

    {"result":
       [
           {
               "intygsId":"9f02dd2f-f57c-4a73-8190-2fe602cd6e27",
               "validationResponse":{
                   "signatureValid":"OK",
                   "referencesValid":"OK",
                   "valid":true
               },
               "certificateInfo":{
                   "subject":"O=Callista, L=Gothenburg, ST=VG, C=SE",
                   "issuer":"O=Callista, L=Gothenburg, ST=VG, C=SE",
                   "alg":"SHA256withRSA",
                   "certificateType":"X.509"
               }
           }
       ]
    }

**Important:** Must run with:

    -Djavax.xml.transform.TransformerFactory=com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl
    
If Saxon somehow gets loaded as XSLT transformer, the reference validation won't work due to how Saxon outputs namespaces.

### Implementation
The signaturevalidator is just a wrapper around our xmldsig library (see https://github.com/sklintyg/infra).

### Building and running

This application is built using gradle.

Run using the gradle wrapper and gretty:

    ./gradlew build appRun
