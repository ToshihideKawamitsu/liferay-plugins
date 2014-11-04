/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.bbb.service.base;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.service.BBBMeetingLocalService;
import com.liferay.bbb.service.persistence.BBBMeetingPersistence;
import com.liferay.bbb.service.persistence.BBBParticipantPersistence;
import com.liferay.bbb.service.persistence.BBBServerPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the b b b meeting local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.bbb.service.impl.BBBMeetingLocalServiceImpl}.
 * </p>
 *
 * @author Shinn Lok
 * @see com.liferay.bbb.service.impl.BBBMeetingLocalServiceImpl
 * @see com.liferay.bbb.service.BBBMeetingLocalServiceUtil
 * @generated
 */
public abstract class BBBMeetingLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements BBBMeetingLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.bbb.service.BBBMeetingLocalServiceUtil} to access the b b b meeting local service.
	 */

	/**
	 * Adds the b b b meeting to the database. Also notifies the appropriate model listeners.
	 *
	 * @param bbbMeeting the b b b meeting
	 * @return the b b b meeting that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BBBMeeting addBBBMeeting(BBBMeeting bbbMeeting) {
		bbbMeeting.setNew(true);

		return bbbMeetingPersistence.update(bbbMeeting);
	}

	/**
	 * Creates a new b b b meeting with the primary key. Does not add the b b b meeting to the database.
	 *
	 * @param bbbMeetingId the primary key for the new b b b meeting
	 * @return the new b b b meeting
	 */
	@Override
	public BBBMeeting createBBBMeeting(long bbbMeetingId) {
		return bbbMeetingPersistence.create(bbbMeetingId);
	}

	/**
	 * Deletes the b b b meeting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bbbMeetingId the primary key of the b b b meeting
	 * @return the b b b meeting that was removed
	 * @throws PortalException if a b b b meeting with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public BBBMeeting deleteBBBMeeting(long bbbMeetingId)
		throws PortalException {
		return bbbMeetingPersistence.remove(bbbMeetingId);
	}

	/**
	 * Deletes the b b b meeting from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bbbMeeting the b b b meeting
	 * @return the b b b meeting that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public BBBMeeting deleteBBBMeeting(BBBMeeting bbbMeeting)
		throws PortalException {
		return bbbMeetingPersistence.remove(bbbMeeting);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(BBBMeeting.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return bbbMeetingPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return bbbMeetingPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return bbbMeetingPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return bbbMeetingPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return bbbMeetingPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public BBBMeeting fetchBBBMeeting(long bbbMeetingId) {
		return bbbMeetingPersistence.fetchByPrimaryKey(bbbMeetingId);
	}

	/**
	 * Returns the b b b meeting with the primary key.
	 *
	 * @param bbbMeetingId the primary key of the b b b meeting
	 * @return the b b b meeting
	 * @throws PortalException if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting getBBBMeeting(long bbbMeetingId)
		throws PortalException {
		return bbbMeetingPersistence.findByPrimaryKey(bbbMeetingId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.bbb.service.BBBMeetingLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(BBBMeeting.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("bbbMeetingId");

		return actionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.bbb.service.BBBMeetingLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(BBBMeeting.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("bbbMeetingId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return bbbMeetingLocalService.deleteBBBMeeting((BBBMeeting)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return bbbMeetingPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the b b b meetings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @return the range of b b b meetings
	 */
	@Override
	public List<BBBMeeting> getBBBMeetings(int start, int end) {
		return bbbMeetingPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of b b b meetings.
	 *
	 * @return the number of b b b meetings
	 */
	@Override
	public int getBBBMeetingsCount() {
		return bbbMeetingPersistence.countAll();
	}

	/**
	 * Updates the b b b meeting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param bbbMeeting the b b b meeting
	 * @return the b b b meeting that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BBBMeeting updateBBBMeeting(BBBMeeting bbbMeeting) {
		return bbbMeetingPersistence.update(bbbMeeting);
	}

	/**
	 * Returns the b b b meeting local service.
	 *
	 * @return the b b b meeting local service
	 */
	public com.liferay.bbb.service.BBBMeetingLocalService getBBBMeetingLocalService() {
		return bbbMeetingLocalService;
	}

	/**
	 * Sets the b b b meeting local service.
	 *
	 * @param bbbMeetingLocalService the b b b meeting local service
	 */
	public void setBBBMeetingLocalService(
		com.liferay.bbb.service.BBBMeetingLocalService bbbMeetingLocalService) {
		this.bbbMeetingLocalService = bbbMeetingLocalService;
	}

	/**
	 * Returns the b b b meeting remote service.
	 *
	 * @return the b b b meeting remote service
	 */
	public com.liferay.bbb.service.BBBMeetingService getBBBMeetingService() {
		return bbbMeetingService;
	}

	/**
	 * Sets the b b b meeting remote service.
	 *
	 * @param bbbMeetingService the b b b meeting remote service
	 */
	public void setBBBMeetingService(
		com.liferay.bbb.service.BBBMeetingService bbbMeetingService) {
		this.bbbMeetingService = bbbMeetingService;
	}

	/**
	 * Returns the b b b meeting persistence.
	 *
	 * @return the b b b meeting persistence
	 */
	public BBBMeetingPersistence getBBBMeetingPersistence() {
		return bbbMeetingPersistence;
	}

	/**
	 * Sets the b b b meeting persistence.
	 *
	 * @param bbbMeetingPersistence the b b b meeting persistence
	 */
	public void setBBBMeetingPersistence(
		BBBMeetingPersistence bbbMeetingPersistence) {
		this.bbbMeetingPersistence = bbbMeetingPersistence;
	}

	/**
	 * Returns the b b b participant local service.
	 *
	 * @return the b b b participant local service
	 */
	public com.liferay.bbb.service.BBBParticipantLocalService getBBBParticipantLocalService() {
		return bbbParticipantLocalService;
	}

	/**
	 * Sets the b b b participant local service.
	 *
	 * @param bbbParticipantLocalService the b b b participant local service
	 */
	public void setBBBParticipantLocalService(
		com.liferay.bbb.service.BBBParticipantLocalService bbbParticipantLocalService) {
		this.bbbParticipantLocalService = bbbParticipantLocalService;
	}

	/**
	 * Returns the b b b participant remote service.
	 *
	 * @return the b b b participant remote service
	 */
	public com.liferay.bbb.service.BBBParticipantService getBBBParticipantService() {
		return bbbParticipantService;
	}

	/**
	 * Sets the b b b participant remote service.
	 *
	 * @param bbbParticipantService the b b b participant remote service
	 */
	public void setBBBParticipantService(
		com.liferay.bbb.service.BBBParticipantService bbbParticipantService) {
		this.bbbParticipantService = bbbParticipantService;
	}

	/**
	 * Returns the b b b participant persistence.
	 *
	 * @return the b b b participant persistence
	 */
	public BBBParticipantPersistence getBBBParticipantPersistence() {
		return bbbParticipantPersistence;
	}

	/**
	 * Sets the b b b participant persistence.
	 *
	 * @param bbbParticipantPersistence the b b b participant persistence
	 */
	public void setBBBParticipantPersistence(
		BBBParticipantPersistence bbbParticipantPersistence) {
		this.bbbParticipantPersistence = bbbParticipantPersistence;
	}

	/**
	 * Returns the b b b server local service.
	 *
	 * @return the b b b server local service
	 */
	public com.liferay.bbb.service.BBBServerLocalService getBBBServerLocalService() {
		return bbbServerLocalService;
	}

	/**
	 * Sets the b b b server local service.
	 *
	 * @param bbbServerLocalService the b b b server local service
	 */
	public void setBBBServerLocalService(
		com.liferay.bbb.service.BBBServerLocalService bbbServerLocalService) {
		this.bbbServerLocalService = bbbServerLocalService;
	}

	/**
	 * Returns the b b b server persistence.
	 *
	 * @return the b b b server persistence
	 */
	public BBBServerPersistence getBBBServerPersistence() {
		return bbbServerPersistence;
	}

	/**
	 * Sets the b b b server persistence.
	 *
	 * @param bbbServerPersistence the b b b server persistence
	 */
	public void setBBBServerPersistence(
		BBBServerPersistence bbbServerPersistence) {
		this.bbbServerPersistence = bbbServerPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.bbb.model.BBBMeeting",
			bbbMeetingLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.bbb.model.BBBMeeting");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return BBBMeeting.class;
	}

	protected String getModelClassName() {
		return BBBMeeting.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = bbbMeetingPersistence.getDataSource();

			DB db = DBFactoryUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.bbb.service.BBBMeetingLocalService.class)
	protected com.liferay.bbb.service.BBBMeetingLocalService bbbMeetingLocalService;
	@BeanReference(type = com.liferay.bbb.service.BBBMeetingService.class)
	protected com.liferay.bbb.service.BBBMeetingService bbbMeetingService;
	@BeanReference(type = BBBMeetingPersistence.class)
	protected BBBMeetingPersistence bbbMeetingPersistence;
	@BeanReference(type = com.liferay.bbb.service.BBBParticipantLocalService.class)
	protected com.liferay.bbb.service.BBBParticipantLocalService bbbParticipantLocalService;
	@BeanReference(type = com.liferay.bbb.service.BBBParticipantService.class)
	protected com.liferay.bbb.service.BBBParticipantService bbbParticipantService;
	@BeanReference(type = BBBParticipantPersistence.class)
	protected BBBParticipantPersistence bbbParticipantPersistence;
	@BeanReference(type = com.liferay.bbb.service.BBBServerLocalService.class)
	protected com.liferay.bbb.service.BBBServerLocalService bbbServerLocalService;
	@BeanReference(type = BBBServerPersistence.class)
	protected BBBServerPersistence bbbServerPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameLocalService.class)
	protected com.liferay.portal.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameService.class)
	protected com.liferay.portal.service.ClassNameService classNameService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private BBBMeetingLocalServiceClpInvoker _clpInvoker = new BBBMeetingLocalServiceClpInvoker();
}