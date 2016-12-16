package com.computing.cloud.validator;

import java.util.List;

import com.computing.cloud.exception.ApiException;
import com.computing.cloud.exception.BadRequestException;
import com.computing.cloud.exception.UnprocessableEntityException;

public abstract class AbstractValidator<T> {

    public RestrictionBuilder createRestrictionBuilder(String validationCategory) {
        return new RestrictionBuilder(validationCategory);
    }

    protected void validate(T validatable) throws ApiException {
        validate400(validatable);
        validate422(validatable);
    }

    private void validate400(T validatable) throws BadRequestException {
        List<ValidationError> validationErrorEntityList = apply400(validatable);
        if (validationErrorEntityList != null && !(validationErrorEntityList.isEmpty())) {
            throw new BadRequestException(validationErrorEntityList);
        }
    }

    private void validate422(T validatable) throws UnprocessableEntityException {
        List<ValidationError> validationErrorEntityList = apply422(validatable);
        if (validationErrorEntityList != null && !(validationErrorEntityList.isEmpty())) {
            throw new UnprocessableEntityException(validationErrorEntityList);
        }
    }

    public List<ValidationError> apply400(T validatable) { return null; }

    public List<ValidationError> apply422(T validatable) { return null; }
    
}