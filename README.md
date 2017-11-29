![Travis](https://travis-ci.org/WellCosta/MathMax.svg?branch=master) [![codecov](https://codecov.io/gh/WellCosta/MathMax/branch/master/graph/badge.svg)](https://codecov.io/gh/WellCosta/MathMax)

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

The test execution entry point is [AllTests](https://github.com/WellCosta/MathMax/blob/master/src/test/java/com/github/mathmax/main/AllTests.java). 
This suite centralizes the full test execution. 
