package io.elastest.epm.pop.interfaces;

import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.messages.network.*;

/**
 * This interface allows an authorized consumer functional block to perform operations on
 * virtualised network resources available to the consumer functional block. The interface includes
 * operations for allocating, querying, updating and terminating virtualised network resources.
 */
public interface VirtualisedNetworkResourceManagementInterface {

    /**
     * This operation allows an authorized consumer functional block to request the allocation of
     * virtualised network resources as indicated by the consumer functional block.
     *
     * @param allocateNetworkRequest
     * @return allocateNetworkResponse
     */
    AllocateNetworkResponse allocateVirtualisedNetworkResource(
            AllocateNetworkRequest allocateNetworkRequest, PoP poP) throws AdapterException;

    /**
     * This operation allows querying information about instantiated virtualised network resources.
     *
     * @param queryNetworkRequest
     * @return queryNetworkResponse
     */
    QueryNetworkResponse queryVirtualisedNetworkResource(
            QueryNetworkRequest queryNetworkRequest, PoP poP) throws AdapterException;

    /**
     * This operation allows updating the information of an instantiated virtualised network resource.
     *
     * @param updateNetworkRequest
     * @return updateNetworkResponse
     */
    UpdateNetworkResponse updateVirtualisedNetworkResource(
            UpdateNetworkRequest updateNetworkRequest, PoP poP);

    /**
     * This operation allows de-allocating and terminating one or more instantiated virtualised
     * network resource(s). When the operation is done on multiple ids, it is assumed to be
     * best-effort, i.e. it can succeed for a subset of the ids, and fail for the remaining ones.
     *
     * @param terminateNetworkRequest
     * @return terminateNetworkResponse
     */
    TerminateNetworkResponse terminateVirtualisedNetworkResource(
            TerminateNetworkRequest terminateNetworkRequest, PoP poP) throws AdapterException;
}
