/*
 * @Copyright: 2016 www.yyfax.com Inc. All rights reserved.
 
package com.chatm.search.Ticket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.JSONPObject;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;

*//**
 * TODO(这里用一句话描述这个类或接口的作用)
 * 
 * @author jiangmy
 * @date 2016-08-08 15:23:31
 * @since TODO(说明当前修改版本号)
 *//*
public class JacksonUtil {
	private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
	// 转换全部字段
	public final static JsonMapper normalMapper = JsonMapper.buildNormalBinder();
	// 不转换默认值的字段
	public final static JsonMapper nodefaultMapper = JsonMapper.buildNonDefaultBinder();
	// 不转换空字段
	public final static JsonMapper noemptyMapper = JsonMapper.buildNonEmptyBinder();
	// 不转换null字段
	public final static JsonMapper nonullMapper = JsonMapper.buildNonNullBinder();
	// 首字母大写
	public final static JsonMapper downCaseMapper = JsonMapper.buildDownCaseBinder();

//	public static XmlMapper xmlMapper = new XmlMapper();

	public static <T> String toJson(T t) {
		return normalMapper.toJson(t);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return normalMapper.fromJson(json, clazz);
		} catch (Exception e) {
			// e.printStackTrace();
			try {
				return nodefaultMapper.mapper.convertValue(e, clazz);
			} catch (Exception e2) {
				e2.printStackTrace();
				return null;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json, TypeReference<T> t) {
		return (T) normalMapper.fromJson(json, t);
	}

	public static <T> List<T> fromJsonList(List<String> jsons, Class<T> t) {
		List<T> list = new ArrayList<>();
		for (String json : jsons) {
			list.add((T) normalMapper.fromJson(json, t));
		}
		return list;
	}

	*//**
	 * 构造函数
	 *//*
	private JacksonUtil() {
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String json = "[\"A\",\"B\"]";
		List<String> list = JacksonUtil.fromJson(json, ArrayList.class);
		System.out.println(list.get(0));
		list = JacksonUtil.fromJson(json, new TypeReference<ArrayList<String>>() {
		});
		System.out.println(list.get(0));
	}

	public static class JsonMapper {
		private ObjectMapper mapper;

		protected JsonMapper() {
			this(JsonInclude.Include.ALWAYS);
		}

		public JsonMapper(JsonInclude.Include include) {
			mapper = new ObjectMapper();
			// 设置输出时包含属性的风格
			mapper.setSerializationInclusion(include);
			// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			// 空的bean不解析
			mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			// 设置日期格式
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			//
		}

		*//** 创建输出全部属性到Json字符串的Binder. *//*
		public static JsonMapper buildNormalBinder() {
			return new JsonMapper(JsonInclude.Include.ALWAYS);
		}

		*//** 创建只输出非空属性到Json字符串的Binder. *//*
		public static JsonMapper buildNonNullBinder() {
			return new JsonMapper(JsonInclude.Include.NON_NULL);
		}

		*//** 创建只输出初始值被改变的属性到Json字符串的Binder. *//*
		public static JsonMapper buildNonDefaultBinder() {
			return new JsonMapper(JsonInclude.Include.NON_DEFAULT);
		}

		*//** 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Binder *//*
		public static JsonMapper buildNonEmptyBinder() {
			return new JsonMapper(JsonInclude.Include.NON_EMPTY);
		}

		*//** 首字母大写 *//*
		public static JsonMapper buildDownCaseBinder() {
			// 首字母默认小写
			JsonMapper mapper = new JsonMapper(JsonInclude.Include.ALWAYS);
			mapper.mapper.setPropertyNamingStrategy(new JacksonInitialDownCaseStrategy());
			return mapper;
		}

		*//**
		 * 反序列化POJO或简单Collection如List<String>. 如果JSON字符串为null或"null"字符串,返回null. 如果JSON字符串为"[]",返回空集合.
		 * 如需读取集合如List/Map,且不是List<String>这种简单类型时,使用後面的函數.
		 *//*
		public <T> T fromJson(String jsonString, Class<T> clazz) {
			if (StringUtils.isEmpty(jsonString)) {
				return null;
			}
			try {
				return mapper.readValue(jsonString, clazz);
			} catch (IOException e) {
				logger.warn("parse json string error:" + jsonString, e);
				return null;
			}
		}

		@SuppressWarnings("rawtypes")
		public Object fromJson(String jsonString, TypeReference type) {
			if (StringUtils.isEmpty(jsonString)) {
				return null;
			}
			try {
				return mapper.readValue(jsonString, type);
			} catch (IOException e) {
				logger.warn("parse json string error:" + jsonString, e);
				return null;
			}
		}

		*//**
		 * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调. 如果JSON字符串为null或"null"字符串,返回null. 如果JSON字符串为"[]",返回空集合.
		 *//*
		@SuppressWarnings("unchecked")
		public <T> T fromJson(String jsonString, JavaType javaType) {
			if (StringUtils.isEmpty(jsonString)) {
				return null;
			}
			try {
				return (T) mapper.readValue(jsonString, javaType);
			} catch (IOException e) {
				logger.warn("parse json string error:" + jsonString, e);
				return null;
			}
		}

		*//** 構造泛型的Type如List<MyBean>, Map<String,MyBean> *//*
		public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
			return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
		}

		*//**
		 * Object可以是POJO,也可以是Collection或数组. 如果对象为null,返回"null". 如果集合为空集合,返回"[]".
		 *//*
		public String toJson(Object object) {
			try {
				if (object instanceof String) {
					return (String) object;
				}
				return mapper.writeValueAsString(object);
			} catch (IOException e) {
				logger.warn("write to json string error:" + object, e);
				return null;
			}
		}

		*//** 當JSON裡只含有Bean的部分屬性時,更新一個已存在Bean,只覆蓋該部分的屬性. *//*
		@SuppressWarnings("unchecked")
		public <T> T update(String jsonString, T object) {
			try {
				return (T) mapper.readerForUpdating(object).readValue(jsonString);
			} catch (JsonProcessingException e) {
				logger.warn("parse json string" + jsonString + " to object:" + object + " error.", e);
			} catch (IOException e) {
				logger.warn("parse json string" + jsonString + " to object:" + object + " error.", e);
			}
			return null;
		}

		*//** 輸出JSONP格式數據. *//*
		public String toJsonP(String functionName, Object object) {
			return toJson(new JSONPObject(functionName, object));
		}

		*//** 设定是否使用Enum的toString函數來读写Enum, *//*
		public void enableEnumUseToString() {
			mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
			mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		}
	}

	*//**
	 * json string convert to map
	 *//*
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> json2Map(String jsonStr) {
		try {
			return JacksonUtil.normalMapper.mapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			logger.warn(jsonStr + " not a JSON string!! Exception:" + e.getMessage());
		}
		return null;
	}

	*//**
	 * json string convert to map with javaBean
	 *//*
	public static <T> Map<String, T> json2Map(String jsonStr, Class<T> clazz) {
		Map<String, T> map = null;
		try {
			map = JacksonUtil.normalMapper.mapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
			});
		} catch (Exception e) {
			logger.warn(jsonStr + " not a JSON string!! Exception:" + e.getMessage());
		}
		return map;
	}

	*//**
	 * json array string convert to list with javaBean
	 *//*
	public static <T> List<T> json2List(String jsonArrayStr, Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		try {
			List<Map<String, Object>> list = JacksonUtil.normalMapper.mapper.readValue(jsonArrayStr, new TypeReference<ArrayList<T>>() {
			});
			for (Map<String, Object> map : list) {
				result.add(JacksonUtil.map2pojo(map, clazz));
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	*//**
	 * map convert to javaBean
	 *//*
	public static <T> T map2pojo(Map<?, ?> map, Class<T> clazz) {
		return JacksonUtil.normalMapper.mapper.convertValue(map, clazz);
	}


	@SuppressWarnings("serial")
	static class JacksonInitialDownCaseStrategy extends PropertyNamingStrategy {
		@Override
		public String nameForField(@SuppressWarnings("rawtypes") MapperConfig config, AnnotatedField field, String defaultName) {
			return convert(defaultName);
		}

		@Override
		public String nameForGetterMethod(@SuppressWarnings("rawtypes") MapperConfig config, AnnotatedMethod method, String defaultName) {
			return convert(defaultName);
		}

		@Override
		public String nameForSetterMethod(@SuppressWarnings("rawtypes") MapperConfig config, AnnotatedMethod method, String defaultName) {
			String a = convert(defaultName);
			return a;
		}

		public String convert(String defaultName) {
			char[] arr = defaultName.toCharArray();
			if (arr.length != 0) {
				if (Character.isLowerCase(arr[0])) {
					char upper = Character.toUpperCase(arr[0]);
					arr[0] = upper;
				}
			}
			return new StringBuilder().append(arr).toString();
		}
	}

}
*/