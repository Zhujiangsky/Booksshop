package com.test.util;

import java.util.Map;

public interface RowMapper<T> {

    public T mapper(Map<String, Object> map);
}
