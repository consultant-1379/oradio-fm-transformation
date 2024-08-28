/*
 * ------------------------------------------------------------------------------
 * ******************************************************************************
 * COPYRIGHT Ericsson 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 * *******************************************************************************
 * -------------------------------------------------------------------------------
 */

package com.ericsson.oss.mediation.fm.oradio.models

import com.ericsson.cds.cdi.support.spock.CdiSpecification

/**
 * Testing NodeConnectivityStatus with CDI support.
 */
class NodeConnectivityStatusSpec extends CdiSpecification {

    def "Object is modelled correctly when created"() {
        given: "A network element FDN and an msmplane instance"
            String fdn = "ORU-123"
            String msmplaneInstance = "msmplane-1"

        when: "NodeConnectivityStatus is created with FDN, status and associated msmplane instance"
            NodeConnectivityStatus connectivityStatus = new NodeConnectivityStatus(fdn, status, msmplaneInstance)

        then: "all data is modelled correctly"
            connectivityStatus.getNetworkElementFdn() == fdn
            connectivityStatus.getConnectionStatus() == status
            connectivityStatus.getMsmplaneInstance() == msmplaneInstance

        where:
            status                          |_
            ConnectionStatus.CONNECTED      |_
            ConnectionStatus.DISCONNECTED   |_

    }
}