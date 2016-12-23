package de.huxhorn.lilith.data.logging.protobuf;

import de.huxhorn.lilith.data.eventsource.EventWrapper;
import de.huxhorn.lilith.data.logging.LoggingEvent;
import de.huxhorn.sulky.codec.DelegatingCodecBase;

public class CompressingLoggingEventWrapperProtobufCodec
	extends DelegatingCodecBase<EventWrapper<LoggingEvent>>
{
	public CompressingLoggingEventWrapperProtobufCodec()
	{
		super(new LoggingEventWrapperProtobufEncoder(true), new LoggingEventWrapperProtobufDecoder(true));
	}
}
