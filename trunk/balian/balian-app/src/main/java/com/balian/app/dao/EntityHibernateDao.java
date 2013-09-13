package com.balian.app.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;


/**
 * 范型的HibernateDao基类 对返回值作了泛型转换. 并提供分页函数和若干便捷查询方法.
 * 
 * @author 
 * @param <E> 实体类名
 */
public class EntityHibernateDao<E> extends HibernateDaoSupport {
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * Dao所管理的Entity类型.
	 */
	protected Class<?> entityClass;

	/**
	 * 取得entityClass的函数.不支持泛型的子类可以抛开Class entityClass, 重新实现此函数达到相同效果。
	 */
	protected Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> clazz) {
		this.entityClass = clazz;
	}

	/**
	 * 通过构造函数对entityClass付值
	 */
	public EntityHibernateDao() {
		this.entityClass = getGenericClass(getClass());
	}
	
	public static Class<?> getGenericClass(Class<?> clazz) {
		Type genType = clazz.getGenericSuperclass();

		if (genType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genType)
					.getActualTypeArguments();

			if ((params != null) && (params.length == 1)) {
				return (Class<?>) params[0];
			}
		}

		return null;
	}
	
	/**
	 * 通过id获取实体实例
	 * 
	 * @param id
	 * @return entity
	 */
	@SuppressWarnings("unchecked")
	public E get(Serializable id) {
		return (E) getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 通过id获取实体实例,并对实体记录加锁
	 * 
	 * @param id
	 * @param mode
	 * @return entity
	 */
	@SuppressWarnings("unchecked")
	public E get(Serializable id, LockMode mode) {
		return (E) getHibernateTemplate().get(entityClass, id, mode);
	}

	/**
	 * 获取实体所有实例
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> list() {
		return getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 新增一条实体实例
	 * 
	 * @param o
	 */
	public Serializable create(Object o) {
		return super.getHibernateTemplate().save(o);
	}

	/**
	 * 修改实体实例
	 * 
	 * @param o
	 */
	public void update(Object o) {
		super.getHibernateTemplate().update(o);
	}

	/**
	 * 新增或修改一条实体实例
	 * 
	 * @param o
	 */
	public void save(Object o) {
		getHibernateTemplate().merge(o);
	}

	/**
	 * 删除实体实例
	 * 
	 * @param o
	 */
	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}

	/**
	 * 通过id删除实体实例
	 * 
	 * @param id
	 */
	public void removeById(Serializable id) {
		remove(get(id));
	}

	/**
	 * 消除与 Session 的关联
	 * 
	 * @deprecated
	 * @param entity
	 */
	// 好像用要报错
	public void evict(Object entity) {
		getHibernateTemplate().evict(entity);
	}

	/**
	 * 同步数据
	 */
	public void flush() {
		getHibernateTemplate().flush();
	}

	/**
	 * 批量增加或修改记录
	 * 
	 * @param c
	 */
	public void saveAll(Collection<?> c) {
		super.getHibernateTemplate().saveOrUpdateAll(c);
	}

	/**
	 * 批量删除记录
	 * 
	 * @param c
	 */
	public void removeAll(Collection<?> c) {
		super.getHibernateTemplate().deleteAll(c);
	}

	/**
	 * 同步数据，清空缓存
	 */
	public void clear() {
		getHibernateTemplate().clear();
	}

	/**
	 * 通过HSQL进行统计
	 * 
	 * @param hsql
	 * @param values 可变参数 用户可以如下四种方式使用 dao.getCount(hql); dao.getCount(hql,arg0); dao.getCount(hql,arg0,arg1);
	 *            dao.getCount(hql,new Object[arg0,arg1,arg2])
	 * @return
	 */
	public int getCount(String hsql, Object... values) {
		Object c = getHibernateTemplate().find(hsql, values).get(0);

		if (c instanceof Integer) {
			return ((Integer) c).intValue();
		}

		return 0;
	}

	/**
	 * 通过标准API查询数量
	 */
	public int getCount(Criteria criteria) {
		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	/**
	 * hql查询.
	 * 
	 * @param hql
	 * @param values 可变参数 用户可以如下四种方式使用 dao.find(hql); dao.find(hql,arg0); dao.find(hql,arg0,arg1); dao.find(hql,new
	 *            Object[arg0,arg1,arg2])
	 * @return
	 */
	public List<?> find(String hql, Object... values) {
		return getHibernateTemplate().find(hql, values);
	}

	/**
	 * 直接通过hql查询，不带参数
	 */
	public List<?> find(String hql) {
		return getHibernateTemplate().find(hql);
	}

	/**
	 * hql查询.
	 * 
	 * @param hql 使用 named query parameter as <tt>from Foo foo where foo.bar = :bar</tt>.
	 * @param param a java.util.Map
	 * @return
	 */
	public List<?> find(String hql, Map<?, ?> param) {
		return getSession().createQuery(hql).setProperties(param).list();
	}



	/**
	 * 根据属性名和属性值查询对象.
	 * 
	 * @return 符合条件的对象列表
	 */
	@SuppressWarnings("unchecked")
	public List<E> findBy(String key, Object value) {
		Assert.hasText(key);
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(key, value));
		return criteria.list();
	}

	/**
	 * 根据属性名和属性值以Like AnyWhere方式查询对象.
	 */
	@SuppressWarnings("unchecked")
	public List<E> findByLike(String key, String value) {
		Assert.hasText(key);
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.like(key, value, MatchMode.ANYWHERE));
		return criteria.list();
	}

	/**
	 * 根据属性名和属性值查询唯一对象.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public E getUniqueBy(String key, Object value) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq(key, value));
		return (E) criteria.uniqueResult();
	}

	/**
	 * 根据属性名和属性值查询唯一对象.
	 * 
	 * @deprecated
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public E getFirst(String key, Object value) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq(key, value));
		return (E) criteria.setMaxResults(1).list();
	}

	/**
	 * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置. 留意可以连续设置,如下：
	 * 
	 * <pre>
	 * dao.createQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下：
	 * 
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 * 
	 * @param values 可变参数.
	 */
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	/**
	 * 创建Criteria对象.
	 * 
	 * @param criterions 可变的Restrictions条件列表
	 */
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

}

