package net.idea.restnet.aa.opensso.policy;

import net.idea.restnet.aa.resource.AdminResource;
import net.idea.restnet.c.ResourceDoc;
import net.idea.restnet.c.reporters.CatalogURIReporter;

import org.restlet.Request;
import org.restlet.data.Reference;

/**
 * Generates URI of type /opentoxuser/{id}/policy/{policyid}
 * 
 * @author nina
 * 
 */
public class PolicyURIReporter extends CatalogURIReporter<Policy> {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3861530813327878373L;

    public PolicyURIReporter() {
    }

    public PolicyURIReporter(Request request, ResourceDoc doc) {
	super(request, doc);
    }

    public String getURI(String ref, Policy item) {
	return String.format("%s/%s/%s/%s", ref, AdminResource.resource, OpenSSOPoliciesResource.resource,
		item == null ? "" : Reference.encode(item.getId()));
    }
}
