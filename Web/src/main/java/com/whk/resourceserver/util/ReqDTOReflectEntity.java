package com.whk.resourceserver.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 利用反射机制，映射到Entity中
 * 避免写get&set方法
 *
 */
public class ReqDTOReflectEntity {
    public static <T,E> void mapping(T requestDTO,E entity,String[] ignoreFields)
            throws IllegalAccessException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        Field[] fields=requestDTO.getClass().getDeclaredFields();
        for(Field field:fields){
            String fieldName=field.getName();
            if(!isIgnoreField(ignoreFields,fieldName)){
                String getMethodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                String setMethodName="set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);

                entity.getClass().getMethod(setMethodName,
                        field.getType()).invoke(entity,requestDTO.getClass().getMethod(getMethodName).invoke(requestDTO));
            }
        }
    }

    private static boolean isIgnoreField(String[] ignoreFields, String fieldName) {
        for (String ignoreName:ignoreFields) {
            if(ignoreName.equalsIgnoreCase(fieldName))
                return true;
        }
        return false;
    }
}
