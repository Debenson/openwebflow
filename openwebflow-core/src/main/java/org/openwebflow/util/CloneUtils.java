package org.openwebflow.util;

import java.lang.reflect.Field;

import org.apache.commons.lang.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 实现对象的克隆功能
 * 
 * @author bluejoe2008@gmail.com
 *
 */
public abstract class CloneUtils {
  private static final Logger logger = LoggerFactory.getLogger(CloneUtils.class);
  
  public static void copyFields(Object source, Object target, String... fieldNames) {
    Assert.notNull(source);
    Assert.notNull(target);
    Assert.isAssignable(source.getClass(), target.getClass());

    for (String fieldName : fieldNames) {
      try {
        Field field = FieldUtils.getField(source.getClass(), fieldName, true);
        field.setAccessible(true);
        field.set(target, field.get(source));
      } catch (Exception e) {
        logger.warn(e.getMessage());
      }
    }
  }
}
