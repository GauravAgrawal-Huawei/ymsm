/*
 * Copyright 2016-present Open Networking Laboratory
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

package org.onosproject.yms.app.ymsm;

import org.onosproject.yms.app.ych.codecutils.YchException;
import org.onosproject.yms.ych.YangCompositeEncoding;
import org.onosproject.yms.ych.YangDataTreeCodec;
import org.onosproject.yms.ydt.YdtBuilder;
import org.onosproject.yms.ydt.YmsOperationType;

/**
 * Represents implementation of YANG SBI broker interfaces.
 */
public class RegisterDataTreeCodec implements YangDataTreeCodec {
    @Override
    public String encodeYdtToProtocolFormat(YdtBuilder ydtBuilder, YmsOperationType protocolOperation) {
        String errorInfo = "RegisterDataTreeCodec encodeYdtToProtocolFormat Called.";
        throw new YchException(errorInfo);
    }

    @Override
    public YangCompositeEncoding encodeYdtToCompositeProtocolFormat(YdtBuilder ydtBuilder,
                                                                    YmsOperationType protocolOperation) {
        String errorInfo = "RegisterDataTreeCodec encodeYdtToCompositeProtocolFormat Called.";
        throw new YchException(errorInfo);
    }

    @Override
    public YdtBuilder decodeProtocolDataToYdt(String protocolData, Object schemaRegistryForYdt,
                                              YmsOperationType protocolOperation) {
        String errorInfo = "RegisterDataTreeCodec decodeProtocolDataToYdt Called.";
        throw new YchException(errorInfo);
    }

    @Override
    public YdtBuilder decodeCompositeProtocolDataToYdt(YangCompositeEncoding protocolData,
                                                       Object schemaRegistryForYdt,
                                                       YmsOperationType protocolOperation) {
        String errorInfo = "RegisterDataTreeCodec decodeCompositeProtocolDataToYdt Called.";
        throw new YchException(errorInfo);
    }
}
