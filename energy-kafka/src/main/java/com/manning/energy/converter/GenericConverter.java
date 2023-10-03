package com.manning.energy.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @param <T1> object target
 * @param <T2> object source
 */
@Service
public class GenericConverter<T1,T2> {

	/**
	 *
	 * @param t1 object target
	 * @param t2 object source
	 * @return
	 */
	public T1 apply(T1 t1 , T2 t2) {
		BeanUtils.copyProperties(t2, t1);
		return t1;
	}

}
