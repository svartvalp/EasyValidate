package com.svartvalp.EasyValidate.FieldValidation;

import com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors.MaxAnnotationProcessor;
import com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors.MinAnnotationProcessor;
import com.svartvalp.EasyValidate.FieldValidation.AnnotationProcessors.SizeAnnotationProcessor;
import com.svartvalp.EasyValidate.ValidationResult;
import com.svartvalp.EasyValidate.ObjectValidator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/* Class used for validation object by annotations from javax.validation.constraints.
 * You can specify, which fields to validate by using field names.
 */


public class FieldObjectAnnotationValidator implements ObjectValidator {

    FieldResolver fieldResolver = new SimpleFieldResolver();
    SizeAnnotationProcessor sizeAnnotationProcessor = new SizeAnnotationProcessor();
    MinAnnotationProcessor minAnnotationProcessor = new MinAnnotationProcessor();
    MaxAnnotationProcessor maxAnnotationProcessor = new MaxAnnotationProcessor();

    public FieldObjectAnnotationValidator() {
    }

    public void setFieldResolver(FieldResolver fieldResolver) {
        this.fieldResolver = fieldResolver;
    }

    public void setSizeAnnotationProcessor(SizeAnnotationProcessor sizeAnnotationProcessor) {
        this.sizeAnnotationProcessor = sizeAnnotationProcessor;
    }

    public void setMinAnnotationProcessor(MinAnnotationProcessor minAnnotationProcessor) {
        this.minAnnotationProcessor = minAnnotationProcessor;
    }

    public void setMaxAnnotationProcessor(MaxAnnotationProcessor maxAnnotationProcessor) {
        this.maxAnnotationProcessor = maxAnnotationProcessor;
    }

    /*
     * @param object - object to validate
     * @param fields - names of fields to validate, if empty func will validate all fields
     * @throws FieldNotSupportedTypeException
     */

    public ValidationResult validate(Object object, String... fields) {
        List<String> fieldNameList = List.of(fields);
        boolean isFieldNameListEmpty = fieldNameList.isEmpty();
        List<ValidationResult> results = new LinkedList<>();
        for(Field field : getObjectFields(object)) {
           if(isFieldNameListEmpty || fieldNameList.contains(field.getName())) {
               if(field.getAnnotation(Size.class) != null) {
                   results.add(sizeAnnotationProcessor.validate(field, object));
               }
               if(field.getAnnotation(Min.class) != null) {
                   results.add(minAnnotationProcessor.validate(field, object));
               }
               if(field.getAnnotation(Max.class) != null) {
                   results.add(maxAnnotationProcessor.validate(field, object));
               }
           }
        }
        return new FieldValidationResult(results.toArray(FieldValidationResult[]::new));
    }

    /*
     * find all fields in object
     * @param object
     */
    private Field[] getObjectFields(Object object){
        return fieldResolver.getAllFields(object);
    }
}
