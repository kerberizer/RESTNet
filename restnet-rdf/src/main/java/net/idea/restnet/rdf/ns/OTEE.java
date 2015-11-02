package net.idea.restnet.rdf.ns;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

/**
 * Echa endpoints ontology
 * 
 * @author nina
 * 
 */
public class OTEE {

    public enum OTEEClass {
	Endpoints;
	public String getNS() {
	    return String.format(_NS, toString());
	}

	public OntClass getOntClass(OntModel model) {
	    OntClass c = model.getOntClass(getNS());
	    return (c == null) ? createOntClass(model) : c;
	}

	public OntClass createOntClass(OntModel model) {
	    return model.createClass(getNS());
	}

    };

    //
    public enum OTAProperty {

	smthing;
	public Property createProperty(OntModel jenaModel) {
	    Property p = jenaModel.getObjectProperty(String.format(_NS, toString()));
	    return p != null ? p : jenaModel.createObjectProperty(String.format(_NS, toString()));
	}
    }

    /**
     * <p>
     * The RDF model that holds the vocabulary terms
     * </p>
     */
    private static Model m_model = ModelFactory.createDefaultModel();
    /**
     * <p>
     * The name space of the vocabulary as a string ({@value})
     * </p>
     */
    protected static final String _NS = "http://www.opentox.org/echaEndpoints.owl#%s";
    public static final String NS = String.format(_NS, "");

    public static String getURI() {
	return NS;
    }

    /**
     * <p>
     * The name space of the vocabulary as a resource
     * </p>
     */
    public static final Resource NAMESPACE = m_model.createResource(NS);
}