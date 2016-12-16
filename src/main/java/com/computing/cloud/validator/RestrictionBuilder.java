package com.computing.cloud.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;

public class RestrictionBuilder {

    private String resourceMessageRoot;

    private List<ValidationError> validationErrors = new ArrayList<ValidationError>();

    public RestrictionBuilder(String resourceMessageRoot) {
        this.resourceMessageRoot = resourceMessageRoot;
    }

    public RestrictionBuilder required(String attributeName, Object attributeValue) {
        addRestriction(isNotNull(attributeValue), attributeName, RestrictionType.REQUIRED, getResourceKey(attributeName, "required"));
        return this;
    }

    public RestrictionBuilder notBlank(String attributeName, String attributeValue) {
        addRestriction(StringUtils.isNotBlank(attributeValue), attributeName, RestrictionType.REQUIRED, getResourceKey(attributeName, "required"));
        return this;
    }

    public RestrictionBuilder notBlankOrIsNull(String attributeName, String attributeValue) {
        boolean restriction = attributeValue == null || StringUtils.isNotBlank(attributeValue);
        addRestriction(restriction, attributeName, RestrictionType.REQUIRED, getResourceKey(attributeName, "required"));
        return this;
    }
    
    public RestrictionBuilder numeric(String attributeName, String attributeValue) {
        if (attributeValue != null) {
            boolean numericRestriction = StringUtils.isNotBlank(attributeValue) && ! StringUtils.isNumeric(attributeValue);
            addRestriction(numericRestriction, attributeName, RestrictionType.NUMERIC, getResourceKey(attributeName, "numeric"));
        }
        return this;
    }

    public RestrictionBuilder lengthBetween(String attributeName, String value, int min, int max) {
        if (value != null) {
            boolean validLength = StringUtils.isNotBlank(value) && value.length() >= min && value.length() <= max;
            addRestriction(validLength, attributeName, RestrictionType.EXACT_LENGTH, getResourceKey(attributeName, "exactLength"));
        }
        return this;
    }
    
    public RestrictionBuilder greaterThanZero(String attributeName, Integer value) {
    	if (value != null) {
    		addRestriction(value > 0, attributeName, RestrictionType.GREATER_THAN, getResourceKey(attributeName, "greaterThan"));
    	}
    	return this;
    }

    public RestrictionBuilder addRestriction(boolean restriction, String attributeName, RestrictionType type, String resourceKey) {
        if (!restriction) {
            validationErrors.add(new ValidationError(attributeName, type, resourceKey));
        }
        return this;
    }
    
    public List<ValidationError> errorsList() {
        return validationErrors;
    }

    public AttributeRestrictionBuilder on(String attributeName, Object value) {
        return new AttributeRestrictionBuilder(attributeName, value, this);
    }

    private String getResourceKey(String attributeName, String restriction) {
        Map<String, String> values = new HashMap<String, String>();
        values.put("root", resourceMessageRoot);
        values.put("atributeName", attributeName);
        values.put("restriction", restriction);
        return new StrSubstitutor(values).replace("${root}.error.${atributeName}.${restriction}");
    }

    private boolean isNotNull(Object object) {
        return object != null;
    }

    public class AttributeRestrictionBuilder {

        private String attributeName;
        private Object attributeValue;
        private RestrictionBuilder restrictionBuilder;

        public AttributeRestrictionBuilder(String attributeName, Object value, RestrictionBuilder superRestrictionBuilder) {
            this.attributeName = attributeName;
            this.attributeValue = value;
            this.restrictionBuilder = superRestrictionBuilder;
        }

        public AttributeRestrictionBuilder required() {
            if (!existsErrorFor(attributeName)) {
                restrictionBuilder.required(attributeName, attributeValue);
            }
            return this;
        }

        public AttributeRestrictionBuilder notBlank() {
            if (!existsErrorFor(attributeName)) {
                restrictionBuilder.notBlank(attributeName, (String) attributeValue);
            }
            return this;
        }

        public AttributeRestrictionBuilder addRestriction(boolean restriction, RestrictionType type, String resourceKey) {
        	if( !existsErrorFor(attributeName) && !restriction ) {
        		validationErrors.add(new ValidationError(attributeName, type, resourceKey));
        	}
        	
            return this;
        }

        public AttributeRestrictionBuilder lengthBetween(int min, int max) {
            if (!existsErrorFor(attributeName)) {
                restrictionBuilder.lengthBetween(attributeName, (String) attributeValue, min, max);
            }
            return this;
        }

        public RestrictionBuilder apply() {
            return this.restrictionBuilder;
        }

        public boolean existsErrorFor(String attributeName) {
            if (StringUtils.isNotBlank(attributeName)) {
                for (ValidationError validationError : restrictionBuilder.validationErrors) {
                    if (attributeName.equals(validationError.getField())) {
                        return true;
                    }
                }
            }
            return false;
        }

		public AttributeRestrictionBuilder notNumeric() {
			if (!existsErrorFor(attributeName)) {
                restrictionBuilder.numeric(attributeName, (String) attributeValue);
            }
			return this;
		}
		
		public AttributeRestrictionBuilder greaterThanZero() {
	    	if (attributeValue != null) {
	    		restrictionBuilder.greaterThanZero(attributeName, (Integer) attributeValue);
	    	}
	    	return this;
	    }

    }

}