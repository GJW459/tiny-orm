package com.wei.orm.type;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wei
 */
public enum JdbcType {

    INTEGER(Types.INTEGER),
    FLOAT(Types.FLOAT),
    DOUBLE(Types.DOUBLE),
    DECIMAL(Types.DECIMAL),
    VARCHAR(Types.VARCHAR),
    TIMESTAMP(Types.TIMESTAMP);

    private final int TYPE_CODE;

    private static final Map<Integer, JdbcType> codeLookUp = new HashMap<>();

    static {
        for (JdbcType type : JdbcType.values()) {
            codeLookUp.put(type.TYPE_CODE, type);
        }
    }

    JdbcType(int TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public static JdbcType forCode(int code) {
        return codeLookUp.get(code);
    }
}
