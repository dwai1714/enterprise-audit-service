package com.audit.utils;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.SerializationException;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonbUserType implements /*ParameterizedType,*/ UserType {


	public static final String JSONB_TYPE = "jsonb";

	@Override
	public Class<Object> returnedClass() {
		return Object.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[]{Types.JAVA_OBJECT};
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
		final String json = resultSet.getString(names[0]);
		JsonNode readTree=null;
		try 
		{
			
			if(json!=null)
			{
				readTree = new ObjectMapper().readTree(json);
			}
			
			return readTree;
		} catch (IOException e) {
			throw new RuntimeException("json parsing problem", e);
		}
		
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		
			final String json = value == null ? null : value.toString();
			PGobject pgo = new PGobject();
			pgo.setType(JSONB_TYPE);
			pgo.setValue(json);
			st.setObject(index, pgo);
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Object deepCopy(Object value) throws HibernateException {

		if (!(value instanceof Collection)) {
			return value;
		}

		Collection<?> collection = (Collection) value;
		Collection collectionClone = CollectionFactory.newInstance(collection.getClass());

		collectionClone.addAll(collection.stream().map(this::deepCopy).collect(Collectors.toList()));

		return collectionClone;
	}

	static final class CollectionFactory {
		@SuppressWarnings("unchecked")
		static <E, T extends Collection<E>> T newInstance(Class<T> collectionClass) {
			if (List.class.isAssignableFrom(collectionClass)) {
				return (T) new ArrayList<E>();
			} else if (Set.class.isAssignableFrom(collectionClass)) {
				return (T) new HashSet<E>();
			} else {
				throw new IllegalArgumentException("Unsupported collection type : " + collectionClass);
			}
		}
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}

		if ((x == null) || (y == null)) {
			return false;
		}

		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		assert (x != null);
		return x.hashCode();
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return deepCopy(cached);
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		Object deepCopy = deepCopy(value);

		if (!(deepCopy instanceof Serializable)) {
			throw new SerializationException(String.format("%s is not serializable class", value), null);
		}

		return (Serializable) deepCopy;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return deepCopy(original);
	}
}