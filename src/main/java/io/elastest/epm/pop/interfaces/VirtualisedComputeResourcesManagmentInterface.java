package io.elastest.epm.pop.interfaces;

import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.messages.compute.*;

/**
 * This interface allows an authorized consumer functional block to perform operations on
 * virtualised compute resources available to the consumer functional block. The interface includes
 * operations for allocating, querying, updating and terminating virtualised compute resources as
 * well as operations for scaling, migrating and operating the administrative status of a
 * virtualised compute resource.
 */
public interface VirtualisedComputeResourcesManagmentInterface {

    /**
     * This operation allows requesting the allocation of virtualised compute resources as indicated
     * by the consumer functional block.
     *
     * @param allocateComputeRequest
     * @return allocateComputeResponse
     */
    AllocateComputeResponse allocateVirtualisedComputeResource(
            AllocateComputeRequest allocateComputeRequest, PoP poP) throws AdapterException;

    /**
     * This operation allows querying information about instantiated virtualised compute resources.
     *
     * @param queryComputeRequest
     * @return queryComputeResponse
     */
    QueryComputeResponse queryVirtualisedComputeResource(
            QueryComputeRequest queryComputeRequest, PoP poP) throws AdapterException;

    /**
     * This operation allows updating the configuration and/or parameters of an instantiated
     * virtualised compute resource. This can include, for instance, updating metadata adding extra
     * virtual network interfaces to a compute resource, or attaching a virtual network interface to a
     * specific network port.
     *
     * @param updateComputeRequest
     * @return updateComputeResponse
     */
    UpdateComputeResponse updateVirtualisedComputeResource(
            UpdateComputeRequest updateComputeRequest, PoP poP) throws AdapterException;

    /**
     * This operation allows de-allocating and terminating one or more instantiated virtualised
     * compute resource(s). When the operation is done on multiple resources, it is assumed to be
     * best-effort, i.e. it can succeed for a subset of the resources, and fail for the remaining
     * ones.
     *
     * @param terminateComputeRequest
     * @return terminateComputeResponse
     */
    TerminateComputeResponse terminateVirtualisedComputeResource(
            TerminateComputeRequest terminateComputeRequest, PoP poP) throws AdapterException;
}
