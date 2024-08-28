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

package com.ericsson.oss.mediation.fm.oradio.api;

import com.ericsson.oss.itpf.sdk.core.annotation.EService;
import com.ericsson.oss.mediation.translator.model.EventNotification;
import javax.ejb.Remote;
import java.util.List;

/**
 * Service that provides FM functionality to manage ORadio types.
 */
@EService
@Remote
public interface MPlaneAlarmService {
    /**
     * Sends the transformed {@code EventNotification} representing the ORadio alarm to FM.
     *
     * @param eventNotification
     *            the {@code EventNotification} representing the ORadio alarm.
     */
    void sendAlarm(final EventNotification eventNotification);

    /**
     * Sends the transformed {@code EventNotifications} representing the ORadio alarms to FM.
     *
     * @param eventNotifications
     *            the {@code EventNotifications} representing the ORadio alarms.
     */
    void sendAlarms(final List<EventNotification> eventNotifications);

    /**
     * Sends a request to raise the heartbeat alarm for the specified Node FDN
     *
     * @param networkElementFdn
     *            The FDN of the NetworkElement
     */
    void raiseHeartbeatAlarm(final String networkElementFdn);

    /**
     * Sends a request to clear the heartbeat alarm for the specified Node FDN
     *
     * @param networkElementFdn
     *            The FDN of the NetworkElement
     */
    void clearHeartbeatAlarm(final String networkElementFdn);

    /**
     * Translate the fields of an alarm that originated as a VES event from the node into a normalized EventNotification.
     *
     * @param alarm
     *            the fields of the alarm to be translated.
     * @return an {@code EventNotification} containing the transformed alarm fields.
     */
    //EventNotification transformAlarm(final Map<String, Object> alarm);

    /**
     * Translate the fields of alarms that were read from the 'AlarmList.alarmRecords' object on the node into normalized EventNotifications.
     *
     * @param alarms
     *            a list of maps containing the properties of the alarms to be transformed.
     * @param meContextFdn
     *            the meContext FDN of the node from which the alarms were read.
     * @return a {@code List<EventNotification>} containing the transformed alarms.
     */
   // List<EventNotification> transformAlarms(final List<Map<String, Object>> alarms, String meContextFdn);

    /**
     * Parse the payload that were read from the 'AlarmList.alarmRecords' object on the node into Map.
     *
     * @param payload
     *            a string containing the alarm to be parsed.

     * @return a {@code Map<String,Object>} containing the parsed alarm.
     */
    //Map<String, Object> parseAlarm(final String payload);

    /**
     * Parse the payload that were read from the 'AlarmList.alarmRecords' object on the node into list of Map.
     *
     * @param payload
     *            a string containing the alarms to be parsed.

     * @return a {@code List<Map<String, Object>>} containing the parsed alarms.
     */
    //List<Map<String, Object>> parseAlarms(final String payload);
}
