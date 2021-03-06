package net.idea.restnet.groups.user.db;

import java.util.List;

import net.idea.modbcum.i.exceptions.AmbitException;
import net.idea.modbcum.i.query.QueryParam;
import net.idea.modbcum.q.update.AbstractUpdate;
import net.idea.restnet.groups.IDBGroup;
import net.idea.restnet.user.DBUser;

public class DeleteGroupPerUser<P extends IDBGroup> extends AbstractUpdate<DBUser, P> {
    protected DeleteGroupsPerUser<P> wrapped;

    public DeleteGroupPerUser(DBUser user, P ref) {
	super();
	wrapped = new DeleteGroupsPerUser<P>(user, ref);
    }

    @Override
    public void setGroup(DBUser group) {
	wrapped.setGroup(group);
    }

    @Override
    public DBUser getGroup() {
	return wrapped.getGroup();
    }

    public void setObject(P object) {
	wrapped.getObject().set(0, object);
    };

    @Override
    public P getObject() {
	return wrapped.getObject().get(0);
    }

    @Override
    public String[] getSQL() throws AmbitException {
	return wrapped.getSQL();
    }

    @Override
    public List<QueryParam> getParameters(int index) throws AmbitException {
	return wrapped.getParameters(index);
    }

    @Override
    public void setID(int index, int id) {

    }

}
