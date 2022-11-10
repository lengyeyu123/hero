package com.han.hero.framework.config;

import com.han.hero.common.enums.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 解决注解与数据库转换问题
 */
public class AutoGenericEnumTypeHandler<E extends BaseEnum> extends BaseTypeHandler<E> {

    private Class<E> enumType;

    private E[] enums;

    public AutoGenericEnumTypeHandler() {
    }

    public AutoGenericEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.enumType = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getName() + " does not represent an enum type.");
        }
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());

    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (rs.getObject(columnName) == null) {
            return null;
        }
        int code = rs.getInt(columnName);
        return loadEnum(code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs.getObject(columnIndex) == null) {
            return null;
        }
        int code = rs.getInt(columnIndex);
        return loadEnum(code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.getObject(columnIndex) == null) {
            return null;
        }
        int code = cs.getInt(columnIndex);
        return loadEnum(code);
    }

    private E loadEnum(int code) {
        for (E e : enums) {
            if (e.getCode() == code) {
                return e;
            }
        }
        throw new IllegalArgumentException(enumType.getName() + "  unknown enumerated type code:" + code);
    }
}
