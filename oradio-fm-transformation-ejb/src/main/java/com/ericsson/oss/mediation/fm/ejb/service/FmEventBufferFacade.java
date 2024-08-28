/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package com.ericsson.oss.mediation.fm.ejb.service;

import com.ericsson.oss.mediation.event.buffering.api.EventBuffer;
import com.ericsson.oss.mediation.event.buffering.impl.EventBufferSingleton;
import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.model.EventNotificationBatch;
import com.ericsson.oss.services.fm.service.util.EventNotificationConverter;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Facade for the FM Common Event Buffering Service.
 */
public class FmEventBufferFacade {

    private static final Logger logger = LoggerFactory.getLogger(FmEventBufferFacade.class);
    private EventBuffer eventBuffer;

    /**
     * Constructor to fetch the singleton instance
     */
    public FmEventBufferFacade() {
        this.eventBuffer = EventBufferSingleton.getInstance();
    }

    /**
     * Constructor for mock implementation in unit testcases
     * @param eventBuffer
     */
    public FmEventBufferFacade(final EventBuffer eventBuffer) {
        this.eventBuffer = eventBuffer;
    }

    /**
     * Sends the event to the FM Event Buffering service. The event will be then buffered and sent to the 'ClusteredFMMediationChannel'.
     *
     * @param eventNotification
     *            the event containing the details of the alarm
     */
    public void sendEvent(final EventNotification eventNotification) {
        final EventNotificationBatch eventBatch = EventNotificationConverter.serializeObject(Arrays.asList(eventNotification));
        logger.debug("Sending event to ClusteredFMMediationChannel [{}]",eventBatch);
        eventBuffer.bufferAndSend(eventBatch);
    }

    /**
     * Sends the eventList to the FM Event Buffering service. The event will be then buffered and sent to the 'ClusteredFMMediationChannel'.
     *
     * @param eventNotifications
     */
    public void sendEventList(final List<EventNotification> eventNotifications) {
        final EventNotificationBatch eventBatch = EventNotificationConverter.serializeObject(eventNotifications);
        logger.debug("Sending events to ClusteredFMMediationChannel [{}]",eventBatch);
        eventBuffer.bufferAndSend(eventBatch);
    }
}
