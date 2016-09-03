package org.onosproject.yms.app.ymsm;

import org.onosproject.yms.app.ych.codecutils.YchException;
import org.onosproject.yms.ych.YangCompositeEncoding;
import org.onosproject.yms.ych.YangDataTreeCodec;
import org.onosproject.yms.ydt.YdtBuilder;
import org.onosproject.yms.ydt.YmsOperationType;

/**
 * Represents implementation of overriden YANG data tree codec interfaces.
 */
public class OverridenDataTreeCodec implements YangDataTreeCodec {

    @Override
    public String encodeYdtToProtocolFormat(YdtBuilder ydtBuilder, YmsOperationType protocolOperation) {
        String errorInfo = "OverridenDataTreeCodec encodeYdtToProtocolFormat Called.";
        throw new YchException(errorInfo);
    }

    @Override
    public YangCompositeEncoding encodeYdtToCompositeProtocolFormat(YdtBuilder ydtBuilder,
                                                                    YmsOperationType protocolOperation) {
        String errorInfo = "OverridenDataTreeCodec encodeYdtToCompositeProtocolFormat Called.";
        throw new YchException(errorInfo);
    }

    @Override
    public YdtBuilder decodeProtocolDataToYdt(String protocolData, Object schemaRegistryForYdt,
                                              YmsOperationType protocolOperation) {
        String errorInfo = "OverridenDataTreeCodec decodeProtocolDataToYdt Called.";
        throw new YchException(errorInfo);
    }

    @Override
    public YdtBuilder decodeCompositeProtocolDataToYdt(YangCompositeEncoding protocolData,
                                                       Object schemaRegistryForYdt,
                                                       YmsOperationType protocolOperation) {
        String errorInfo = "OverridenDataTreeCodec decodeCompositeProtocolDataToYdt Called.";
        throw new YchException(errorInfo);
    }
}
