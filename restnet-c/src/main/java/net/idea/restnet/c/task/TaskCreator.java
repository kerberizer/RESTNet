package net.idea.restnet.c.task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.idea.modbcum.i.IQueryRetrieval;
import net.idea.modbcum.i.exceptions.DbAmbitException;
import net.idea.modbcum.r.QueryReporter;
import net.idea.restnet.i.task.ICallableTask;
import net.idea.restnet.i.task.ITask;
import net.idea.restnet.i.task.ITaskResult;

import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

/**
 * creates tasks by iterating over query results
 * 
 * @author nina
 * 
 */
public class TaskCreator<USERID, T, INPUT> extends QueryReporter<T, IQueryRetrieval<T>, List<UUID>> {
    protected boolean async;
    protected INPUT input;

    public INPUT getInput() {
	return input;
    }

    public void setInput(INPUT input) {
	this.input = input;
    }

    protected List<UUID> tasks;

    public TaskCreator(INPUT input, boolean async) throws Exception {
	super();
	tasks = new ArrayList<UUID>();
	setAsync(async);
	setInput(input);
    }

    @Override
    public void setOutput(List<UUID> output) throws Exception {
    }

    @Override
    public List<UUID> getOutput() throws Exception {
	return tasks;
    }

    public boolean isAsync() {
	return async;
    }

    public void setAsync(boolean async) {
	this.async = async;
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 8074204952293683628L;

    @Override
    public Object processItem(T item) throws Exception {
	try {
	    ICallableTask callable = getCallable(input, item);
	    if (async) {
		ITask<Reference, USERID> task = createTask(callable, item);
		tasks.add(task.getUuid());
	    } else {
		ITaskResult ref = callable.call();
	    }

	} catch (Exception x) {
	    throw x;

	}
	return item;
    }

    public void open() throws DbAmbitException {
    }

    protected ICallableTask getCallable(INPUT input, T item) throws ResourceException {
	throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }

    protected ITask<Reference, USERID> createTask(ICallableTask callable, T item) throws ResourceException {
	throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }

    @Override
    public void footer(List<UUID> output, IQueryRetrieval<T> query) {

    }

    @Override
    public void header(List<UUID> output, IQueryRetrieval<T> query) {

    }

    @Override
    public List<UUID> process(IQueryRetrieval<T> query) throws Exception {
	if (query == null) {
	    processItem(null);
	    return tasks;
	} else
	    return super.process(query);
    }
    /*
     * protected void processRepresentation(Representation entity, Variant
     * variant) throws FileUploadException { if
     * (MediaType.APPLICATION_WWW_FORM.equals(entity.getMediaType())) { return;
     * } else if
     * (MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(),true)) {
     * DiskFileItemFactory factory = new DiskFileItemFactory();
     * RestletFileUpload restletUpload = new RestletFileUpload(factory);
     * List<FileItem> items = restletUpload.parseRequest(getRequest()); } else
     * if (isAllowedMediaType(entity.getMediaType())) {
     * 
     * } }
     */
}
