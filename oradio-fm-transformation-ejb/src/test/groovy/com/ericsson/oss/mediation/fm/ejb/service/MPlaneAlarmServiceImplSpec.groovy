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

package com.ericsson.oss.mediation.fm.ejb.service


import com.ericsson.cds.cdi.support.rule.MockedImplementation
import com.ericsson.cds.cdi.support.rule.ObjectUnderTest
import com.ericsson.cds.cdi.support.spock.CdiSpecification
import com.ericsson.oss.mediation.translator.model.EventNotification


class MPlaneAlarmServiceImplSpec extends CdiSpecification {

    static private final String NETWORK_ELEMENT_FDN = "NetworkElement=NodeA"

    @ObjectUnderTest
    private MPlaneAlarmServiceImpl mPlaneAlarmService

    @MockedImplementation
    private FmEventBufferFacade fmEventBufferFacade


    def "Test transformed alarm is sent to Event buffer"() {

        given: "a transformed alarm"
        final EventNotification event = new EventNotification()
        event.setManagedObjectInstance("ManagedElement=Node1")

        when: "the alarm service is called"
        mPlaneAlarmService.sendAlarm(event)

        then: "the alarm service is successfully invoked for the transformed alarm"
        1 * fmEventBufferFacade.sendEvent(_)
    }

    def "Test transformed alarm list is sent to Event Buffer"() {

        given: "a transformed alarm"
        List<EventNotification> eventList = new ArrayList<>();
        final EventNotification event = new EventNotification();
        event.setRecordType("SYNCHRONIZATION_ALARM")
        eventList.add(event)

        when: "the alarm service is called"
        mPlaneAlarmService.sendAlarms(eventList)

        then: "the alarm service is successfully invoked for the transformed alarm"
        1 * fmEventBufferFacade.sendEventList(eventList)

    }

    def "Test raising a heartbeat alarm to Event buffer"() {

        when: "request sent to alarm service to raise heartbeat alarm"
        mPlaneAlarmService.raiseHeartbeatAlarm(NETWORK_ELEMENT_FDN)

        then: "the raise heartbeat alarm is sent"
        1 * fmEventBufferFacade.sendEvent({
            it.toString().contains("eventType=Communications alarm, probableCause=LAN Error/Communication Error, perceivedSeverity=CRITICAL,") &&
                    it.getManagedObjectInstance().equals(NETWORK_ELEMENT_FDN) && it.getSourceType().equals("ORadio") && it.getEventTime() != null
        })
    }

    def "Test clearing a heartbeat alarm to Event buffer"() {

        when: "request sent to alarm service to clear the raised heartbeat alarm"
        mPlaneAlarmService.raiseHeartbeatAlarm(NETWORK_ELEMENT_FDN)
        mPlaneAlarmService.clearHeartbeatAlarm(NETWORK_ELEMENT_FDN)


        then: "the clear heartbeat alarm is sent"
        1 * fmEventBufferFacade.sendEvent({
            it.toString().contains("eventType=Communications alarm, probableCause=LAN Error/Communication Error, perceivedSeverity=CLEARED,") &&
                    it.getManagedObjectInstance().equals(NETWORK_ELEMENT_FDN) && it.getSourceType().equals("ORadio") && it.getEventTime() != null
        })
    }
}
