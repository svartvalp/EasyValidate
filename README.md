This is my simple implementation for validation objects)
To use just add annotations from javax.validation.constraints to class.
for Example:
```java
class SomeClass {
    @Size(min = 10, max = 20)
    private int value;
    // ....
}
```
Then create instance of ObjectValidator - FieldObjectAnnotationValidator
 ```java
SomeClass someClass = new SomeClass();
someClass.setValue(40);
ObjectValidator validator = new FieldObjectAnnotationValidator();
var result = validator.validate(someClass);
```