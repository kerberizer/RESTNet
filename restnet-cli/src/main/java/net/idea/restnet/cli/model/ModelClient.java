package net.idea.restnet.cli.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.idea.restnet.cli.AbstractClient;
import net.idea.restnet.cli.InvalidInputException;
import net.idea.restnet.cli.algorithm.Algorithm;
import net.idea.restnet.cli.dataset.Dataset;
import net.idea.restnet.cli.task.RemoteTask;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.opentox.rest.RestException;

public class ModelClient<POLICY_RULE> extends AbstractClient<Model,POLICY_RULE> {

	/**
	 * Async prediction
	 * @param model
	 * @param dataset
	 * @param accessRights
	 * @return
	 * @throws InvalidInputException
	 * @throws Exception
	 */
	public RemoteTask predictAsync(Model model, Dataset dataset, List<POLICY_RULE> accessRights) throws InvalidInputException ,Exception {
		if (model.getResourceURL()==null) throw new InvalidInputException("No model URI");
		if (dataset.getResourceURL()==null) throw new InvalidInputException("No dataset URI");
		return sendAsync(model.getResourceURL(), createPOSTEntity(dataset,accessRights), HttpPost.METHOD_NAME);
	}
	/**
	 * Prediction
	 * @param model
	 * @param dataset
	 * @param accessRights
	 * @return
	 * @throws InvalidInputException
	 * @throws Exception
	 */
	public Dataset predict(Model model, Dataset dataset, List<POLICY_RULE> accessRights) throws InvalidInputException ,Exception {
		RemoteTask task = predictAsync(model,dataset,accessRights);
		task.waitUntilCompleted(500);	
		if (task.isERROR()) throw task.getError();
		else {
			Dataset result = new Dataset();
			result.setResourceURL(task.getResult());
			return result;
		}
	}
	
	
	protected HttpEntity createPOSTEntity(Dataset dataset, List<POLICY_RULE> accessRights, String... params) throws UnsupportedEncodingException {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("dataset_uri", dataset.getResourceURL().toExternalForm()));
		//TODO params
		return new UrlEncodedFormEntity(formparams, "UTF-8");
	}
	/**
	 * Search model by algorithm and dataset
	 * @param url
	 * @param algorithm
	 * @param dataset
	 * @return
	 * @throws RestException
	 * @throws IOException
	 */
	public List<URL> getModelByAlgorithmDataset(URL url,Algorithm algorithm, Dataset dataset) throws  RestException, IOException {
		return listURI(url, getModelByAlgorithmDataset(algorithm,dataset));
	}
	/**
	 * 
	 * @param algorithm
	 * @param dataset
	 * @return
	 */
	protected String[] getModelByAlgorithmDataset(Algorithm algorithm,Dataset dataset) {
		List<String> params = new ArrayList<String>();
		if (algorithm!=null) {
			params.add("algorithm");
			params.add(algorithm.getResourceURL().toExternalForm());
		}
		if (dataset!=null) {
			params.add("dataset");
			params.add(dataset.getResourceURL().toExternalForm());
		}		
		return (String[]) params.toArray();
	}
}