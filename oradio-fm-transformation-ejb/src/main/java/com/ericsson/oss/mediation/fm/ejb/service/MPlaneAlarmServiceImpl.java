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

import com.ericsson.oss.mediation.fm.oradio.api.MPlaneAlarmService;
import com.ericsson.oss.mediation.fm.util.EventNotificationUtil;
import com.ericsson.oss.mediation.translator.model.EventNotification;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Service implementation that provides FM functionality to manage ORadio types.
 *
 */
@Stateless
public class MPlaneAlarmServiceImpl implements MPlaneAlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MPlaneAlarmServiceImpl.class);
    private static final String ORADIO_NODE = "ORadio";
    @Inject
    private FmEventBufferFacade fmEventBufferFacade;

    /**
     * Send the transformed {@code EventNotification} representing the ORadio alarm to FM.
     * @param eventNotification
     *            the {@code EventNotification} representing the ORadio alarm.
     */
    @Override
    public void sendAlarm(EventNotification eventNotification) {
        logger.debug("Send event [{}]",eventNotification);
        fmEventBufferFacade.sendEvent(eventNotification);
    }

    /**
     * Send the transformed {@code EventNotifications} representing the ORadio alarms to FM.
     *
     * @param eventNotifications
     *            the {@code EventNotifications} representing the ORadio alarms.
     */
    @Override
    public void sendAlarms(List<EventNotification> eventNotifications) {
        logger.debug("Send events [{}]",eventNotifications);
        fmEventBufferFacade.sendEventList(eventNotifications);
    }

    /**
     * Sends a request to raise the heartbeat alarm for the specified Node FDN
     *
     * @param networkElementFdn
     *            The FDN of the NetworkElement
     */
    @Override
    public void raiseHeartbeatAlarm(String networkElementFdn) {
        logger.debug("Raising heartbeat alarm for node: {}", networkElementFdn);
        final EventNotification eventNotification = EventNotificationUtil.createHeartbeatAlarm(networkElementFdn, "", ORADIO_NODE, networkElementFdn);
        fmEventBufferFacade.sendEvent(eventNotification);
    }

    /**
     * Sends a request to clear the heartbeat alarm for the specified Node FDN
     *
     * @param networkElementFdn
     *            The FDN of the NetworkElement
     */
    @Override
    public void clearHeartbeatAlarm(String networkElementFdn) {
        logger.debug("Clearing heartbeat alarm for node: {}", networkElementFdn);
        final EventNotification eventNotification =
                EventNotificationUtil.createHeartbeatClearAlarm(networkElementFdn, "", ORADIO_NODE, networkElementFdn);
        fmEventBufferFacade.sendEvent(eventNotification);
    }

}
