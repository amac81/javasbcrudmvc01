package pt.bitclinic.javasbcrudmvc01.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/* implements ConstraintValidator<CourseCode, String>
 * 
 * This class implements the ConstraintValidator interface.
 *  
 * The ConstraintValidator is a interface provided by jakarta.validation.ConstraintValidator; 
 * It is used to create custom validation constraints for specific data types.
    CourseCode: This is a custom annotation that specifies a validation constraint. 
    Annotations are used to provide metadata about classes, methods, or fields, and in this context; 
    CourseCode indicates that this validator is associated with validating course codes.
    
    String: This indicates that the type of data this validator will validate is a String. 
    
    In other words, this validator is meant to validate course codes represented as strings.
 * 
 * */

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

	private String coursePrefix;

	@Override
	public void initialize(CourseCode theCourseCode) {
		coursePrefix = theCourseCode.value();
	}

	@Override
	// theCode: HTML form data entered by the user
	public boolean isValid(String theCode, ConstraintValidatorContext context) {
		boolean result;

		if (theCode != null) {
			result = theCode.startsWith(coursePrefix);
		} else {
			result = true; // courseCode isn't a required field
		}

		return result;
	}

}
