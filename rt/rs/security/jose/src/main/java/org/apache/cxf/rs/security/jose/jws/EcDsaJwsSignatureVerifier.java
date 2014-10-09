/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.rs.security.jose.jws;

import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;

import org.apache.cxf.rs.security.jose.jwa.Algorithm;

public class EcDsaJwsSignatureVerifier extends PublicKeyJwsSignatureVerifier {
    public EcDsaJwsSignatureVerifier(PublicKey key) {
        this(key, null);
    }
    public EcDsaJwsSignatureVerifier(PublicKey key, String supportedAlgo) {
        this(key, null, supportedAlgo);
    }
    public EcDsaJwsSignatureVerifier(PublicKey key, AlgorithmParameterSpec spec, String supportedAlgo) {
        super(key, spec, supportedAlgo);
    }
    @Override
    protected boolean isValidAlgorithmFamily(String algo) {
        return Algorithm.isEcDsaSign(algo);
    }
}