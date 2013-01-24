package com.pis.util;
import java.util.Collections;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheManager;
public class CacheUtil {
	
	public static void put(String key, Object value){
		Cache cache;

        try {
            cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());

            // Put the value into the cache.
            cache.put(key, value);
        } catch (CacheException e) {
            // ...
        }
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(String key){
		Cache cache;
		T value = null;
        try {
            cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());

            // Get the value from the cache.
            value = (T) cache.get(key);
        } catch (CacheException e) {
            // ...
        }
        return value;
	}
}
