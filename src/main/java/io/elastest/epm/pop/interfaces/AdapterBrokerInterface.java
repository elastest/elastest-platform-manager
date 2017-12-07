package io.elastest.epm.pop.interfaces;

import io.elastest.epm.model.PoP;

public interface AdapterBrokerInterface {

  RuntimeManagmentInterface getAdapter(PoP pop);
}
