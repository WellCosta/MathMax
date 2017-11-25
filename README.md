# MathMax
Arithmetic expression evaluator for Java

### Usage

```java
// Instantiate an Evaluator
final Evaluator evaluator = Evaluator.create();
// Pass String with expression to be interpreted
final double meaningOfLife = evaluator.evaluate( "37 + 5" );
// Yields 42.0
System.out.println( meaningOfLife );
```

### Running Tests

The test execution entry point is `AllTests`. 
This suite centralizes the full test execution. 
