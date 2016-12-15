package com.computing.cloud.utils;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;

public abstract class DomainCopyProperties  {

	protected static void copyProperties(Object source, Object target, String[] allowedPropertiesNames) {
		if( source == null || target == null ) {
			return;
		}
		
		BeanUtils.copyProperties(source, target, ignoredProperties(source.getClass(), Arrays.asList(allowedPropertiesNames)));
	}

	private static String[] ignoredProperties(Class<? extends Object> sourceClass, List<String> allowedPropertiesNames) {
		BeanWrapperImpl src = new BeanWrapperImpl(sourceClass);
		
		Set<String> ignoredPropertiesName = new HashSet<String>();

		for( PropertyDescriptor descriptor : src.getPropertyDescriptors() ) {
			boolean isWritable = src.isWritableProperty( descriptor.getName() );
			
			if( isWritable ) {
				if( ! allowedPropertiesNames.contains(descriptor.getName()) ) {
					ignoredPropertiesName.add( descriptor.getName() );
				}
			}
		}
		
		return ignoredPropertiesName.toArray( new String[ignoredPropertiesName.size()] );
	}
	
}