/*
 * Copyright 2018-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.openstacknode.web;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.onosproject.codec.CodecService;
import org.onosproject.net.behaviour.ControllerInfo;
import org.onosproject.openstacknode.api.OpenstackAuth;
import org.onosproject.openstacknode.api.OpenstackNode;
import org.onosproject.openstacknode.api.OpenstackPhyInterface;
import org.onosproject.openstacknode.api.OpenstackSshAuth;
import org.onosproject.openstacknode.codec.OpenstackAuthCodec;
import org.onosproject.openstacknode.codec.OpenstackControllerCodec;
import org.onosproject.openstacknode.codec.OpenstackNodeCodec;
import org.onosproject.openstacknode.codec.OpenstackPhyInterfaceCodec;
import org.onosproject.openstacknode.codec.OpenstackSshAuthCodec;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Implementation of the JSON codec brokering service for OpenstackNode.
 */
@Component(immediate = true)
public class OpenstackNodeCodecRegister {

    private final org.slf4j.Logger log = getLogger(getClass());

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected CodecService codecService;

    @Activate
    protected void activate() {
        codecService.registerCodec(OpenstackNode.class, new OpenstackNodeCodec());
        codecService.registerCodec(OpenstackAuth.class, new OpenstackAuthCodec());
        codecService.registerCodec(OpenstackPhyInterface.class, new OpenstackPhyInterfaceCodec());
        codecService.registerCodec(ControllerInfo.class, new OpenstackControllerCodec());
        codecService.registerCodec(OpenstackSshAuth.class, new OpenstackSshAuthCodec());

        log.info("Started");
    }

    @Deactivate
    protected void deactivate() {
        codecService.unregisterCodec(OpenstackNode.class);
        codecService.unregisterCodec(OpenstackAuth.class);
        codecService.unregisterCodec(OpenstackPhyInterface.class);
        codecService.unregisterCodec(ControllerInfo.class);
        codecService.unregisterCodec(OpenstackSshAuth.class);

        log.info("Stopped");
    }
}
