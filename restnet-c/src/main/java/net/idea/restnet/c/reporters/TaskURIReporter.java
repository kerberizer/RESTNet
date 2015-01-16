package net.idea.restnet.c.reporters;

import java.util.UUID;

import net.idea.restnet.c.ResourceDoc;
import net.idea.restnet.i.task.ITask;
import net.idea.restnet.i.task.ITaskResult;
import net.idea.restnet.i.task.ITaskStorage;

import org.restlet.Request;
import org.restlet.data.Reference;

/**
 * URI of the task
 * 
 * @author nina
 * 
 * @param <USERID>
 */
public class TaskURIReporter<USERID> extends CatalogURIReporter<UUID> {
    /**
	 * 
	 */
    private static final long serialVersionUID = 9136099541989811170L;
    protected ITaskStorage<USERID> storage;

    public TaskURIReporter(ITaskStorage<USERID> storage) {
	this.storage = storage;
    }

    public TaskURIReporter(ITaskStorage<USERID> storage, Request request, ResourceDoc doc) {
	super(request, doc);
	this.storage = storage;
    }

    protected TaskURIReporter(ITaskStorage<USERID> storage, Reference baseRef, ResourceDoc doc) {
	super(baseRef, doc);
	this.storage = storage;
    }

    public String getURI(String ref, UUID name) {
	ITask<ITaskResult, USERID> task = storage.findTask(name);
	return task.getUri() == null ? null : task.getUri().toString();

    }
}
