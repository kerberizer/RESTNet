package net.idea.restnet.user.db;

import java.util.List;

import net.idea.modbcum.i.exceptions.AmbitException;
import net.idea.modbcum.i.query.QueryParam;
import net.idea.modbcum.q.update.AbstractObjectUpdate;
import net.idea.restnet.user.DBUser;



public class UpdateUser extends AbstractObjectUpdate<DBUser>{

	public UpdateUser(DBUser ref) {
		super(ref);
	}
	public UpdateUser() {
		this(null);
	}			
	public List<QueryParam> getParameters(int index) throws AmbitException {
		throw new AmbitException("Not implemented");
/*
		List<QueryParam> params = new ArrayList<QueryParam>();
		params.add(new QueryParam<String>(String.class, getObject().getTitle()));
		params.add(new QueryParam<String>(String.class, getObject().getURL()));
		params.add(new QueryParam<String>(String.class, getObject().getType().toString()));
		params.add(new QueryParam<Integer>(Integer.class, getObject().getId()));
			return params;
*/
	
		
	}

	public String[] getSQL() throws AmbitException {
		return null;
	}
	public void setID(int index, int id) {
			
	}
}