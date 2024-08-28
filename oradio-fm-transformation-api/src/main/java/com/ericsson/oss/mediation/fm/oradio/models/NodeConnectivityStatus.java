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

package com.ericsson.oss.mediation.fm.oradio.models;

import java.io.Serializable;

/**
 * Node Connectivity Status class for ORadio
 */
public class NodeConnectivityStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String networkElementFdn;
    private final ConnectionStatus connectionStatus;
    private final String msmplaneInstance;

    public NodeConnectivityStatus(final String fdn, final ConnectionStatus connStatus, final String msmplaneInstance) {
        this.networkElementFdn = fdn;
        this.connectionStatus = connStatus;
        this.msmplaneInstance = msmplaneInstance;
    }

    public String getNetworkElementFdn() {
        return networkElementFdn;
    }

    public ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    public String getMsmplaneInstance() {
        return msmplaneInstance;
    }
}