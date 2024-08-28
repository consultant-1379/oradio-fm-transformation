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


import com.ericsson.cds.cdi.support.spock.CdiSpecification
import com.ericsson.oss.mediation.event.buffering.api.EventBuffer
import com.ericsson.oss.mediation.translator.model.EventNotification


class FmEventBufferFacadeSpec extends CdiSpecification{


    EventBuffer eventBuffer = mock(EventBuffer)
    FmEventBufferFacade fmEventBufferFacade = new FmEventBufferFacade(eventBuffer)

    def "Test transformed alarm is sent to Cluster FM Mediation Channel"() {

        given: "a transformed alarm"
        final EventNotification event = new EventNotification()
        event.setManagedObjectInstance("ManagedElement=Node1")
        event.setRecordType("SYNCHRONIZATION_ALARM")

        when: "the Event buffer facade is called"
        fmEventBufferFacade.sendEvent(event)

        then: "the Event buffer facade is successfully invoked for the transformed alarm to Cluster FM Mediation Channel "
        1 * eventBuffer.bufferAndSend(_)
    }


    def "Test transformed alarms are sent to Cluster FM Mediation Channel"() {

        given: "a transformed alarms"
        List<EventNotification> eventList = new ArrayList<>();
        final EventNotification event = new EventNotification();
        event.setManagedObjectInstance("ManagedElement=Node1")
        event.setRecordType("SYNCHRONIZATION_ALARM")
        eventList.add(event)

        when: "the Event buffer facade is called"
        fmEventBufferFacade.sendEventList(eventList)

        then: "the Event buffer facade is successfully invoked for the transformed alarm list to Cluster FM Mediation Channel "
        1 * eventBuffer.bufferAndSend(_)
    }
}
