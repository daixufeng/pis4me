package com.pis.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultList;
import com.pis.domain.EntityFactory;
import com.pis.domain.MyEntities;
import com.pis.domain.Page;
import com.pis.service.CategoryService;
import com.pis.service.DailyPayService;
import com.pis.service.DictionaryService;

public class DailyPayServiceImpl extends BaseService implements DailyPayService {

	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private DictionaryService dictionaryService;

	public DictionaryService dictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	@Override
	public Map<String, Object> getById(Long id) {
		return super.getById(id);
	}

	@Override
	public Map<String, Object> create(Entity dailyPay) {
		return super.create(dailyPay);
	}

	@Override
	public Map<String, Object> update(Entity dailyPay) {
		return super.update(dailyPay);
	}

	@Override
	public void delete(Entity dailyPay) {
		super.delete(dailyPay);
	}

	@Override
	public void delete(List<Entity> dailyPays) {
		super.delete(dailyPays);
	}

	@Override
	public List<Map<String, Object>> getAll() {
		return super.getAll();
	}

	@SuppressWarnings("deprecation")
	public List<Map<String, Object>> find(Map<String, Object> filterMap, Map<String, Object> likeMap, Map<String, Object> sortMap){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<Map<String, Object>> entites = new ArrayList<Map<String, Object>>();
		Query q = new Query(getEntityName());

		// set filter parameters;
		if (filterMap.get("CategoryId") != null)
			q.addFilter("CategoryId", Query.FilterOperator.EQUAL,
					filterMap.get("CategoryId"));
		if (filterMap.get("BegDate") != null) {
			try {
				q.addFilter("CreateDate",
						Query.FilterOperator.GREATER_THAN_OR_EQUAL,
						sdf.parse(filterMap.get("BegDate").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (filterMap.get("EndDate") != null) {
			try {
				q.addFilter("CreateDate",
						Query.FilterOperator.LESS_THAN_OR_EQUAL,
						sdf.parse(filterMap.get("EndDate").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// set sort fields
		q.addSort("CreateDate", SortDirection.DESCENDING);

		PreparedQuery pq = datastore.prepare(q);

		for (Entity o : pq.asIterable()) {
			Map<String, Object> item = EntityFactory.entityToMap(o);
			entites.add(item);
		}
		return entites;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Page getPageData(int pageNo, int pageSize,
			Map<String, Object> filterMap, Map<String, Object> likeMap,
			Map<String, Object> sortMap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		int start = (pageNo - 1) * pageSize;
		int count = 0;

		List<Map<String, Object>> entites = new ArrayList<Map<String, Object>>();
		Query q = new Query(getEntityName());

		// set filter parameters;
		if (filterMap.get("CategoryId") != null)
			q.addFilter("CategoryId", Query.FilterOperator.EQUAL,
					filterMap.get("CategoryId"));
		if (filterMap.get("BegDate") != null) {
			try {
				q.addFilter("CreateDate",
						Query.FilterOperator.GREATER_THAN_OR_EQUAL,
						sdf.parse(filterMap.get("BegDate").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (filterMap.get("EndDate") != null) {
			try {
				q.addFilter("CreateDate",
						Query.FilterOperator.LESS_THAN_OR_EQUAL,
						sdf.parse(filterMap.get("EndDate").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// set sort fields
		q.addSort("CreateDate", SortDirection.DESCENDING);

		count = getDataCount(q);

		PreparedQuery pq = datastore.prepare(q);
		// get total count

		// set page size
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(pageSize);
		fetchOptions.offset(start);

		QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);

		for (Entity o : results) {
			boolean fuzzyQuery = false;
			Set<String> keySet = likeMap.keySet();
			// do fuzzy query
			for (String key : keySet) {
				String val = likeMap.get(key).toString();
				if (o.getProperty(key).toString().contains(val)) {
					fuzzyQuery = true;
					break;
				}
			}
			// if don't do fuzzy query or fuzzy query is true, add this record
			if ((!fuzzyQuery && keySet.size() == 0) || fuzzyQuery) {
				Map<String, Object> item = EntityFactory.entityToMap(o);
				entites.add(item);
			}
		}
		return new Page(entites, count);

	}

	@SuppressWarnings("deprecation")
	public Map<String, Object> getDataByYear(int year) {
		Map<String, Object> data = new HashMap<String, Object>();
		double total = 0.0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 12; i++) {
			Query q = new Query(getEntityName());

			Calendar calendar = Calendar.getInstance();
			calendar.set(calendar.get(Calendar.YEAR), i, 1, 0, 0, 0);
			try {
				q.addFilter("CreateDate",
						Query.FilterOperator.GREATER_THAN_OR_EQUAL,
						sdf.parse(sdf.format(calendar.getTime())));
				// 这种多余的操作是为了解决直接用calendar.getTime() 做参数时无法查询到1号的数据。
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			calendar.set(calendar.get(Calendar.YEAR), i,
					calendar.getActualMaximum(Calendar.DATE), 23, 59, 59);
			q.addFilter("CreateDate", Query.FilterOperator.LESS_THAN_OR_EQUAL,
					calendar.getTime());

			PreparedQuery pq = datastore.prepare(q);
			Iterable<Entity> results = pq.asIterable();

			double qty = 0.0;
			for (Entity o : results) {
				qty += Double.parseDouble(o.getProperty("Qty").toString());
			}
			total += qty;
			data.put(String.valueOf(i), String.valueOf(qty));
		}
		data.put("total", String.valueOf(total));
		return data;
	}

	public List<Map<String, String>> getDataByMonth(int year, int month) {
		List<Map<String, String>> monthData = new ArrayList<Map<String, String>>();

		Map<String, Object> item = dictionaryService.getByTypeAndValue(
				"Category", "DailyPay");
		List<Map<String, Object>> categories = this.categoryService
				.getByType(Long.parseLong(item.get("id").toString()));

		Map<String, Object> filterMap = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1, 0, 0, 0);
		filterMap.put("BegDate", sdf.format(calendar.getTime()));
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.getActualMaximum(Calendar.DATE), 23, 59, 59);
		filterMap.put("EndDate", sdf.format(calendar.getTime()));

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("CategoryId", "CategoryId");

		Page monthDailyPay = this.getPageData(1, 1000, filterMap,
				new HashMap<String, Object>(), sortMap);
		List<Map<String, Object>> data = monthDailyPay.data;
		double total = 0.0;
		for (Map<String, Object> category : categories) {
			String categoryId = category.get("id").toString();
			double qty = 0;
			for (Map<String, Object> dailyPay : data) {
				if (dailyPay.get("categoryId").toString().equals(categoryId)) {
					qty += Double.parseDouble(dailyPay.get("qty").toString());
				}
			}
			if (qty > 0) {
				Map<String, String> record = new HashMap<String, String>();
				record.put("categoryId", categoryId);
				record.put("categoryName", category.get("name").toString());
				record.put("qty", String.valueOf(qty));
				total += qty;
				monthData.add(record);
			}
		}
		Map<String, String> totalRecord = new HashMap<String, String>();
		totalRecord.put("categoryName", "Total(￥)");
		totalRecord.put("qty", String.valueOf(total));
		monthData.add(totalRecord);
		return monthData;
	}

	public Map<String, Object> getDataByPerDay(int year, int month) {
		Map<String, Object> data = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		int days = calendar.getActualMaximum(Calendar.DATE);
		List<Integer> count = new ArrayList<Integer>();
		for (int i = 0; i < days; i++)
			count.add(i + 1);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1, 0, 0, 0);
		filterMap.put("BegDate", sdf.format(calendar.getTime()));
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.getActualMaximum(Calendar.DATE), 23, 59, 59);
		filterMap.put("EndDate", sdf.format(calendar.getTime()));

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("CreateDate", "CreateDate");
		Page monthDailyPay = this.getPageData(1, 1000, filterMap,
				new HashMap<String, Object>(), sortMap);
		List<Map<String, Object>> monthData = monthDailyPay.data;

		List<String> qtys = new ArrayList<String>();
		for (int i : count) {
			double qty = 0;
			for (Map<String, Object> o : monthData) {

				Date date = (Date) (o.get("createDate"));
				calendar.setTime(date);
				if (calendar.get(Calendar.DATE) == i) {
					qty += Double.parseDouble(o.get("qty").toString());
				}
			}
			DecimalFormat df = new DecimalFormat("0.00");
			qtys.add(df.format(qty));
		}

		data.put("days", count);
		data.put("qtys", qtys);

		return data;
	}

	@Override
	protected String getEntityName() {
		return MyEntities.DailyPay.class.getSimpleName();
	}
}
